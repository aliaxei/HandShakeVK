package com.example.myapplication;

import android.content.Context;

import com.example.myapplication.settings.Fonts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;

public class WorkWithFile {
    final String FILENAME = "font";
    String font;
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
    public String ReadFromFile(Context context){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    context.openFileInput(FILENAME)));
            font = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return font;
    }
}
