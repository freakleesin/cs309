package com.example.ape.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.ape.PersonalCenterActivity;

public class Util {
    private static addFile createws = new addFile();
    //public static final String ws = "ws://echo.websocket.org";
    public static final String ws = "ws://echo.websocket.org";
    //public static final String ws = createws.readToString("WebSocketUrl.txt");//websocket test url

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
