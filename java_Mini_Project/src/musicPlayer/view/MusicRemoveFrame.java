package musicPlayer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import musicPlayer.controller.MusicManager;
import musicPlayer.model.vo.Music;
import musicPlayer.view.PlayListView.MyListSelectionListener;

public class MusicRemoveFrame extends JFrame{
	
	private MusicManager musicM;
	private MainViewFrame2 mainView;
	
	private DefaultListModel<Music> listModel = new DefaultListModel<>(); 
	
	private JPanel jPanel1; //열기 패널
	private JPanel jPanel2; //저장/취소 패널
	private JScrollPane jScrollPane1; //가운데 file 추가 화면
	private JList<Music> list;
	
	private JButton btnRemove; //삭제 버튼
	private JButton btnCancel; //취소버튼
	
	private static List<Music> musicList; //음악 리스트
	private static List<Music> removeList = new ArrayList<Music>(); //삭제할 리스트
	
	public MusicRemoveFrame(MusicManager musicM, MainViewFrame2 mainView){
		this.mainView = mainView;
		this.musicM = musicM;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initComponents(); 
		list.setModel(listModel);
		
	}

	private void initComponents() { 
		jScrollPane1 = new JScrollPane(); 
		setLayout(null);
		setTitle("곡 삭제");
		setSize(315,250);
		
		//위 패널
		jPanel1 = new JPanel();
		jPanel1.setBounds(0, 0, 300, 30);
		JLabel jLabel1 = new JLabel("Files");
		jPanel1.add(jLabel1);
		add(jPanel1, BorderLayout.NORTH); 
		
		//file 넣는 공간
		musicList = musicM.getAllMusic();
		for(Music music : musicList){
			listModel.addElement(music); 
		}
		list = new JList<>(listModel); 
		jScrollPane1.setBounds(0, 40, 300, 130);
		jScrollPane1.setViewportView(list); 		
		add(jScrollPane1, BorderLayout.CENTER);
		list.addListSelectionListener(new MyListSelectionListener());
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		//아래패널
		jPanel2 = new JPanel();
		jPanel2.setBounds(0, 170, 300, 40);
		add(jPanel2, BorderLayout.SOUTH);
		
		//저장/취소버튼
		btnRemove = new JButton("삭제");
		btnRemove.setSize(40, 30);
		btnCancel = new JButton("취소");
		btnCancel.setSize(40, 30);
		
		//이벤트리스너등록
		MyActionListener listener = new MyActionListener();
		btnRemove.addActionListener(new MyActionListener());
		btnCancel.addActionListener(listener);
		
		jPanel2.add(btnRemove);
		jPanel2.add(btnCancel);
		
		setVisible(true); 
	} 
	
	private class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//저장을 눌렀을 때 관리자의 메소드를 불러와서 음악 추가
			if(e.getSource() == btnRemove){
				System.out.println("삭제될 파일:"+removeList);
				for (Music m : removeList) { 
					musicM.removeMusic(m);
				}
				removeList.clear();
				mainView.chartPanel.setVisible(false);
				mainView.addChartPanel();
				setVisible(false);
			}
			//취소를 눌렀을 때 화면 없어짐
			else if(e.getSource() == btnCancel){
				setVisible(false);
			}			
		}		
	}
	
	class MyListSelectionListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			int[] indexs = list.getSelectedIndices();
			for(int i=0;i<indexs.length;i++){
				System.out.println(indexs);
				removeList.add(musicList.get(indexs[i]));
			}
		}
	}
	
}
