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

    final String fontsFileName = "font";
    String font;

    public String getFontsFileName() {
        return fontsFileName;
    }

    public void WriteToFile(String fileName, String text, Context context) {

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



        public String ReadFromFile (String fontsFileName, Context context){
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(
                                context.openFileInput(fontsFileName)));

                        font = br.readLine();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return font;
                }
            }
