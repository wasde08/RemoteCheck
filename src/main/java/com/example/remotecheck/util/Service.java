package com.example.remotecheck.util;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Service {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public String getNewKey(){
        StringBuilder sb = new StringBuilder( 20 );
        for( int i = 0; i < 20; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }


    public void sendToTelegram(String msg) {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
        String apiToken = "5143502842:AAGZOII-xwtJ3-FC_OXEdcgKhB5trNKmCd4";
        String chatId = "-637859275";
        urlString = String.format(urlString, apiToken, chatId, msg);
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
