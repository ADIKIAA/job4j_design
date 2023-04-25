package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int i = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        int h = hashCode;
        return (hashCode == 0) ? 0 : h  ^ (h >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] tempTable = new MapEntry[capacity];
        Iterator<K> it = iterator();
        while (it.hasNext()) {
            K k = it.next();
            MapEntry<K, V> t = new MapEntry<>(k, get(k));
            int hash = k == null ? 0 : hash(k.hashCode());
            tempTable[hash & tempTable.length - 1] = t;
        }
        table = tempTable;
    }

    @Override
    public V get(K key) {
        V value = null;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] != null && table[index].key == key) {
            value = table[index].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            table[index] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            private int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
