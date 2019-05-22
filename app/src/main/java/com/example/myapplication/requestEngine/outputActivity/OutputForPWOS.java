package com.example.myapplication.requestEngine.outputActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.settings.BGChanger;
import com.example.myapplication.FontChanger;
import com.example.myapplication.R;
import com.example.myapplication.WorkWithFile;
import com.example.myapplication.requestEngine.HandshakeRequest;
import com.example.myapplication.requestEngine.inputOutput.Output;
import com.example.myapplication.requestEngine.transformation.CircularTransformation;
import com.example.myapplication.requestEngine.users.User;
import com.example.myapplication.settings.MusicState;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OutputForPWOS extends AppCompatActivity implements View.OnClickListener {
    ConstraintLayout constraintLayout;
    private HandshakeRequest handshakeRequest = new HandshakeRequest();
    TextView firstEditTextName, secondEditTextName, thirdEditTextName, fouthEditTextName, fiveEditTextName;
    ImageView firstImageView, secondImageView, thirdImageView, fouthImageView;
    WorkWithFile workWithFile = new WorkWithFile();
    String font,bgColor,musicState;
    MediaPlayer mp = new MediaPlayer();
    List<Integer> vkId = new ArrayList<>();
    List<ImageView> imageViews = new ArrayList<>();
    List<TextView> textViews = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_output_for_pwos);

        constraintLayout = findViewById(R.id.background);
        firstEditTextName = findViewById(R.id.firstEditTextName);
        textViews.add(firstEditTextName);
        secondEditTextName = findViewById(R.id.secondEditTextName);
        textViews.add(secondEditTextName);
        thirdEditTextName = findViewById(R.id.thirdEditTextName);
        textViews.add(thirdEditTextName);
        fouthEditTextName = findViewById(R.id.fourthEditTextName);
        textViews.add(fouthEditTextName);

        firstImageView = findViewById(R.id.firstImageView);
        imageViews.add(firstImageView);
        secondImageView = findViewById(R.id.secondImageView);
        imageViews.add(secondImageView);
        thirdImageView = findViewById(R.id.thirdImageView);
        imageViews.add(thirdImageView);
        fouthImageView = findViewById(R.id.fourthImageView);
        imageViews.add(fouthImageView);
        outData(imageViews, textViews);
        for (ImageView iv:imageViews) {
            iv.setOnClickListener(this);
        }


        musicState = workWithFile.ReadFromFile(workWithFile.getToggleFileName(),getApplicationContext());
        if (musicState != null ){
            mp = MusicState.setPlayer(musicState,mp,getApplicationContext());
        }

        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
            BGChanger.bgForTextViews(bgColor,textViews);
        }
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            FontChanger.changeTheFont(font,textViews,this);
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
            case R.id.firstImageView:
                mp.start();
                workWithFile.WriteToFile(workWithFile.getChoiceUserID(),vkId.get(0).toString(),this);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);
                break;
            case R.id.secondImageView:
                mp.start();
                workWithFile.WriteToFile(workWithFile.getChoiceUserID(),vkId.get(1).toString(),this);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);
                break;
            case R.id.thirdImageView:
                mp.start();
                workWithFile.WriteToFile(workWithFile.getChoiceUserID(),vkId.get(2).toString(),this);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_animation,R.anim.alpha_animation);
                break;
            case R.id.fourthImageView:
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
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
        }
    }
}
