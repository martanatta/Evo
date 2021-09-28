package com.example.evo.apiShmapi;

import java.util.List;

public class CategoryDetail {
    int id;
    String name;
    String description;
    String icon;
    List<Audio> audios;

    public class List<Audio>{
        int id;
        int category;
        String name;
        String audio_file;
        String picture;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public List<Audio> getAudios() {
//        return audios;
//    }
//
//    public void setAudios(List<Audio> audios) {
//        this.audios = audios;
//    }
//
//    public CategoryDetail(int id, String name, String description, List<Audio> audios) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.audios = audios;
//    }
}
