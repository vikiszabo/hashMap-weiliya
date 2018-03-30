package com.hashmap;

public class Main {

    public static void main(String[] args) {

        HashMap<String, Integer> hm = new HashMap<>();
        hm.add("Lili", 20);

        System.out.println(hm.getBucketSize());
        System.out.println(hm.getElements());


    }
}
