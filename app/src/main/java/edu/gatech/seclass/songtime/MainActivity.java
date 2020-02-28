package edu.gatech.seclass.songtime;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.icu.text.SymbolTable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SearchEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private MediaPlayer mediaPlayer;
    private SongMap songMap;
    private ArrayList featuredPlaylists;
    private Library playlistLibrary;
    private String nowPlaying;
    private String currentPlaylist;
    private ArrayList usersPlaylists;
    private String newPlaylistName = "";
    private Menu mMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.itsy_bitsy_spider_instrumental);
        songMap = new SongMap();
        setupPlaylists();
        loadUsersPlaylists();
        goToFeaturedPlaylistsPage();
    }

    public void loadUsersPlaylists(){
        usersPlaylists = new ArrayList();
    }

    //Initialize and setup pre-built playlists
    public void setupPlaylists(){
        //setting up featured playlists
        featuredPlaylists = new ArrayList();
        featuredPlaylists.add("Fun");
        featuredPlaylists.add("Instrumental");
        featuredPlaylists.add("Nap Time");
        featuredPlaylists.add("Meal Time");
        featuredPlaylists.add("Morning");
        featuredPlaylists.add("Sing Along");


        //Instrumental
        Playlist instrumentalPlaylist = new Playlist("Instrumental");
        instrumentalPlaylist.addSong("Are You Sleeping (Instrumental)");
        instrumentalPlaylist.addSong("Itsy Bitsy Spider (Instrumental)");
        instrumentalPlaylist.addSong("London Bridge (Instrumental)");
        instrumentalPlaylist.addSong("Mary Had a Little Lamb (Instrumental)");
        instrumentalPlaylist.addSong("Old MacDonald (Instrumental)");
        instrumentalPlaylist.addSong("Rock a Bye Baby (Instrumental)");
        instrumentalPlaylist.addSong("The Farmer in the Dell (Instrumental)");
        instrumentalPlaylist.addSong("The Muffin Man (Instrumental)");
        instrumentalPlaylist.addSong("This Old Man (Instrumental)");
        instrumentalPlaylist.addSong("Twinkle Twinkle Litter Star (Instrumental)");


        //Sing Along
        Playlist singAlongPlaylist = new Playlist("Sing Along");
        singAlongPlaylist.addSong("The Alphabet Song");
        singAlongPlaylist.addSong("The Muffin Man");
        singAlongPlaylist.addSong("Mary Had a Little Lamb");
        singAlongPlaylist.addSong("The Wheels on the Bus");
        singAlongPlaylist.addSong("Pop Goes the Weasel");
        singAlongPlaylist.addSong("Row Row Row Your Boat");
        singAlongPlaylist.addSong("Old MacDonald");
        singAlongPlaylist.addSong("Yankee Doodle");


        //Nap Time
        Playlist napTimePlaylist = new Playlist("Nap Time");
        napTimePlaylist.addSong("Are You Sleeping");
        napTimePlaylist.addSong("Are You Sleeping (Instrumental)");
        napTimePlaylist.addSong("Hush Little Baby");
        napTimePlaylist.addSong("Rock a Bye Baby");
        napTimePlaylist.addSong("Rock a Bye Baby (Instrumental)");
        napTimePlaylist.addSong("Twinkle Twinkle Little Star");
        napTimePlaylist.addSong("Twinkle Twinkle Litter Star (Instrumental)");


        //Fun
        Playlist funPlaylist = new Playlist("Fun");
        funPlaylist.addSong("The Alphabet Song");
        funPlaylist.addSong("Itsy Bitsy Spider");
        funPlaylist.addSong("Old MacDonald");
        funPlaylist.addSong("Pop Goes the Weasel");
        funPlaylist.addSong("The Muffin Man");
        funPlaylist.addSong("This Old Man");


        //Morning
        Playlist morningPlaylist = new Playlist("Morning");
        morningPlaylist.addSong("The Alphabet Song");
        morningPlaylist.addSong("Hickory Dickory Dock");
        morningPlaylist.addSong("Humpty Dumpty");
        morningPlaylist.addSong("Yankee Doodle");
        morningPlaylist.addSong("Skip to My Lou");


        //Meal Time
        Playlist mealTimePlaylist = new Playlist("Meal Time");
        mealTimePlaylist.addSong("Humpty Dumpty");
        mealTimePlaylist.addSong("The Muffin Man");
        mealTimePlaylist.addSong("The Muffin Man (Instrumental)");
        mealTimePlaylist.addSong("Pop Goes the Weasel");


        //Full Playlist
        Playlist fullPlaylist = new Playlist("Full Playlist");
        Map<String, String > fullSongs = songMap.getSongMap();
        for (Map.Entry<String, String> entry : fullSongs.entrySet()) {
            fullPlaylist.addSong(entry.getKey());
        }


        //adding all featured playlists to library
        playlistLibrary = new Library();
        playlistLibrary.addPlaylist(fullPlaylist);
        playlistLibrary.addPlaylist(funPlaylist);
        playlistLibrary.addPlaylist(instrumentalPlaylist);
        playlistLibrary.addPlaylist(napTimePlaylist);
        playlistLibrary.addPlaylist(mealTimePlaylist);
        playlistLibrary.addPlaylist(morningPlaylist);
        playlistLibrary.addPlaylist(singAlongPlaylist);
    }

    //Handles UI for naming a new playlist
    public void nameNewPlaylist(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Playlist Name");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newPlaylistName = input.getText().toString();
                createNewPlaylist(newPlaylistName);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    //Handles the UI for confirming a playlist delete
    public void confirmDeletePlaylist(String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to delete this playlist?");
        final String playlistToDelete = name;


        // Set up the buttons
        builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteUserPlaylist(playlistToDelete);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    //creating a new playlist and adding it to the library
    public void createNewPlaylist(String name){
        usersPlaylists.add(name);

        Playlist testPlaylist = new Playlist(name);

        playlistLibrary.addPlaylist(testPlaylist);

        goToUsersPlaylistsPage();

        //show success
        String text = "New playlist created.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }

    //removing the chosen playlist to be deleted from the library.
    public void deleteUserPlaylist(String name){
        //this is done by name... needs to be fixed to be done by id in fht future
        Playlist playlistToRemove = playlistLibrary.getPlaylistByTitle(name);

        Iterator<String> iterator = usersPlaylists.iterator();
        while(iterator.hasNext())
        {
            String value = iterator.next();
            if (name.equals(value))
            {
                iterator.remove();
                break;
            }
        }

        playlistLibrary.removePlaylist(playlistToRemove);

        goToUsersPlaylistsPage();

        //show success
        String text = "Playlist removed.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();

    }

    //back button
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    //not currently using, but need the override, I believe
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    //Handling UI navigation for drawer
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.your_playlists) {

            goToUsersPlaylistsPage();

        }
        else if (id == R.id.featured_playlists) {

            goToFeaturedPlaylistsPage();

        }
        else if (id == R.id.now_playing) {

            goToNowPlayingPage();

        }
        else if (id == R.id.search) {

            goToSearchPage("menu");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //"Your Playlists" page
    public void goToUsersPlaylistsPage(){
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_container);
        mainLayout.removeAllViews();

        //Text Header
        TextView title = new TextView(this);
        title.setPadding(0,25, 0,25);
        title.setText("Your Playlists");
        title.setTypeface(null, Typeface.BOLD);
        title.setTextSize(30);
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        //if we want to add padding to headers
//        title.setPadding(0,0,0,20);
        mainLayout.addView(title);

        //creating 'Add New Playlist' button
        ImageButton newButton = new ImageButton(this);
        newButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.plus));
        newButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                nameNewPlaylist();
            }
        });

        mainLayout.addView(newButton);

        //Generating buttons on fly
        for(int i = 0; i<usersPlaylists.size(); i++){
            Button myButton = new Button(this);
            myButton.setText(usersPlaylists.get(i).toString());
            myButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 125));
            myButton.setId(i);
            final String playlistName = usersPlaylists.get(i).toString();
            System.out.println(playlistName);
            myButton.setText(playlistName);
            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // your handler code here
                    goToUsersPlaylist(playlistName);
                    currentPlaylist = playlistName;
                }
            });

            mainLayout.addView(myButton);

        }
    }

    //"Featured Playlists" page
    public void goToFeaturedPlaylistsPage(){
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_container);

        mainLayout.removeAllViews();

        //Text Header
        TextView title = new TextView(this);
        title.setPadding(0,25, 0,25);
        title.setText("Featured Playlists");
        title.setTypeface(null, Typeface.BOLD);
        title.setTextSize(30);
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        mainLayout.addView(title);

        //Generating buttons on fly
        for(int i = 0; i<featuredPlaylists.size(); i++){
            Button myButton = new Button(this);
            myButton.setText(featuredPlaylists.get(i).toString());
            myButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 125));
            myButton.setId(i);
            final String playlistName = featuredPlaylists.get(i).toString();
            System.out.println(playlistName);
            myButton.setText(playlistName);
            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // your handler code here
                    goToPlaylist(playlistName);
                    currentPlaylist = playlistName;
                }
            });

            mainLayout.addView(myButton);

        }
    }

    //"Search" page
    public void goToFilteredSearchPage(List<String> filteredList, final String from){

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_container);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.search, null);
        mainLayout.removeAllViews();
        mainLayout.addView(layout);


        final SearchView searchView = (SearchView) findViewById(R.id.simpleSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // do something on text submit
                searchView.setIconified(true);
                searchView.clearFocus();
                // call your request, do some stuff..

                // collapse the action view
                if (mMenu != null) {
                    (mMenu.findItem(R.id.simpleSearchView)).collapseActionView();
                }
//                return false;


                filterSearchList(query, from);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // do something when text changes
//                filterSearchList(newText);
                return false;
            }
        });


        //got here from menu
        if(from.equals("menu")){

            ArrayList<String> songs = new ArrayList<String>(filteredList);
            //Generating buttons on fly
            Collections.sort(songs);
            for(int i = 0; i<songs.size(); i++){
                System.out.println(songs.get(0));
                final Button myButton = new Button(this);
                myButton.setText(songs.get(i));
                myButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 125));
                myButton.setId(i);
                myButton.setText(songs.get(i));
                myButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                    currentPlaylist = "Full Playlist";
                    playSong(v);

                    }
                });

                mainLayout.addView(myButton);

            }

        }

        //got here from a playlist
        else{

            ArrayList<String> songs = new ArrayList<String>(filteredList);
            //Generating buttons on fly
            Collections.sort(songs);
            for(int i = 0; i<songs.size(); i++){
                System.out.println(songs.get(0));
                final Button myButton = new Button(this);
                myButton.setText(songs.get(i));
                final String songName = myButton.getText().toString();
                myButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 125));
                myButton.setId(i);
                myButton.setText(songs.get(i));
                myButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                    addSongToPlaylist(songName, from);

                    }
                });

                mainLayout.addView(myButton);

            }
        }

    }

    //"Search" page
    public void goToSearchPage(final String from){

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_container);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.search, null);
        mainLayout.removeAllViews();
        mainLayout.addView(layout);


        final SearchView searchView = (SearchView) findViewById(R.id.simpleSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
            // do something on text submit
                searchView.setIconified(true);
                searchView.clearFocus();
                // call your request, do some stuff..

                // collapse the action view
                if (mMenu != null) {
                    (mMenu.findItem(R.id.simpleSearchView)).collapseActionView();
                }
//                return false;


                filterSearchList(query, from);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
            // do something when text changes
//                filterSearchList(newText);
                return false;
            }
        });


        //got here from menu
        if(from.equals("menu")){

            Playlist playlist = playlistLibrary.getPlaylistByTitle("Full Playlist");
            ArrayList<String> songs = playlist.getSongs();
            //Generating buttons on fly
            Collections.sort(songs);
            for(int i = 0; i<songs.size(); i++){
                System.out.println(songs.get(0));
                final Button myButton = new Button(this);
                myButton.setText(songs.get(i));
                myButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 125));
                myButton.setId(i);
                myButton.setText(songs.get(i));
                myButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        currentPlaylist = "Full Playlist";
                        playSong(v);

                    }
                });

                mainLayout.addView(myButton);

            }

        }


        //got here from a playlist
        else{
            Playlist playlist = playlistLibrary.getPlaylistByTitle("Full Playlist");
            ArrayList<String> songs = playlist.getSongs();
            //Generating buttons on fly
            Collections.sort(songs);
            for(int i = 0; i<songs.size(); i++){
                System.out.println(songs.get(0));
                final Button myButton = new Button(this);
                myButton.setText(songs.get(i));
                final String songName = myButton.getText().toString();
                myButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 125));
                myButton.setId(i);
                myButton.setText(songs.get(i));
                myButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        addSongToPlaylist(songName, from);

                    }
                });

                mainLayout.addView(myButton);

            }
        }

    }


    //"Now Playing" page
    public void goToNowPlayingPage(){
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_container);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.now_playing, null);
        TextView nowPlayingContent = (TextView)findViewById(R.id.now_playing_content);
        mainLayout.removeAllViews();
        mainLayout.addView(layout);
        TextView content = (TextView)findViewById(R.id.now_playing_content);
        content.setText(nowPlaying);

        //seekbar updating
        final SeekBar seekBar = findViewById(R.id.songProgressBar);
        seekBar.setMax(mediaPlayer.getDuration()/1000);
        final Handler mHandler = new Handler();
        //Make sure you update Seekbar on UI thread
        MainActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(mediaPlayer != null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer != null && fromUser){
                    mediaPlayer.seekTo(progress * 1000);
                }
            }
        });
    }

    //Goes to a specific playlist. Displays the songs that are available to play.
    public void goToPlaylist(String playlistName)
    {

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_container);

        mainLayout.removeAllViews();


        //Text Header
        TextView title = new TextView(this);
        title.setPadding(0,25, 0,25);
        title.setText(playlistName);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextSize(30);
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        mainLayout.addView(title);


        Playlist playlist = playlistLibrary.getPlaylistByTitle(playlistName);
        ArrayList<String> songs = playlist.getSongs();


        //Generating buttons on fly
        Collections.sort(songs);
        for(int i = 0; i<songs.size(); i++){
            System.out.println(songs.get(0));
            final Button myButton = new Button(this);
            myButton.setText(songs.get(i));
            myButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 125));
            myButton.setId(i);
            myButton.setText(songs.get(i));
            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // your handler code here
                    playSong(v);
                }
            });

            mainLayout.addView(myButton);

        }
    }

    //Goes to a user-created playlist. Displays songs to play, ability to add & remove songs, delete entire playlist.
    public void goToUsersPlaylist(final String playlistName){

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_container);
        mainLayout.removeAllViews();


        //Text Header
        TextView title = new TextView(this);
        title.setPadding(0,25, 0,25);
        title.setText(playlistName);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextSize(30);
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        mainLayout.addView(title);


        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.playlist, null);
        mainLayout.addView(layout);


        final Playlist playlist = playlistLibrary.getPlaylistByTitle(playlistName);
        ArrayList<String> songs = playlist.getSongs();


        //Adding click action for Add Song Button
        ImageButton newButton = (ImageButton) findViewById(R.id.addSongButton);
        newButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToSearchPage(playlistName);
            }
        });

        //Adding click action for Remove Song Button
        ImageButton deleteSongButton = (ImageButton) findViewById(R.id.deleteSongButton);
        deleteSongButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(playlistLibrary.getPlaylistByTitle(playlistName).getSongs().size() != 0){
                    goToRemoveSongPage(playlistName);
                }
            }
        });


        //Adding click action for Delete Playlist Button
        ImageButton deletePlaylistButton = (ImageButton) findViewById(R.id.deletePlaylistButton);
        deletePlaylistButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                confirmDeletePlaylist(playlistName);
            }
        });


        //Generating buttons on fly
        Collections.sort(songs);
        for(int i = 0; i<songs.size(); i++){
            System.out.println(songs.get(0));
            final Button myButton = new Button(this);
            myButton.setText(songs.get(i));
            myButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 125));
            myButton.setId(i);
            myButton.setText(songs.get(i));
            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // your handler code here
                    playSong(v);
                }
            });

            mainLayout.addView(myButton);

        }
    }

    //remove song page. Lets the user select a song from their playlist to remove.
    public void goToRemoveSongPage(final String playlistName){

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_container);
        mainLayout.removeAllViews();


        //Text Header
        TextView title = new TextView(this);
        title.setPadding(0,25, 0,25);
        title.setText("Select a song to remove from this playlist.");
        title.setTextSize(30);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        mainLayout.addView(title);


        final Playlist playlist = playlistLibrary.getPlaylistByTitle(playlistName);
        ArrayList<String> songs = playlist.getSongs();


        //Generating buttons on fly
        Collections.sort(songs);
        for(int i = 0; i<songs.size(); i++){
            System.out.println(songs.get(0));
            final Button myButton = new Button(this);
            myButton.setText(songs.get(i));
            myButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 125));
            myButton.setId(i);
            myButton.setText(songs.get(i));
            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // your handler code here
                    removeSongFromPlaylist(v, playlistName);
                }
            });

            mainLayout.addView(myButton);

        }

    }

    //the search functionality. filtering songs via user's text
    public void filterSearchList(String query, String from){
        Playlist playlist = playlistLibrary.getPlaylistByTitle("Full Playlist");
        ArrayList<String> songs = playlist.getSongs();

        List<String> songList = new ArrayList<>(songs);

        for (Iterator<String> it=songList.iterator(); it.hasNext();) {
            if(!Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE).matcher(it.next()).find()){
                it.remove();
            }
        }


        goToFilteredSearchPage(songList, from);
    }

    //adding a song to a user-created playlist
    public void addSongToPlaylist(final String songName, final String playlistName){

        //get playlist they're editing from library, remove it from library, add song to it, add it back to library.
        Playlist playlistToEdit = playlistLibrary.getPlaylistByTitle(playlistName);
        playlistLibrary.removePlaylist(playlistToEdit);
        playlistToEdit.addSong(songName);
        playlistLibrary.addPlaylist(playlistToEdit);
        goToUsersPlaylist(playlistName);

        //show success
        String text = "Song added to playlist.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }

    //removing a song from a user-created playlist
    public void removeSongFromPlaylist(View v, String playlistName){

        Button b = (Button) v;
        String songToRemove = b.getText().toString();

        Playlist playlistToEdit = playlistLibrary.getPlaylistByTitle(playlistName);
        playlistLibrary.removePlaylist(playlistToEdit);
        playlistToEdit.removeSong(songToRemove);
        playlistLibrary.addPlaylist(playlistToEdit);
        goToUsersPlaylist(playlistName);

        //show success
        String text = "Song removed from playlist.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();

    }

    //playing the selected song
    public void playSong(View view) {

        mediaPlayer.reset();

        //grabs the text of the button clicked, checks songMap and plays appropriate song
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        String songToPlay = songMap.getPlayable(buttonText);
        mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(songToPlay, "raw", getPackageName()));

        playMedia(view);
        nowPlaying = buttonText;
        goToNowPlayingPage();

    }

    //play via media player
    public void playMedia(View view) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    //pause via mediaplayer
    public void pauseMedia(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    //playing the previous song in the current playlist
    public void previousSong(View view){
        Playlist playlist = playlistLibrary.getPlaylistByTitle(currentPlaylist);
        ArrayList<String> songs = playlist.getSongs();
        String previousSong = null;

        //Generating buttons on fly
        for(int i = 0; i<songs.size(); i++){
            if(songs.get(i).equals(nowPlaying) && i != 0){
                previousSong = songs.get(i-1);
            }
        }
        if(previousSong != null){
            mediaPlayer.reset();
            String songToPlay = songMap.getPlayable(previousSong);
            mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(songToPlay, "raw", getPackageName()));
            nowPlaying = previousSong;
            playMedia(view);
            TextView content = (TextView)findViewById(R.id.now_playing_content);
            content.setText(nowPlaying);
        }
    }

    //playing the next song in the current playlist
    public void nextSong(View view){
        Playlist playlist = playlistLibrary.getPlaylistByTitle(currentPlaylist);
        ArrayList<String> songs = playlist.getSongs();
        String nextSong = null;

        //Generating buttons on fly
        for(int i = 0; i<songs.size(); i++){
            if(songs.get(i).equals(nowPlaying) && i != songs.size() - 1){
                nextSong = songs.get(i+1);
            }
        }

        if(nextSong != null){
            mediaPlayer.reset();
            String songToPlay = songMap.getPlayable(nextSong);
            mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(songToPlay, "raw", getPackageName()));
            nowPlaying = nextSong;
            playMedia(view);
            TextView content = (TextView)findViewById(R.id.now_playing_content);
            content.setText(nowPlaying);
        }
    }


}
