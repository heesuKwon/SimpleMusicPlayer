package musicPlayer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import musicPlayer.model.vo.Music;

public class MusicSearch extends JFrame{
Scanner sc = new Scanner(System.in);
	
	private JTextField textFieldTitle;
	private JTextField textFieldArtist;

	//Music 클래스에서 가져옴
	private List<Music> mList = new ArrayList<>();
	
	//{"파일경로", 고유번호, "곡명", "가수", "장르", "발매년도", 좋아요수, 재생시간(초)}
	{
		mList.add(new Music("", 1, "Kill This Love", "BLACKPINK(블랙핑크)", "K-Pop", "", 0, 193));
		mList.add(new Music("", 2, "Bom(나만,봄)", "BOL4(볼빨간사춘기)", "K-Pop", "", 0, 222));
		mList.add(new Music("", 3, "사계(Four Seasons)", "Taeyeon(태현)", "K-Pop", "", 0, 210));
		mList.add(new Music("", 4, "FANCY", "TWICE", "K-Pop", "", 0, 218));
	}//필드 생성
	
	//데이터
	Object[][] data = {
			{"", 1, "Kill This Love", "BLACKPINK(블랙핑크)", "K-Pop", "", 0, 193},
			{"", 2, "Bom(나만,봄)", "BOL4(볼빨간사춘기)", "K-Pop", "", 0, 222},
			{"", 3, "사계(Four Seasons)", "Taeyeon(태현)", "K-Pop", "", 0, 210},
			{"", 4, "FANCY", "TWICE", "K-Pop", "", 0, 218}
	};

	private JPanel panel6;

	private List<Music> searchMusic;
	
	public MusicSearch() {
		configureFrame();//프레임설정(size, location, title등)
		addPanel1();//곡명, 가수
//		addPanel2();//가수
		addPanel3();//장르 체크박스
		addPanel4();//발매년도
		addPanel5();//총체 검색버튼
//		addPanel6();//곡 검색결과
		
		//시각화여부 true
		//컴포넌트(component)추가 후 마지막에 실행할 것.
		setVisible(true);
	}//end of MusicSearch 생성자
	
	private void configureFrame() {
		//프레임 상단에 이름설정
		setTitle("음악검색");
		setBounds(400, 100, 500, 500);
		setResizable(false);
		//닫기버튼 -> 프로그램 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JCheckBox chkBallad = new JCheckBox("발라드", false);
		JCheckBox chkTrot = new JCheckBox("트로트", false);
		JCheckBox chkKPop = new JCheckBox("K-Pop", false);
		JCheckBox chkRock = new JCheckBox("Rock", false);
		JCheckBox chkDance = new JCheckBox("Dance", false);
		
		//체크박스 기본 백그라운드 컬러: LightGray => White
		chkBallad.setBackground(Color.white);
		chkTrot.setBackground(Color.white);
		chkKPop.setBackground(Color.white);
		chkRock.setBackground(Color.white);
		chkDance.setBackground(Color.white);
		
		panel3.add(chkBallad);
		panel3.add(chkTrot);
		panel3.add(chkKPop);
		panel3.add(chkRock);
		panel3.add(chkDance);
		
		add(panel3);
	}//end of addPanel3
	
	//발매년도 체크박스
	private void addPanel4() {
		//발매년도별 체크박스
		JPanel panel4 = new JPanel();
		panel4.setBounds(10, 190, 480, 70);
		panel4.setBackground(Color.white);
		
		TitledBorder titleBorder4 = new TitledBorder(new LineBorder(Color.BLACK), "발매년도별 검색 (다중선택 가능)");
		panel4.setBorder(titleBorder4);
		
		JCheckBox chk1990 = new JCheckBox("1990~1999", false);
		JCheckBox chk2000 = new JCheckBox("2000~2009", false);
		JCheckBox chk2010 = new JCheckBox("2010~2019", false);
		
		//체크박스 기본 백그라운드 컬러: LightGray => White
		chk1990.setBackground(Color.white);
		chk2000.setBackground(Color.white);
		chk2010.setBackground(Color.white);
		
		panel4.add(chk1990);
		panel4.add(chk2000);
		panel4.add(chk2010);
		
		add(panel4);
	}//end of addPanel4
	
	//총체 검색버튼
	private void addPanel5() {
		JPanel panel5 = new JPanel();
		//검색버튼
		JButton btnSearch = new JButton("검색");
		btnSearch.setBounds(220, 265, 60, 30);
		
		panel5.add(btnSearch);
		add(panel5);
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//사용자가 입력한값 가져오기
				String srch =  textFieldTitle.getText();
				searchTitle(srch);
				System.out.println(searchMusic);
				addPanel6();
			}//end of actionPerformed
		});//end of addActionListener
		setVisible(true);
	}//end of addPanel5
	
	//곡 검색결과
	private void addPanel6() {
		panel6 = new JPanel();
		panel6.setBounds(0, 300, 480, 150);
		panel6.setBackground(Color.white);
		
//		JLabel info = new JLabel("정보 : ");
//		info.setBounds(35, 255, 60, 50);
//		info.setFont(new Font("Sans-serif", Font.BOLD, 15));
		
		//컬럼: String[]
		String[] columns = {"곡명", "가수", "장르", "발매년도", "좋아요수", "재생시간(초)"};

		//행 데이터: Object[][]
		Object[][] rowData = new Object[searchMusic.size()][columns.length];

		for(int i=0; i<rowData.length; i++) {
			Music m = searchMusic.get(i);
			Object[] o = {m.getTitle(), m.getArtist(), m.getGenre(), m.getOpenYear(), m.getLike(), m.getSeconds()};
			rowData[i] = o;
		}//for

		//JTable 생성
		JTable musicInfo = new JTable(rowData, columns);
		//스크롤페인에 테이블 추가
		JScrollPane scr = new JScrollPane(musicInfo);
		//JTextArea
		JTextArea textArea = new JTextArea(10, 30);

		//Panel에 추가
		panel6.add(musicInfo);
		panel6.add(scr);
		panel6.add(textArea);
//		panel6.add(info);	
		add(panel6);
	}//end of addPanel6
	
	/*
	 * #############################################################################################
	 */
	
	public void printList() {
		System.out.println("=====================================================================");
		System.out.println("곡명\t가수\t장르\t발매년도\t좋아요수\t재생시간(초)\t패스\t코드");
		System.out.println("---------------------------------------------------------------------");
		
		for(Music m : mList) {
			System.out.println(m.getTitle()+"\t"+
							m.getArtist()+"\t"+
							m.getGenre()+"\t"+
							m.getOpenYear()+"\t"+
							m.getLike()+"\t"+
							m.getSeconds()+"\t"+
							m.getPath()+"\t"+
							m.getCode());
		}//for
		System.out.println("---------------------------------------------------------------------\n");
	}//end of printList
	
	/**
	 * 특정곡이 있는지 검사하는 메소드
	 * (곡명으로 검색해서 해당곡이 있다면, 곡 정보(제목/가수)를 출력하고,
	 * 없다면, "검색결과가 없습니다" 출력
	 */
	public void searchTitle(String title) {
		//찾는 곡이 존재하는지 여부를 담을 변수
		boolean flag = false;
		searchMusic = new ArrayList<>();
		
		//리스트 순회: 곡이 있다면, 곡 정보 출력
		//없다면, 곡이 없다고 피드백 주기
		for(int i=0; i<mList.size(); i++) {
			//해당곡이 있을 경우 출력
			if(mList.get(i).getTitle().equals(title)) {
				flag = true;		
				searchMusic.add(mList.get(i));
			}//if
		}//for
		if(!flag) {
			System.out.println("찾는 곡이 없습니다.");
		}//if
		
//		//컬럼: String[]
//		String[] columns = {"곡명", "가수", "장르", "발매년도", "좋아요수", "재생시간(초)"};
//		
//		//행 데이터: Object[][]
//		Object[][] rowData = new Object[searchMusic.size()][columns.length];
//		
//		for(int i=0; i<rowData.length; i++) {
//			Music m = searchMusic.get(i);
//			Object[] o = {m.getTitle(), m.getArtist(), m.getGenre(), m.getOpenYear(), m.getLike(), m.getSeconds()};
//			rowData[i] = o;
//		}//for
//		
//		//JTable 생성
//		JTable musicInfo = new JTable(rowData, columns);
//		//스크롤페인에 테이블 추가
//		JScrollPane scr = new JScrollPane(musicInfo);
//		//JTextArea
//		JTextArea textArea = new JTextArea(10, 30);
//		
//		//Panel에 추가
//		panel6.add(musicInfo);
//		panel6.add(scr);
//		panel6.add(textArea);
	}//end of searchTitle
	
	//가수
	public void searchArtist(String artist) {
		//찾는 가수가 존재하는지 여부를 담을 변수
		boolean flag = false;
		
		//리스트 순회: 가수가 있다면, 곡 정보 출력
		//없다면, 가수가 없다고 피드백 주기
		for(int i=0; i<mList.size(); i++) {
			//해당 가수가 있을 경우 출력
			if(mList.get(i).getArtist().indexOf(artist) != -1) {
				flag = true;
				System.out.println("==================================================");
				System.out.println("가수명");
				System.out.println("--------------------------------------------------");
				System.out.println(mList.get(i));
				System.out.println("---------------------------------------------------\n");
			}//if
		}//for
		if(!flag) {
			System.out.println("찾는 가수가 없습니다.");
		}//if
	}//end of searchArtist
	
	//장르
	public void searchGenre(String genre) {
		//찾는 장르가 존재하는지 여부를 담을 변수
		boolean flag = false;
		
		//리스트 순회: 장르가 있다면, 곡 정보 출력
		//없다면, 장르가 없다고 피드백 주기
		for(int i=0; i<mList.size(); i++) {
			//해당 장르가 있을 경우 출력
			if(mList.get(i).getGenre().indexOf(genre) != -1) {
				flag = true;
				System.out.println("==================================================");
				System.out.println("___________장르");
				System.out.println("--------------------------------------------------");
				System.out.println(mList.get(i));
				System.out.println("---------------------------------------------------\n");
			}//if
		}//for
		if(!flag) {
			System.out.println("찾는 장르가 없습니다.");
		}//if
	}//end of searchGenre
	
	//발매년도
	public void searchYear(String openYear) {
		//찾는 발매년도가 존재하는지 여부를 담을 변수
		boolean flag = false;
		
		//리스트 순회: 발매년도가 있다면, 곡 정보 출력
		//없다면, 발매년도가 없다고 피드백 주기
		for(int i=0; i<mList.size(); i++) {
			//해당 발매년도가 있을 경우 출력
			if(mList.get(i).getOpenYear().indexOf(openYear) != -1) {
				flag = true;
				System.out.println("==================================================");
				System.out.println("_______________발매년도");
				System.out.println("--------------------------------------------------");
				System.out.println(mList.get(i));
				System.out.println("---------------------------------------------------\n");
			}//if
		}//for
		if(!flag) {
			System.out.println("찾는 발매년도가 없습니다.");
		}//if
	}//end of searchYear
	
	//좋아요수
	public void searchLike(int like) {
		//찾는 좋아요수가 존재하는지 여부를 담을 변수
		boolean flag = false;
		
		//리스트 순회: 좋아요수가 있다면, 곡 정보 출력
		//없다면, 좋아요수가 없다고 피드백 주기
		for(int i=0; i<mList.size(); i++) {
			//해당 좋아요수가 있을 경우 출력
			if(mList.get(i).getLike() != -1) {
				flag = true;
				System.out.println("==================================================");
				System.out.println("_________________좋아요수");
				System.out.println("--------------------------------------------------");
				System.out.println(mList.get(i));
				System.out.println("---------------------------------------------------\n");
			}//if
		}//for
		if(!flag) {
			System.out.println("찾는 좋아요수가 없습니다.");
		}//if
	}//end of searchLike
	
	//재생시간(초)
	public void searchLength(int seconds) {
		//찾는 재생시간(초)가 존재하는지 여부를 담을 변수
		boolean flag = false;
		
		//리스트 순회: 재생시간(초)가 있다면, 곡 정보 출력
		//없다면, 재생시간(초)가 없다고 피드백 주기
		for(int i=0; i<mList.size(); i++) {
			//해당 재생시간(초)가 있을 경우 출력
			if(mList.get(i).getLike() != -1) {
				flag = true;
				System.out.println("==================================================");
				System.out.println("______________________재생시간(초)");
				System.out.println("--------------------------------------------------");
				System.out.println(mList.get(i));
				System.out.println("---------------------------------------------------\n");
			}//if
		}//for
		if(!flag) {
			System.out.println("찾는 재생시간(초)가 없습니다.");
		}//if
	}//end of searchLength
	
	//파일경로(패스)
	public void searchPath(String path) {
		//찾는 패스가 존재하는지 여부를 담을 변수
		boolean flag = false;
		
		//리스트 순회: 패스가 있다면, 곡 정보 출력
		//없다면, 패스가 없다고 피드백 주기
		for(int i=0; i<mList.size(); i++) {
			//해당 패스가 있을 경우 출력
			if(mList.get(i).getPath().indexOf(path) != -1) {
				flag = true;
				System.out.println("==================================================");
				System.out.println("________________________________패스");
				System.out.println("--------------------------------------------------");
				System.out.println(mList.get(i));
				System.out.println("---------------------------------------------------\n");
			}//if
		}//for
		if(!flag) {
			System.out.println("찾는 패스가 없습니다.");
		}//if
	}//end of searchPath
	
	//고유코드
	public void searchCode(long code) {
		//찾는 코드가 존재하는지 여부를 담을 변수
		boolean flag = false;
		
		//리스트 순회: 코드가 있다면, 곡 정보 출력
		//없다면, 코드가 없다고 피드백 주기
		for(int i=0; i<mList.size(); i++) {
			//해당 코드가 있을 경우 출력
			if(mList.get(i).getCode() != -1) {
				flag = true;
				System.out.println("==================================================");
				System.out.println("코드");
				System.out.println("--------------------------------------------------");
				System.out.println(mList.get(i));
				System.out.println("---------------------------------------------------\n");
			}//if
		}//for
		if(!flag) {
			System.out.println("찾는 코드가 없습니다.");
		}//if
	}//end of searchCode
	
	public static void main(String[] args) {
		MusicSearch ms = new MusicSearch();
		ms.printList();
//		ms.searchTitle("어머님께");//제목명으로 검색 메소드
//		ms.searchArtist("G.O.D");//가수명으로 검색 메소드: 복수개의 결과가 나올 수 있음
//		ms.searchGenre("K-Pop");//장르별로 검색 메소드
//		ms.searchYear("1999");//발매년도
//		ms.searchLike(0);//좋아요수
//		ms.searchLength(0);//곡 재생시간(초)
	}//end of main

};//end of class
