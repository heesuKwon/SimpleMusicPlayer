package musicPlayer.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import musicPlayer.model.vo.Music;
public class MusicSearch extends JFrame{
	private MainViewFrame mainView;
	
	//MusicManager 클래스에서 가져옴
	private List<Music> allMusic;
	private List<Music> playList;
	private HashSet<Music> searchMusic=new HashSet<Music>();
	
	//컴포넌트 추가
	private JPanel panel6;
	private JTextField textFieldTitle;
	private JTextField textFieldArtist;
	private JCheckBox[] chkYear;
	private JCheckBox[] chkGenre;
	private JButton btnSearch;
	
	private boolean flag = true;
	
	public MusicSearch(List<Music> allMusic, List<Music> playList, MainViewFrame mainView) {
		this.playList = playList;
		this.allMusic = allMusic;
		this.mainView = mainView;
		if(playList == null){
			flag = false;
		}
		
		configureFrame();//프레임설정(size, location, title등)
		addPanel1();//곡명, 가수
		addPanel3();//장르 체크박스
		addPanel4();//발매년도
		addPanel5();//총체 검색버튼
		addPanel6(searchMusic);//곡 검색결과
		
		setVisible(true);
	}//end of MusicSearch 생성자
	
	private void configureFrame() {
		//프레임 상단에 이름설정
		setTitle("음악검색");
		setBounds(400, 100, 500, 600);
		setResizable(false);
		//닫기버튼 -> 프로그램 종료
		dispose();
		getContentPane().setBackground(Color.GRAY);
		//레이아웃 매니저 설정
		setLayout(null);
		
		JPanel panel0 = new JPanel();
		panel0.setBounds(10, 10, 480, 40);
		panel0.setBackground(Color.white);
		//보더 추가
		TitledBorder titledBorder1 = new TitledBorder(new LineBorder(Color.black));
		panel0.setBorder(titledBorder1);
		
		JLabel musicSearch = new JLabel("음악검색", JLabel.CENTER);
		musicSearch.setFont(new Font("Serif", Font.BOLD, 20));
		panel0.add(musicSearch);
		
		add(panel0);
	}//end of configureFrame
	
	//곡명, 가수명
	private void addPanel1() {
		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 55, 480, 40);
		panel1.setBackground(Color.white);
		//곡명 TextField 추가
		JLabel title = new JLabel("곡명 : ", JLabel.RIGHT);
		textFieldTitle = new JTextField(6);
		
		//위치를 직접 좌표로 지정
		title.setBounds(10, 60, 60, 30);
		textFieldTitle.setBounds(80, 60, 60, 30);
		
		panel1.add(title);
		panel1.add(textFieldTitle);
		
		JLabel artist = new JLabel("가수명 : ", JLabel.RIGHT);
		textFieldArtist = new JTextField(6);
		
		//위치를 직접 좌표로 지정
		artist.setBounds(230, 60, 60, 30);
		textFieldArtist.setBounds(300, 60, 60, 30);
		
		panel1.add(artist);
		panel1.add(textFieldArtist);
		panel1.setVisible(true);
		
		add(panel1);
		
	}//end of addPanel1
	
	//장르 체크박스
	private void addPanel3() {
		//장르별 체크박스
		JPanel panel3 = new JPanel();
		panel3.setBounds(10, 100, 480, 70);
		panel3.setBackground(Color.white);
		
		TitledBorder titleBorder3 = new TitledBorder(new LineBorder(Color.BLACK), "장르별 검색 (다중선택 가능)");
		panel3.setBorder(titleBorder3);
		
		String[] chk = new String[] {"발라드", "인디뮤직", "J-Pop","Rock","Dance"};
        //JCheckBox배열
        chkGenre = new JCheckBox[chk.length];
        
        for(int i=0; i<chk.length; i++) {
        	chkGenre[i] = new JCheckBox(chk[i]);
        	chkGenre[i].setBackground(Color.white);
            panel3.add(chkGenre[i]);
//          
        }
		
		add(panel3);
	}//end of addPanel3
	
	//발매년도 체크박스
	private void addPanel4() {
		//발매년도별 체크박스
		JPanel panel4 = new JPanel();
		panel4.setBounds(10, 175, 480, 70);
		panel4.setBackground(Color.white);
		
		TitledBorder titleBorder4 = new TitledBorder(new LineBorder(Color.BLACK), "발매년도별 검색 (다중선택 가능)");
		panel4.setBorder(titleBorder4);
		
		String[] chk = new String[] {"1990~1999", "2000~2009", "2010~2019"};
        //JCheckBox배열
        chkYear = new JCheckBox[chk.length];
        
        for(int i=0; i<chkYear.length; i++) {
            chkYear[i] = new JCheckBox(chk[i]);
            chkYear[i].setBackground(Color.white);
            panel4.add(chkYear[i]);
//            chk_[i].addActionListener(new MyActionListener());
        }
		
//		chk1990 = new JCheckBox("1990~1999", false);
//		chk2000 = new JCheckBox("2000~2009", false);
//		chk2010 = new JCheckBox("2010~2019", false);
		
		//체크박스 기본 백그라운드 컬러: LightGray => White
		
		
//		panel4.add(chk1990);
//		panel4.add(chk2000);
//		panel4.add(chk2010);
		
		add(panel4);
	}//end of addPanel4
	
	//총체 검색버튼
	private void addPanel5() {
		JPanel panel5 = new JPanel();
		panel5.setBounds(10, 250, 480, 40);
		btnSearch = new JButton("검색");
		btnSearch.setBounds(220, 265, 60, 30);
		panel5.add(btnSearch);
		add(panel5);
		
		
		btnSearch.addActionListener(new MyActionListener());
		setVisible(true);
	}//end of addPanel5
	
	//곡 검색결과
	private void addPanel6(HashSet<Music> searchMusic) {
		panel6 = new JPanel();
		panel6.setBounds(10, 300, 480, 265);
		panel6.setBackground(Color.white);
		JLabel info = new JLabel("정보 : ");
//		info.setBounds(35, 255, 60, 50);
//		info.setFont(new Font("Sans-serif", Font.BOLD, 15));
		
		//컬럼: String[]
		String[] columns = {"곡명", "가수", "장르", "발매년도", "좋아요", "재생시간(초)"};

		//행 데이터: Object[][]
		Object[][] rowData = new Object[searchMusic.size()][columns.length];

//		for(int i=0; i<rowData.length; i++) {
//			Music m = searchMusic.get(i);
//			Object[] o = {m.getTitle(), m.getArtist(), m.getGenre(), m.getOpenYear(), m.getLike(), m.getSeconds()};
//			rowData[i] = o;
		
		int index=0;
		for(Music m:searchMusic) {
			Object[] o = {m.getTitle(), m.getArtist(), m.getGenre(), m.getOpenYear(), m.getLike(), m.getSeconds()};
			rowData[index] = o;
			index++;
		}//for

		//JTable 생성
		
		//Jtable 내용 편집 안되도록 하는 기능
        @SuppressWarnings("serial")
        DefaultTableModel model=new DefaultTableModel(rowData, columns){                                
            @Override
            public boolean isCellEditable(int row,int column) {  
            	//playList를 가지고 있지 않으면(비로그인시, 관리자로그인시)
            	if(!flag){
            		JOptionPane.showMessageDialog(null, "회원 로그인 후 플레이 리스트에 추가 가능합니다.");
            	}
            	else{
            		mainView.playListM.addPlayList(playList.get(row));	      			
            		mainView.playListView.setVisible(false);
            		mainView.addPlayListPanel();
            	}
                return false;
            }
        };        
        JTable musicInfo = new JTable(model);
//        musicInfo.setBounds(0, 0, 480, 50);

		//JTable columns width 조절 
//		musicInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		musicInfo.getColumnModel().getColumn(0).setPreferredWidth(150);
		musicInfo.getColumnModel().getColumn(1).setPreferredWidth(60);
		musicInfo.getColumnModel().getColumn(2).setPreferredWidth(60);
		musicInfo.getColumnModel().getColumn(3).setPreferredWidth(60);
		musicInfo.getColumnModel().getColumn(4).setPreferredWidth(60);
		musicInfo.getColumnModel().getColumn(5).setPreferredWidth(60);
		
		//스크롤페인에 테이블 추가
		JScrollPane scr = new JScrollPane(musicInfo);
		scr.setBounds(0,0,480, 100);
//		JTextArea textArea = new JTextArea(10, 30);

		//Panel에 추가
//		panel6.add(info);	
		panel6.add(scr);
//		panel6.add(textArea);
		add(panel6);
		
	}//end of addPanel6

	
	/**
	 * 특정곡이 있는지 검사하는 메소드
	 * (곡명으로 검색해서 해당곡이 있다면, 곡 정보(제목/가수)를 출력하고,
	 * 없다면, "검색결과가 없습니다" 출력
	 */
	public void searchTitle(String title) {
		if(title.isEmpty()) {
			return;
		}
		//allMusic의 곡 이름에 title이 포함되어있으면 searchMusic Set에 추가
		for(Music m : allMusic) {
			if(m.getTitle().contains(title)) {
				searchMusic.add(m);
			}
		}
	}	
	//가수
	public void searchArtist(String artist) {
		if(artist.isEmpty()) {
			return;
		}
		
		//allMusic의 곡 아티스트에 artist가 포함되어있으면 searchMusic Set에 추가
		for(Music m : allMusic) {
			if(m.getTitle().contains(artist)) {
				searchMusic.add(m);
			}
		}
		
	}
	//장르
	public void searchGenre(int i) {
		for(Music m : allMusic) {
			//만약에 발라드 이면
			if(i==0) {
				if(m.getGenre().equals("Ballad")) {
					searchMusic.add(m);
				}
			}
			//만약에 인디뮤직 이면
			else if(i==1){
				if(m.getGenre().equals("Indie Music")) {
					searchMusic.add(m);
				}
			}
			//만약에 J-Pop 이면
			else if(i==2) {
				if(m.getGenre().equals("J-Pop")) {
					searchMusic.add(m);
				}
			//만약에 Rock 이면
			}else if(i==3){
				if(m.getGenre().equals("Rock")) {
					searchMusic.add(m);
				}
			//만약에 Dance 이면
			}else if(i==4){
				if(m.getGenre().equals("Dance")) {
					searchMusic.add(m);
				}
			}

		}	
		
		
		
	}//end of searchGenre
	
	//발매년도
	public void searchYear(int i) {
		for(Music m : allMusic) {
			//만약에 1990~1999년 이면
			if(i==0) {
				if(1990<=m.getOpenYear()&&m.getOpenYear()<=1999) {
					searchMusic.add(m);
				}
			}
			//만약에 2000~2009년 이면
			else if(i==1){
				if(2000<=m.getOpenYear()&&m.getOpenYear()<=2009) {
					searchMusic.add(m);
				}
			}
			//만약에 2010~2019년 이면
			else if(i==2) {
				if(2010<=m.getOpenYear()&&m.getOpenYear()<=2019) {
					searchMusic.add(m);
				}

			}

		}	
		
		
//		if(srchC.isEmpty()) {
//			return;
//		}
//		
//		//allMusic의 곡 아티스트에 artist가 포함되어있으면 searchMusic Set에 추가
//		for(Music m : allMusic) {
//			if(m.getTitle().contains(artist)) {
//				searchMusic.add(m);
//			}
//		}
		
	
	//end of searchYear
	}
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			String srchT = textFieldTitle.getText();
			searchTitle(srchT);
			String srchA = textFieldArtist.getText();
			searchArtist(srchA);

			for(int i=0;i<chkYear.length;i++) {
				if(chkYear[i].isSelected()) {
					searchYear(i);
				}
			}			
	
			for(int i=0;i<chkGenre.length;i++) {
				if(chkGenre[i].isSelected()) {
					searchGenre(i);
				}
			}			
			
			if(searchMusic == null) {
				JOptionPane.showMessageDialog(null, "검색한 곡이 없습니다.");
			}
		
			panel6.setVisible(false);
			addPanel6(searchMusic);
			//set 리셋
			
			playList=new ArrayList<Music>(searchMusic);
			searchMusic.removeAll(searchMusic);
		}
		
	}
	

};//end of class
