package com.ysh.strawpoll.util;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class IpUtil {
    public static String getIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if(forwarded != null){
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }

    public static void listTest() {
        ArrayList<Object> objects = new ArrayList<>();
        String s = "yanshuhang";
        Integer integer = 10;
        objects.add(s);
        objects.add(integer);
        System.out.println(objects);
    }
}
