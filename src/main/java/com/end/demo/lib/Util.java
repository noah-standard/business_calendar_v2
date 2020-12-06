package com.end.demo.lib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Util {
    public static boolean isEmpty(Object obj) {

        if(obj == null) return true;

        if ((obj instanceof String) && (((String)obj).trim().length() == 0)) { return true; }

        if (obj instanceof Map) { return ((Map<?, ?>) obj).isEmpty(); }

        if (obj instanceof Map) { return ((Map<?, ?>)obj).isEmpty(); }

        if (obj instanceof List) { return ((List<?>)obj).isEmpty(); }

        if (obj instanceof Object[]) { return (((Object[])obj).length == 0); }

        return false;

    }

    public static String getIp(){
        String ip = "";
        //IP
        InetAddress local;
        try {
            local = InetAddress.getLocalHost();
            ip = local.getHostAddress();
        } catch (UnknownError | UnknownHostException e1) {
            e1.printStackTrace();
        }
        return ip;
    }

    public static String getDate(String dateType){
        String date = "";
        Calendar time = Calendar.getInstance();
        if(dateType.equals("timestamp")){
            SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
            date = format.format(time.getTime());
        }else if (dateType.equals("date")){
            SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");
            date = format.format(time.getTime());
        }else{
            SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
            date = format.format(time.getTime());
        }
        return date;
    }

    public static void errorMsg(String msg, HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('"+msg+"'); history.go(-1);</script>");
        out.flush();
    }

    public static void errorLogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('로그인 정보를 확인해주세요');location.href = '/';</script>");
        out.flush();
    }

    public static void errorAdminLogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('로그인 정보를 확인해주세요');location.href = '/cms';</script>");
        out.flush();
    }

}
