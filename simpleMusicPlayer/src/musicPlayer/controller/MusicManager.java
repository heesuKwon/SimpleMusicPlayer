package musicPlayer.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

import musicPlayer.model.vo.Music;

//전체 음악정보 관리
public class MusicManager {
	private List<Music> allMusicList;
	private IOController ioc = new IOController();//입출력 객체
	long code = 0;
	
	public MusicManager(){
		System.out.println("MusicManager 객체 생성");
		allMusicList = (List)ioc.loadList("Musics");
		//음악 파일이 비어있지 않으면
		if(allMusicList != null){
//			System.out.println(allMusicList.size());
			code = allMusicList.size();
		}
		//음악 파일이 비어있으면
		else{
			allMusicList = new ArrayList<>();
		}
	}
	
	//음악 한곡씩 추가
	public void addMusic(File f) {

		try{
			MP3File mp3 = (MP3File) AudioFileIO.read(f);
			AbstractID3v2Tag tag2 = mp3.getID3v2Tag();

			Tag tag = mp3.getTag();
			String title = tag.getFirst(FieldKey.TITLE);
			String artist = tag.getFirst(FieldKey.ARTIST);
			String album = tag.getFirst(FieldKey.ALBUM);
			int year = Integer.parseInt(tag.getFirst(FieldKey.YEAR));
			String genre = tag.getFirst(FieldKey.GENRE);
			String image = tag.getFirst(FieldKey.COVER_ART);
			Integer seconds = mp3.getAudioHeader().getTrackLength();
			int minute = seconds/60;
			int second = seconds%60;


			//public Music(String path, long code, String title, String artist, String genre, int openYear, int like, int seconds);
			Music music = new Music(f.toString(), code, title, artist, genre, year, 0, seconds);
			if(!allMusicList.contains(music)){//같은 곡이 있으면 저장하지 않음
//				path로 Music 객체 equals overriding
				allMusicList.add(music);
				code++;
			}
			System.out.println("추가한 곡"+music);
			printAllMusic();

			ioc.saveList(allMusicList, "Musics");

			//	            System.out.println("Tag : " + tag2);
			//	            System.out.println("Song Name : " + title);
			//	            System.out.println("Artist : " + artist);
			//	            System.out.println("Album : " + album);
			//	            System.out.println("Year : " + year);
			//	            System.out.println("Genre : " + genre);
			//	            System.out.println("Seconds : "+seconds);
			//	            System.out.println("minute : "+minute); //4 ->실제 길이 4:23
			//	            System.out.println("second : "+second); //23 ->실제 길이 4:23
				            System.out.println("image : "+image);
			String[] str = image.split(":");
			System.out.println(str[0]);

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//모든 음악 가져오기
	public List<Music> getAllMusic(){
		return allMusicList;
	}
	
	public void setAllMusic(List<Music> allMusicList) {
		this.allMusicList = allMusicList;
		ioc.saveList(allMusicList, "Musics");
	}
	
	//음악 한개 삭제
	public void removeMusic(Music music) {
		allMusicList.remove(music);
		ioc.saveList(allMusicList, "Musics");
	}
	
	public void printAllMusic(){
		for(Music m : allMusicList){
			System.out.println(m);
		}
	}
}
