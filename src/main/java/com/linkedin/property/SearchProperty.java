package com.linkedin.property;


import ru.qatools.properties.Property;
import ru.qatools.properties.Resource;

@Resource.Classpath("search.properties")
public interface SearchProperty {

    @Property("search.request")
    String getSearchRequest();

    @Property("count.profile")
    Integer getCountProfile();
}
