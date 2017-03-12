package com.example.lusen.bihuplus.data;

import java.util.ArrayList;

/**
 * Created by lusen on 2017/2/15.
 */

public class NewsContent {
    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String [] js;
    private ArrayList<Recommenders> recommenders;
    private String ga_prefix;
    private Section section;
    private int type;
    private int id;
    private String [] css;

    public class Recommenders{
        private String avatar;

        public String getAvatar(){
            return avatar;
        }
        public void setAvatar(String avatar){
            this.avatar = avatar;
        }
    }
    public class Section{
        private String thumbnail;
        private String name;
        private int id;

        public String getThumbnail(){
            return thumbnail;
        }
        public String getName(){
            return name;
        }
        public int getId(){
            return id;
        }


        public void setThumbnail(String thumbnail){
            this.thumbnail = thumbnail;
        }
        public void setName(String name){
            this.name = name;
        }
        public void setId(int id){
            this.id = id;
        }
    }

    public String getBody(){
        return body;
    }
    public String getImage_source(){
        return image_source;
    }
    public String getTitle(){
        return title;
    }
    public String getImage(){
        return image;
    }
    public String getShare_url(){
        return share_url;
    }
    public String[] getJs(){
        return js;
    }
    public ArrayList<Recommenders> getRecommenders(){
        return recommenders;
    }
    public String getGa_prefix(){
        return ga_prefix;
    }
    public Section getSection(){
        return section;
    }
    public int getType(){
        return type;
    }
    public int getId(){
        return id;
    }
    public String [] getCss(){
        return css;
    }

    public void setBody(String body){
        this.body = body;
    }
    public void setImage_source(String image_source){
        this.image_source = image_source;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setImage(String image){
        this.image = image;
    }
    public void setShare_url(String share_url){
        this.share_url = share_url;
    }
    public void setJs(String[] js){
        this.js = js;
    }
    public void setRecommenders(ArrayList<Recommenders> recommenders){
        this.recommenders = recommenders;
    }
    public void setGa_prefix(String ga_prefix){
        this.ga_prefix = ga_prefix;
    }
    public void setSection(Section section){
        this.section = section;
    }
    public void setType(int type){
        this.type = type;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setCss(String [] css){
        this.css = css;
    }

}
