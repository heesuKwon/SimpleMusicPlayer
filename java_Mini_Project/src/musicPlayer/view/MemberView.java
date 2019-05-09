package musicPlayer.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import musicPlayer.model.vo.Member;

public class MemberView extends JFrame{
	Member member;
	private JTable table;

	public MemberView(Member member){
		setSize(500,200);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		//컬럼: String[]
		String[] columns = {"이름", "아이디", "비밀번호"};
		
		//행 데이터: Object[][]
		Object[][] rowData = new Object[1][columns.length];
	
			Member m = member;
			Object[] o = {m.getName(), m.getId(), m.getPassword()};
			rowData[0] = o;
		table = new JTable(rowData, columns);
		
		//스크롤페인에 테이블추가
		JScrollPane scr = new JScrollPane(table);
		
		//JTextArea
		JTextArea textArea = new JTextArea(10, 50);
		
		add(scr);
		add(textArea);	
		setVisible(true);
	}
}