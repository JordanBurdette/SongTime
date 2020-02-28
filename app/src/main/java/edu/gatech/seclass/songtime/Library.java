package edu.gatech.seclass.songtime;

import java.util.ArrayList;

public class Library {
    private ArrayList<Playlist> playlists;

    public Library() {
        this.playlists = new ArrayList<Playlist>();
    }

    public Playlist getPlaylistByTitle(String title){
        for (int i = 0; i<playlists.size(); i ++){
            if(playlists.get(i).getTitle().equals(title)){
                return playlists.get(i);
            }
        }
        return null;
    }

    public void addPlaylist(Playlist playlist){
        playlists.add(playlist);
    }

    public void removePlaylist(Playlist playlist){
        for(int i = 0; i<playlists.size(); i++){

            if(playlist.getTitle().equals(playlists.get(i).getTitle())){
                playlists.remove(i);
            }

        }
    }

}
