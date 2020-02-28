package edu.gatech.seclass.songtime;

import java.util.ArrayList;

public class Library {
    private ArrayList<Playlist> playlists;

    //Library is the list of all playlists for a user
    public Library() {
        this.playlists = new ArrayList<Playlist>();
    }

    //currently handling playlists by title, not ideal, should adjust in the future
    public Playlist getPlaylistByTitle(String title){
        for (int i = 0; i<playlists.size(); i ++){
            if(playlists.get(i).getTitle().equals(title)){
                return playlists.get(i);
            }
        }
        return null;
    }

    //adding a playlist to the library
    public void addPlaylist(Playlist playlist){
        playlists.add(playlist);
    }

    //removing a playlist from the library
    public void removePlaylist(Playlist playlist){
        for(int i = 0; i<playlists.size(); i++){

            if(playlist.getTitle().equals(playlists.get(i).getTitle())){
                playlists.remove(i);
            }

        }
    }

}
