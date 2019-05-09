package musicPlayer.view;

import java.awt.Color;
<<<<<<< HEAD
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import musicPlayer.common.MyUtil;
import musicPlayer.controller.IOController;
import musicPlayer.controller.LoginSignController;
import musicPlayer.controller.MemberManager;
import musicPlayer.controller.MusicManager;
import musicPlayer.model.vo.Administrator;
=======
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import musicPlayer.controller.IOController;
import musicPlayer.controller.LoginSignController;
import musicPlayer.controller.MemberManager;
import musicPlayer.controller.MusicManager;
>>>>>>> branch 'master' of https://github.com/heesuKwon/SimpleMusicPlayer.git
import musicPlayer.model.vo.Member;
import musicPlayer.model.vo.Music;


public class MainViewFrame{
	private MemberManager MemberM = new MemberManager();
	private MusicManager MusicM = new MusicManager();
	private IOController mc = new IOController();
	private LoginSignController lsc;
	private Member m; //로그인한 회원
//	private Administrator admin = new Administrator();
		
	private JTable chartTable;//차트 테이블
	private int like=0;//좋아요 수 저장 변수
	private List<Music> allMusic = new ArrayList<>(); //음악 저장 리스트
	

	//컨테이너모음
	private JFrame f;
//	private JPanel rootPanel;
	private JPanel loginPanel;
	private JPanel chartPanel; //인기차트 
//	private JPanel adminPanel; //관리자 로그인 후 화면
	private JPanel memberPanel; //회원 로그인 후 화면


	//사용자입력컴포넌트
	private JTextField textFieldId;	//id
	private JPasswordField pwFieldPassword;	//password
	private JButton btnLogin;
	private JButton btnJoinM;
	private JButton btnLogout;

	public MainViewFrame(MemberManager MemberM) {	
		this.MemberM = MemberM;

		configureFrame();//프레임설정(size, location, title등)
//		addRootPanel();
		addLoginPanel();
//		addChartPanel();

//		f.add(rootPanel);
		f.setVisible(true);

	}

	private void configureFrame() {

		//int x, int y, int width, int height)
//		f = new MyFrame(400, 100, 900, 500);
		
		//MyFrame(int x,int y, int width, int height, boolean resizable, String title, String icon)
		f = new MyFrame(400, 100, 900, 500, false, "MusicPlayer", null);
	}

	private void addRootPanel() {

//		//(int x, int y, int width, int height, String borderTitle, Color borderColor, Color bgColor, LayoutManager mgr)
//		rootPanel = new MyPanel(0, 0, 900, 500, null, null, Color.LIGHT_GRAY);
//		rootPanel.setLayout(null); //기본 LayoutManger인 FlowLayout이 아니라 null로 함. 즉 좌표값으로 배치하겠다.
	}

	private void addLoginPanel() {
		loginPanel = new loginPanel(f);

//		loginPanel = new MyPanel(300, 0, 300, 500, null, Color.black, Color.white);
//		loginPanel.setLayout(null);
//
//		JLabel id = new JLabel("ID: ",JLabel.RIGHT);
//		JLabel password = new JLabel("비밀번호: ",JLabel.RIGHT);
//		textFieldId = new JTextField(10);
//		pwFieldPassword = new JPasswordField(10);
//
//		id.setBounds(30, 160, 80, 30);
//		textFieldId.setBounds(110, 160, 100, 30);
//		password.setBounds(30, 200, 80, 30); //y좌표 +40
//		pwFieldPassword.setBounds(110, 200, 100, 30);
//
//		loginPanel.add(id);
//		loginPanel.add(textFieldId);
//		loginPanel.add(password);
//		loginPanel.add(pwFieldPassword);
//
//		btnLogin = new JButton("로그인");
//		btnJoinM = new JButton("회원가입");
//		btnLogin.setBounds(60, 240, 80, 30);
//		btnJoinM.setBounds(150, 240, 90, 30);
//
//		//이벤트리스너등록
//		LoginActionListener listener = new LoginActionListener();
//		btnLogin.addActionListener(listener);
//		btnJoinM.addActionListener(listener);
//
//		loginPanel.add(btnLogin);
//		loginPanel.add(btnJoinM);
//
////		rootPanel.add(loginPanel);
//		f.add(loginPanel);
	}
//
//	private class LoginActionListener implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			lsc=new LoginSignController();
//			//로그인 버튼 클릭시
//			if(e.getSource() == btnLogin) {		
//				m = lsc.loginCheck(textFieldId.getText(),new String(pwFieldPassword.getPassword()).toString());
//				if(m.getId().equals("admin")) {
//					addAdminPanel();
//					//패널변경
//					//this는 MouseAdapter클래스를 가리키기 때문에 MainPanel.this를 해줘야 함.
//					MyUtil.changePanel(f, loginPanel, adminPanel);
////					loginPanel.setVisible(false);								
//					new MusicManagerView();
//				}
//				else {
//					loginPanel.setVisible(false);
//					addMemberPanel();
//				}
//			}
//			//회원가입 버튼 클릭시
//			else if(e.getSource() == btnJoinM) {
//				new SignView();
//			}
//			//로그아웃 버튼 클릭시
//			else if(e.getSource() == btnLogout){
//				loginPanel.setVisible(true);
//			}
//		}
//	}

	//음악 차트 패널
	public void addChartPanel() {
		allMusic = MusicM.getAllMusic();
		chartPanel = new MyPanel(0, 0, 300, 500, null, Color.black, Color.WHITE);
		//JTable 컬럼 저장
		String[] columns= {"곡명","가수","장르","재생시간","좋아요"," "};
		//JTable 데이터 저장
		Object[][] data=new Object[allMusic.size()][columns.length];		
		for(int i=0;i<data.length;i++) {
			data[i][0]=allMusic.get(i).getTitle();
			data[i][1]=allMusic.get(i).getArtist();
			data[i][2]=allMusic.get(i).getGenre();
			data[i][3]=allMusic.get(i).getSeconds();
			data[i][4]=allMusic.get(i).getLike();
			data[i][5]=new JButton("♥");	
		}	

		//Jtable 내용 편집 안되도록 하는 기능
		@SuppressWarnings("serial")
		DefaultTableModel model=new DefaultTableModel(data, columns){								
			@Override
			public boolean isCellEditable(int row,int column) {				
				//마우스로 클릭시 자표값을 like메소드로 보내준다(value값을 얻기 위해서) 
				like(row,column);
				return false;
			}

		};		
		chartTable = new JTable(model);

		//만들어둔 JTableButtonRenderer 클래스를 이용해서 JTable에 JButton 넣는 기능 
		TableCellRenderer buttonRenderer = new JTableButtonRenderer();
		chartTable.getColumn(" ").setCellRenderer(buttonRenderer);
		//JTable 크기 조절 기능
		chartTable.setPreferredScrollableViewportSize(new Dimension(300,500));
		//JTable 스크롤 기능
		JScrollPane scr=new JScrollPane(chartTable);
		chartPanel.add(scr);
//		rootPanel.add(chartPanel);
		f.add(chartPanel);
	}
	//좋아요 클릭 기능 + 좋아요 순서 내림차순 기능
	public void like(int row, int column) {
		if(column==5) {
			//클릭한 노래의 like 수 저장
			like=allMusic.get(row).getLike();

			allMusic.set(row, new Music(allMusic.get(row).getPath(), 
					allMusic.get(row).getCode(), allMusic.get(row).getTitle(), 
					allMusic.get(row).getArtist(), allMusic.get(row).getGenre(), 
					allMusic.get(row).getOpenYear(), ++like, allMusic.get(row).getSeconds()));

			chartPanel.setVisible(false);
			//내림차순 기능
			Comparator<Music> comp=new descendingByLike();
			Collections.sort(allMusic, comp);
			addChartPanel();
		}
	}

	//관리자 페이지 패널
//	private void addAdminPanel() {
//		adminPanel = new MyPanel(300, 0, 300, 500, null, Color.black, Color.white);		
//		JLabel idLabel=new JLabel();
//		idLabel.setText(m.getName()+"님 환영합니다.");	
//		adminPanel.add(idLabel);
//		
//		//로그아웃 버튼
//		btnLogout = new JButton("로그아웃");
//		//이벤트리스너 연결
////		LoginActionListener listener = new LoginActionListener();
////		btnLogout.addActionListener(listener);
//		adminPanel.add(btnLogout);
//		
////		rootPanel.add(adminPanel);
//		f.add(adminPanel);
//	}
	//회원 페이지 패널
	private void addMemberPanel() {
		memberPanel = new MyPanel(300, 0, 300, 500, null, Color.black, Color.white);		
		JLabel idLabel=new JLabel();
		idLabel.setText(m.getName()+"님 환영합니다.");	
		memberPanel.add(idLabel);
		
		//로그아웃 버튼
		btnLogout = new JButton("로그아웃");
		//이벤트리스너 연결
//		LoginActionListener listener = new LoginActionListener();
//		btnLogout.addActionListener(listener);
		memberPanel.add(btnLogout);
		
//		rootPanel.add(memberPanel);
		f.add(memberPanel);
	}
}
/*
//JTable에 JButton 넣는 기능 클래스
class JTableButtonRenderer implements TableCellRenderer {        
	@Override 
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		JButton button = (JButton)value;
		return button;  
	}
}
//곡 좋아요 순서 내림차순 기능 클래스
class descendingByLike implements Comparator<Music>{
	@Override
	public int compare(Music o1, Music o2) {

		return o2.getLike()-o1.getLike();
	}

}
*/
