package com.example.lusen.bihuplus.data;

import java.util.ArrayList;

/**
 * Created by lusen on 2017/2/8.
 */

public class WelcomeData {
    private ArrayList<Creatives> creatives;

    public class Creatives{
        private String url;
        private String text;
        private long start_time;
        private String [] impression_tracks;
        private int type;
        private String id;

        public String getUrl(){
            return url;
        }
        public String getText(){
            return text;
        }
        public long getStart_time (){
            return start_time;
        }
        public String [] getImpression_tracks(){
            return impression_tracks;
        }
        public int getType(){
            return type;
        }
        public String getId(){
            return id;
        }

        public void setText(String text){
            this.text = text;
        }
        public void setImpression_tracks(String [] impression_tracks){
            this.impression_tracks = impression_tracks;
        }
        public void setType(int type){
            this.type = type;
        }
        public void setId(String id){
            this.id = id;
        }
        public void setUrl(String url){
            this.url = url;
        }
        public void setStart_time(long start_time){
            this.start_time = start_time;
        }
    }
    public void setCreatives(ArrayList<Creatives> creatives){
        this.creatives = creatives;
    }
    public ArrayList<Creatives> getCreatives(){return creatives;}
}

