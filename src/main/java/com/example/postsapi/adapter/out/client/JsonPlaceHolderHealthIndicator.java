package com.example.postsapi.adapter.out.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JsonPlaceHolderHealthIndicator implements HealthIndicator {

    private final RestTemplate restTemplate;
    @Autowired
    String getJsonPlaceHolderUrl;

    @Autowired
    public JsonPlaceHolderHealthIndicator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Health health() {
        try {
            //Acá iría un endpoint de test, pero como no puedo ponerlo, pues... me traigo un post
            restTemplate.getForObject(getJsonPlaceHolderUrl + "/posts/1", String.class);
            return Health.up().build();
        } catch (Exception e) {
            return Health.down().withException(e).build();
        }
    }
}