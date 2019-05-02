package musicPlayer.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import musicPlayer.controller.PlayerController;
import musicPlayer.model.vo.Member;


public class PlayerView{
	private PlayerController pc;
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
			if(e.getSource() == btnLogin) {
				
			}
			else if(e.getSource() == btnJoinM) {
				
			}
		}
	}
	
	private void addChartPanel() {
		
	}

}
