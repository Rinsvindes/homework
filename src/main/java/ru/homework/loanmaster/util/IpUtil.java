package ru.homework.loanmaster.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
    public static String getIpFromHttpRequest(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
