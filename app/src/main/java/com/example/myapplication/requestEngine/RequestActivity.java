package com.example.myapplication.requestEngine;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RequestActivity extends AppCompatActivity {

    final String[] scope = new String[]{VKScope.WALL, VKScope.PHOTOS};
   // String text;
    ArrayList items = new ArrayList();
    // private static final android.app.Activity Activity = runningActivity ;

   // public void Click(){


    //}
    private String text;
    public String[] getScope() {
        return scope;
    }

    public String getText() {
        return text;
    }

    public ArrayList getItems() {
        return items;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        //VKSdk.initialize(this);
        VKSdk.login(this, scope);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(final VKAccessToken res) {
               // Пользователь успешно авторизовался
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Успешная!", Toast.LENGTH_SHORT);
            }
            @Override
            public void onError(VKError error) {
// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Нет!", Toast.LENGTH_SHORT);
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
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
                //  String s;
                for(int i = 0;i<list.getCount();i++) {
                    setText(list.get(i).fields.toString()); //list.get(i).fields.toString();

                    //setText("hui");
                }
                        /*//text = response.responseString;
                        JSONObject item;
                        item = response.json;
                        //ArrayList list = new ArrayList();
                        JSONObject count = null ;
                        try {
                            count = item.getJSONObject("response");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //text = item.toString();
                        int str = 0;
                        try {
                            str = count.getInt("count");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        JSONArray array = null;
                        try {
                            array = count.getJSONArray("items");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ArrayList list = new ArrayList();
                        //JSONArray jsonArray = (JSONArray)jsonObject;
                        if (array != null) {
                            int len = array.length();
                            for (int i=0;i<len;i++){
                                try {
                                    list.add(array.get(i).toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        for (Object temp :list){
                        text += "https://vk.com/id"+String.valueOf(temp)+" ";
                        }
                        //text = count;

                        //JSONObject items = response.json;*/
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });
        //VKApi.friends().get(params).executeWithListener();

    }
    public void Click(View view) {
        TextView myTextView = findViewById(R.id.myText);
        friendRequest();
        myTextView.setText(getText());

    }
}

