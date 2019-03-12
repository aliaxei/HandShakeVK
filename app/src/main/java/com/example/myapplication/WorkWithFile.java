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
<<<<<<< HEAD
    final String fontsFileName = "font";
    String font;

    public String getFontsFileName() {
        return fontsFileName;
    }

    public void WriteToFile(String fileName,String text,Context context){
=======
    final String FILENAME = "font";
    String font;
    public void WriteToFile(Fonts fonts,Context context){
>>>>>>> 97d7e4dc936746a5e05866d8f2b273a58f2439e8
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    context.openFileOutput(fileName, MODE_PRIVATE)));
            bw.write(text);
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
<<<<<<< HEAD
    public String ReadFromFile(String fileName,Context context){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    context.openFileInput(fileName)));
=======
    public String ReadFromFile(Context context){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    context.openFileInput(FILENAME)));
>>>>>>> 97d7e4dc936746a5e05866d8f2b273a58f2439e8
            font = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return font;
    }
}
