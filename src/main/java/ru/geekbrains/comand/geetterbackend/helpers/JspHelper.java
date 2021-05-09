package ru.geekbrains.comand.geetterbackend.helpers;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class JspHelper {
    public static String generateAbsURL (final HttpServletRequest request){
        URL url = null;
        try {
            url = new URL(request.getRequestURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String host  = Objects.requireNonNull(url).getHost();
        String userInfo = url.getUserInfo();
        String scheme = url.getProtocol();
        int port = url.getPort();
        String path  = request.getContextPath();

        URI uri = null;
        try {
            uri = new URI(scheme,userInfo,host,port,path,null,null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(uri).toString();
    }
}
