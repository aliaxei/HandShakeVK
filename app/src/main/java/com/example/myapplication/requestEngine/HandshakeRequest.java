package com.example.myapplication.requestEngine;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.requestEngine.users.User;

import com.vk.sdk.VKObject;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.vk.sdk.api.VKApiConst.FIELDS;

public class HandshakeRequest extends AppCompatActivity {

    private static final String FIELDS_PHOTO = "photo_max";
    public VKList<VKApiUserFull> list;
    public List<Integer> friendsIds ;
    private VKRequest.VKRequestListener listener = new VKRequest.VKRequestListener() {
        @Override
        public void onComplete(VKResponse response) {
            super.onComplete(response);
            JSONArray jsonArray = null;
            try {
                jsonArray = response.json.getJSONObject("response").getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++) {
                    friendsIds.add(jsonArray.getInt(i));
                }
            } catch (JSONException error) {
            }
        }

        @Override
        public void onError(VKError error) {
            super.onError(error);
        }
    };

    public List<Integer> friendRequest(int id) {

        friendsIds = new ArrayList<>();
        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.USER_ID, id));

        request.executeSyncWithListener(listener);

        return friendsIds;
    }

    public Boolean areFriends(Integer startId, Integer finishId){
        List<Integer> friendsList = friendRequest(startId);
        return friendsList.contains(finishId);
    }


    public User getFullInfo(String id) {

        final VKApiUserFull[] userFull = new VKApiUserFull[1];
        VKRequest request;
        if (id.equalsIgnoreCase("")) {
            request = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS,FIELDS_PHOTO));
            //request = VKApi.users().get(VKParameters.from(FIELDS, ));
        } else {
            request = VKApi.users().get(VKParameters.from(FIELDS, FIELDS_PHOTO, "user_ids", id));
        }


        request.executeSyncWithListener(new VKRequest.VKRequestListener() {

            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                VKList vkList = (VKList) response.parsedModel;
                userFull[0] = (VKApiUserFull) vkList.get(0);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });

        User user = new User(userFull[0].photo_max, userFull[0].first_name, userFull[0].last_name, userFull[0].id);
        return user;
    }




    public List<Integer> getMutualFriendsWithFinishUserFriendsList(List<Integer> friendsIds, final Integer startId) {

        final int[] userFull = new int[1];
        VKRequest request;
        final List<Integer> resultMutualFriendId = new ArrayList<>();
        for (final Integer id : friendsIds) {
            request = VKApi.friends().getMutual(VKParameters.from("source_uid", startId, "target_uid", id));

            request.executeSyncWithListener(new VKRequest.VKRequestListener() {

                @Override
                public void onComplete(VKResponse response) {
                    super.onComplete(response);
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = response.json.getJSONArray("response");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (jsonArray.length() > 0) {
                        try {
                            userFull[0] = jsonArray.getInt(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        resultMutualFriendId.add(startId);
                        resultMutualFriendId.add(userFull[0]);
                        resultMutualFriendId.add(id);

                    }

                }

                @Override

                public void onError(VKError error) {
                    super.onError(error);
                }
            });
            if (resultMutualFriendId.size() > 0) {
                return resultMutualFriendId;
            }
        }
        return resultMutualFriendId;
    }

    public List<Integer> getMutualFriends(final Integer startId, final Integer finishId) {

        final int[] userFull = new int[1];
        VKRequest request;
        final List<Integer> resultMutualFriendId = new ArrayList<>();
        request = VKApi.friends().getMutual(VKParameters.from("source_uid", startId, "target_uid", finishId));

        request.executeSyncWithListener(new VKRequest.VKRequestListener() {

            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.json.getJSONArray("response");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonArray.length() > 0) {
                    try {
                        userFull[0] = jsonArray.getInt(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    resultMutualFriendId.add(startId);
                    resultMutualFriendId.add(userFull[0]);
                    resultMutualFriendId.add(finishId);
                }

            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });
        return resultMutualFriendId;
    }


}