package com.example.lusen.bihuplus.data;

import java.util.List;

/**
 * Created by lusen on 2017/1/18.
 */

public class News {
    private String date;
    private List<Stories> stories;
    private List<Top_stories> top_stories;

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

    public static class Top_stories{
        private String image;
        private int type;
        private String id;
        private String ga_prefix;
        private String title;

        public String getImages(){
            return image;
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
        public void setImages(String image){
            this.image = image;
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
    public List<Stories> getStories(){
        return stories;
    }
    public List<Top_stories> getTop_stories(){
        return top_stories;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setStories(List<Stories> stories){
        this.stories = stories;
    }
    public void setTop_stories(List<Top_stories> top_stories){
        this.top_stories = top_stories;
    }
}
