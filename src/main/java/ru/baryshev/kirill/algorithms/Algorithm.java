package ru.baryshev.kirill.algorithms;

public interface Algorithm<K, V> {

    K removeValue();

    void put(K key, V value);

    void get(K key);
}
