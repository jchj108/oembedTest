package com.jin.oembedtest.service;

import com.jin.oembedtest.dto.Oembed;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ws.transport.http.HttpUrlConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class OembedServiceImpl implements OembedService {

    private List<String> urlList = new ArrayList<>();

    @Override
    public Map<String, Oembed> parsingOembed(Oembed oembed, List<String> urlList) throws Exception {
        String url = oembed.getUrl().trim();
        String[] arr = url.split("\\.");
        String res = "";
        Map<String, Oembed> map = new HashMap<>();

        if (arr.length < 3) {
            throw new IllegalArgumentException("올바른 URL 형식이 아닙니다");
        }
        String host = arr[0] + "." + arr[1] + "." + arr[2].substring(0, arr[2].lastIndexOf("/"));
        for (String providerUrl : urlList) {
            if (providerUrl.contains(host)) {
                res = providerUrl + "?url=" + URLEncoder.encode(oembed.getUrl(), "euc-kr");
                break;
            }
        }
        oembed = CallOembed(res);

        map.put("oembed", oembed);

        return map;
    }

    @Override
    public Oembed CallOembed(String requestUrl) {

        Oembed oembed = new Oembed();

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) { // api 응답 성공
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder sb = new StringBuilder();
                String line = "";

                while((line = br.readLine()) != null) {
                    sb.append(line);
                }

                JSONParser jsonParser = new JSONParser();
                Object obj = jsonParser.parse(sb.toString());
                JSONObject jsonObject = (JSONObject) obj;

                oembed.setTitle((String) jsonObject.get("title"));
                oembed.setAuthor_url((String)jsonObject.get("author_url"));
                oembed.setType((String)jsonObject.get("type"));
                oembed.setHeight(Integer.parseInt(String.valueOf(jsonObject.get("height"))));
                oembed.setWidth(Integer.parseInt(String.valueOf(jsonObject.get("width"))));
                oembed.setVersion((String)jsonObject.get("version"));
                oembed.setProvider_name((String)jsonObject.get("provider_name"));
                oembed.setThumbnail_height(Integer.parseInt(String.valueOf(jsonObject.get("thumbnail_height"))));
                oembed.setThumbnail_width(Integer.parseInt(String.valueOf(jsonObject.get("thumbnail_width"))));
                oembed.setThumbnail_url((String)jsonObject.get("thumbnail_url"));
                oembed.setHtml((String)jsonObject.get("html"));

                System.out.println(oembed);
            } else {
                System.out.println(conn.getResponseMessage()); // error message 출력
                throw new IllegalStateException("oEmbed 서버로부터 응답을 받지 못했습니다.");
            }
        } catch (MalformedURLException e) {
            oembed.setErrorMsg("provider url 형식 오류");
            e.printStackTrace();
        } catch (IllegalStateException e) {
            oembed.setErrorMsg(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oembed;
    }


}
