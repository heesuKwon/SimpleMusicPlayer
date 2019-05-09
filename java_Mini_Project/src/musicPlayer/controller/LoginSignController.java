package musicPlayer.controller;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashSet;
=======
>>>>>>> branch 'master' of https://github.com/heesuKwon/SimpleMusicPlayer.git
import java.util.List;

import javax.swing.JOptionPane;

import musicPlayer.model.vo.Member;

public class LoginSignController {
	private List<Member> MemberList = new ArrayList<>();
	private MemberManager memberM;

	private boolean loginOk=false;//로그인ok 
	private boolean signOk=false;//회원가입 가능여부 최종확인
	private boolean idCheck=false;//아이디중복체크
	private String id="";//중복확인 후 id값
	
	public LoginSignController(MemberManager memberM){
		this.memberM = memberM;
		MemberList = memberM.getMemberList();
	}


	//아이디 체크
	public String idCheck(String id) {
		String returnId="";
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
		}
		else {
			for(Member m : MemberList){
				if((m.getId()).equals(id)) {
					JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.");				
					idCheck=true;
					signOk=false;
					break;
				}
			}
			if(idCheck==false) {
				JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다.");
				this.id=id;
				returnId=id;
				signOk=true;
			}
			idCheck=false;
		}
		return returnId;
	}
	
	//로그인 체크
    public Member loginCheck(String id,String password) {
        Member loginM = null;
        if(id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
        }else if(password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
        }else {
        	for(Member m : MemberList){
        		if(m.getId().equals(id)&&m.getPassword().equals(password)) {
        			loginOk=true;
        			JOptionPane.showMessageDialog(null, "로그인 되셨습니다.");
        			loginM=m;
        			break;
        		}   
        	}
        	if(loginOk==false) {
        		JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 확인해주세요");                   
        	}

        }
        return loginM;
    }
	
	//회원가입 체크
	public boolean signCheck(String textName,String textId,String textPwd,String textPwd_) {

		boolean check=true;
		String msg="";
		if(textName.isEmpty()) {
			signOk=false;
			msg="이름을 입력해주세요";				
		}else if(textId.isEmpty()) {
			signOk=false;
			msg="아이디를 입력해주세요";
		}else if(textPwd.isEmpty()||
				textPwd_.isEmpty()) {
			signOk=false;
			msg="비밀번호를 입력해주세요";										
		}else if(!(textPwd).equals
				(textPwd_)) {					
			signOk=false;
			msg="비밀번호를 확인해 주세요";
		}else{
			signOk=true;
		}
		if(!(id.equals(textId))) {
			signOk=false;
			msg="아이디 중복체크 해주세요";
			id="";
		}		
		if(signOk==true) {
			//회원정보 저장
			memberM.addMember(textName, textId, textPwd);
			JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다");
			check=false;
		}else {
			JOptionPane.showMessageDialog(null, msg);
		}
		return check;
	}

}
