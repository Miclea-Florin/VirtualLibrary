package com.example.proiect;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ISBN_DB extends Main{

    private static HttpURLConnection con;

    public static Carte ISBN_to_Object(String ISBN) throws MalformedURLException,
            ProtocolException, IOException {

        String url = "https://api2.isbndb.com/book/";
        url = url + ISBN;

        //String url = " https://api.isbndb.com/books/Amintiri din copilarie";
        StringBuilder content;
        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "48613_99b57040c29aff6fa047b49575416a85");
            con.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            //System.out.println(content.toString());

        } finally {

            con.disconnect();
        }
        String test = content.toString();
        JSONObject obj = new JSONObject(test);
        String title= obj.getJSONObject("book").getString("title");
        //System.out.println(title);
        Carte c = new Carte(obj.getJSONObject("book").getString("title"),obj.getJSONObject("book").getString("publisher"),obj.getJSONObject("book").getString("isbn"),obj.getJSONObject("book").getString("language"),obj.getJSONObject("book").getJSONArray("authors"),obj.getJSONObject("book").getInt("date_published"),obj.getJSONObject("book").getInt("pages"),5);

        //System.out.println(c);
        return c;
    }



}
