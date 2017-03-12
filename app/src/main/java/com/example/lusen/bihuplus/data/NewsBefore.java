package com.example.lusen.bihuplus.data;

import java.util.List;

/**
 * Created by lusen on 2017/2/17.
 */

public class NewsBefore {
    private String date;
    private List<News.Stories> stories;

    public static class Stories{
        private String[] images;
        private int type;
        private String id;
        private String ga_prefix;
        private String title;

        public String[] getImages(){
            return images;
        }
        public int getType(){
            return type;
        }
        public String getId(){
            return id;
        }
        public String getGa_prefix(){
            return ga_prefix;
        }
        public String getTitle(){
            return title;
        }
        public void setImages(String[] images){
            this.images = images;
        }
        public void setType(int type){
            this.type = type;
        }
        public void setId(String id){
            this.id = id;
        }
        public void setGa_prefix(String ga_prefix){
            this.ga_prefix = ga_prefix;
        }
        public void setTitle(String title){
            this.title = title;
        }
    }

    public String getDate(){
        return date;
    }
    public List<News.Stories> getStories(){
        return stories;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setStories(List<News.Stories> stories){
        this.stories = stories;
    }
}
