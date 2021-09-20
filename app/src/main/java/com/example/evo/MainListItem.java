package com.example.evo;

public class MainListItem {

    public String text;
    public String description;
    public int image;
    public String button;

    public MainListItem(String text, String description, int image, String button) {
        this.text = text;
        this.description = description;
        this.image = image;
        this.button = button;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
}