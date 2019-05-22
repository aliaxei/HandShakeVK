package com.example.myapplication;

import android.content.Context;

import com.example.myapplication.settings.Fonts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class WorkWithFile {
    final String choiceUserID = "choiceUserID";
    final String fontsFileName = "font";
    final String bgFileName = "bgColors";
    final String toggleFileName = "toggle";
    final String vkIdList = "idList";
    final String switchState = "swState";

    String subject;
    String temp;
    List<Integer> vkIds = new ArrayList<>();
    public String getSwitchState() {
        return switchState;
    }
    public String getVkIdList() {
        return vkIdList;
    }
    public String getToggleFileName() {
        return toggleFileName;
    }
    public String getBgFileName() {
        return bgFileName;
    }
    public String getFontsFileName() {
        return fontsFileName;
    }
    public String getChoiceUserID() {
        return choiceUserID;
    }

    public void WriteToFile(String fileName,String text,Context context){
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
    public String ReadFromFile(String fileName,Context context){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    context.openFileInput(fileName)));
            subject = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subject;
    }


    public void WriteListToFile(String fileName,List<Integer> id, Context context){
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    context.openFileOutput(fileName, MODE_PRIVATE)));
            for (Integer ids :id) {
                bw.write(ids.toString());
                bw.newLine();
            }
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Integer> ReadListFromFile(String fileName, Context context){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    context.openFileInput(fileName)));
while ((temp = br.readLine()) != null){
    vkIds.add(Integer.parseInt(temp));
}




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vkIds;
    }
}
