package ru.baryshev.kirill;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Вариант LRU кеша
 *
 * @param <K> Ключ
 * @param <V> Значение
 */

public class MySuperCache<K, V> {

    /**
     * Мапа для хранения
     */
    private Map<K, V> linkedHashMap;

    private Integer maxSize = 10;

    //    TODO потом вынести и прмиенить IoC/DI
    public MySuperCache() {
        linkedHashMap = new LinkedHashMap<>(maxSize);
    }

    /**
     * Добавление элемента в КЕШ
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
//        TODO оставить вверху или вызвать в конце, разобраться
        if (linkedHashMap.size() == maxSize) {
            Object o = linkedHashMap.keySet().toArray()[0];
            linkedHashMap.remove(o);
        }
        linkedHashMap.put(key, value);
    }

    /**
     * Получение элемента кеша по ключу
     *
     * @param key Ключ элемента
     * @return Возвращает значение для ключа
     */
    public V get(K key) {
        if (!linkedHashMap.containsKey(key)) {
            return null;
        }
        V value = linkedHashMap.get(key);
        linkedHashMap.remove(key);
        linkedHashMap.put(key, value);
        return value;
    }
}
