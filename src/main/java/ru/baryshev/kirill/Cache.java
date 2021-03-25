package ru.baryshev.kirill;

public interface Cache<K, V> {

    /**
     * Добавление элемента
     *
     * @param key   Ключ
     * @param value Значение
     */
    void put(K key, V value);

    /**
     * Полчение элемента по ключу
     *
     * @param key Ключ
     */
    V get(K key);

    /**
     * Полчение текущего размера кеша
     *
     * @return Размер кеша
     */
    Integer currentSize();
}
