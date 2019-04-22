package com.example.myapplication.requestEngine;



import android.widget.Toast;

import com.example.myapplication.requestEngine.trees.Node;
import com.example.myapplication.requestEngine.trees.Root;
import com.example.myapplication.requestEngine.trees.Tree;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.vk.sdk.VKUIHelper.getApplicationContext;

public class DataProcess {

    private List<Node> buffer1 = new ArrayList<Node>();
    private List<Node> buffer2 = new ArrayList<Node>();
    private boolean flag = true;
    Tree tree = new Tree();


    public void mainProc(int startID, int finishID) {

        Node node = new Node(startID, 0);
        //tree.addNew(node);
        node.addPrevKey(0);
        buffer1.add(node);
        for (int i = 1; i <= 5; i++) {
            if (flag) {
                fillTree(i, finishID);
                buffer1 = new ArrayList<Node>();
                //Collections.copy(buffer1,buffer2);
                buffer1.addAll(buffer2);
                buffer2 = new ArrayList<Node>();
            }
        }

    }

    public void fillTree(int currentLevel, int finishID) {
       /* VKList<VKApiUserFull> listWithFriendsID;
        HandshakeRequest handshakeRequest = new HandshakeRequest();

        for (Node node : buffer1) {

            if (flag) {

                int currentNodeKey = node.getKey();
                List<Integer> previosKeys = node.getPrevKeys();
                //listWithFriendsID = handshakeRequest.friendRequest(currentNodeKey);
                try {

                    for (VKApiUserFull userFull : listWithFriendsID) {

                        if (userFull.id != finishID) {
                            Node nodeNew = new Node(userFull.id, currentLevel);
                            nodeNew.setPrevKeys(previosKeys);
                            nodeNew.addPrevKey(currentNodeKey);
                            buffer2.add(nodeNew);
                            //tree.addNew(nodeNew);

                        } else {
                            //if we find
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Нашел!" + currentLevel, Toast.LENGTH_SHORT);
                            toast.show();
                            flag = false;
                            break;
                        }

                    }
                } catch (NullPointerException e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Тут пусто" + currentNodeKey, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }*/
    }

    public List<Integer> bestAlgorithmEver(int startID, int finishID) {
        Set<Integer> scannedPersons = new HashSet<>();
        scannedPersons.add(startID);

        int[] nextPersonsForScanning = new int[10_000_000];
        int nextPersonsForScanningSize = 0;
        int[] currentScanningPersons = new int[10_000_000];
        int currentScanningPersonsSize = 0;
// Person me = new Person(startID);
        currentScanningPersons[currentScanningPersonsSize++] = startID;

        int processed = 0;

        HandshakeRequest friendsApi = new HandshakeRequest();
        for (int iteration = 0; iteration < 5; iteration++) {
            for (int personIndex = 0; personIndex < currentScanningPersonsSize; personIndex++) {
                int currentPerson = currentScanningPersons[personIndex];
                int[] vkApiUserFulls = friendsApi.friendRequest(currentPerson);
                int friendsCount = vkApiUserFulls.length;
                for (int friendIndex = 0; friendIndex < friendsCount; friendIndex++) {
                    int friendId = vkApiUserFulls[friendIndex];
                    if (friendId == finishID) {
                        List<Integer> resultWay = new ArrayList<>();
                        resultWay.add(currentPerson);
                        resultWay.add(friendId);
                        return resultWay;
                    }
                    if (!scannedPersons.contains(friendId)) {
                        scannedPersons.add(friendId);
// friendPerson.way = currentPerson.way;
// friendPerson.currentWayId = currentPerson.currentWayId + 1;
// friendPerson.way[friendPerson.currentWayId] = currentPerson.id;
                        nextPersonsForScanning[nextPersonsForScanningSize++] = friendId;
                    }
                    processed++;
                    if (processed % 1_000 == 0) {
                        System.out.println("Processed: " + processed);
// System.out.println("Amount in scanned: " + scannedPersons.size());
                    }
                }

// System.out.println("Scanned persons count: " + scannedPersons.size());
            }

            currentScanningPersonsSize = nextPersonsForScanningSize;
            if (nextPersonsForScanningSize >= 0)
                System.arraycopy(nextPersonsForScanning, 0, currentScanningPersons, 0, nextPersonsForScanningSize);
            nextPersonsForScanningSize = 0;
        }

        return null;
    }



    private class Person {
        private int id;
//        private int[] way = new int[5];
//        private int currentWayId = 0;

        private Person(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return id == person.id;
        }
    }
}

