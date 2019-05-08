package musicPlayer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import musicPlayer.common.MyUtil;
import musicPlayer.controller.LoginSignController;
import musicPlayer.model.vo.Member;

public class loginPanel extends JPanel{
	private LoginSignController lsc;
	private JFrame f;
	
	private Member m; //로그인한 회원
	
	private JTextField textFieldId;	//id
	private JPasswordField pwFieldPassword;	//password
	private JButton btnLogin;
	private JButton btnJoinM;
	
	public loginPanel(JFrame f){
		this.f = f;
//		loginPanel = new MyPanel(300, 0, 300, 500, null, Color.black, Color.white);
		setBounds(300, 0, 300, 500);
		setLayout(null);

		JLabel id = new JLabel("ID: ",JLabel.RIGHT);
		JLabel password = new JLabel("비밀번호: ",JLabel.RIGHT);
		textFieldId = new JTextField(10);
		pwFieldPassword = new JPasswordField(10);

		id.setBounds(30, 160, 80, 30);
		textFieldId.setBounds(110, 160, 100, 30);
		password.setBounds(30, 200, 80, 30); //y좌표 +40
		pwFieldPassword.setBounds(110, 200, 100, 30);

		add(id);
		add(textFieldId);
		add(password);
		add(pwFieldPassword);

		btnLogin = new JButton("로그인");
		btnJoinM = new JButton("회원가입");
		btnLogin.setBounds(60, 240, 80, 30);
		btnJoinM.setBounds(150, 240, 90, 30);

		//이벤트리스너등록
		LoginActionListener listener = new LoginActionListener();
		btnLogin.addActionListener(listener);
		btnJoinM.addActionListener(listener);

		add(btnLogin);
		add(btnJoinM);
		
		f.add(this);
	}
	
	private class LoginActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			lsc=new LoginSignController();
			//로그인 버튼 클릭시
			if(e.getSource() == btnLogin) {		
				m = lsc.loginCheck(textFieldId.getText(),new String(pwFieldPassword.getPassword()).toString());
				if(m.getId().equals("admin")) {
					//패널변경
//					//this는 MouseAdapter클래스를 가리키기 때문에 MainPanel.this를 해줘야 함.
					MyUtil.changePanel(f, loginPanel.this, new AdminPanel(f));
					new MusicAddFrame();
				}
				else {
					setVisible(false);
//					addMemberPanel();
				}
			}
			//회원가입 버튼 클릭시
			else if(e.getSource() == btnJoinM) {
				new SignView();
			}
		}
	}
}
