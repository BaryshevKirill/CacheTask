package ru.baryshev.kirill;


import java.util.Map;

public interface Algorithm<K, V> {

    void removeValue(Map<K,V> mapWithValue);

    void put(K key, V value);

    void get(K key);
}
