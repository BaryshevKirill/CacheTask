package ru.baryshev.kirill.algorithms;


import lombok.extern.log4j.Log4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Реализация LRU.
 *
 * @param <K> Тип ключа
 * @param <V> Тип значения
 */
@Log4j
public class AlgorithmLRU<K, V> implements Algorithm<K, V> {

    /**
     * Из-за особенности линкед листа в нем будут храниться ключи.
     */
    private List<K> list = new LinkedList<>();

    /**
     * Алгоритм удаления для LRU
     *
     * @param mapWithValue Мапа содержащая ключ и значение кеша
     */
    @Override
    public K removeValue() {
        int firstElemIndex = 0;
        K keyForRemove = list.get(firstElemIndex);
        list.remove(firstElemIndex);
        return keyForRemove;
    }

    /**
     * После добавления нового ключа необходимо добавить его в список ключей.
     *
     * @param key   Ключ элемента
     * @param value Значение элемента (не используется)
     */
    @Override
    public void put(K key, V value) {
        list.add(key);
        log.info(String.format("Добавлен элемент в list. Ключ: %s; Значение: %s", key, value));
    }

    /**
     * После обращения к элементу, необходимо его перенести в конец списка.
     * Т.к. он самый последний элемент, к которому обратились.
     *
     * @param key Ключ
     */
    @Override
    public void get(K key) {
        list.remove(key);
        list.add(key);
        log.info(String.format("В кеше \"Возраст\" для ключа %s был сброшен!", key));
    }
}
