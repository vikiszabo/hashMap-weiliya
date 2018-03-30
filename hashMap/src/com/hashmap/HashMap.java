package com.hashmap;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class HashMap<String, Integer> {

    private int size = 0;

    private List<Object>[] pairs = new LinkedList[16];

    public void add(String key, Integer value) {

        List<Object> actualList = pairs[size];

        actualList = new LinkedList<>();
        actualList.add(key);
        actualList.add(value);

        size++;

        for (List item:
             pairs) {

        }




    }
}
