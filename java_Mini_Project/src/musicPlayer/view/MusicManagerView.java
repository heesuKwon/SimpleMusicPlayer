package musicPlayer.view;

import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;

import musicPlayer.model.vo.Administrator;

public class MusicManagerView extends JFrame implements DropTargetListener{

	//관리자 객체
	private Administrator admin = new Administrator();
	
	private static final long serialVersionUID = -361325089091244347L; 
	private DefaultListModel<File> listModel = new DefaultListModel<>(); 
	@SuppressWarnings("unused")
	private DropTarget dropTarget; 
	private JFileChooser fileChooser = new JFileChooser();
	
	private JPanel jPanel1; //열기 패널
	private JPanel jPanel2; //저장/취소 패널
	private JScrollPane jScrollPane1; //가운데 file 추가 화면
	private JList<File> list;
	
	private static List<File> mList = new ArrayList<File>();
	
	//이벤트 컴포넌트
	private JButton btnSave;//저장버튼
	private JButton btnCancel;//취소버튼

	private JButton btnOpen;
	

	public MusicManagerView() {	
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents(); 
		dropTarget = new DropTarget(list, this); 
		list.setModel(listModel); 
		list.setDragEnabled(true); 
		list.setTransferHandler(new FileTransferHandler()); 
		
//		setVisible(true);
	}

	private void initComponents() { 
		jScrollPane1 = new JScrollPane(); 
		list = new JList<>(); 
		setLayout(null);
		setTitle("곡 추가");
		setSize(315,250);
		
		//위 패널
		jPanel1 = new JPanel();
		jPanel1.setBounds(0, 0, 300, 35);
		JLabel jLabel1 = new JLabel("Files");
		jPanel1.add(jLabel1);
		btnOpen = new JButton("열기");
		btnOpen.setSize(40, 30);
		jPanel1.add(btnOpen);

		add(jPanel1, BorderLayout.NORTH); 
		
		//file 넣는 공간
		jScrollPane1.setBounds(0, 40, 300, 130);
		jScrollPane1.setViewportView(list); 		
		add(jScrollPane1, BorderLayout.CENTER);
		
		//아래패널
		jPanel2 = new JPanel();
		jPanel2.setBounds(0, 170, 300, 40);
		add(jPanel2, BorderLayout.SOUTH);
		
		//저장/취소버튼
		btnSave = new JButton("저장");
		btnCancel = new JButton("취소");
		btnSave.setSize(40, 30);
		btnCancel.setSize(40, 30);
		
		//이벤트리스너등록
		MyActionListener listener = new MyActionListener();
		btnSave.addActionListener(listener);
		btnCancel.addActionListener(listener);
		btnOpen.addActionListener(listener);
		
		jPanel2.add(btnSave);
		jPanel2.add(btnCancel);
		
		setVisible(true); 
	} 
	
	public void dragEnter(DropTargetDragEvent arg0) {} 
	public void dragOver(DropTargetDragEvent arg0) {} 
	public void dropActionChanged(DropTargetDragEvent arg0) {} 
	public void dragExit(DropTargetEvent arg0) {} 
	@SuppressWarnings("unchecked") 
	//드래그로 곡 추가
	public void drop(DropTargetDropEvent evt) { 
		int action = evt.getDropAction(); 
		evt.acceptDrop(action); 
		try { 
			Transferable data = evt.getTransferable(); 
			if (data.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) { 
				List<File> files = (List<File>) data.getTransferData(DataFlavor.javaFileListFlavor); 			
				for (File file : files) { 
					if(!checkConflict(file)){
						mList.add(file);
						listModel.addElement(file); 
					}
				} 
			} 
		} catch (UnsupportedFlavorException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} finally { 
			evt.dropComplete(true); 
		} 
	} 
	private class FileTransferHandler extends TransferHandler { 
		private static final long serialVersionUID = -3731855776760816485L; 
		@SuppressWarnings("unchecked") 
		@Override 
		protected Transferable createTransferable(JComponent c) { 
			JList<File> list = (JList<File>) c; 
			List<File> files = new ArrayList<File>(); 
			for (Object obj : list.getSelectedValuesList()) { 
				files.add((File) obj); 
			} 
			return new FileTransferable(files); 
		} 
		@Override 
		public int getSourceActions(JComponent c) { 
			return MOVE; 
		} 
	} 
	private class FileTransferable implements Transferable { 
		private List<File> files; 
		public FileTransferable(List<File> files) { 
			this.files = files; 
		} 
		public DataFlavor[] getTransferDataFlavors() { 
			return new DataFlavor[] { DataFlavor.javaFileListFlavor }; 
		} 
		public boolean isDataFlavorSupported(DataFlavor flavor) { 
			return flavor.equals(DataFlavor.javaFileListFlavor); 
		} 
		public Object getTransferData(DataFlavor flavor) 
				throws UnsupportedFlavorException, IOException { 
			if (!isDataFlavorSupported(flavor)) { 
				throw new UnsupportedFlavorException(flavor); 
			} 
			return files; 
		} 
	} 
	//같은 곡이 이미 추가되어 있는지 확인 
	public static boolean checkConflict(File file){
		if(mList.contains(file)){
			return true;
		}
		return false;
	}
	//파일 열기로 곡 추가
	public void fileOpen(){
		fileChooser.setMultiSelectionEnabled(true);
		if(fileChooser.showOpenDialog(MusicManagerView.this)==JFileChooser.APPROVE_OPTION){
			File[] tempFiles = fileChooser.getSelectedFiles();
			for(File file : tempFiles){
				if(!checkConflict(file)){
					mList.add(file);
					listModel.addElement(file); 
				}
				else{
					System.out.println("Already existing file");
				}
			}
		}
	}
	
	private class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//저장을 눌렀을 때 관리자의 메소드를 불러와서 음악 추가
			if(e.getSource() == btnSave){
				System.out.println("저장될 파일:"+mList);
				for (File file : mList) { 
					admin.addMusic(file);
				}
				setVisible(false);
			}
			//취소를 눌렀을 때 화면 없어짐
			else if(e.getSource() == btnCancel){
				setVisible(false);
			}
			//열기를 눌렀을 때 파일 추가
			else if(e.getSource()== btnOpen){
				fileOpen();
			}			
		}		
	}
	
}
