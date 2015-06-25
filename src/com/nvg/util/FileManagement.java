package com.nvg.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * This class provides methods useful for the file upload
 *
 * @author GoNun
 */
public class FileManagement {


    /**
     * Appends a random number to the end of the filename
     *
     * @param originalFilename filename to be changed
     * @return filename with random id appended
     */
    public static String createUniqueFilename(String originalFilename) {
        int separator = originalFilename.lastIndexOf(".");
        String filename = originalFilename.substring(0, separator);
        String extention = originalFilename.substring(separator,originalFilename.length());

        String rId = String.valueOf(System.currentTimeMillis()).substring(0,8);

        return filename + rId + extention;
    }


    /**
     * UPLOAD
     */
    /**
     * Uploads file for a location with proxy
     *
     * @param uploadUrl upload location
     * @param buffer document contents
     * @param filename name for the uploaded file
     * @param headerName connection header name
     * @param headerValue connection header value
     * @return status
     * @throws Exception by connection or file
     */
    public static String uploadFile(String uploadUrl, byte[] buffer, String filename, String headerName, String headerValue) throws Exception {

        URL url = new URL(uploadUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/pdf");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Disposition", String.format("attachment; filename=%s", filename));

            if (headerName != null && headerName.trim() != null) {
                connection.setRequestProperty(headerName, URLEncoder.encode(headerValue, "UTF-8"));
            }

            connection.setUseCaches(false);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();

            String decodedString;
            String response = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((decodedString = in.readLine()) != null) {
                response += decodedString;
            }

            in.close();
            return response;
        } finally {
            connection.disconnect();
        }
    }

}
