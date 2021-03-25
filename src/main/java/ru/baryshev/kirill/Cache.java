package ru.baryshev.kirill;

import java.util.Map;

public interface Cache<K, V> {

    /**
     * Добавление элемента
     *
     * @param key   Ключ
     * @param value Значение
     */
    void put(K key, V value);

    /**
     * Получение элемента по ключу
     *
     * @param key Ключ
     */
    V get(K key);

    /**
     * Получение текущего размера кеша
     *
     * @return Размер кеша
     */
    Integer currentSize();

    /**
     * Возвращает все элементы кеша
     */
    Map<K,V> getAll();
}
