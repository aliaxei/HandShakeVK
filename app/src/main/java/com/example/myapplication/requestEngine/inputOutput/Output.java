package com.example.myapplication.requestEngine.inputOutput;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Output extends AppCompatActivity {

    private static final String REGEX = "id";
    private static final String STRWITHID = "https://vk.com/id";
    private static final String STRWITOUTHID = "https://vk.com/";
    private static String REPLACE = "";



    public String cutUrl(String url){

        String result = "";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(url);
        try {
            if (matcher.lookingAt()) {
                Pattern p = Pattern.compile(STRWITHID);

                // получение matcher объекта
                Matcher m = p.matcher(url);
                result = m.replaceAll(REPLACE);
            } else {
                Pattern p = Pattern.compile(STRWITOUTHID);
                // получение matcher объекта
                Matcher m = p.matcher(url);
                result = m.replaceAll(REPLACE);
            }
        }catch (IllegalStateException e){
            Toast.makeText(this, "Please, input correct ID", Toast.LENGTH_LONG).show();
        }
        return result;
    }
}

