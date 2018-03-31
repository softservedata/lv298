@echo.off

set path=.\.allure\allure-2.0.1\bin\; %path%;
allure serve targer\allure-results


echo Press any key to continue...
pause