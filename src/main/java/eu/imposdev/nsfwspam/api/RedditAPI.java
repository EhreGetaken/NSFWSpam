package eu.imposdev.nsfwspam.api;

import net.dv8tion.jda.api.entities.Activity;
import net.md_5.bungee.api.ProxyServer;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RedditAPI {

    public static final ArrayList<String> REDDIT_LIST = new ArrayList<>();

    public static String getContent(String reddit) {
        String outputMessage = "https://imposdev.eu/cdn/private/404notfound.jpg";
        JSONObject json = null;
        try {
            String url = "https://reddit-meme-api.herokuapp.com/" + reddit + "/";
            json = readJsonFromUrl(url);
            outputMessage = json.getString("url");
        } catch (IOException | JSONException exc) {
            exc.printStackTrace();
        }
        return outputMessage;
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String readAll = readAll(rd);
            JSONObject json = new JSONObject(readAll);
            return json;
        } finally {
            is.close();
        }
    }

    public static JSONObject readJsonFromUrl2(String url) throws IOException, JSONException {
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();
        BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
        JSONObject json = new JSONObject(readAll(r));
        return json;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
