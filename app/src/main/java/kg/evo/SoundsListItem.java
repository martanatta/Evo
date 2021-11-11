package kg.evo;

public class SoundsListItem {
    private String path;
    private String title;
    private String album;
    private String duration;

    public SoundsListItem(String path, String title, String artist, String album, String duration) {
        this.path = path;
        this.title = title;
        this.album = album;
        this.duration = duration;
    }

    public SoundsListItem() {
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