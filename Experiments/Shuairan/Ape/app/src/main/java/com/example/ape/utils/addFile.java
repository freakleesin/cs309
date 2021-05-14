package com.example.ape.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class addFile {

    String filePath = null;
    boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

    public void setFilePath(){
        if (this.hasSDCard) {
            this.filePath = Environment.getExternalStorageDirectory().toString() + File.separator + "WebSocketUrl.txt";
        } else{
            this.filePath = Environment.getDownloadCacheDirectory().toString() + File.separator + "WebSocketUrl.txt";
        }
    }

    public addFile(){
        setFilePath();
    }

    public String getFilePath(){
        return filePath;
    }

    public boolean addFile(String string, String path) {
        PrintStream stream = null;

        try {
            stream=new PrintStream(path);
            stream.print(string);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String readToString(String fileName){
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}
