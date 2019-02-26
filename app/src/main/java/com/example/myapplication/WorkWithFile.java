package com.example.myapplication;

import android.content.Context;

import com.example.myapplication.settings.Fonts;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;

public class WorkWithFile {
    final String FILENAME = "font";
    public void WriteToFile(Fonts fonts,Context context){
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    context.openFileOutput(FILENAME, MODE_PRIVATE)));
            bw.write(fonts.toString());
            bw.close();
            //sheva hui

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ReadFromFile(){

    }
}
