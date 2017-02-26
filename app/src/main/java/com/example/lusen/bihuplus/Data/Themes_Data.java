package com.example.lusen.bihuplus.Data;

import java.util.ArrayList;

/**
 * Created by lusen on 2017/2/23.
 */

public class Themes_Data {
    private int limit;
    private String [] subscribed;
    private ArrayList<Others> others;

    public class Others{
        private int color;
        private int id;
        private String thumbnail;
        private String description;
        private String name;

        public int getColor(){return color;}
        public int getId(){return id;}
        public String getThumbnail(){return thumbnail;}
        public String getDescription(){return description;}
        public String getName(){return name;}

        public void setColor(int color){
            this.color = color;
        }
        public void setId(int id){
            this.id = id;
        }
        public void setThumbnail(String thumbnail){
            this.thumbnail = thumbnail;
        }
        public void setDescription(String description){
            this.description = description;
        }
        public void setName(String name){
            this.name = name;
        }
    }

    public int getLimit(){return limit;}
    public String[] getSubscribed(){return subscribed;}
    public ArrayList<Others> getOthers(){return others;}

    public void setLimit(int limit){this.limit = limit;}
    public void setSubscribed(String [] subscribed){this.subscribed = subscribed;}
    public void setOthers(ArrayList<Others> others){this.others = others;}



}
