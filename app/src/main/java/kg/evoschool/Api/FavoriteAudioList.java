package kg.evoschool.Api;

public class FavoriteAudioList {
    Integer id;
    Integer user;
    Boolean status;
    Integer audio;

    public FavoriteAudioList(Integer id, Integer user, Boolean status, Integer audio) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.audio = audio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getAudio() {
        return audio;
    }

    public void setAudio(Integer audio) {
        this.audio = audio;
    }
}
