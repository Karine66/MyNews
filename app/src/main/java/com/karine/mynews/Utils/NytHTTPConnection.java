package com.karine.mynews.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
class NytHTTPConnection {

    public static String startHttpRequest(String urlString) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            //Declare URL connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //Open InputStream
            conn.connect();
            InputStream in = conn.getInputStream();
            //Download and decode string response
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (MalformedURLException exception) {
        } catch (IOException exception) {
        } catch (Exception e) {
        }
        return stringBuilder.toString();
    }
}