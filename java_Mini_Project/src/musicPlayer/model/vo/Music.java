package musicPlayer.model.vo;

import java.io.Serializable;

public class Music implements Serializable{
	private String path; //파일경로
	private long code;	//고유번호
	private String Title; //곡명
	private String artist; //가수
	private String genre; //장르
	private String openYear; //발매년도
	private int like; //좋아요수
	private int seconds; //재생시간(분)
//	private String image; //사진 이미지
	
	public Music() {
		
	}

	public Music(String path, long code, String title, String artist, String genre, String openYear, int like, int seconds) {
		this.path = path;
		this.code = code;
		this.Title = title;
		this.artist = artist;
		this.genre = genre;
		this.openYear = openYear;
		this.like = like;
		this.seconds = seconds;
//		this.image = image;
	}
	
	@Override
	public String toString(){
		return "["+path+","+code+","+Title+","+artist+","+genre+","+
				openYear+","+like+","+seconds+"]";
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getOpenYear() {
		return openYear;
	}

	public void setOpenYear(String openYear) {
		this.openYear = openYear;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}
	
}
