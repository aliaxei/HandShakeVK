package com.example.myapplication.requestEngine.trees;


import java.util.ArrayList;
import java.util.List;

public class Node {

    private int key;
    private List<Integer> prevKeys = new ArrayList<>();
    private int level;

    public Node(int key, int level) {
        this.key = key;
        this.level = level;
    }

    public List<Integer> getPrevKeys() {
        return prevKeys;
    }

    public void setPrevKeys(List<Integer> prevKeys) {
        this.prevKeys = prevKeys;
    }

    public void addPrevKey(Integer prevKey) {
        prevKeys.add(prevKey);
    }

    public int getKey() {
        return key;
    }


    public int getLevel() {
        return level;
    }

}

