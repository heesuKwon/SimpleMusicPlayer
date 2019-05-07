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

	//Music Ŭ�������� ������
	private List<Music> mList = new ArrayList<>();
	
	//{"���ϰ��", ������ȣ, "���", "����", "�帣", "�߸ų⵵", ���ƿ��, ����ð�(��)}
	{
		mList.add(new Music("", 0, "��ӴԲ�", "G.O.D", "K-Pop", "1999", 0, 253));
		mList.add(new Music("", 1, "������ �ϱ�", "�ν�", "Pop", "2000", 0, 225));
		mList.add(new Music("", 2, "���� ����", "����Ŀ ����Ŀ", "Rock", "2012", 0, 262));
	}//�ʵ� ����
	
	private void configureFrame() {
		//������ ��ܿ� �̸�����
		setTitle("���ǰ˻�");
		setBounds(400, 100, 500, 500);
		setResizable(false);
		//�ݱ��ư -> ���α׷� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.GRAY);
		//���̾ƿ� �Ŵ��� ����
		setLayout(null);
	}//end of configureFrame
	
	private void addPanelA() {
		//���
		JPanel panelA = new JPanel();
		panelA.setBounds(10, 10, 480, 40);
		panelA.setBackground(Color.white);
		//���� �߰�
		TitledBorder titledBorderA = new TitledBorder(new LineBorder(Color.black));
		panelA.setBorder(titledBorderA);
		
		JLabel musicSearch = new JLabel("���ǰ˻�", JLabel.CENTER);
		musicSearch.setFont(new Font("Serif", Font.BOLD, 20));
		panelA.add(musicSearch);
		
		//���ǰ˻� TextField �߰�
		JLabel title = new JLabel("��� : ", JLabel.RIGHT);
		textFieldTitle = new JTextField(6);
		
		//��ġ�� ���� ��ǥ�� ����
		title.setBounds(50, 60, 60, 30);
		textFieldTitle.setBounds(120, 60, 60, 30);
		
		panelA.add(title);
		panelA.add(textFieldTitle);
		
		add(panelA);
	}//end of addPanelA
	
	//�˻� ��ư
	private void addPanelB() {
		JButton btnSearch = new JButton("�˻�");
		btnSearch.setBounds(210, 60, 60, 30);
		
		add(btnSearch);
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//����ڰ� �Է��Ѱ� ��������
				String srch =  textFieldTitle.getText();
				System.out.println(srch);
				searchTitle(srch);
			}//end of actionPerformed
		});//end of addActionListener
	}//end of addPanelB
	
	//�� ����
	private void addPanelC() {
		JLabel info = new JLabel("���� : ");
		info.setBounds(69, 100, 60, 50);
		info.setFont(new Font("Sans-serif", Font.BOLD, 15));
		add(info);
		
		JPanel panelC = new JPanel();
		panelC.setBounds(10, 150, 480, 110);
		panelC.setBackground(Color.white);

		add(panelC);
		
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		//�÷�: String[]
		String[] columns = {"���ϰ��", "������ȣ", "���", "����", "�帣", "�߸ų⵵", "���ƿ��", "����ð�(��)"};
		
		//�� ������: Object[][]
		Object[][] rowData = new Object[mList.size()][columns.length];
		for(int i=0; i<rowData.length; i++) {
			Music m = mList.get(i);
			Object[] o = {m.getTitle()};
			rowData[i] = o;
		}//for
		
		//���̺� ����
		JTable musicInfo = new JTable(rowData, columns);
		//��ũ�����ο� ���̺� �߰�
		JScrollPane scr = new JScrollPane(musicInfo);
		//JTextArea
		JTextArea textArea = new JTextArea(100, 200);
	}//end of addPanelC	
	
	public MusicSearch() {
		configureFrame();//�����Ӽ���(size, location, title��)
		addPanelA();//���
		addPanelB();//�˻���ư
		addPanelC();//�� ����
		//�ð�ȭ���� true
		//������Ʈ(component)�߰� �� �������� ������ ��.
		setVisible(true);
	}//end of MusicSearch ������
	
	public void printList() {
		System.out.println("===================================================");
		System.out.println("���\t����\t�帣\t�߸ų⵵\t���ƿ��\t����ð�(��)");
		System.out.println("---------------------------------------------------");
		
		for(Music m : mList) {
			System.out.println(m.getTitle());
		}//for
		System.out.println("---------------------------------------------------\n");
	}//end of printList
	
	/**
	 * Ư������ �ִ��� �˻��ϴ� �޼ҵ�
	 * (������� �˻��ؼ� �ش���� �ִٸ�, �� ����(����/����)�� ����ϰ�,
	 * ���ٸ�, "�˻������ �����ϴ�" ���
	 */
	public void searchTitle(String title) {
		//ã�� ���� �����ϴ��� ���θ� ���� ����
		boolean flag = false;
		
		//����Ʈ ��ȸ: ���� �ִٸ�, �� ���� ���
		//���ٸ�, ���� ���ٰ� �ǵ�� �ֱ�
		for(int i=0; i<mList.size(); i++) {
			//3. �ش���� ���� ��� ���
			if(mList.get(i).getTitle().indexOf(title) != -1) {
				flag = true;
				System.out.println("===========================================");
				System.out.println("���\t����\t�帣\t�߸ų⵵\t���ƿ��\t����ð�(��)");
				System.out.println("-------------------------------------------");
				System.out.println(mList.get(i));
				System.out.println("-------------------------------------------\n");
			}//if
		}//for
		if(!flag) {
			System.out.println("ã�� ���� �����ϴ�.");
		}//if
	}//end of searchTitle
	
	public static void main(String[] args) {
		MusicSearch ms = new MusicSearch();
		ms.printList();
//		ms.searchTitle();//��������� �˻� �޼ҵ�
//		ms.searchArtist();//����/�ڰ������ �˻� �޼ҵ�: �������� ����� ���� �� ����
//		ms.searchAlbum();//�ٹ������� �˻� �޼ҵ�
//		ms.searchGenre();//�帣���� �˻� �޼ҵ�
	}//end of main

};//end of class
