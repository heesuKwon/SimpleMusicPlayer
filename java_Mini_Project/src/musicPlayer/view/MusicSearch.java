package musicPlayer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import musicPlayer.model.vo.Music;

public class MusicSearch extends JFrame{
	Scanner sc = new Scanner(System.in);
	//Music 클래스에서 가져옴
	private List<Music> mList = new ArrayList<>();
	//{"파일경로", 고유번호, "곡명", "가수", "장르", "발매년도", 좋아요수, 재생시간(분)}
	{
		mList.add(new Music("", , "어머님께", "G.O.D", "K-Pop", "1999", , 4));
		mList.add(new Music("", , "엄마의 일기", "왁스", "Pop", "2000", , 3));
		mList.add(new Music("", , "벚꽃 엔딩", "버스커 버스커", "Rock", "2012", , 4));
	}//필드 생성
	
	public void printList() {
		System.out.println("============");
		System.out.println("파일경로\t고유번호\t곡명\t가수\t장르\t발매년도\t좋아요수\t재생시간(분)");
		System.out.println("------------");
		
		for(Music m : mList) {
			System.out.println(m);
		}//for
		System.out.println("-------------\n");
	}//end of printList
	
	public MusicSearch() {
		//크기지정
		setSize(300, 200);
		//위치지정
		setLocation(800, 500);
		//프레임 상단에 이름설정
		setTitle("음악 검색");
		setBounds(500, 500, 500, 300);
		//닫기버튼 -> 프로그램 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(500, 200);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		//레이아웃 매니저 설정
		
		//컬럼: String[] 필터명을 배열에 보관
		String[] columns = {"음악제목"};
		//체크박스 배열
		JCheckBox[] checkboxes = new JCheckBox[columns.length];
		
		for(int i=0; i<checkboxes.length; i++) {
			checkboxes[i] = new JCheckBox(columns[i]);
		}//for
		
		//panel1 생성 : 위
		JPanel panel1 = new JPanel();
		
		for(int i=0; i<checkboxes.length; i++) {
			panel1.add(checkboxes[i]);
		}//for
		
		//결과패널 생성
		JPanel resultPanel = new JPanel();
		JLabel result = new JLabel("선택한 곡명이 없습니다.");
		resultPanel.add(result);
		
		//체크박스에 이벤트 핸들러 추가
		for(int i=0; i<checkboxes.length; i++) {
			checkboxes[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("actionPerformed!");
				}//end of actionPerformed
			});//end of ActionListener
		}//for
		
		//행 데이터: Object[][]
		//Member 클래스에서 가져옴
//		Object[][] rowData = new Object[list.size()][columns.length];
//		for(int i=0; i<rowData.length; i++) {
//			Member m = list.get(i);
//			Object[] o = {m.getTitle(), m.getArtist(), m.getAlbum(), m.getGenre()};
//			rowData[i] = o;
//		}//for
		
		//테이블 생성
//		JTable table = new JTable(rowData, columns);
		
		//스크롤페인에 테이블 추가
		JScrollPane scr = new JScrollPane();
		
		//JTextArea
		JTextArea textArea = new JTextArea(10, 30);
		
		//라벨생성
		JLabel label = new JLabel("");
		
		//시각화여부 true
		//컴포넌트(component)추가 후 마지막에 실행할 것.
		setVisible(true);
	}//end of MusicSearch 생성자
	
	/**
	 * 특정곡이 있는지 검사하는 메소드
	 * (곡명으로 검색해서 해당곡이 있다면, 곡 정보(제목/가수)를 출력하고,
	 * 없다면, "검색결과가 없습니다" 출력
	 */
	public void searchTitle() {
		//찾는 곡이 존재하는지 여부를 담을 변수
		boolean flag = false;
		//1. 검색할 곡명 입력받기
		String title = getTitle();
		//2. 리스트 순회: 곡이 있다면, 곡 정보 출력
		//없다면, 곡이 없다고 피드백 주기
		for(int i=0; i<mList.size(); i++) {
			//3. 해당곡이 있을 경우 출력
			if(title.contentEquals(mList.get(i).getTitle())) {
				flag = true;
				System.out.println("============");
				System.out.println("곡명/t가수/작곡가");
				System.out.println("------------");
				System.out.println(mList.get(i));
				System.out.println("------------\n");
			}//if
		}//for
		
		if(!flag) {
			System.out.println("찾는 곡이 없습니다.");
		}//if
	}//end of searchTitle
	
	public static void main(String[] args) {
//		new MusicSearch();
		
		MusicSearch ms = new MusicSearch();
//		ms.searchTitle();//제목명으로 검색 메소드
//		ms.searchArtist();//가수/자곡가명으로 검색 메소드: 복수개의 결과가 나올 수 있음
//		ms.searchAlbum();//앨범명으로 검색 메소드
//		ms.searchGenre();//장르별로 검색 메소드
	}//end of main

};//end of class
