Dropwizard Soy Integration
==============

Intro
--------------
This project is aimed to provide support for rendering [soy](https://developers.google.com/closure/templates) templates from [dropwizard](http://dropwizard.codahale.com/)

How to Use
--------------
* Build and install the parent module
* Include the following snippet in the pom of your dropwizard project:
    ```
        <dependency>
            <groupId>com.github.dwsoy</groupId>
            <artifactId>core</artifactId>
            <version>${parent.version}</version>
        </dependency>
    ```
* Add the [SoyViewBundle](https://github.com/zero1zero/dropwizard-soy/blob/master/core/src/main/java/com/github/dwsoy/view/SoyViewBundle.java) to your bootstrap
* Add soy files under "soy" in your classpath (this is configurable)
* Return SoyView objects from your resources


TODO
--------------
Javascript rendering support is not yet integrated.

The best way to go about this might often depend on the end implementation of DW but I am open to suggestions
