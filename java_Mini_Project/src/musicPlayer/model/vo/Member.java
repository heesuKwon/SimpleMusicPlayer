package musicPlayer.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Member implements Serializable{
	private String name;
	private String id;
	private String password;
	private HashSet<Long> likeCheck;
//	private List<Music> playList;
	
	public Member() {
	}
	
	public Member(String name, String id, String password, HashSet<Long> likeCheck) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.likeCheck=likeCheck;
//		playList = new ArrayList<>();
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
	
//	public List<Music> getPlayList() {
//		return playList;
//	}
//
//	public void addPlayList(Music music) {
//		playList.add(music);
//	}
	
	public HashSet<Long> getLikeCheck() {
		return likeCheck;
	}

	public void setLikeCheck(HashSet<Long> likeCheck) {
		this.likeCheck = likeCheck;
	}

	@Override
	public String toString() {
		return "id : "+id+", name : "+name; 
	}
	
}
