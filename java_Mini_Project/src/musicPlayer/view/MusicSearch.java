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

	//Music 클래스에서 가져옴
	private List<Music> mList = new ArrayList<>();
	
	//{"파일경로", 고유번호, "곡명", "가수", "장르", "발매년도", 좋아요수, 재생시간(초)}
	{
		mList.add(new Music("", 0, "어머님께", "G.O.D", "K-Pop", "1999", 0, 253));
		mList.add(new Music("", 1, "엄마의 일기", "왁스", "Pop", "2000", 0, 225));
		mList.add(new Music("", 2, "벚꽃 엔딩", "버스커 버스커", "Rock", "2012", 0, 262));
	}//필드 생성
	
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
	}//end of configureFrame
	
	private void addPanelA() {
		//곡명
		JPanel panelA = new JPanel();
		panelA.setBounds(10, 10, 480, 40);
		panelA.setBackground(Color.white);
		//보더 추가
		TitledBorder titledBorderA = new TitledBorder(new LineBorder(Color.black));
		panelA.setBorder(titledBorderA);
		
		JLabel musicSearch = new JLabel("음악검색", JLabel.CENTER);
		musicSearch.setFont(new Font("Serif", Font.BOLD, 20));
		panelA.add(musicSearch);
		
		//음악검색 TextField 추가
		JLabel title = new JLabel("곡명 : ", JLabel.RIGHT);
		textFieldTitle = new JTextField(6);
		
		//위치를 직접 좌표로 지정
		title.setBounds(50, 60, 60, 30);
		textFieldTitle.setBounds(120, 60, 60, 30);
		
		panelA.add(title);
		panelA.add(textFieldTitle);
		
		add(panelA);
	}//end of addPanelA
	
	//검색 버튼
	private void addPanelB() {
		JButton btnSearch = new JButton("검색");
		btnSearch.setBounds(210, 60, 60, 30);
		
		add(btnSearch);
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//사용자가 입력한값 가져오기
				String srch =  textFieldTitle.getText();
				System.out.println(srch);
				searchTitle(srch);
			}//end of actionPerformed
		});//end of addActionListener
	}//end of addPanelB
	
	//곡 정보
	private void addPanelC() {
		JLabel info = new JLabel("정보 : ");
		info.setBounds(69, 100, 60, 50);
		info.setFont(new Font("Sans-serif", Font.BOLD, 15));
		add(info);
		
		JPanel panelC = new JPanel();
		panelC.setBounds(10, 150, 480, 110);
		panelC.setBackground(Color.white);

		add(panelC);
		
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		//컬럼: String[]
		String[] columns = {"파일경로", "고유번호", "곡명", "가수", "장르", "발매년도", "좋아요수", "재생시간(초)"};
		
		//행 데이터: Object[][]
		Object[][] rowData = new Object[mList.size()][columns.length];
		for(int i=0; i<rowData.length; i++) {
			Music m = mList.get(i);
			Object[] o = {m.getTitle()};
			rowData[i] = o;
		}//for
		
		//테이블 생성
		JTable musicInfo = new JTable(rowData, columns);
		//스크롤페인에 테이블 추가
		JScrollPane scr = new JScrollPane(musicInfo);
		//JTextArea
		JTextArea textArea = new JTextArea(100, 200);
	}//end of addPanelC	
	
	public MusicSearch() {
		configureFrame();//프레임설정(size, location, title등)
		addPanelA();//곡명
		addPanelB();//검색버튼
		addPanelC();//곡 정보
		//시각화여부 true
		//컴포넌트(component)추가 후 마지막에 실행할 것.
		setVisible(true);
	}//end of MusicSearch 생성자
	
	public void printList() {
		System.out.println("===================================================");
		System.out.println("곡명\t가수\t장르\t발매년도\t좋아요수\t재생시간(초)");
		System.out.println("---------------------------------------------------");
		
		for(Music m : mList) {
			System.out.println(m.getTitle());
		}//for
		System.out.println("---------------------------------------------------\n");
	}//end of printList
	
	/**
	 * 특정곡이 있는지 검사하는 메소드
	 * (곡명으로 검색해서 해당곡이 있다면, 곡 정보(제목/가수)를 출력하고,
	 * 없다면, "검색결과가 없습니다" 출력
	 */
	public void searchTitle(String title) {
		//찾는 곡이 존재하는지 여부를 담을 변수
		boolean flag = false;
		
		//리스트 순회: 곡이 있다면, 곡 정보 출력
		//없다면, 곡이 없다고 피드백 주기
		for(int i=0; i<mList.size(); i++) {
			//3. 해당곡이 있을 경우 출력
			if(mList.get(i).getTitle().indexOf(title) != -1) {
				flag = true;
				System.out.println("===========================================");
				System.out.println("곡명\t가수\t장르\t발매년도\t좋아요수\t재생시간(초)");
				System.out.println("-------------------------------------------");
				System.out.println(mList.get(i));
				System.out.println("-------------------------------------------\n");
			}//if
		}//for
		if(!flag) {
			System.out.println("찾는 곡이 없습니다.");
		}//if
	}//end of searchTitle
	
	public static void main(String[] args) {
		MusicSearch ms = new MusicSearch();
		ms.printList();
//		ms.searchTitle();//제목명으로 검색 메소드
//		ms.searchArtist();//가수/자곡가명으로 검색 메소드: 복수개의 결과가 나올 수 있음
//		ms.searchAlbum();//앨범명으로 검색 메소드
//		ms.searchGenre();//장르별로 검색 메소드
	}//end of main

};//end of class
