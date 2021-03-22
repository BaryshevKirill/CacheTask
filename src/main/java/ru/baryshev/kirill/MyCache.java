package ru.baryshev.kirill;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.Map;

@Log4j
public abstract class MyCache<K, V> {

    /**
     * Мапа для хранения данных кеша
     */
    protected Map<K, V> mapWithValue = new HashMap<>();

    /**
     * Максимальный размер кеша
     */
    protected Integer maxSize;

    public void setMaxSize(@NonNull Integer maxSize) {
        if (maxSize <= 0) {
            log.error("Максимальный размер хеша должен быть больше 0");
            throw new IllegalArgumentException("Максимальный размер хеша должен быть больше 0");
        }
        this.maxSize = maxSize;
    }

    /**
     * Метод добавления элемента кеша
     *
     * @param key   Ключ элемента
     * @param value Значение элемента
     */
    abstract void put(K key, V value);

    /**
     * Метод получения элемента кеша по ключу
     *
     * @param key Ключ элмента
     * @return Возвращает значение кеша для ключа
     */
    abstract V get(K key);
}
