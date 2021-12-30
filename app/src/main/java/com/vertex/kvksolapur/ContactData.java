package com.vertex.kvksolapur;

import java.util.ArrayList;

public class ContactData {

    String name;
    String image;
    String discription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public static ArrayList<ContactData> data=new ArrayList<>();
}
