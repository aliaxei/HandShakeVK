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
import java.util.concurrent.CountDownLatch;

import static com.vk.sdk.api.VKApiConst.FIELDS;

public class HandshakeRequest extends AppCompatActivity {

    private static final String FIELDS_PHOTO = "photo_50";
    public VKList<VKApiUserFull> list ;
    public int[] friendsIds;
    private VKRequest.VKRequestListener listener = new VKRequest.VKRequestListener() {
        @Override
        public void onComplete(VKResponse response) {
            super.onComplete(response);
            JSONArray jsonArray = null;
            try {
                jsonArray = response.json.getJSONObject("response").getJSONArray("items");
                int len = jsonArray.length();
                int[] resultIds = new int[len];
                for (int i = 0; i < len; i++)
                    resultIds[i] = jsonArray.getInt(i);
                friendsIds = resultIds;
            } catch (JSONException e) {
                //e.printStackTrace();
                friendsIds = new int[0];
            }
            //list.clear();
        }

        @Override
        public void onError(VKError error) {
            super.onError(error);
            friendsIds = new int[0];
        }
    };
    private String sex = "sex";
    private String user_id = "user_id";
    ;

    public int[] friendRequest(int id){

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.USER_ID,id));

        request.executeSyncWithListener(listener);

        return friendsIds;
    }


    public User getFullInfo(String id) {

        final VKApiUserFull[] userFull = new VKApiUserFull[1];
        VKRequest request;
        if(id.equalsIgnoreCase("")){
            request = VKApi.users().get(VKParameters.from(FIELDS, FIELDS_PHOTO)); }
        else{ request = VKApi.users().get(VKParameters.from(FIELDS, FIELDS_PHOTO,"user_ids",id)); }


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

        User user = new User(userFull[0].photo_50,userFull[0].first_name,userFull[0].last_name,userFull[0].id);
        return user;
    }





}


