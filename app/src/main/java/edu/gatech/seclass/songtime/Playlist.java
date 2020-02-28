package edu.gatech.seclass.songtime;

import java.util.ArrayList;

public class Playlist {
    private int length;
    private String title;
    private ArrayList songs;

    public Playlist(String title) {
        this.title = title;
        songs = new ArrayList();
        length = songs.size();
    }

    public int getLength() {
        return songs.size();
    }

    public String getTitle() {
        return title;
    }

    public ArrayList getSongs() {
        return songs;
    }

    public void setSongs(ArrayList songs) {
        this.songs = songs;
    }

    public void addSong(String song) {

        songs.add(song);
    }

    public void removeSong(String song) {
        songs.remove(song);
    }
}
