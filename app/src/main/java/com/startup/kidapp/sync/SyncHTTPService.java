package com.startup.kidapp.sync;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.Map;

/**
 * Created by ansumanjaiswal on 4/20/15.
 */
public class SyncHTTPService {

    /**
     * Sends an blocking HTTP request.
     * @param request A SPDHTTPRequest with the request details.
     * @return String response of the HTTP request.
     * @throws IOException
     */
    public static String request(URLBundle request) throws IOException {
        // Setup
        HttpURLConnection conn = (HttpURLConnection ) request.getUrl().openConnection();

        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);

        // Verb
        conn.setRequestMethod( request.getVerb() );

        // Headers
        Map<String, String> headers = request.getHeaders();
        for (String header : headers.keySet()) {
            String value = headers.get( header );
            conn.addRequestProperty( header, value );
        }

        // Send
        OutputStream out = conn.getOutputStream();

        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(out, "UTF-8") );
        writer.write( request.getBody() );
        writer.flush();
        writer.close();

        //Receive
        InputStreamReader stream = new InputStreamReader( conn.getInputStream() );
        BufferedReader reader = new BufferedReader(stream);

        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        String response = builder.toString();
        return response;
    }
}
