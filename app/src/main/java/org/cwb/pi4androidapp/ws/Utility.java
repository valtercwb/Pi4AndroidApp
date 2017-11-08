package org.cwb.pi4androidapp.ws;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by valter.franco on 11/2/2017.
 */


public class Utility {
    public static <T> List<T> convertJsonStringToList(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr);
    }

    public static <T> T convertJsonStringToPojo(String json, Class<T> tClass){
        Gson gson = new Gson();

        return tClass.cast(gson.fromJson(json, tClass));
    }

    public static String convertPojoToString(Object obj){
        return new Gson().toJson(obj);
    }
}
