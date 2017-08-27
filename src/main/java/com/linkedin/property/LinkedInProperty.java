package com.linkedin.property;


import org.aeonbits.owner.Config;

@Config.Sources({"classpath:linkedin.properties"})
public interface LinkedInProperty extends Config {
    String url();

    String username();

    String password();
}
