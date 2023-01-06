package com.example.postsapi.adapter.in.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health")
@RestController
@PropertySource("classpath:application.properties")
public class HealthResource {

    private static final Logger log = Logger.getLogger(HealthResource.class);
    @Autowired
    String getAppName;

    @RequestMapping(value = "/checkhealth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkHealth() {

        log.info("called endpoint --> GET: /checkhealth");
        try{
        return new JSONObject()
                .put("appName", getAppName)
                .put("estado", "ok")
                .toString();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

}
