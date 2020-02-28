package edu.gatech.seclass.songtime;

public class Song {
    private String title;
    private String playableName;

    public Song(String title) {
        this.title = title;
        SongMap sm = new SongMap();
        this.playableName = sm.getPlayable(this.title);
    }

    public String getTitle() {
        return title;
    }

    public String getPlayableName() {
        return playableName;
    }

}
