package com.jin.oembedtest.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class OembedProviderUrlList {

    private static JSONParser jsonParser;
    private static List<String> urlList;
    private static JSONArray jsonArray;
    private static ResourceLoader resourceLoader;

    public OembedProviderUrlList() throws Exception {

        urlList = new ArrayList<String>();
        jsonParser = new JSONParser();

        ClassPathResource classPathResource = new ClassPathResource("static/assets/providers.json");
        JSONParser jsonParser = new JSONParser();
        BufferedReader br = new BufferedReader(new InputStreamReader(classPathResource.getInputStream()));
        try {
            Object obj = jsonParser.parse(br);

            JSONArray jsonArr = (JSONArray) obj;

            for (int i = 0; i < jsonArr.size(); i++) {
                JSONObject provider_url = (JSONObject) jsonArr.get(i);
                String url = (String) provider_url.get("endpoints").toString();

                Object obj2 = jsonParser.parse(url);
                jsonArray = new JSONArray();
                jsonArray = (JSONArray) obj2;
                JSONObject urlData = (JSONObject) jsonArray.get(0);

                String value = (String) urlData.get("url");
                urlList.add(value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getUrlList() {
        return urlList;
    }

    public static void setUrlList(List<String> urlList) {
        OembedProviderUrlList.urlList = urlList;
    }
}
