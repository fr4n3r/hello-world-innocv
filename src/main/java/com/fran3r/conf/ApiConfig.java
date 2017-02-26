package com.fran3r.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fran3r.boundary.SerializationModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * @author Fran Alonso @ byteflair.com
 */
@Configuration
@EnableAsync
public class ApiConfig extends RepositoryRestConfigurerAdapter {
    /**
     * Configurar el sistema de mapeo de objetos a JSON y viceversa.
     *
     * @param objectMapper
     */
    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new SerializationModule());
        super.configureJacksonObjectMapper(objectMapper);
    }

    @Bean
    @Order(0)
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(
            Arrays.asList("Origin", "Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method",
                          "Access-Control-Request-Headers", "Authorization"));
        corsConfiguration
            .setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
        corsConfiguration.addAllowedOrigin("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }

    @Bean(name = "messageSource")
    public ResourceBundleMessageSource configureResourceBundleMessageSource() {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setDefaultEncoding("UTF-8");
        resource.setBasename("error-messages");
        return resource;
    }
}
