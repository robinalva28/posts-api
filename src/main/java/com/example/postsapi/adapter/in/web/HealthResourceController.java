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
import com.example.postsapi.adapter.out.client.JsonPlaceHolderHealthIndicator;

@Tag(name = "Health")
@RestController
@PropertySource("classpath:application.properties")
public class HealthResourceController {

    private static final Logger log = Logger.getLogger(HealthResourceController.class);

    String getAppName;

    JsonPlaceHolderHealthIndicator jsonPlaceHolderHealthIndicator;

    @Autowired
    public HealthResourceController(String getAppName, JsonPlaceHolderHealthIndicator jsonPlaceHolderHealthIndicator) {
        this.getAppName = getAppName;
        this.jsonPlaceHolderHealthIndicator = jsonPlaceHolderHealthIndicator;
    }

    @RequestMapping(value = "/checkhealth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkHealth() {

        log.info("calling endpoint --> GET: /checkhealth");
        try{
        return new JSONObject()
                .put("appName", getAppName)
                .put("estado", "ok")
                .put("dependencia = jsonPlaceHolder estado", this.jsonPlaceHolderHealthIndicator.health())
                .toString();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

}
