package ru.baryshev.kirill;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import ru.baryshev.kirill.algorithms.Algorithm;

import java.util.HashMap;
import java.util.Map;

@Log4j
public class MySuperCache<K, V> {
    /**
     * Мапа для хранения данных кеша
     */
    private Map<K, V> mapWithValue = new HashMap<>();
    /**
     * Максимальный размер кеша
     */
    private Integer maxSize;
    /**
     * Алгоритм удаления
     */
    private Algorithm algorithm;

    public MySuperCache(Integer maxSize, Algorithm algorithm) {
        if (maxSize <= 0) {
            log.error("Максимальный размер хеша должен быть больше 0");
            throw new IllegalArgumentException("Максимальный размер хеша должен быть больше 0");
        }
        this.maxSize = maxSize;
        this.algorithm = algorithm;
    }

    /**
     * Добавление элемента
     *
     * @param key   Ключ
     * @param value Значение
     */
    public void put(K key, V value) {
        if (mapWithValue.size() == maxSize) {
            Object o = algorithm.removeValue();
            mapWithValue.remove(o);
            log.info(String.format("Из кеша удален элемент. Ключ: %s; ", o.toString()));
        }

        mapWithValue.put(key, value);
        algorithm.put(key, value);
        log.info(String.format("В кеш добавлен элемент. Ключ: %s; Значение: %s.", key, value));
    }

    /**
     * Полчение элемента по ключу
     *
     * @param key Ключ
     */
    public V get(K key) {
        if (!mapWithValue.containsKey(key)) {
            log.error("В кеше не найден элемент с ключом: " + key);
            return null;
        }
        algorithm.get(key);
        return mapWithValue.get(key);
    }

    public Integer currentSize() {
        return mapWithValue.size();
    }

    public String getAlgorithmName() {
        return algorithm.getClass().getSimpleName();
    }

}
