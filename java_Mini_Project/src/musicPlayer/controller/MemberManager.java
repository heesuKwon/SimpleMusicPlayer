package musicPlayer.controller;

import java.util.ArrayList;
import java.util.List;

import musicPlayer.model.vo.Member;

//회원정보 관리
public class MemberManager {
	private List<Member> MemberList;
	private IOController ioc = new IOController();//입출력 객체
	
	public MemberManager(){
		MemberList = (List)ioc.loadList("Members");
		//음악 파일이 비어있으면
		if(MemberList == null){
			MemberList = new ArrayList<>();
		}
	}
	
	public List<Member> getMemberList(){
		return MemberList;
	}
	
	//멤버 한명 추가
	public void addMember(String name, String id, String password){
		Member m = new Member(name, id, password);
		MemberList.add(m);		
		ioc.saveList(MemberList, "Members");		
	}
}
