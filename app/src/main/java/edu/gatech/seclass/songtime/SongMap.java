package edu.gatech.seclass.songtime;

import java.util.HashMap;
import java.util.Map;

/*
Song map is used to convert text name of song to playable filename in our resources.
 */
public class SongMap {
    private Map<String, String> songMap;

    public SongMap() {
        songMap = new HashMap<String, String>();
        songMap.put("Are You Sleeping", "are_you_sleeping");
        songMap.put("Are You Sleeping (Instrumental)", "are_you_sleeping_instrumental");
        songMap.put("Hickory Dickory Dock", "hickory_dickory_dock");
        songMap.put("Humpty Dumpty", "humpty_dumpty");
        songMap.put("Hush Little Baby", "hush_little_baby");
        songMap.put("Itsy Bitsy Spider", "itsy_bitsy_spider");
        songMap.put("Itsy Bitsy Spider (Instrumental)", "itsy_bitsy_spider_instrumental");
        songMap.put("London Bridge", "london_bridge");
        songMap.put("London Bridge (Instrumental)", "london_bridge_instrumental");
        songMap.put("Mary Had a Little Lamb", "mary_had_a_little_lamb");
        songMap.put("Mary Had a Little Lamb (Instrumental)", "mary_had_a_little_lamb_instrumental");
        songMap.put("Old MacDonald", "old_macdonald");
        songMap.put("Old MacDonald (Instrumental)", "old_macdonald_instrumental");
        songMap.put("Pop Goes the Weasel", "pop_goes_the_weasel");
        songMap.put("Rock a Bye Baby", "rock_a_bye_baby");
        songMap.put("Rock a Bye Baby (Instrumental)", "rock_a_bye_baby_instrumental");
        songMap.put("Row Row Row Your Boat", "row_row_row_your_boat");
        songMap.put("Skip to My Lou", "skip_to_my_lou");
        songMap.put("The Alphabet Song", "the_alphabet_song");
        songMap.put("The Farmer in the Dell", "the_farmer_in_the_dell");
        songMap.put("The Farmer in the Dell (Instrumental)", "the_farmer_in_the_dell_instrumental");
        songMap.put("The Muffin Man", "the_muffin_man");
        songMap.put("The Muffin Man (Instrumental)", "the_muffin_man_instrumental");
        songMap.put("The Wheels on the Bus", "the_wheels_on_the_bus");
        songMap.put("This Old Man (Instrumental)", "this_old_man_instrumental");
        songMap.put("This Old Man", "this_old_man_vocal");
        songMap.put("Twinkle Twinkle Little Star", "twinkle_twinkle_little_star");
        songMap.put("Twinkle Twinkle Litter Star (Instrumental)", "twinkle_twinkle_little_star_instrumental");
        songMap.put("Yankee Doodle", "yankee_doodle");
    }

    //grab the song map
    public Map<String, String> getSongMap(){
        return songMap;
    }

    //grab the playable filename of a song
    public String getPlayable(String name){
        return this.songMap.get(name);
    }
}
