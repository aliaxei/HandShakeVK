package com.example.myapplication.requestEngine.outputActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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

public class UserDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    TextView userName, textSurname, textLink,textOnButton;
    ImageView userImage;
    Button backToMap;
    private HandshakeRequest handshakeRequest = new HandshakeRequest();
    WorkWithFile workWithFile = new WorkWithFile();
    String font,musicState,bgColor,userId;
    List<TextView> textViews = new ArrayList<>();
    List<Button> buttons = new ArrayList<>();
    ConstraintLayout constraintLayout;
    MediaPlayer mp = new MediaPlayer();
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_description);
        constraintLayout =  findViewById(R.id.background);
        userName = findViewById(R.id.textName);
        textViews.add(userName);
        textSurname = findViewById(R.id.textSurname);
        textViews.add(textSurname);
        textLink = findViewById(R.id.textLink);
        textViews.add(textLink);
        textOnButton = findViewById(R.id.buttonText);
        textViews.add(textOnButton);
        userImage = findViewById(R.id.userImage);
        backToMap = findViewById(R.id.backToMap);
        buttons.add(backToMap);
        backToMap.setOnClickListener(this);
        FillUserForm();


        musicState = workWithFile.ReadFromFile(workWithFile.getToggleFileName(),getApplicationContext());
        if (musicState != null ){
            mp = MusicState.setPlayer(musicState,mp,getApplicationContext());
        }
        bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
        if (bgColor != null){
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
            BGChanger.bgForTextViews(bgColor,textViews);
            BGChanger.bgForButtons(bgColor,buttons);

        }
        musicState = workWithFile.ReadFromFile(workWithFile.getToggleFileName(),getApplicationContext());
        if(musicState != null){
            mp = MusicState.setPlayer(musicState,mp,getApplicationContext());
        }
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            FontChanger.changeTheFont(font,textViews,this);
        }
    }

    public void FillUserForm(){
        userId = workWithFile.ReadFromFile(workWithFile.getChoiceUserID(),this);
        Output output = new Output();
        User user = handshakeRequest.getFullInfo(output.cutUrl(userId));
        Picasso.with(getApplicationContext())
                .load(user.getPhotoUrl())
                .transform(new CircularTransformation(0))
                .into(userImage);
        String firstName = "Name: " + user.getFirstName();
        userName.setText(firstName);
        String lastName = "Surname: " + user.getLastName();
        textSurname.setText(lastName);
        textLink.setText(" https://vk.com/id"+ userId);
        textLink.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backToMap:

                Boolean flag;
                bgColor = workWithFile.ReadFromFile(workWithFile.getBgFileName(),getApplicationContext());
                if (bgColor != null) {
                    flag = BGChanger.ChangeView(bgColor);
                    if (flag == true){
                        intent = new Intent(this, HandShakeOutputScrollingActivity.class);
                    }else {
                        intent = new Intent(this, OutputForPWOS.class);
                    }
                }
                mp.start();
                startActivity(intent);
                overridePendingTransition(R.anim.activity_animation, R.anim.alpha_animation);
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
            BGChanger.ChangeTheBG(bgColor,constraintLayout);
            BGChanger.bgForTextViews(bgColor,textViews);
            BGChanger.bgForButtons(bgColor,buttons);
        }
    }
}
