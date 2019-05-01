package musicPlayer.model.vo;

public class Music {
	private String Title; //곡명
	private String artist; //가수
	private String genre; //장르
	private String openYear; //발매년도
	private int like; //좋아요수
	private int seconds; //재생시간(분)
	
	public Music() {
		
	}

	public Music(String title, String artist, String genre, String openYear, int like, int seconds) {
		this.Title = title;
		this.artist = artist;
		this.genre = genre;
		this.openYear = openYear;
		this.like = like;
		this.seconds = seconds;
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
	
}
