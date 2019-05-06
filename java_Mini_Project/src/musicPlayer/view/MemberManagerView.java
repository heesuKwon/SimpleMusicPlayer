package musicPlayer.view;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import musicPlayer.model.vo.Member;

public class MemberManagerView extends JFrame{
	List<Member> mList;
	private JTable table;

	public MemberManagerView(List<Member> mList){
		setSize(500,200);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		//컬럼: String[]
		String[] columns = {"이름", "아이디", "비밀번호"};
		
		//행 데이터: Object[][]
		Object[][] rowData = new Object[mList.size()][columns.length];

		for(int i=0;i<rowData.length;i++) {
			Member m = mList.get(i);
			Object[] o = {m.getName(), m.getId(), m.getPassword()};
			rowData[i] = o;
		}
		
		table = new JTable(rowData, columns);
		
		//스크롤페인에 테이블추가
		JScrollPane scr = new JScrollPane(table);
		
		//JTextArea
		JTextArea textArea = new JTextArea(10, 50);
		
		/*
		//@실습문제: 사용자가 컬럼의 정보를 변경했을 때,
		//변경된 정보를 textArea에 로깅처리 하세요
		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				//값이 변경된 셀의 위치 추적
				int row = e.getFirstRow();
//				int column = e.getColumn();
				
				//변경된 데이터 가져오기
				TableModel model = ((TableModel)e.getSource());
				
				Member m = new Member();
				m.setName(model.getValueAt(row, 0).toString());
				m.setId(model.getValueAt(row, 1).toString());
				m.setAge(Integer.parseInt(model.getValueAt(row, 2).toString()));
				m.setMarried(Boolean.parseBoolean(model.getValueAt(row, 3).toString()));
				
				System.out.println(row+"행의 정보가 "+m.toString()+"로 변경되었습니다.");
	
				Object[] o = {model.getValueAt(row, 0), model.getValueAt(row, 1), model.getValueAt(row, 2), model.getValueAt(row, 3)};
				rowData[row] = o;
				table = new JTable(rowData, columns);
				
				list.set(row, m);
				
			}
		});
		*/
		
		
		
		add(scr);
		add(textArea);
		
		
		setVisible(true);
	}
}
