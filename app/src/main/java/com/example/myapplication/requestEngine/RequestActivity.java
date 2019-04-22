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

    public void Click(View view) {
        HandshakeRequest handshakeRequest = new HandshakeRequest();
        TextView myTextView = findViewById(R.id.myText);
        handshakeRequest.friendRequest();
        myTextView.setText(handshakeRequest.getText());

    }
}

