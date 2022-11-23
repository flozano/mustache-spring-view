[Mustache.js](http://mustache.github.com/mustache.5.html) View for [Spring Web MVC 6.0](https://docs.spring.io/spring-framework/docs/6.0.x/reference/html/web.html#mvc)
============================================================================

This is a minimal fork from https://github.com/sps/mustache-spring-view to make it work with Spring 6.0.

Supports both [jmustache](https://github.com/samskivert/jmustache) and [mustache.java](https://github.com/spullara/mustache.java)


Maven Dependency
-----------------

    <dependency>
        <groupId>io.github.flozano.mustache</groupId>
        <artifactId>mustache-spring-view</artifactId>
        <version>2.0.0</version>
    </dependency>

    <!-- jmustache -->
    <dependency>
        <groupId>com.samskivert</groupId>
        <artifactId>jmustache</artifactId>
        <version>${jmustache.version}</version>
    </dependency>

    <!-- mustache.java -->
    <dependency>
	<groupId>com.github.spullara.mustache.java</groupId>
        <artifactId>compiler</artifactId>
        <version>${mustache.java.version}</version>
    </dependency>



Spring Configuration
-------------
    <!-- jmustache -->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.mustache.MustacheViewResolver">
        <property name="suffix" value=""/>
        <property name="cache" value="${TEMPLATE_CACHE_ENABLED}" />
        <property name="templateFactory">
            <bean class="org.springframework.web.servlet.view.mustache.jmustache.JMustacheTemplateFactory">
                <property name="escapeHTML" value="true"/>
                <property name="standardsMode" value="false"/>
                <property name="templateLoader">
                    <bean class="org.springframework.web.servlet.view.mustache.jmustache.JMustacheTemplateLoader"/>                                
                </property>
            </bean>
        </property>
    </bean>

	<!-- mustache.java -->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.mustache.MustacheViewResolver">
        <property name="suffix" value=""/>
        <property name="cache" value="${TEMPLATE_CACHE_ENABLED}"/>
        <property name="templateFactory">
            <bean class="org.springframework.web.servlet.view.mustache.java.MustacheJTemplateFactory" />
        </property>
    </bean>

    

Localization Support
---------------
	<bean id="messageSource" .../>
    
    <!-- using mustache.java -->
    <bean id="i18nMessageInterceptor"
          class="org.springframework.web.servlet.view.mustache.java.LocalizationMessageInterceptor">
        <property name="localeResolver" ref="..." />
    </bean>

	<!-- using jmustache -->
	<bean id="i18nMessageInterceptor" class="org.springframework.web.servlet.view.mustache.jmustache.LocalizationMessageInterceptor">
        <property name="localeResolver" ref="..." />
    </bean>
    

Thanks
---------------
Thanks to [Sean Scanlon](https://github.com/sps) for writing this in the first place!
Thanks to [Eric White](https://github.com/ericdwhite) for [forking](https://github.com/ericdwhite/mustache.java-spring-webmvc/) this code base and providing the mustache.java implementation.

