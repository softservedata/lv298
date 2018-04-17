package com.softserve.edu.opencart.annotations;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface JiraTicket {
    String type() default "";
    String name() default "LVSET-";
    String url() default "http://ssu-jira.softserveinc.com/browse/";
}
