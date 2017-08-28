package com.linkedin.property;


import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;

@Resource.Classpath("linkedin.properties")
public interface LinkedInProperty {

    @Property("url")
    String getUrl();

    @Property("username")
    String getUsername();

    @Property("password")
    String getPassword();
}
