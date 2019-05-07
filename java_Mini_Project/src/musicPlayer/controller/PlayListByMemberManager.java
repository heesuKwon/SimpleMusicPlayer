package musicPlayer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import musicPlayer.model.vo.Music;

public class PlayListByMemberManager {

	private Map<String, List<Music>> MemberMusicMap;
	IOController ioc = new IOController();
	
	String id;//접속한 회원 id
	List<Music> playList;
	
	public PlayListByMemberManager(String id) {
		MemberMusicMap = (Map)ioc.loadMap("MemberMusicMap");
		//음악 파일이 비어있으면
		if(MemberMusicMap == null){
			MemberMusicMap = new HashMap<>();
		}
		//map의 key값 중 id가 있으면 (회원의 플레이리스트가 존재하면)
		if(MemberMusicMap.containsKey(id)) {
			playList = MemberMusicMap.get(id);
		}
		else {
			playList = new ArrayList<>();		
		}
		this.id = id;
	}
	
	public void addPlayList(Music m) {
		playList.add(m);
		setMemberMusicMap();
	}
	
	public void setMemberMusicMap() {
		MemberMusicMap.put(id, playList);	
		ioc.saveMap(MemberMusicMap, "MemberMusicMap");
	}
	
	public void printPlayList() {
		System.out.println("--------"+id+"님의 플레이 리스트----------");
		for(Music m : playList) {
			System.out.println(m);
		}
	}
	
}
