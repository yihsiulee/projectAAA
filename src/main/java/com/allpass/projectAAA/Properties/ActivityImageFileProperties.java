package com.allpass.projectAAA.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties()
public class ActivityImageFileProperties {


    private String location = "src/main/resources/activityImage";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
