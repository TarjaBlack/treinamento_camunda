package com.example.treinamento.util;

import java.util.Objects;

public class StringUtil {

    private StringUtil() {
    }
    
    public static String safeToString(Object obj){
        if (Objects.isNull(obj)){
            return "";
        }else {
            return obj.toString();
        }
        
    }
}
