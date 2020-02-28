package edu.gatech.seclass.songtime;

import java.util.ArrayList;

public class Playlist {
    private int length;
    private String title;
    private ArrayList songs;

    /*
    Playlist is a grouping of songs.
    Will have some prebuilt and some user-generated.
     */
    public Playlist(String title) {
        this.title = title;
        songs = new ArrayList();
        length = songs.size();
    }

    //gets the length of a playlist
    public int getLength() {
        return songs.size();
    }

    //gets the title of a playlist
    public String getTitle() {
        return title;
    }

    //gets the list of songs in the playlist. Will return an arraylist.
    public ArrayList getSongs() {
        return songs;
    }

    //sets the songs on a playlist. param songs is type arraylist
    public void setSongs(ArrayList songs) {
        this.songs = songs;
    }

    //adds a song to a playlist via the song name
    public void addSong(String song) {

        songs.add(song);
    }

    //removes a song from a playlist via the song name
    public void removeSong(String song) {
        songs.remove(song);
    }
}
