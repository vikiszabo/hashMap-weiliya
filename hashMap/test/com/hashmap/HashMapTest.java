package com.hashmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    @Test
    void add() {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < 21; i++) {
            hm.add(i, i);
        }
        for (int i = 0; i < 21; i++) {
            assertEquals(Integer.valueOf(i), hm.getValue(i));
        }
    }



    @Test
    void remove() {
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.add(1, 1);
        hm.remove(1);
        assertNull(hm.getValue(1));
    }

    @Test
    void clearAll() {
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.add(1, 1);
        hm.clearAll();
        assertNull(hm.getValue(1));
    }
}