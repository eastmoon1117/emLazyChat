package com.jared.emlazychat.lib;

/**
 * Created by jared on 16/3/4.
 */
public class EMURL {
    public static String BASE_HTTP    = "http://192.168.1.105:8080/EMLazyChatServer";
    public static String BASE_EM_HOST = "192.168.1.105";
    public static int BASE_EM_PORT    = 9090;
    public static String BASE_QR      = BASE_HTTP + "/QR/";

    public final static String URL_HTTP_LOGIN    = BASE_HTTP + "/login";
    public final static String URL_HTTP_REGISTER = BASE_HTTP + "/register";
    public final static String URL_HTTP_LOGOUT   = BASE_HTTP + "/logout";

    public final static String URL_HTTP_SEARCH_CONTACT = BASE_HTTP + "/user/search";
}
