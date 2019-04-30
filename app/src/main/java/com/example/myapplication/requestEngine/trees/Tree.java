package com.example.myapplication.requestEngine.trees;


import com.vk.sdk.VKObject;

import java.util.ArrayList;

public class Tree {

    private ArrayList<Node> treeList = new ArrayList<Node>();

    public ArrayList<Node> getTreeList() {
        return treeList;
    }


    public void  addNew (Node node){
        treeList.add(node);
    }
}

