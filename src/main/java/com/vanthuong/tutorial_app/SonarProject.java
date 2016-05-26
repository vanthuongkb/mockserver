package com.vanthuong.tutorial_app;

import java.util.HashMap;
import java.util.Map;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

public class SonarProject {
    public String getSomething(String relativeURL) throws UnirestException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("desc", false);
        HttpRequest request = Unirest
                .get("http://localhost:9000/"+relativeURL)
                .queryString(params)
                .basicAuth("abc", "xyz")
                .getHttpRequest();
        HttpResponse<String> response = request.asString();
        return response.getBody();
    }
}
