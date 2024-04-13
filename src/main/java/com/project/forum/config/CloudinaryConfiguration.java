package com.project.forum.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {

    @Value("${cloudinary.cloud_name}")
    private static final String cloudName = "dqvshjkgn";

    @Value("${cloudinary.api_key}")
    private static final String apiKey = "725981873859846";

    @Value("${cloudinary.api_secret}")
    private static final String apiSecret = "M5f5sNMu2Jg0qHHG0-5eM3nWyhs";

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }
}
