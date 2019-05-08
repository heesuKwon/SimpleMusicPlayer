package musicPlayer.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import musicPlayer.controller.LoginSignController;
import musicPlayer.controller.MemberManager;

public class SignView {
	private LoginSignController lsc;
	private JTextField textId; //입력id값

	public SignView(MemberManager memberM) {
		lsc=new LoginSignController(memberM);
		JFrame sign=new JFrame();
		sign.setSize(300, 300);
		sign.setLocationRelativeTo(null);
		sign.dispose();
		sign.setLayout(new GridLayout(10,1));
		JLabel labelName= new JLabel("이름");
		JTextField textName=new JTextField();
		JLabel labelId= new JLabel("아이디");
		textId = new JTextField();
		JButton idCheckBtn=new JButton("아이디 중복체크");
		idCheckBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String returnId=lsc.idCheck(textId.getText());
				textId.setText(returnId);
			}
		});
		JLabel labelPwd=new JLabel("비밀번호 입력");		
		JPasswordField textPwd=new JPasswordField();	
		JLabel labelPwd_=new JLabel("비밀번호 재확인");		
		JPasswordField textPwd_=new JPasswordField();
		JButton signBtn=new JButton("가입하기");
		signBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean check=lsc.signCheck(textName.getText(),textId.getText(),
						new String(textPwd.getPassword()).toString(),
						new String(textPwd_.getPassword()).toString());
				sign.setVisible(check);
			}
		});		
		sign.add(labelName);
		sign.add(textName);
		sign.add(labelId);
		sign.add(textId);
		sign.add(idCheckBtn);
		sign.add(labelPwd);
		sign.add(textPwd);
		sign.add(labelPwd_);
		sign.add(textPwd_);
		sign.add(signBtn);
		sign.setVisible(true);
	}


}
