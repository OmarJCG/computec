package com.computec.computec;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Mapea las peticiones /images/** al directorio /img en el sistema de archivos
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:images/");
    }
}
