package ru.baryshev.kirill;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Log4j
public class MyCacheLfuImpl<K, V> extends MyCache<K, V> {

    /**
     * Мапа для хранения частоты использования различных элементов.
     * Необходим для реализации LFU необходимо
     */
    private Map<K, Integer> mapWithCount = new HashMap<>();

    @Override
    public void put(K key, V value) {
        if (mapWithValue.size() == maxSize) {
            removeValue();
        }
        mapWithCount.put(key, 1);
        mapWithValue.put(key, value);
        log.info(String.format("В кеш добавлен элемент. Ключ: %s; Значение: %s.", key, value));
    }

    @Override
    public V get(K key) {
        if (!mapWithCount.containsKey(key)) {
            log.error("В кеше не найден эелмент с ключом: " + key);
            return null;
        }
        mapWithCount.replace(key, mapWithCount.get(key) + 1);
        log.info(String.format("В кеше увеличилось количество обращений к ключу: %s на 1. Текущее значение: %s", key,mapWithCount.get(key)));
        return mapWithValue.get(key);
    }

    /**
     * Метод для удаления элемента из кеша, соотвествующий LFU алгоритму
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
}
