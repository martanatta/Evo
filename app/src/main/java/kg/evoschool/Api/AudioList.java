package kg.evoschool.Api;

public class AudioList {
    String path;
    String title;
    String album;
    String duration;

    public AudioList(String path, String title, String album, String duration) {
        this.path = path;
        this.title = title;
        this.album = album;
        this.duration = duration;
    }

    public AudioList() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}