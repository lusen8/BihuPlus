package com.example.lusen.bihuplus.data;

/**
 * Created by lusen on 2017/2/14.
 */

public class FirstPagerData {
    private String title;
    private String imageId;
    private String imageHref;
    private String image_theme;

    public FirstPagerData(String title){
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle(){
        return title;
    }
    public String getImagId(){
        return imageId;
    }
    public String getImageHref(){
        return imageHref;
    }
    public String getImage_theme(){
        return image_theme;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setImage_theme(String image_theme){
        this.image_theme = image_theme;
    }
    public void setImagId(String imageId){
        this.imageId = imageId;
    }
    public void setImageHref(String imageHref){
        this.imageHref = imageHref;
    }

}
