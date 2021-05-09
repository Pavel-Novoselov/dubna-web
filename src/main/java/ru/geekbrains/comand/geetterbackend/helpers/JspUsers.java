package ru.geekbrains.comand.geetterbackend.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.geekbrains.comand.geetterbackend.entities.dto.UserDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class JspUsers {

    public static JSONArray getAllUsers(final String url) throws IOException, ParseException {
        String received;
        final StringBuilder buffer = new StringBuilder();
        final URL jsonpage = new URL(url + "/api/users");
        final URLConnection urlcon = jsonpage.openConnection();
        final BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
        while ((received = buffread.readLine()) != null) {
            buffer.append(received);
        }
        buffread.close();
        final JSONParser parse = new JSONParser();
        return (JSONArray) parse.parse(buffer.toString());
    }

    public static String createUser(final UserDto user, final String url) throws IOException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final MediaType mediaType = MediaType.parse("application/json");

        final ObjectMapper mapper = new ObjectMapper();
        final String jsonString = mapper.writeValueAsString(user);

        final RequestBody body = RequestBody.create(mediaType, jsonString);
        final Request request1 = new Request.Builder()
                .url(url + "/api/users")
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

    public static JSONObject getUserById(final String userId, final String url) throws IOException, ParseException {
        return getJsonObject(new URL(url + "/api/users/" + userId + "/id"));
    }

    private static JSONObject getJsonObject(URL jsonpage) throws IOException, ParseException {
        String received;
        final StringBuilder buffer = new StringBuilder();
        final URLConnection urlcon = jsonpage.openConnection();
        final BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
        while ((received = buffread.readLine()) != null) {
            buffer.append(received);
        }
        buffread.close();
        final JSONParser parse = new JSONParser();
        return (JSONObject) parse.parse(buffer.toString());
    }

    public static JSONObject getUserByUsername(final String username, final String url) throws IOException, ParseException {
        return getJsonObject(new URL(url + "/api/users/" + username + "/login"));
    }

    public static String deleteUser(final String userId, final String url) throws IOException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final MediaType mediaType = MediaType.parse("application/json");

        final ObjectMapper mapper = new ObjectMapper();
        final String jsonString = mapper.writeValueAsString(userId);

        final RequestBody body = RequestBody.create(mediaType, jsonString);
        final Request request1 = new Request.Builder()
                .url(url + "/api/users/" + userId + "/id")
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
                result = "Error!";
            }
        }
        return result;
    }

    public static String updateUser(final UserDto user, final String url) throws IOException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final MediaType mediaType = MediaType.parse("application/json");

        final ObjectMapper mapper = new ObjectMapper();
        final String jsonString = mapper.writeValueAsString(user);

        final RequestBody body = RequestBody.create(mediaType, jsonString);
        final Request request1 = new Request.Builder()
                .url(url + "/api/users")
                .method("PUT", body)
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

    public static String putJsonToUrl(final String jsonString, final String url) throws IOException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final MediaType mediaType = MediaType.parse("application/json");

        final RequestBody body = RequestBody.create(mediaType, jsonString);
        final Request request1 = new Request.Builder()
                .url(url)
                .method("PUT", body)
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
}
