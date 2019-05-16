package com.allpass.projectAAA.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration()
public class ArticleUploadFileProperties {
    private String location = "src/main/resources/articleUpload";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
