package kg.evo.apiShmapi;

import java.util.List;

public class CategoryDetail {
    public int id;
    public String name;
    public String description;
    public String icon;
    public List<Audio> audios;

    public class Audio {
        public int id;
        public int category;
        public String name;
        public String audio_file;
        public String picture;
    }
}