package ru.geekbrains.comand.geetterbackend.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import ru.geekbrains.comand.geetterbackend.entities.dto.CommentDto;

import java.io.IOException;

public class JspComments {

    public static String createComment(
            final CommentDto newCommentDto,
            final String url
    ) throws IOException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                                                      .build();
        final MediaType mediaType = MediaType.parse("application/json");

        final ObjectMapper mapper = new ObjectMapper();
        final String jsonString = mapper.writeValueAsString(newCommentDto);

        final RequestBody body = RequestBody.create(mediaType, jsonString);
        final Request request1 = new Request.Builder()
                .url(url + "/api/comments")
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


}
