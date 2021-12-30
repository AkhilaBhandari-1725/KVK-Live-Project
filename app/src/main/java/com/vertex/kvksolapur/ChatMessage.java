package com.vertex.kvksolapur;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ChatMessage {

    String message[],rmessage;

    Bitmap image;

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getRmessage() {
        return rmessage;
    }

    public void setRmessage(String rmesage) {
        this.rmessage = rmessage;
    }

    public static ArrayList<ChatMessage> data=new ArrayList<ChatMessage>();
}
