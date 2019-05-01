package musicPlayer.model.vo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

public class StoreMusic {
	
	private List<Music> allMusic = new ArrayList<>();
	
	public StoreMusic(String path) {
		File fs = new File(path);
		if(fs.isDirectory()) {
			File list[] = fs.listFiles();			
			for(File f : list) {
				try{
                    MP3File mp3 = (MP3File) AudioFileIO.read(f);
                    AbstractID3v2Tag tag2 = mp3.getID3v2Tag();
                    
                    Tag tag = mp3.getTag();
                    String title = tag.getFirst(FieldKey.TITLE);
                    String artist = tag.getFirst(FieldKey.ARTIST);
                    String album = tag.getFirst(FieldKey.ALBUM);
                    String year = tag.getFirst(FieldKey.YEAR);
                    String genre = tag.getFirst(FieldKey.GENRE);
//                    String trackStr = tag.getFirst(FieldKey.TRACK);
//                    int track;
//                    if (trackStr.isEmpty()) 
//                    { 
//                    	track = 0; 
//                    }
//                    else {
//                    	track = Integer.parseInt(trackStr);
//                    }
//                    Integer seconds = mp3.getAudioHeader().getTrackLength() + 1;//+1은 왜한거지?
                    Integer seconds = mp3.getAudioHeader().getTrackLength();//+1은 왜한거지?
                    int minute = seconds/60;
                    int second = seconds%60;
                    
             
                    //public Music(String title, String artist, String genre, Date openDate, int like, int seconds);
                    Music music = new Music(title, artist, genre, year, 0, seconds);
                    allMusic.add(music);
                    
                    System.out.println("Tag : " + tag2);
                    System.out.println("Song Name : " + title);
                    System.out.println("Artist : " + artist);
                    System.out.println("Album : " + album);
                    System.out.println("Year : " + year);
                    System.out.println("Genre : " + genre);
//                    System.out.println("TrackStr"+trackStr);
//                    System.out.println("Track"+track);
                    System.out.println("Seconds"+seconds);
                    System.out.println("minute"+minute); //4:4 ->실제 길이 4:23
                    System.out.println("second"+second); //4:4 ->실제 길이 4:23
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
			}
		}
	}
	
	public List<Music> getAllMusic(){
		return allMusic;
	}
}
