package platon.ru.vsu.cs.bweb_lib.server.path;

import platon.ru.vsu.cs.bweb_lib.server.method.WebMethodDescr;

import java.util.HashMap;
import java.util.Map;

public class PathParser {
    public static String getPathParam(String path){
        if(path == null) {
            return null;
        }
        String[] params = path.split("&")[0].split("/");

        if(params.length != 0 && params.length != 2) {
            throw new IllegalArgumentException("illegal path");
        }
        if(params.length == 0){
            return null;
        }
        try {
            return params[1];
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("illegal path");
        }
    }

    public static Map<String, String> getQueryParams(String path) throws IllegalPath {
        Map<String, String> map = new HashMap<>();
        String[] params = path.split("&");
        for (int i = 1; i < params.length; i++) {
            String[] param = params[i].split("=");
            String key = param[0];
            String value = param[1];
            if(map.get(key) != null){
                throw new IllegalPath("found another param same name");
            }
            map.put(key, value);
        }
        return map;
    }

    public static class IllegalPath extends Exception{
        public final String msg;

        public IllegalPath(String msg){
            super(msg);
            this.msg = msg;
        }

    }

    public static ParseMethod getParseMethod(Class<?> type){
        ParseMethod parseMethod;

        if(type.equals(Integer.class)) {
            parseMethod = Integer::parseInt;
        }
        else if(type.equals(String.class)) {
            parseMethod = s -> s;
        }
        else if(type.equals(Boolean.class)){
            parseMethod = in -> {
                if(in.equals("0") || in.equals("false"))  {
                    return false;
                }
                if(in.equals("1") || in.equals("true"))  {
                    return true;
                }
                throw new NumberFormatException();
            };
        }

        else {
            throw new RuntimeException();
        }
        return parseMethod;
    }

}
