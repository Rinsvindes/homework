package ru.homework.loanmaster.util;

import com.google.common.net.InetAddresses;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Countries {
    private final static String IP_INFO_HOST = "http://ipinfo.io/";
    private final static String COUNTRY_SERVICE = "/country";

    private static final int DEFAULT_TIMEOUT = 10000;

    public static String getCountryCodeByIp(String ip){
        if (!InetAddresses.isInetAddress(ip)) {
            throw new IllegalIpAddressException();
        }

        try {
            URL countryCodeServiceUri = new URL(IP_INFO_HOST + ip + COUNTRY_SERVICE);
            HttpURLConnection connection = (HttpURLConnection) countryCodeServiceUri.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(DEFAULT_TIMEOUT);
            connection.setReadTimeout(DEFAULT_TIMEOUT);

            return new Scanner(connection.getInputStream()).next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
