package ru.baryshev.kirill;

import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Log4j
public class CacheLRU<K, V> implements Cache<K, V> {
    /**
     * Мапа для хранения данных кеша
     */
    private Map<K, V> mapWithValue = new LinkedHashMap<>();
    /**
     * Максимальный размер кеша
     */
    private Integer maxSize;
//    /**
//     * Из-за особенности линкед листа в нем будут храниться ключи.
//     */
//    private List<K> listOfKeys = new LinkedList<>();

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
//        listOfKeys.add(key);
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
        V value = mapWithValue.get(key);
//        listOfKeys.remove(key);
//        listOfKeys.add(key);
        mapWithValue.remove(key);
        mapWithValue.put(key,value);
        log.info(String.format("В кеше \"Возраст\" для ключа %s был сброшен!", key));
        return mapWithValue.get(key);
    }

    /**
     * Полчить текущий размер кеша
     *
     * @return Текущий размер кеша
     */
    @Override
    public Integer currentSize() {
        return mapWithValue.size();
    }

    /**
     * Алгоритм удаления для LRU
     */
    private void removeValue() {
        int firstElemIndex = 0;
        K keyForRemove = mapWithValue.keySet().iterator().next();
//        K keyForRemove = listOfKeys.get(firstElemIndex);
        mapWithValue.remove(keyForRemove);
//        listOfKeys.remove(firstElemIndex);
    }

    /**
     * Возвращает все элементы кеша
     *
     * @return Мапа с ключом-значением кеша
     */
    @Override
    public Map<K, V> getAll() {
        return mapWithValue;
    }
}
