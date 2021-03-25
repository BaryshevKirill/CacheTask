package ru.baryshev.kirill;

import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Log4j
public class CacheLRU<K, V> implements Cache<K, V> {
    /**
     * Мапа для хранения данных кеша
     */
    private Map<K, V> mapWithValue = new HashMap<>();
    /**
     * Максимальный размер кеша
     */
    private Integer maxSize;
    /**
     * Из-за особенности линкед листа в нем будут храниться ключи.
     */
    private List<K> list = new LinkedList<>();

    public CacheLRU(Integer maxSize) {
        if (maxSize <= 0) {
            log.error("Максимальный размер хеша должен быть больше 0");
            throw new IllegalArgumentException("Максимальный размер хеша должен быть больше 0");
        }
        this.maxSize = maxSize;
        log.info("Создан кеш с реализацией LRU(Самый старый)");
    }

    /**
     * Добавление элемента
     *
     * @param key   Ключ
     * @param value Значение
     */
    public void put(K key, V value) {
        if (mapWithValue.size() == maxSize) {
            removeValue();
        }

        mapWithValue.put(key, value);
        list.add(key);
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
        list.remove(key);
        list.add(key);
        log.info(String.format("В кеше \"Возраст\" для ключа %s был сброшен!", key));
        return mapWithValue.get(key);
    }

    @Override
    public Integer currentSize() {
        return mapWithValue.size();
    }

    /**
     * Алгоритм удаления для LRU
     */
    public void removeValue() {
        int firstElemIndex = 0;
        K keyForRemove = list.get(firstElemIndex);
        mapWithValue.remove(keyForRemove);
        list.remove(firstElemIndex);
    }
}
