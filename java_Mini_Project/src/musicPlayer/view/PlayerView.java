package musicPlayer.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

import musicPlayer.controller.MemberController;
import musicPlayer.controller.PlayerController;
import musicPlayer.model.vo.Member;
import musicPlayer.model.vo.Music;


public class PlayerView{
	private PlayerController pc;
	private MemberController mc = new MemberController();
	private Member m;
	
	//컨테이너모음
	private JFrame f;
	private JPanel rootPanel;
	private JPanel loginPanel;
	
	//사용자입력컴포넌트
	private JTextField textFieldId;	//id
	private JPasswordField pwFieldPassword;	//password
	private JButton btnLogin;
	private JButton btnJoinM;
	
	public PlayerView(PlayerController pc) {	
		this.pc = pc;
		
		configureFrame();//프레임설정(size, location, title등)
		addRootPanel();
		addLoginPanel();
		addChartPanel();
		
		f.add(rootPanel);
		f.setVisible(true);
		
	}
	
	private void configureFrame() {
		
		//int x, int y, int width, int height)
		f = new MyFrame(400, 100, 900, 500);
	}
	
	private void addRootPanel() {
			
		//(int x, int y, int width, int height, String borderTitle, Color borderColor, Color bgColor, LayoutManager mgr)
		rootPanel = new MyPanel(0, 0, 900, 500, null, null, Color.LIGHT_GRAY);
		rootPanel.setLayout(null); //기본 LayoutManger인 FlowLayout이 아니라 null로 함. 즉 좌표값으로 배치하겠다.
	}
	
	private void addLoginPanel() {
		
		loginPanel = new MyPanel(300, 0, 300, 500, null, Color.black, Color.white);
		loginPanel.setLayout(null);
		
		JLabel id = new JLabel("ID: ",JLabel.RIGHT);
		JLabel password = new JLabel("비밀번호: ",JLabel.RIGHT);
		textFieldId = new JTextField(10);
		pwFieldPassword = new JPasswordField(10);
		
		id.setBounds(30, 160, 80, 30);
		textFieldId.setBounds(110, 160, 100, 30);
		password.setBounds(30, 200, 80, 30); //y좌표 +40
		pwFieldPassword.setBounds(110, 200, 100, 30);
		
		loginPanel.add(id);
		loginPanel.add(textFieldId);
		loginPanel.add(password);
		loginPanel.add(pwFieldPassword);
		
		btnLogin = new JButton("로그인");
		btnJoinM = new JButton("회원가입");
		btnLogin.setBounds(60, 240, 80, 30);
		btnJoinM.setBounds(150, 240, 90, 30);
		
		//이벤트리스너등록
		LoginActionListener listener = new LoginActionListener();
		btnLogin.addActionListener(listener);
		btnJoinM.addActionListener(listener);
		
		loginPanel.add(btnLogin);
		loginPanel.add(btnJoinM);

		rootPanel.add(loginPanel);
	}
	
	private class LoginActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//로그인 버튼을 눌렀을 때
			if(e.getSource() == btnLogin) {
				String id = textFieldId.getText();
				//로그인시 회원정보 파일을 가져와서 그 안의 파일 이름과 id를 비교하여 있으면 id존재
				//id가 이름인 파일을 member변수에 넣어서 비밀번호가 입력한 값과 같은지 확인
				File fs = new File("Members");
				if(fs.isDirectory()) {
					File list[] = fs.listFiles();
					boolean flag = true;
					for(File f : list) {
						String filename = f.toString();
						System.out.println(filename);
						try{
							m = mc.loadMember(f.toString());
							//파일명과 id가 같으면 password 검사
							if(filename.equals(id)) {
								flag = false;
								String pw = pwFieldPassword.getPassword().toString();
								if(m.getPassword().equals(pw)) {
									System.out.println("로그인에 성공하셨습니다.");
								}
								
							}
		                    
		                }catch(Exception ex){
		                    ex.printStackTrace();
		                }
					}
					//회원정보를 못찾으면
					if(flag) {
						System.out.println("회원정보가 없습니다.");
					}
				}
				//회원정보가 하나도 없으면
				else {
					System.out.println("회원정보 폴더가 비어있습니다.");
				}
			}
			//회언가입 버튼을 눌렀을 때
			else if(e.getSource() == btnJoinM) {
				//회원가입 창 팝업 
				//-> 가입하기 누르면 membercontroller로 넘어가서 회원정보 저장 
			}
		}
	}
	
	private void addChartPanel() {
		
	}

}
