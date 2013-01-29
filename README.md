Dropwizard Soy Integration
==============

Intro
--------------
This project is aimed to provide support for rendering [soy](https://developers.google.com/closure/templates) templates
from within a [dropwizard](http://dropwizard.codahale.com/) project.

This project does not depend on the Dropwizard view libraries as they are explicity tied to freemarker and mustache.

The Dropwizard view module code was not in a place where different rendering implementations could be added cleanly, thus,
there is a nominal amount of duplicated code here ([a discussion on the topic](https://groups.google.com/forum/?fromgroups=#!searchin/dropwizard-user/soy/dropwizard-user/WXN-Pc9FUps/8tJ_n_-6uuQJ).

Features
--------------
* Realtime template compilation on change when developing locally (ie, when not running from shaded jar)

How to Use
--------------
1. Build and install the parent module
2. Include the core module as a maven dependency

    ```xml
    <dependency>
      <groupId>com.github.dwsoy</groupId>
      <artifactId>core</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>
    ```

3. Instantiate and add a [SoyViewBundle](https://github.com/zero1zero/dropwizard-soy/blob/master/core/src/main/java/com/github/dwsoy/view/SoyViewBundle.java)
to your bootstrap
4. Add soy files under "soy" in your classpath (this is configurable).  Preferred location would be under src/main/resources/soy.
5. Return SoyView objects from your resources to render templates.


TODO
--------------
Javascript rendering support is not yet integrated.

The best way to go about this might often depend on the end implementation of DW but I am open to suggestions
