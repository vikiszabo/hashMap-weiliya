package com.hashmap;

import java.util.Arrays;
import java.util.LinkedList;

class HashMap<K, V> {

    private int bucketSize = 16;
    private int indexSize = 0;


    // This holds all the data. Its a primitive array where every element is a Linked List.
    // The Linked List holds elements of type KeyValue
    private LinkedList<KeyValue>[] elements = new LinkedList[bucketSize];

    public int getBucketSize() {
        return bucketSize;
    }

    public LinkedList<KeyValue>[] getElements() {
        return elements;
    }

    public void add(K key, V value) {
        // find out which position of the primitive array to use:
        int position = getHash(key);
        LinkedList list = elements[position];

        
        
        // If the key already exists throw an error.
        for (int i = 0; i < elements.length; i++) {
            if(elements[i].contains(key)){
                throw new IllegalArgumentException("Key already exists.");
            }
        // Make a new instance of the KeyValue class, fill it with the key, value parameters,
            // then add it to the list.
            else {
                KeyValue<K, V> currentKV = new KeyValue<>();
                currentKV.setKey(key);
                currentKV.setValue(value);
                list.add(currentKV);
                indexSize++;
            }
        }

        resizeIfNeeded();
    }

    public V getValue(K key) {
        // 1. Calculate the hash of the key. This defines which element to get from the "elements" array
        // 2. Find in the List in this position the KeyValue element that has this key, then return its value.
        //    If none of the items in the list has this key throw error.

        int position = getHash(key);

        LinkedList pair = elements[position];
        for (int i = 0; i < elements.length; i++) {
            if(pair.get(0) == key){
                return (V) pair.get(1);
            }
        }

        return null;
    }

    private int getHash(K key) {

        // This function converts somehow the key to an integer between 0 and bucketSize
        // In C# GetHashCode(), in Java hashCode() is a function of Object, so all non-primitive types
        // can easily be converted to an integer.
        return key.hashCode() % bucketSize;
    }

    private void resizeIfNeeded() {
    // If it holds more elements than bucketSize * 2, destroy and recreate it
    // with the double size of the elements array.

        if(indexSize > bucketSize){
            LinkedList<KeyValue>[] newElements = Arrays.copyOf(elements, elements.length*2);
            elements = newElements;
        }

    // if it holds less elements than bucketSize / 2, destroy and recreate it
    // with half size of the elements array.
        else if(indexSize < bucketSize/2) {
            LinkedList<KeyValue>[] newElements = new LinkedList[elements.length/2];
            elements = newElements;
        }
}

// + other functions, like clearAll(), delete(),..

    /* Your task will be to create your own HashMap with the following requirements:

It should have the following functions: add(key, value), getValue(key), remove(key), clearAll()
Its keys are Strings, its values Integers.
Its initialized with the size of 16, you don't need to resize it when it gets too big.
Hint: You can easilly convert an Object to a number between 0 and N with the following code:
obj.GetHashCode() % N (C#), obj.hashCode() % N (Java). */

    public void remove(K key){

    }


    public void clearAll(){

    }


    @Override
    public String toString() {
        return "HashMap{" +
                "bucketSize=" + bucketSize +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }
}