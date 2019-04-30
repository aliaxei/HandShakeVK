package com.example.myapplication.requestEngine.outputActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.FontChanger;
import com.example.myapplication.R;
import com.example.myapplication.WorkWithFile;
import com.example.myapplication.requestEngine.HandshakeRequest;
import com.example.myapplication.requestEngine.inputOutput.Output;
import com.example.myapplication.requestEngine.transformation.CircularTransformation;
import com.example.myapplication.requestEngine.users.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HandShakeOutputScrollingActivity extends AppCompatActivity implements View.OnTouchListener {
    private HandshakeRequest handshakeRequest = new HandshakeRequest();
    TextView firstUserEditTextName, secondUserEditTextName, thirdUserEditTextName, fouthUserEditTextName, fiveUserEditTextName;
    ImageView firstUserImageView, secondUserImageView, thirdUserImageView, fouthUserImageView, fiveUserImageView;
    Button button;
    WorkWithFile workWithFile = new WorkWithFile();
    public int startID;
    public int finishID;
    String font;
    public String id = "261852819";
    List<ImageView> imageViews = new ArrayList<>();
    List<TextView> textViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_shake_output_scrolling);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        button = findViewById(R.id.startUserButton);
        button.setOnTouchListener(this);
        firstUserEditTextName = findViewById(R.id.firstUserEditTextName);
        textViews.add(firstUserEditTextName);
        secondUserEditTextName = findViewById(R.id.secondUserEditTextName);
        textViews.add(secondUserEditTextName);
        thirdUserEditTextName = findViewById(R.id.thirdUserEditTextName);
        textViews.add(thirdUserEditTextName);
        fouthUserEditTextName = findViewById(R.id.fourthUserEditTextName);
        textViews.add(fouthUserEditTextName);
        fiveUserEditTextName = findViewById(R.id.fiveUserEditTextName);
        textViews.add(fiveUserEditTextName);

        firstUserImageView = findViewById(R.id.firstUserImageView);
        imageViews.add(firstUserImageView);
        secondUserImageView = findViewById(R.id.secondUserImageView);
        imageViews.add(secondUserImageView);
        thirdUserImageView = findViewById(R.id.thirdUserImageView);
        imageViews.add(thirdUserImageView);
        fouthUserImageView = findViewById(R.id.fourthUserImageView);
        imageViews.add(fouthUserImageView);
        fiveUserImageView = findViewById(R.id.fiveUserImageView);
        imageViews.add(fiveUserImageView);

        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            FontChanger.changeTheFont(font,textViews,this);
        }
    }

    public void outData(List<ImageView> imageView, List<TextView> textViewName) {

        Output output = new Output();
        User user = handshakeRequest.getFullInfo(output.cutUrl(id));
        for (TextView tw : textViewName) {
            String fullName = user.getFirstName() + " " + user.getLastName();
            tw.setText(fullName);
        }
        for (ImageView iw : imageView) {
            Picasso.with(getApplicationContext())
                    .load(user.getPhotoUrl())
                    .transform(new CircularTransformation(0))
                    .into(iw);
        }


    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.startUserButton:
                outData(imageViews,textViews);
        }

        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        font = workWithFile.ReadFromFile(workWithFile.getFontsFileName(),getApplicationContext());
        if (font != null) {
            FontChanger.changeTheFont(font,textViews,this);
        }
    }
}
