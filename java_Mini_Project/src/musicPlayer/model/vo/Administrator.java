package musicPlayer.model.vo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

public class Administrator {
	
	private List<Music> allMusic = new ArrayList<>();
	private List<Member> memberList = new ArrayList<>();
	long code = 0;
	
	public Administrator() {
//		File fs = new File(path);
//		if(fs.isDirectory()) {
//			File list[] = fs.listFiles();			
//			for(File f : list) {
//				try{
//                    MP3File mp3 = (MP3File) AudioFileIO.read(f);
//                    AbstractID3v2Tag tag2 = mp3.getID3v2Tag();
//                    
//                    Tag tag = mp3.getTag();
//                    String title = tag.getFirst(FieldKey.TITLE);
//                    String artist = tag.getFirst(FieldKey.ARTIST);
//                    String album = tag.getFirst(FieldKey.ALBUM);
//                    String year = tag.getFirst(FieldKey.YEAR);
//                    String genre = tag.getFirst(FieldKey.GENRE);
////                    Integer seconds = mp3.getAudioHeader().getTrackLength() + 1;//+1은 왜한거지?
//                    Integer seconds = mp3.getAudioHeader().getTrackLength();//+1은 왜한거지?
//                    int minute = seconds/60;
//                    int second = seconds%60;
//                    
//             
//                    //public Music(String path, long code, String title, String artist, String genre, String openYear, int like, int seconds);
//                    Music music = new Music(f.toString(), code++, title, artist, genre, year, 0, seconds);
//                    allMusic.add(music);
//                    
//                    System.out.println("Tag : " + tag2);
//                    System.out.println("Song Name : " + title);
//                    System.out.println("Artist : " + artist);
//                    System.out.println("Album : " + album);
//                    System.out.println("Year : " + year);
//                    System.out.println("Genre : " + genre);
//                    System.out.println("Seconds : "+seconds);
//                    System.out.println("minute : "+minute); //4 ->실제 길이 4:23
//                    System.out.println("second : "+second); //23 ->실제 길이 4:23
//                    
//                }catch(Exception ex){
//                    ex.printStackTrace();
//                }
//			}
//		}
	}
	
	//회원정보출력 -> 나중에 뷰로 수정 필요
	public void MemberListInfo() {
		String filePath = "MemberInfo.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath));){
			String line = null;
			while((line=br.readLine())!=null) {
				String[] str=line.split(",");
				memberList.add(new Member(str[0],str[1],str[2]));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addMusic(File f) {
//		String path = "";
//		File f = new File(path);
		
		try{
            MP3File mp3 = (MP3File) AudioFileIO.read(f);
            AbstractID3v2Tag tag2 = mp3.getID3v2Tag();
            
            Tag tag = mp3.getTag();
            String title = tag.getFirst(FieldKey.TITLE);
            String artist = tag.getFirst(FieldKey.ARTIST);
            String album = tag.getFirst(FieldKey.ALBUM);
            String year = tag.getFirst(FieldKey.YEAR);
            String genre = tag.getFirst(FieldKey.GENRE);
//            Integer seconds = mp3.getAudioHeader().getTrackLength() + 1;//+1은 왜한거지?
            Integer seconds = mp3.getAudioHeader().getTrackLength();
            int minute = seconds/60;
            int second = seconds%60;
            
     
            //public Music(String path, long code, String title, String artist, String genre, String openYear, int like, int seconds);
            Music music = new Music(f.toString(), code++, title, artist, genre, year, 0, seconds);
            allMusic.add(music);
            
            System.out.println("Tag : " + tag2);
            System.out.println("Song Name : " + title);
            System.out.println("Artist : " + artist);
            System.out.println("Album : " + album);
            System.out.println("Year : " + year);
            System.out.println("Genre : " + genre);
            System.out.println("Seconds : "+seconds);
            System.out.println("minute : "+minute); //4 ->실제 길이 4:23
            System.out.println("second : "+second); //23 ->실제 길이 4:23
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
	}
	
	public void removeMusic() {
		
	}
	
	public List<Music> getAllMusic(){
		return allMusic;
	}
	
	public void printAllMusic() {
		for(int i=0;i<allMusic.size();i++) {
			System.out.println(allMusic.get(i).getTitle());
		}
	}
}
