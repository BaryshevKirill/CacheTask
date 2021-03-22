package ru.baryshev.kirill;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String,String> map = new LinkedHashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");

        System.out.println(map.keySet());
        for(Map.Entry<String,String> entry : map.entrySet()) {
            System.out.println("Ключ: " + entry.getKey() + "; Значение: " + entry.getValue());
        }

        map.remove("2");
        map.put("2","5");
        System.out.println(map.keySet());

        for(Map.Entry<String,String> entry : map.entrySet()) {
            System.out.println("Ключ: " + entry.getKey() + "; Значение: " + entry.getValue());
        }

    }
}
