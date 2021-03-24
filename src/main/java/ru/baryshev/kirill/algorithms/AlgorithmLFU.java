package ru.baryshev.kirill.algorithms;

import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Реализация LFU.
 *
 * @param <K> Тип ключа
 * @param <V> Тип значения
 */
@Log4j
public class AlgorithmLFU<K, V> implements Algorithm<K, V> {

    /**
     * Мапа для хранения частоты использования различных элементов.
     * Необходим для реализации LFU
     */
    private Map<K, Integer> mapWithCount = new HashMap<>();

    @Override
    public void removeValue(Map<K, V> mapWithValue) {
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
        mapWithCount.put(key, 1);
        log.info(String.format("Добавлен элемент в mapWithCount. Ключ: %s; Значение: %s", key, value));
    }

    @Override
    public void get(K key) {
        mapWithCount.replace(key, mapWithCount.get(key) + 1);
        log.info(String.format("В кеше увеличилось количество обращений к ключу: %s на 1. Текущее значение: %s", key, mapWithCount.get(key)));
    }
}
