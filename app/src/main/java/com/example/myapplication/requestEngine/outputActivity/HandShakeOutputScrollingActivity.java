package com.example.myapplication.requestEngine.outputActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myapplication.settings.BGChanger;
import com.example.myapplication.FontChanger;
import com.example.myapplication.R;
import com.example.myapplication.WorkWithFile;
import com.example.myapplication.requestEngine.HandshakeRequest;
import com.example.myapplication.requestEngine.InputUserInfoActivity;
import com.example.myapplication.requestEngine.inputOutput.Output;
import com.example.myapplication.requestEngine.transformation.CircularTransformation;
import com.example.myapplication.requestEngine.users.User;
import com.example.myapplication.settings.MusicState;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HandShakeOutputScrollingActivity extends AppCompatActivity implements View.OnClickListener {
    private HandshakeRequest handshakeRequest = new HandshakeRequest();
    TextView firstUserEditTextName, secondUserEditTextName, thirdUserEditTextName, fouthUserEditTextName, fiveUserEditTextName;
    ImageView firstUserImageView, secondUserImageView, thirdUserImageView, fouthUserImageView, fiveUserImageView,backgroundHouses;
    WorkWithFile workWithFile = new WorkWithFile();
    String font,bgColor,musicState;
    List<Integer> vkId = new ArrayList<>();
    List<ImageView> imageViews = new ArrayList<>();
    List<TextView> textViews = new ArrayList<>();
    List<Integer> fake = new ArrayList<>();
    ScrollView scrollView;
    MediaPlayer mp = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_shake_output_scrolling);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        scrollView = findViewById(R.id.background);
        backgroundHouses = findViewById(R.id.backgroundHouses);
        firstUserEditTextName = findViewById(R.id.firstUserEditTextName);
        textViews.add(firstUserEditTextName);
        secondUserEditTextName = findViewById(R.id.secondUserEditTextName);
        textViews.add(secondUserEditTextName);
        thirdUserEditTextName = findViewById(R.id.thirdUserEditTextName);
        textViews.add(thirdUserEditTextName);
        fouthUserEditTextName = findViewById(R.id.fouthUserEditTextName);
        textViews.add(fouthUserEditTextName);
        fiveUserEditTextName = findViewById(R.id.fiveUserEditTextName);
        textViews.add(fiveUserEditTextName);

        firstUserImageView = findViewById(R.id.firstUserImageView);
        imageViews.add(firstUserImageView);
        secondUserImageView = findViewById(R.id.secondUserImageView);
        imageViews.add(secondUserImageView);
        thirdUserImageView = findViewById(R.id.thirdUserImageView);
        imageViews.add(thirdUserImageView);
        fouthUserImageView = findViewById(R.id.fouthUserImageView);
        imageViews.add(fouthUserImageView);
        fiveUserImageView = findViewById(R.id.fiveUserImageView);
        imageViews.add(fiveUserImageView);
        outData(imageViews, textViews);
        for (ImageView iv:imageViews) {
           iv.setOnClickListener(this);
        }
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            FontChanger.changeTheFont(font,textViews,this);
        }
        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null) {
            BGChanger.bgForTextViews(bgColor, textViews);
            BGChanger.ChangeScrollViewBG(bgColor,scrollView);
        }
        musicState = workWithFile.ReadFromFile(workWithFile.getToggleFileName(),getApplicationContext());
        if (musicState != null ){
            mp = MusicState.setPlayer(musicState,mp,getApplicationContext());
        }
    }

    public void outData(List<ImageView> imageView, List<TextView> textViewName) {
        vkId = workWithFile.ReadListFromFile(workWithFile.getVkIdList(),getApplicationContext());
        Output output = new Output();
        for (int i=0;i<vkId.size();i++){
            imageView.get(i).setVisibility(View.VISIBLE);
            textViewName.get(i).setVisibility(View.VISIBLE);
            User user = handshakeRequest.getFullInfo(output.cutUrl(vkId.get(i).toString()));
            String fullName = " " + user.getFirstName() + " " + user.getLastName() + " ";
            textViewName.get(i).setText(fullName);

            Picasso.with(getApplicationContext())
                      .load(user.getPhotoUrl())
                      .transform(new CircularTransformation(0))
                      .into(imageView.get(i));

        }
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, UserDescriptionActivity.class);
        switch (v.getId()){
            case R.id.startUserButton:
                mp.start();
                Intent intent1 = new Intent(this, InputUserInfoActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);
                break;
                case R.id.firstUserImageView:
                    mp.start();
                    workWithFile.WriteToFile(workWithFile.getChoiceUserID(),vkId.get(0).toString(),this);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);
                    break;
                case R.id.secondUserImageView:
                    mp.start();
                    workWithFile.WriteToFile(workWithFile.getChoiceUserID(),vkId.get(1).toString(),this);
                startActivity(intent);
                    overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);
                break;
                case R.id.thirdUserImageView:
                    mp.start();
                    workWithFile.WriteToFile(workWithFile.getChoiceUserID(),vkId.get(2).toString(),this);
                startActivity(intent);
                    overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);
                break;
                case R.id.fouthUserImageView:
                    mp.start();
                    workWithFile.WriteToFile(workWithFile.getChoiceUserID(),vkId.get(3).toString(),this);
                startActivity(intent);
                    overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);
                break;
        }

    }



    @Override
    protected void onRestart() {
        super.onRestart();
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            FontChanger.changeTheFont(font,textViews,this);
        }
        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.bgForTextViews(bgColor,textViews);
            BGChanger.ChangeScrollViewBG(bgColor,scrollView);
        }

    }
}
