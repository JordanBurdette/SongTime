package edu.gatech.seclass.songtime;

public class Song {
    private String title;
    private String playableName;

    /*
    Identify song by title, will convert to playable name of resource.
    Not currently needing to use this class.
     */
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
