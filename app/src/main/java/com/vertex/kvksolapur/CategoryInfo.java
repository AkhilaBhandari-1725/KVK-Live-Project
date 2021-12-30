package com.vertex.kvksolapur;

import java.util.ArrayList;

public class CategoryInfo {

    String image;
    String categoryName;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static ArrayList<CategoryInfo> data=new ArrayList<CategoryInfo>();
}
