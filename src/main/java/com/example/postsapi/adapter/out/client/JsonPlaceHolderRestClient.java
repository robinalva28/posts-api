package com.example.postsapi.adapter.out.client;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class JsonPlaceHolderRestClient {

    final Logger log = Logger.getLogger(JsonPlaceHolderRestClient.class);

    @Autowired
    String getJsonPlaceHolderUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public JsonPlaceHolderRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PostEntitiesResponse> getPostEntities() {
        String uri = getJsonPlaceHolderUrl + "/posts";

        log.info("RestClient: Executing getPostEntities...");
        ResponseEntity<List<PostEntitiesResponse>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        return (response.getStatusCode().is2xxSuccessful()) ? response.getBody() : List.of();
    }

}
