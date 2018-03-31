package com.hashmap;

import java.util.Iterator;
import java.util.LinkedList;

class HashMap<K, V> {

    private static final int START_BUCKET_ARRAY_SIZE = 16;
    private int valueCount;


    // This holds all the data. Its a primitive array where every element is a Linked List.
    // The Linked List holds bucketList of type KeyValue
    private LinkedList<KeyValue<K, V>>[] bucketList;

    public HashMap() {
        bucketList = initializeBucketList(START_BUCKET_ARRAY_SIZE);
    }

    private LinkedList[] initializeBucketList(int startBucketArraySize){
        LinkedList[] newBucketList = new LinkedList[startBucketArraySize];
        for (int i = 0; i < newBucketList.length; i++) {
            newBucketList[i] = new LinkedList<>();
        }
        return newBucketList;
    }

    public void add(K key, V value) {
        // find out which position of the primitive array to use:
        int position = getHash(key);
        LinkedList<KeyValue<K, V>> currentBucket = bucketList[position];

        // If the key already exists throw an error.
        for (int i = 0; i < currentBucket.size(); i++) {
            if(currentBucket.get(i).getKey() == key){
                throw new IllegalArgumentException("Key already exists.");
            }
        // Make a new instance of the KeyValue class, fill it with the key, value parameters,
            // then add it to the currentBucket.

        }
        KeyValue<K, V> newValue = new KeyValue<>();
        newValue.setValue(value);
        newValue.setKey(key);
        currentBucket.add(newValue);
        valueCount++;

        resizeIfNeeded();
    }

    public V getValue(K key) {
        // 1. Calculate the hash of the key. This defines which element to get from the "bucketList" array
        // 2. Find in the List in this position the KeyValue element that has this key, then return its value.
        //    If none of the items in the list has this key throw error.

        int position = getHash(key);

        LinkedList<KeyValue<K, V>> currentBucket = bucketList[position];
        for (Iterator<KeyValue<K, V>> iterator = currentBucket.iterator(); iterator.hasNext(); ) {
            KeyValue<K, V> aCurrentBucket = iterator.next();
            if (aCurrentBucket.getKey() == key) {
                return aCurrentBucket.getValue();
            }
        }

        return null;
    }

    private int getHash(K key) {

        // This function converts somehow the key to an integer between 0 and bucketSize
        // in Java hashCode() is a function of Object, so all non-primitive types
        // can easily be converted to an integer.
        return getHash(key, bucketList.length);
    }

    private int getHash(K key, int bucketListLength) {
        return key.hashCode() % bucketListLength;
    }

    private void resizeIfNeeded() {
        // If it holds more bucketList than bucketSize * 2, destroy and recreate it
        // with the double size of the bucketList array.
        if(valueCount > bucketList.length * 2){
            LinkedList[] linkedLists = initializeBucketList(bucketList.length * 2);
            getCopyIntoBucketListAndReplace(linkedLists);
        }
        // if it holds less bucketList than bucketSize / 2, destroy and recreate it
        // with half size of the bucketList array.
        else if(valueCount < bucketList.length / 2){
            LinkedList[] linkedLists = initializeBucketList(bucketList.length / 2);
            getCopyIntoBucketListAndReplace(linkedLists);
        }

    }

    private void getCopyIntoBucketListAndReplace(LinkedList[] newBucketList) {
        for (LinkedList<KeyValue<K, V>> linkedList: bucketList) {
            for (KeyValue<K, V> keyValue: linkedList){
                K key = keyValue.getKey();
                int newBucketIndex = getHash(key, newBucketList.length);
                newBucketList[newBucketIndex].add(keyValue.getValue());

            }
        }
        bucketList = newBucketList;
    }


    public void remove(K key){

        int position = getHash(key);

        LinkedList<KeyValue<K, V>> currentBucket = bucketList[position];
        for (Iterator<KeyValue<K, V>> iterator = currentBucket.iterator(); iterator.hasNext(); ) {
            KeyValue<K, V> aCurrentBucket = iterator.next();
            if (aCurrentBucket.getKey() == key) {
                iterator.remove();
            }
        }
        valueCount--;
    }


    public void clearAll(){
        initializeBucketList(START_BUCKET_ARRAY_SIZE);
    }

}