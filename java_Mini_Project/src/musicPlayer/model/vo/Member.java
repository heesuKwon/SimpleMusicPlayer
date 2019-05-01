package musicPlayer.model.vo;

import java.util.ArrayList;
import java.util.List;

public class Member {
	private String name;
	private String id;
	private String password;
	private List<Music> playList;
	
	public Member() {
	}
	
	public Member(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
		playList = new ArrayList<>();
	}//회원 가입시 사용

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Music> getPlayList() {
		return playList;
	}

	public void addPlayList(Music music) {
		playList.add(music);
	}
	//곡삭제
//	public void add
	
	//곡정렬
	
	//
}
