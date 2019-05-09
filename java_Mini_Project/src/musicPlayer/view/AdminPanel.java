package musicPlayer.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

<<<<<<< HEAD
import musicPlayer.model.vo.Member;

=======
>>>>>>> branch 'master' of https://github.com/heesuKwon/SimpleMusicPlayer.git
public class AdminPanel extends JPanel{
	private JFrame f;
	
	private JButton btnLogout;

	public AdminPanel(JFrame f){
		this.f = f;
		
//		adminPanel = new MyPanel(300, 0, 300, 500, null, Color.black, Color.white);	
		setBounds(300, 0, 300, 500);
		setLayout(null);
		
		JLabel idLabel=new JLabel("환영합니다.");
//		idLabel.setText(m.getName()+"님 환영합니다.");	
		add(idLabel);
		
		//로그아웃 버튼
		btnLogout = new JButton("로그아웃");
		//이벤트리스너 연결
//		LoginActionListener listener = new LoginActionListener();
//		btnLogout.addActionListener(listener);
		add(btnLogout);
		
//		rootPanel.add(adminPanel);
//		f.add(this);
	}
}
