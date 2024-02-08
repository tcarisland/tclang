package com.tcarisland.tclang.components;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class TranslationService {

    public String translateText(String message, Locale from, Locale to, String uri) {
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("q", message);
        requestBody.put("source", StringUtils.lowerCase(from.getLanguage()));
        requestBody.put("target", StringUtils.lowerCase(to.getLanguage()));
        requestBody.put("format", "text");

        HttpResponse<JsonNode> response = Unirest.post(uri)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .asJson();
        return response.getBody().getObject().getString("translatedText");
    }

}
