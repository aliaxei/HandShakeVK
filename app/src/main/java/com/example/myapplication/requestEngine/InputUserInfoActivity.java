package com.example.myapplication.requestEngine;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication.BGChanger;
import com.example.myapplication.FontChanger;
import com.example.myapplication.WorkWithFile;
import com.example.myapplication.R;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication.requestEngine.inputOutput.Output;
import com.example.myapplication.requestEngine.outputActivity.HandShakeOutputScrollingActivity;
import com.example.myapplication.requestEngine.transformation.CircularTransformation;
import com.example.myapplication.requestEngine.users.User;
import com.example.myapplication.settings.MusicState;
import com.squareup.picasso.Picasso;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import java.util.ArrayList;
import java.util.List;


public class InputUserInfoActivity extends AppCompatActivity {

    private final String[] scope = new String[]{VKScope.WALL, VKScope.PHOTOS};
    private ArrayList items = new ArrayList();
    WorkWithFile workWithFile;
    ConstraintLayout constraintLayout;
    MediaPlayer mediaPlayer = new MediaPlayer();
    private HandshakeRequest handshakeRequest = new HandshakeRequest();
    public int startID;
    public int finishID;
    String musicState, font,bgColor;
    ImageView startUserImageView, finishUserImageView;
    TextView startUserEditTextName,startUserEditTextID,finishUserEditTextName,finishUserEditTextID;
    List<TextView> textViews = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_input_user_info);
        constraintLayout =  findViewById(R.id.background);
        workWithFile = new WorkWithFile();
        startUserImageView = findViewById(R.id.startUserImageView);
        startUserEditTextName = findViewById(R.id.startUserEditTextName);
        textViews.add(startUserEditTextName);
        startUserEditTextID = findViewById(R.id.startUserEditTextID);
        textViews.add(startUserEditTextID);
        finishUserImageView = findViewById(R.id.finishUserImageView);
        finishUserEditTextName = findViewById(R.id.finishUserEditTextName);
        textViews.add(finishUserEditTextName);
        finishUserEditTextID = findViewById(R.id.finishUserEditTextID);
        textViews.add(finishUserEditTextID);
        VKSdk.login(this, scope);


        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
        }
        musicState = workWithFile.ReadFromFile(workWithFile.getToggleFileName(),getApplicationContext());
        if(musicState != null){
            mediaPlayer = MusicState.setPlayer(musicState,mediaPlayer,getApplicationContext());
        }
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            FontChanger.changeTheFont(font,textViews,this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(final VKAccessToken res) {
                // Пользователь успешно авторизовался
                //handshakeRequest.SavePhoto(1);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Успешная авторизация!", Toast.LENGTH_SHORT);
                toast.show();
                ImageView startUserImageView = findViewById(R.id.startUserImageView);
                //String url = handshakeRequest.GetUrl();
                //Picasso.with(getApplicationContext()).load(handshakeRequest.GetUrl()).into(startUserImageView);
            }
            @Override
            public void onError(VKError error) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ошибка авторизации!", Toast.LENGTH_SHORT);
                toast.show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void Click(View view) {
        mediaPlayer.start();
        outData(finishUserImageView,finishUserEditTextName,finishUserEditTextID,0);

    }
    public void clickForText(View view) {
        mediaPlayer.start();
        outData(startUserImageView,startUserEditTextName,startUserEditTextID,1);
    }

    public void outData(ImageView imageView,TextView textViewName,TextView textViewId,int flag){
        Output output = new Output();
        try {
           User user = handshakeRequest.getFullInfo(output.cutUrl(textViewId.getText().toString()));
           Picasso.with(getApplicationContext())
                    .load(user.getPhotoUrl())
                    .transform(new CircularTransformation(0))
                    .into(imageView);
            String fullName = user.getFirstName()+" "+user.getLastName();
            textViewName.setText(fullName);
            textViewId.setText(String.valueOf(user.getId()));
            if(flag==1){
                startID =user.getId();
            }else{
                finishID = user.getId();
            }
        }catch (NullPointerException e){
            Toast.makeText(this, "Please, input correct ID", Toast.LENGTH_LONG).show();
        }


    }

    public void startSearch(View view) {
        mediaPlayer.start();
        DataProcess dataProcess = new DataProcess();
//        dataProcess.mainProc(startID,finishID);
        List<Integer> way = dataProcess.bestAlgorithmEver(startID, finishID);

        StringBuilder wayString = new StringBuilder();
        for (int i = 0; i<way.size(); i++){
            wayString.append(way.get(i)).append(", ");
        }

        Toast.makeText(this, wayString.toString(), Toast.LENGTH_LONG).show();
        System.out.println(wayString);
        Intent intent = new Intent(this, HandShakeOutputScrollingActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            FontChanger.changeTheFont(font,textViews,this);
        }
        if(musicState != null){
            mediaPlayer = MusicState.setPlayer(musicState,mediaPlayer,getApplicationContext());
        }
        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
        }
    }
}

