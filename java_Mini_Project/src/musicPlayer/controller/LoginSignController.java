package musicPlayer.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import musicPlayer.model.vo.Member;

public class LoginSignController {
//	private List<Member> mList = new ArrayList<>();

	private boolean loginOk=false;//로그인ok 
	private boolean signOk=false;//회원가입 가능여부 최종확인
	private boolean idCheck=false;//아이디중복체크
	private String id="";//중복확인 후 id값
	private Member m;//로그인에 성공한 회원정보
		
	public Member getMember() {
		return m;
	}

	//아이디 체크
	public String idCheck(String id) {
		String returnId="";
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
		}
		else {
			String filePath="MemberInfo.txt";
//			Member m = null;
			String line = "";

			try (BufferedReader br = new BufferedReader(new FileReader(filePath))){		
//			try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)));){
//				m = (Member)ois.readObject();
				
				while((line=br.readLine())!=null) {

					String[] str=line.split(",");
					if((str[1]).equals(id)) {
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

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnId;
	}
	
	//로그인 체크
	public void loginCheck(String id,String password) {
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
		}else if(password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
		}else {
			String line="";
			try (BufferedReader br = new BufferedReader(new FileReader("MemberInfo.txt"))){				
				while((line=br.readLine())!=null) {
					String[] str=line.split(",");
					if(str[1].equals(id)&&str[2].equals(password)) {
						loginOk=true;
						JOptionPane.showMessageDialog(null, "로그인 되셨습니다.");
						m = new Member(str[0],str[1],str[2]);
						break;
					}	
				}
				if(loginOk==false) {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 확인해주세요");					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
			signOk(textName,textId,textPwd);
			JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다");
			check=false;
		}else {
			JOptionPane.showMessageDialog(null, msg);
		}
		return check;
	}

	//회원가입 체크 완료하면 실행
	//회원정보 저장
	public void signOk(String name, String id, String password) {
		String filePath="MemberInfo.txt";
		Member m = new Member(name, id, password);
//		mList.add(m);
		/*try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));){
			oos.writeObject(m+"\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		} */
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
				BufferedReader br = new BufferedReader(new FileReader(filePath))){		
			
			if(br.readLine()==null) {
				bw.write(name+","+id+","+password);	
			}else {
				bw.write("\n"+name+","+id+","+password);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}		
	}
}
