package musicPlayer.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
<<<<<<< HEAD
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import musicPlayer.controller.PlayListByMemberManager;
import musicPlayer.model.vo.Member;
import musicPlayer.model.vo.Music;


public class MainViewFrame2{
	private MemberManager memberM = new MemberManager(); //멤버매니저는 한번만 생성
	private MusicManager musicM = new MusicManager();
	private IOController mc = new IOController();
	private LoginSignController lsc;
	private Member member; //로그인한 회원
	private PlayListByMemberManager playList;
	
//	private Music music; //플레이리스트에 추가할 노래	
	private boolean boolLogin = false;
	private JTable chartTable;//차트 테이블
	private int like=0;//좋아요 수 저장 변수
	private List<Music> allMusic = new ArrayList<>(); //모든 음악 저장 리스트
	private List<Member> memberList = new ArrayList<>();
	

	//컨테이너모음
	private JFrame f;
	private JPanel rootPanel;
	private JPanel loginPanel;
	private JPanel chartPanel; //인기차트 
	private JPanel adminPanel; //관리자 로그인 후 화면
	private JPanel memberPanel; //회원 로그인 후 화면


	//사용자입력컴포넌트
	private JTextField textFieldId;	//id
	private JPasswordField pwFieldPassword;	//password
	private JButton btnLogin;
	private JButton btnJoinM;
	private JButton btnLogout;

	public MainViewFrame2() {	

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

		loginPanel = new MyPanel(500, 0, 400, 500, null, Color.black, Color.white);
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
			//로그인 버튼 클릭시
			if(e.getSource() == btnLogin) {		
				lsc=new LoginSignController(memberM);
				member = lsc.loginCheck(textFieldId.getText(),new String(pwFieldPassword.getPassword()).toString());
				if(member.getId().equals("admin")) {
					//패널변경
					//this는 MouseAdapter클래스를 가리키기 때문에 MainPanel.this를 해줘야 함.
//					MyUtil.changePanel(rootPanel, loginPanel, adminPanel);
					loginPanel.setVisible(false);								
					addAdminPanel();
					
				}
				else {
					loginPanel.setVisible(false);
					playList = new PlayListByMemberManager(member.getId());//회원 로그인시 플레이리스트 생성
					boolLogin = true;
					addMemberPanel();
				}
			}
			//회원가입 버튼 클릭시
			else if(e.getSource() == btnJoinM) {
				new SignView(memberM);
			}
		}
	}

	//음악 차트 패널
    public void addChartPanel() {
        allMusic = musicM.getAllMusic();
        chartPanel = new MyPanel(0, 0, 500, 500, null, Color.black, Color.WHITE);
        
        JLabel play = new JLabel("인기 차트");
        chartPanel.add(play);
        
        //JTable 컬럼 저장
        String[] columns= {"곡명","가수","재생","좋아요"," "};
        //JTable 데이터 저장
        Object[][] data=new Object[allMusic.size()][columns.length];        
        for(int i=0;i<data.length;i++) {
            data[i][0]=allMusic.get(i).getTitle();
            data[i][1]=allMusic.get(i).getArtist();
            data[i][2]=new JButton("▶");
            data[i][3]=allMusic.get(i).getLike();
            data[i][4]=new JButton("♥");    
        }    

        //Jtable 내용 편집 안되도록 하는 기능
        @SuppressWarnings("serial")
        DefaultTableModel model=new DefaultTableModel(data, columns){                                
            @Override
            public boolean isCellEditable(int row,int column) {                
                //마우스로 클릭시 자표값을 like메소드로 보내준다(value값을 얻기 위해서)
            	if(boolLogin) {
            		if(column==4) {
            			like(row,column);
            		}
            		//재생버튼을 눌렀을 때
            		else if(column == 2) {
            			playList.addPlayList(allMusic.get(row));
            			playList.printPlayList();
            			//재생되는 부분 코딩해주세요
            		}
            	}
                else {
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.");
                }
                return false;
            }

        };        
        chartTable = new JTable(model);

        //만들어둔 JTableButtonRenderer 클래스를 이용해서 JTable에 JButton 넣는 기능
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        chartTable.getColumn(" ").setCellRenderer(buttonRenderer);
        chartTable.getColumn("재생").setCellRenderer(buttonRenderer);
        //JTable 크기 조절 기능
        chartTable.setPreferredScrollableViewportSize(new Dimension(500,500));
        //JTable 스크롤 기능
        JScrollPane scr=new JScrollPane(chartTable);
        
        chartPanel.add(scr);
        rootPanel.add(chartPanel);
    }
	
  //좋아요 클릭 기능 + 좋아요 순서 내림차순 기능 + 좋아요 1번만 클릭가능 기능
    public void like(int row, int column) {
        //클릭한 노래의 like 수 저장
        Music m = allMusic.get(row);
        HashSet<Long> likeCheck=new HashSet<Long>(); //좋아요 클릭 여부 HashSet
        likeCheck = member.getLikeCheck();
        //좋아요 클릭한 곡 확인 if문
        if(likeCheck.add(m.getCode())) {
        	member.setLikeCheck(likeCheck);
            like = m.getLike();
            allMusic.set(row, new Music(m.getPath(), m.getCode(), m.getTitle(), m.getArtist(),
                    m.getGenre(), m.getOpenYear(), ++like, m.getSeconds()));
            chartPanel.setVisible(false);
            //내림차순 기능
            Comparator<Music> comp=new descendingByLike();
            Collections.sort(allMusic, comp);
            musicM.setAllMusic(allMusic);
            //곡 좋아요 클릭했을때 member에 저장
            
            memberList=memberM.getMemberList();
            for(int i=0;i<memberList.size();i++) {
            	Member mem = memberList.get(i);
                if(mem.getId().equals(member.getId())) {
//                	System.out.println("like 누른 후 "+member);
                	
                    memberList.set(i, member);
                }
            }
            memberM.setAllMember(memberList);
            addChartPanel();
        }else {
            JOptionPane.showMessageDialog(null, "좋아요 한번만 클릭 가능");
        }
    }

	//관리자 페이지 패널
	private void addAdminPanel() {
		adminPanel = new MyPanel(500, 0, 400, 500, null, Color.black, Color.white);		
		JLabel idLabel=new JLabel();
		idLabel.setText(member.getName()+"님 환영합니다.");	
		adminPanel.add(idLabel);
		
		//로그아웃 버튼
		btnLogout = new JButton("로그아웃");
		//이벤트리스너 연결
		btnLogout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnLogout){
					adminPanel.setVisible(false);
					addLoginPanel();
				}
			}
		});
		adminPanel.add(btnLogout);
		
		JButton btnAddMusic = new JButton("곡 추가");
		btnAddMusic.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new MusicManagerView();
			}
		});
		adminPanel.add(btnAddMusic);
		
		JButton btnManageMember = new JButton("멤버 관리");
		btnManageMember.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new MemberManagerView(memberM.getMemberList());
			}
		});
		adminPanel.add(btnManageMember);
		
		rootPanel.add(adminPanel);
	}
	//회원 페이지 패널
	private void addMemberPanel() {
		memberPanel = new MyPanel(500, 0, 400, 500, null, Color.black, Color.white);		
		JLabel idLabel=new JLabel();
		idLabel.setText(member.getName()+"님 환영합니다.");	
		memberPanel.add(idLabel);
		
		//로그아웃 버튼
		btnLogout = new JButton("로그아웃");
		//이벤트리스너 연결
		btnLogout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnLogout) {
//					playList.setMemberMusicMap();//회원 로그아웃시 플레이리스트를 맵으로 저장 -> 그냥 창을 껐을 때는 저장이 안됌...
					memberPanel.setVisible(false);
					addLoginPanel();
				}
			}
		});
		memberPanel.add(btnLogout);
		
		rootPanel.add(memberPanel);
=======
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javazoom.jl.decoder.JavaLayerException;
import musicPlayer.controller.LoginSignController;
import musicPlayer.controller.MemberManager;
import musicPlayer.controller.MusicManager;
import musicPlayer.controller.PlayListByMemberManager;
import musicPlayer.model.vo.Member;
import musicPlayer.model.vo.Music;


public class MainViewFrame2{
	private MemberManager memberM = new MemberManager(); //멤버매니저 한번만 생성
	private MusicManager musicM = new MusicManager(); //뮤직매니저 한번만 생성
	
	private LoginSignController lsc;
	private Member member; //로그인한 회원
	private PlayListByMemberManager playListM; //회원 로그인시 플레이리스트 객체 생성
	
//	private Music music; //플레이리스트에 추가할 노래	
	private boolean boolLogin = false;
	private JTable chartTable;//차트 테이블
	private int like=0;//좋아요 수 저장 변수
	private List<Music> allMusic = new ArrayList<>(); //모든 음악 저장 리스트
	private List<Member> memberList = new ArrayList<>(); //모든 회원 저장 리스트

	//컨테이너모음
	private JFrame f;
	private JPanel rootPanel;
	private JPanel loginPanel;
	public JPanel chartPanel; //인기차트 
	private JPanel adminPanel; //관리자 로그인 후 화면
	private JPanel memberPanel; //회원 로그인 후 화면
	private JPanel playListView;

	//사용자입력컴포넌트
	private JTextField textFieldId;	//id
	private JPasswordField pwFieldPassword;	//password
	private JButton btnLogin;
	private JButton btnJoinM;
	private JButton btnLogout;
	private JButton btnMyInfo;

	public MainViewFrame2() {	

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

		loginPanel = new MyPanel(500, 0, 400, 500, null, Color.black, Color.white);
		loginPanel.setLayout(null);
		
		Image scaledImage = 
				new ImageIcon("images/logo.jpg").getImage()
												.getScaledInstance(300, 150, 0);
		JLabel logo = new JLabel(new ImageIcon(scaledImage));
		logo.setBounds(50, 30, 300, 150);

		JLabel id = new JLabel("ID: ",JLabel.RIGHT);
		JLabel password = new JLabel("비밀번호: ",JLabel.RIGHT);
		textFieldId = new JTextField(10);
		pwFieldPassword = new JPasswordField(10);

		id.setBounds(90, 200, 80, 30);
		textFieldId.setBounds(170, 200, 100, 30);
		password.setBounds(90, 240, 80, 30); //y좌표 +40
		pwFieldPassword.setBounds(170, 240, 100, 30);

		loginPanel.add(logo);
		loginPanel.add(id);
		loginPanel.add(textFieldId);
		loginPanel.add(password);
		loginPanel.add(pwFieldPassword);

		btnLogin = new JButton("로그인");
		btnJoinM = new JButton("회원가입");
		btnLogin.setBounds(100, 280, 80, 30); //y좌표+40
		btnJoinM.setBounds(200, 280, 90, 30);

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
			//로그인 버튼 클릭시
			if(e.getSource() == btnLogin) {		
				lsc=new LoginSignController(memberM);
				member = lsc.loginCheck(textFieldId.getText(),new String(pwFieldPassword.getPassword()).toString());
				if(member.getId().equals("admin")) {
					//패널변경
					//this는 MouseAdapter클래스를 가리키기 때문에 MainPanel.this를 해줘야 함.
//					MyUtil.changePanel(rootPanel, loginPanel, adminPanel);
					loginPanel.setVisible(false);								
					addAdminPanel();
					
				}
				else {
					loginPanel.setVisible(false);
					playListM = new PlayListByMemberManager(member.getId());//회원 로그인시 플레이리스트 생성
					boolLogin = true;
					addMemberPanel();
				}
			}
			//회원가입 버튼 클릭시
			else if(e.getSource() == btnJoinM) {
				new SignView(memberM);
			}
		}
	}

	//음악 차트 패널
    public void addChartPanel() {
        allMusic = musicM.getAllMusic();
        chartPanel = new MyPanel(0, 0, 500, 500, null, Color.black, Color.white);
        
        JLabel play = new JLabel("인기 차트");
        chartPanel.add(play);
        
        //JTable 컬럼 저장
        String[] columns= {"곡명","가수","재생","좋아요"," "};
        //JTable 데이터 저장
        Object[][] data=new Object[allMusic.size()][columns.length];        
        for(int i=0;i<data.length;i++) {
            data[i][0]=allMusic.get(i).getTitle();
            data[i][1]=allMusic.get(i).getArtist();
            data[i][2]=new JButton("▶");
            data[i][3]=allMusic.get(i).getLike();
            data[i][4]=new JButton("♥");    
        }    

        //Jtable 내용 편집 안되도록 하는 기능
        @SuppressWarnings("serial")
        DefaultTableModel model=new DefaultTableModel(data, columns){                                
            @Override
            public boolean isCellEditable(int row,int column) {                
                //마우스로 클릭시 자표값을 like메소드로 보내준다(value값을 얻기 위해서)
            	if(boolLogin) {
            		if(column==4) {
            			like(row,column);
            		}
            		//재생버튼을 눌렀을 때
            		else if(column == 2) {
            			playListM.addPlayList(allMusic.get(row));	//플레이리스트에 곡 추가      			
//            			playListM.printPlayList();
            			playListView.setVisible(false);
            			addPlayListPanel();
            		}
            	}
                else {
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.");
                }
                return false;
            }

        };        
        chartTable = new JTable(model);

        //만들어둔 JTableButtonRenderer 클래스를 이용해서 JTable에 JButton 넣는 기능
        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        chartTable.getColumn(" ").setCellRenderer(buttonRenderer);
        chartTable.getColumn("재생").setCellRenderer(buttonRenderer);
        //JTable 크기 조절 기능
        chartTable.setPreferredScrollableViewportSize(new Dimension(494,500));
        //JTable 스크롤 기능
        JScrollPane scr=new JScrollPane(chartTable);
        
        chartPanel.add(scr);
        chartPanel.setVisible(true);
        
        rootPanel.add(chartPanel);
    }
	
  //좋아요 클릭 기능 + 좋아요 순서 내림차순 기능 + 좋아요 1번만 클릭가능 기능
    public void like(int row, int column) {
        //클릭한 노래의 like 수 저장
        Music m = allMusic.get(row);
        HashSet<Long> likeCheck=new HashSet<Long>(); //좋아요 클릭 여부 HashSet
        likeCheck = member.getLikeCheck();
        //좋아요 클릭한 곡 확인 if문
        if(likeCheck.add(m.getCode())) {
        	member.setLikeCheck(likeCheck);
            like = m.getLike();
            allMusic.set(row, new Music(m.getPath(), m.getCode(), m.getTitle(), m.getArtist(),
                    m.getGenre(), m.getOpenYear(), ++like, m.getSeconds()));
            chartPanel.setVisible(false);
            //내림차순 기능
            Comparator<Music> comp=new descendingByLike();
            Collections.sort(allMusic, comp);
            musicM.setAllMusic(allMusic);
            //곡 좋아요 클릭했을때 member에 저장
            
            memberList=memberM.getMemberList();
            for(int i=0;i<memberList.size();i++) {
            	Member mem = memberList.get(i);
                if(mem.getId().equals(member.getId())) {
//                	System.out.println("like 누른 후 "+member);         	
                    memberList.set(i, member);
                }
            }
            memberM.setAllMember(memberList);
            addChartPanel();
        }else {
            JOptionPane.showMessageDialog(null, "좋아요 한번만 클릭 가능");
        }
    }

	//관리자 페이지 패널
	private void addAdminPanel() {
		adminPanel = new MyPanel(500, 0, 400, 500, null, Color.black, Color.white);		
		JLabel idLabel=new JLabel();
		idLabel.setText(member.getName()+"님 환영합니다.");	
		adminPanel.add(idLabel);
		
		//로그아웃 버튼
		btnLogout = new JButton("로그아웃");
		//이벤트리스너 연결
		btnLogout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnLogout){
					adminPanel.setVisible(false);
					addLoginPanel();
				}
			}
		});
		adminPanel.add(btnLogout);
		
		JButton btnAddMusic = new JButton("곡 추가");
		btnAddMusic.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new MusicAddFrame(musicM, MainViewFrame2.this);
			}
		});
		adminPanel.add(btnAddMusic);
		
		JButton btnRemoveMusic = new JButton("곡 삭제");
		btnRemoveMusic.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new MusicRemoveFrame(musicM, MainViewFrame2.this);
			}
		});
		adminPanel.add(btnRemoveMusic);
		
		JButton btnManageMember = new JButton("멤버 관리");
		btnManageMember.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new MemberManagerView(memberM.getMemberList());
			}
		});
		adminPanel.add(btnManageMember);
		
		rootPanel.add(adminPanel);
	}
	//회원 페이지 패널
	private void addMemberPanel() {
		memberPanel = new MyPanel(500, 0, 400, 500, null, Color.black, Color.white);		
		memberPanel.setLayout(null);
		JLabel idLabel=new JLabel(member.getName()+"님 환영합니다.", JLabel.CENTER);
//		idLabel.setText(member.getName()+"님 환영합니다.",JLabel.CENTER);	
		idLabel.setBounds(0, 0, 400, 30);
		memberPanel.add(idLabel);
		
		//로그아웃 버튼
		btnLogout = new JButton("로그아웃");
		btnLogout.setBounds(100,30,90,30);
		btnMyInfo = new JButton("개인정보");
		btnMyInfo.setBounds(200,30,90,30);
		
		//이벤트리스너 연결
		btnLogout.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnLogout) {
					memberPanel.setVisible(false);
					addLoginPanel();
				}
			}
		});
		//회원정보 버튼
//		JButton btnMember =new JButton("회원정보");
		btnMyInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MemberView(member);

			}
		});
		
		addPlayListPanel();
		
		memberPanel.add(btnLogout);
//		memberPanel.add(btnMember);
		memberPanel.add(btnMyInfo);
		rootPanel.add(memberPanel);
	}
	
	public void addPlayListPanel(){
		try {
			playListView = new PlayListView(playListM.getPlayList());
		} catch(JavaLayerException e){
			e.printStackTrace();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			
		}
		memberPanel.add(playListView);
>>>>>>> branch 'master' of https://github.com/heesuKwon/SimpleMusicPlayer.git
	}
}

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

