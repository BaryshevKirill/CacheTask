package ru.baryshev.kirill;

import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.Map;

@Log4j
public class CacheLFU<K, V> implements Cache<K, V> {

    /**
     * Мапа для хранения данных кеша
     */
    private Map<K, V> mapWithValue = new HashMap<>();
    /**
     * Мапа для хранения частоты использования различных элементов.
     * Необходим для реализации LFU
     */
    private Map<K, Integer> mapWithCount = new HashMap<>();
    /**
     * Максимальный размер кеша
     */
    private Integer maxSize;

    public CacheLFU(Integer maxSize) {
        if (maxSize <= 0) {
            log.error("Максимальный размер хеша должен быть больше 0");
            throw new IllegalArgumentException("Максимальный размер хеша должен быть больше 0");
        }
        this.maxSize = maxSize;
        log.info("Создан кеш с реализацией LFU(Часто используемые)");
    }


    /**
     * Алгоритм удаления для LFU
     */
    private void removeValue() {
        K keyOfMinValue = null;
        Integer minValue = Integer.MAX_VALUE;
        for (Map.Entry<K, Integer> entry : mapWithCount.entrySet()) {
            if (minValue > entry.getValue()) {
                minValue = entry.getValue();
                keyOfMinValue = entry.getKey();
            }
        }
        V valueOfMinValue = mapWithValue.get(keyOfMinValue);
        mapWithValue.remove(keyOfMinValue);
        mapWithCount.remove(keyOfMinValue);
        log.info(String.format("Из кеша удален элемент. Ключ: %s; Значение: %s; Количество использований: %s.",
                keyOfMinValue, valueOfMinValue, minValue));
    }

    @Override
    public void put(K key, V value) {
        if (mapWithValue.size() == maxSize) {
            removeValue();
        }
        mapWithValue.put(key, value);
        mapWithCount.put(key, 1);
        log.info(String.format("В LFU кеш добавлен элемент. Ключ: %s; Значение: %s.", key, value));
    }

    @Override
    public V get(K key) {
        if (!mapWithValue.containsKey(key)) {
            log.error("В кеше не найден элемент с ключом: " + key);
            return null;
        }
        mapWithCount.replace(key, mapWithCount.get(key) + 1);
        log.info(String.format("В кеше увеличилось количество обращений к ключу: %s на 1. Текущее значение: %s", key, mapWithCount.get(key)));
        return mapWithValue.get(key);
    }

    @Override
    public Integer currentSize() {
        return mapWithValue.size();
    }
}
