package ru.geekbrains.comand.geetterbackend.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.geekbrains.comand.geetterbackend.entities.dto.NewTweetDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class JspTwits {

    public static JSONArray getAllTwits(final String url) throws IOException, ParseException {
        String received;
        final StringBuilder buffer = new StringBuilder();
        final URL jsonpage = new URL(url + "/api/tweets");
        final URLConnection urlcon = jsonpage.openConnection();
        final BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
        while ((received = buffread.readLine()) != null) {
            buffer.append(received);
        }
        buffread.close();
        final JSONParser parse = new JSONParser();
        return (JSONArray) parse.parse(buffer.toString());
    }

    public static JSONArray getTwitsOwnAndFromSubscription(final String userId, final String url) throws IOException, ParseException {
        String received;
        final StringBuilder buffer = new StringBuilder();
        final URL jsonpage = new URL(url + "/api/tweets/" + userId + "/ownandsubscr");
        final URLConnection urlcon = jsonpage.openConnection();

        final BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
        while ((received = buffread.readLine()) != null) {
            buffer.append(received);
        }
        buffread.close();
        final JSONParser parse = new JSONParser();
        return (JSONArray) parse.parse(buffer.toString());
    }

    public static void doLike(final String tweetId, final String url) throws IOException {
        final URL jsonpage = new URL(url + "/api/tweets/" + tweetId + "/like");
        final URLConnection urlcon = jsonpage.openConnection();
        final BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
        buffread.close();
    }

    public static String createTweet(final NewTweetDto newTweetDto,
                              final String url
                                    ) throws IOException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                                                      .build();
        final MediaType mediaType = MediaType.parse("application/json");

        final ObjectMapper mapper = new ObjectMapper();
        final String jsonString = mapper.writeValueAsString(newTweetDto);

        final RequestBody body = RequestBody.create(mediaType, jsonString);
        final Request request1 = new Request.Builder()
                .url(url + "/api/tweets")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        final String result;
        try (final Response response1 = client.newCall(request1).execute();
             final ResponseBody responseBody = response1.body()
        ) {
            if (responseBody != null) {
                result = responseBody.string().replaceAll(",", ",<br>");
            } else {
                result = "Error!";
            }
        }
        return result;
    }



    public static JSONObject getTweet(final String tweetId, final String url) throws IOException, ParseException {
        String received;
        final StringBuilder buffer = new StringBuilder();
        final URL jsonpage = new URL(url + "/api/tweets/" + tweetId + "/id");
        final URLConnection urlcon = jsonpage.openConnection();
        final BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
        while ((received = buffread.readLine()) != null) {
            buffer.append(received);
        }
        buffread.close();
        final JSONParser parse = new JSONParser();
        return (JSONObject) parse.parse(buffer.toString());
    }

    public static String deleteTweet(final String tweetId, final String url) throws IOException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                                                      .build();
        final MediaType mediaType = MediaType.parse("application/json");

        final ObjectMapper mapper = new ObjectMapper();
        final String jsonString = mapper.writeValueAsString(tweetId);

        final RequestBody body = RequestBody.create(mediaType, jsonString);
        final Request request1 = new Request.Builder()
                .url(url + "/api/tweets/" + tweetId + "/id")
                .method("DELETE", body)
                .addHeader("Content-Type", "application/json")
                .build();
        final String result;
        try (final Response response1 = client.newCall(request1).execute();
             final ResponseBody responseBody = response1.body()
        ) {
            if (responseBody != null) {
                result = responseBody.string();
            } else {
                result = "Error deleting tweet!";
            }
        }
        return result;
    }
}
