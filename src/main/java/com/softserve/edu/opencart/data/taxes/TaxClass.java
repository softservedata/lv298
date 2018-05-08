package com.softserve.edu.opencart.data.taxes;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.GeoZones;
import com.softserve.edu.opencart.tools.RegexUtils;

import java.util.*;

public class TaxClass {

    class TaxClassParameter {

        private ITaxRate taxRate;
        private BasedOn basedOn;
        private int priority;

        TaxClassParameter(ITaxRate taxRate, BasedOn basedOn, int priority){
            this.taxRate = taxRate;
            this.basedOn = basedOn;
            this.priority = priority;
        }

        TaxClassParameter(String taxRate, String basedOn, String priority){
            this.taxRate = TaxRateRepository.get().byName(taxRate);
            this.basedOn = basedOnbyName(basedOn);
            this.priority = Integer.parseInt(priority);
        }

        @Override
        public String toString() {
            return String.format("Tax rate: %s, Based on: %s, Priority %d",
                    getTaxRate(),getBasedOn().toString(),getPriority());
        }

        public ITaxRate getTaxRate() {
            return taxRate;
        }

        public BasedOn getBasedOn() {
            return basedOn;
        }

        public int getPriority() {
            return priority;
        }
    }

    private static final String TITLE_HEADER = "title";
    private static final String DESCRIPTION_HEADER = "description";
    private static final String BASED_ON_HEADER = "based";
    private static final String PRIORITY_HEADER = "priority";
    private static final String TAX_RATE_NAME_HEADER = "taxName";

    public enum BasedOn{
        PAYMENT_ADDRESS("Payment"),
        STORE_ADDRESS("Store"),
        SHIPPING_ADDRESS("Shipping");

        String name;

        BasedOn (String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public BasedOn basedOnbyName(String basedOn){
        BasedOn result = null;
        for (BasedOn currentBased : BasedOn.values()){
            if (currentBased.getName().equalsIgnoreCase(basedOn)){
                result = currentBased;
            }
        }
        return result;
    }

    private String title;
    private String description;
    private List<TaxClassParameter> parametres;

    public TaxClass (String title, String description) {
        this.title = title;
        this.description = description;
        parametres = new ArrayList<>();
    }

    public void addParameter(TaxClassParameter parameter){
        getParameters().add(parameter);
    }

    public void addParameter(ITaxRate taxRate, BasedOn basedOn, int priority){
        getParameters().add(new TaxClassParameter(taxRate,basedOn,priority));
    }

    public void addParameter(String taxRate, String basedOn, String priority){
        getParameters().add(new TaxClassParameter(taxRate,basedOn,priority));
    }

    public void removeParameter(int index){
        getParameters().remove(index);
    }

    public String showParameters(){
        String result = "";
        int counter = 0;
        Iterator iter = parametres.iterator();
        while (iter.hasNext()){
            counter++;
            result = result + String.format("PARAMETER %d: %s%n",counter,iter.next().toString());
        }
        return result;
    }

    public double taxedPrice(double price){
        double result = price;
        List<Double> taxes = new ArrayList<>();
        for (TaxClassParameter currentParam : getParameters()){
            if ((currentParam.taxRate.getType().equals(TaxRate.Type.FIXED)) && !(currentParam.getBasedOn().equals(BasedOn.PAYMENT_ADDRESS)) ){
                taxes.add(currentParam.taxRate.getRate());
            } else if ((currentParam.taxRate.getType().equals(TaxRate.Type.PERCENTAGE)) && !(currentParam.getBasedOn().equals(BasedOn.PAYMENT_ADDRESS)) ) {
                taxes.add(price*(currentParam.taxRate.getRate()/100));
            }
        }
        for (double tax : taxes){
            result = result + tax;
        }
        return result;
    }

    public static List<TaxClass> getByList(List<List<String>> rows) {
        List<TaxClass> result = new ArrayList<>();
        Map<String, Integer> headers = new HashMap<>();
        result.add(TaxClassRepository.get().defaultTaxClass());
        List<String> taxClassTitles = new ArrayList<>();

        for (List<String> currentRow : rows) {
            for (TaxClass currentTaxClass : result) {
                if (!taxClassTitles.contains(currentTaxClass.getTitle())){
                    taxClassTitles.add(currentTaxClass.getTitle());
                }
            }
            System.out.println("taxClassTitles = "+taxClassTitles);

            if ( !RegexUtils.isWords(currentRow.toString()) ) {

                    if (!taxClassTitles.contains(currentRow.get(headers.get(TITLE_HEADER)))){
                        //build Tax Class
                        result.add(
                                new TaxClass(
                                        currentRow.get(headers.get(TITLE_HEADER)),
                                        currentRow.get(headers.get(DESCRIPTION_HEADER))
                                )
                        );
                        System.out.println("result1 = "+ result.toString());
                    }
            } else {
                //fill columns header map
                for (int i = 0; i < currentRow.size(); i++) {
                    headers.put(currentRow.get(i), i);
                }
                System.out.println("headers= "+ headers);
            }

            for (TaxClass currentTaxClass : result){
                if (currentTaxClass.getTitle().equalsIgnoreCase(currentRow.get(headers.get(TITLE_HEADER)))){
                    currentTaxClass.addParameter(
                        currentRow.get(headers.get(TAX_RATE_NAME_HEADER)),
                        currentRow.get(headers.get(BASED_ON_HEADER)),
                        currentRow.get(headers.get(PRIORITY_HEADER))
                    );
                }
            }
        }
        //TODO delete

//        System.out.println("prices = "+ prices);
        System.out.println("result2 = "+ result.toString());
        return result;
    }

    // *********Setters*********
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // *********Getters*********
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<TaxClassParameter>  getParameters(){
        return parametres;
    }


    @Override
    public String toString() {
        return String.format("%s%nDescription: %s%nParameters: %s",title,description,showParameters());
    }



    public static void main(String[] args) {
        TaxClass taxeble = new TaxClass("Downloadable Products","Downloadable");
        taxeble.addParameter(TaxRateRepository.get().ecoTax(),BasedOn.STORE_ADDRESS,2);
        taxeble.addParameter(TaxRateRepository.get().vat(),BasedOn.SHIPPING_ADDRESS,2);
        double price = 2000;
        TaxClass tax1 = TaxClassRepository.get().taxebleGoods();
        TaxClass tax2 = TaxClassRepository.get().downloadableProducts();
        TaxClass tax3 = TaxClassRepository.get().noTax();

        System.out.println("taxebleGoods Euro "+price+" = "+ Currencies.EURO.evaluatePrice(price,tax1));
        System.out.println("taxebleGoods dolar "+price+" = "+ Currencies.US_DOLLAR.evaluatePrice(price,tax1));
        System.out.println("taxebleGoods pound "+price+" = "+ Currencies.POUND_STERLING.evaluatePrice(price,tax1));

        System.out.println("downloadableProducts Euro "+price+" = "+ Currencies.EURO.evaluatePrice(price,tax2));
        System.out.println("downloadableProducts dolar "+price+" = "+ Currencies.US_DOLLAR.evaluatePrice(price,tax2));
        System.out.println("downloadableProducts pound "+price+" = "+ Currencies.POUND_STERLING.evaluatePrice(price,tax2));

        System.out.println("noTax Euro "+price+" = "+ Currencies.EURO.evaluatePrice(price,tax3));
        System.out.println("noTax dolar "+price+" = "+ Currencies.US_DOLLAR.evaluatePrice(price,tax3));
        System.out.println("noTax pound "+price+" = "+ Currencies.POUND_STERLING.evaluatePrice(price,tax3));


    }
}
