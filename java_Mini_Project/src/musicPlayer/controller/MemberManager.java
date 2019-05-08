package musicPlayer.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import musicPlayer.model.vo.Member;

//회원정보 관리
public class MemberManager {
	private List<Member> MemberList;
	private IOController ioc = new IOController();//입출력 객체
	private HashSet<Long> likeCheck = new HashSet<>(); 
	
	public MemberManager(){
		MemberList = (List)ioc.loadList("Members");
		System.out.println("객체 생성");
		//음악 파일이 비어있으면
		if(MemberList == null){
			MemberList = new ArrayList<>();
		}
	}
	
	public List<Member> getMemberList(){
		return MemberList;
	}
	
	//멤버 갱신 메소드
    public void setAllMember(List<Member> MemberList) {
        this.MemberList = MemberList;
        ioc.saveList(MemberList, "Members");
    }
	
  //멤버 한명 추가
    public void addMember(String name, String id, String password){
        Member m = new Member(name, id, password, likeCheck);
  
        MemberList.add(m); 
        ioc.saveList(MemberList, "Members");        
    }
    
    public void printMemberList() {
    	System.out.println("멤버 리스트 확인");
    	for(Member m : MemberList) {
    		System.out.println(m);
    	}
    }
}
