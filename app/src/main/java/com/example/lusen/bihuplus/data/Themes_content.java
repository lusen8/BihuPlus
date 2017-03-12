package com.example.lusen.bihuplus.data;

import java.util.ArrayList;

/**
 * Created by lusen on 2017/2/23.
 */

public class Themes_content {
    private ArrayList<Stories> stories;
    private String description;
    private String name;
    private int color;
    private String background;
    private ArrayList<Editors> editors;

    public class Stories{
        private String [] images;
        private int type;
        private String id;
        private String title;

        public String [] getImages(){
            return images;
        }
        public int getType(){
            return type;
        }
        public String getId(){
            return id;
        }
        public String getTitle(){
            return title;
        }
        public void setImages(String [] images){
            this.images = images;
        }
        public void setType(int type){
            this.type = type;
        }
        public void setId(String id){
            this.id = id;
        }
        public void setTitle(String title){
            this.title = title;
        }
    }

    public class Editors{
        private String url;
        private String bio;
        private String avatar;
        private int id;
        private String name;

        public String getUrl(){
            return url;
        }
        public String getBio(){
            return bio;
        }
        public String getAvatar(){
            return avatar;
        }
        public int getId(){
            return id;
        }
        public String getName(){
            return name;
        }

        public void setUrl(String url){
            this.url = url;
        }
        public void setBio(String bio){
            this.bio = bio;
        }
        public void setAvatar(String avatar){
            this.avatar = avatar;
        }
        public void setId(int id){
            this.id = id;
        }
        public void setName(String name){
            this.name = name;
        }

    }

    public ArrayList<Stories> getStories(){
        return stories;
    }
    public String getDescription(){
        return description;
    }
    public String getName(){
        return name;
    }
    public int getBackground(){
        return color;
    }
    public ArrayList<Editors> getEditors(){
        return editors;
    }

    public void setStories(ArrayList<Stories> stories){
        this.stories = stories;
    }
    public void setEditors(ArrayList<Editors> editors){
        this.editors = editors;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setBackground(String background){
        this.background = background;
    }
    public void setColor(int color){
        this.color = color;
    }

}
