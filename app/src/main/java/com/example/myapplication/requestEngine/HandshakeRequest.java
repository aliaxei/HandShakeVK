package com.example.myapplication.requestEngine;

import android.support.v7.app.AppCompatActivity;

import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;

import java.util.ArrayList;

public class HandshakeRequest extends AppCompatActivity {

    private String[] scope ;//= new String[]{VKScope.WALL, VKScope.PHOTOS};
    private String text;
    private ArrayList items ;


    public String[] getScope() {
        return scope;
    }

    public String getText() {
        return text;
    }

    public ArrayList getItems() {
        return items;
    }

    public void setScope(String[] scope) {
        this.scope = scope;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setItems(ArrayList items) {
        this.items = items;
    }

    public void Login(){
        VKSdk.login(this, scope);
    }

    public void friendRequest(){
        //Toast toast = new Toast(getApplicationContext());
               /* Toast toast = Toast.makeText(getApplicationContext(),
                        "Успешная!", Toast.LENGTH_SHORT);
                toast.show();*/
                /*VKParameters params = new VKParameters();
                params.put(VKApiConst.FIELDS,VKApiUserFull);*/
        //VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,"counters","user_id", 35509985));
        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,"sex"));

        request.executeWithListener(new VKRequest.VKRequestListener() {

            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                VKList list =  (VKList) response.parsedModel;
                String s;
               for(int i = 0;i<list.getCount();i++) {
                   setText(list.get(i).fields.toString()); //list.get(i).fields.toString();

                   // setText("hui");
                }


            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });
        //VKApi.friends().get(params).executeWithListener();

    }



}

