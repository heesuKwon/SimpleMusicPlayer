package musicPlayer.view;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;

import musicPlayer.model.vo.Administrator;

public class MusicManagerView extends JFrame implements DropTargetListener{

	Administrator admin = new Administrator();
	
	private static final long serialVersionUID = -361325089091244347L; 
	private DefaultListModel<File> listModel = new DefaultListModel<>(); 
	@SuppressWarnings("unused") 
	private DropTarget dropTarget; 
	private JLabel jLabel1; 
	private JScrollPane jScrollPane1; 
	private JList<File> list;
	
	private List<File> files = new ArrayList<File>();
	
	private JMenu Mfile = new JMenu("File");
	
	//이벤트 컴포넌트
	private JButton btnSave;//저장버튼
	private JButton btnCancel;//취소버튼
	

	public MusicManagerView() {	
		initComponents(); 
		dropTarget = new DropTarget(list, this); 
		list.setModel(listModel); 
		list.setDragEnabled(true); 
		list.setTransferHandler(new FileTransferHandler()); 
	}

	private void initComponents() { 
		java.awt.GridBagConstraints gridBagConstraints; 
		jLabel1 = new JLabel(); 
		jScrollPane1 = new JScrollPane(); 
		list = new JList<>(); 
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); 
		getContentPane().setLayout(new java.awt.GridBagLayout()); 
		jLabel1.setText("Files:"); 
		gridBagConstraints = new java.awt.GridBagConstraints(); 
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER; 
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST; 
		getContentPane().add(jLabel1, gridBagConstraints); 
		jScrollPane1.setViewportView(list); 
		gridBagConstraints = new java.awt.GridBagConstraints(); 
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER; 
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL; 
		getContentPane().add(jScrollPane1, gridBagConstraints); 
		
		//저장/취소버튼
		btnSave = new JButton("저장");
		btnCancel = new JButton("취소");
		btnSave.setSize(60, 30);
		btnCancel.setSize(60, 30);
		
		//이벤트리스너등록
		MyActionListener listener = new MyActionListener();
		btnSave.addActionListener(listener);
		btnCancel.addActionListener(listener);
		
		getContentPane().add(btnSave);
		getContentPane().add(btnCancel);
		setVisible(true); 
		pack(); 
	} 
	public void dragEnter(DropTargetDragEvent arg0) {} 
	public void dragOver(DropTargetDragEvent arg0) {} 
	public void dropActionChanged(DropTargetDragEvent arg0) {} 
	public void dragExit(DropTargetEvent arg0) {} 
	@SuppressWarnings("unchecked") 
	public void drop(DropTargetDropEvent evt) { 
		int action = evt.getDropAction(); 
		evt.acceptDrop(action); 
		try { 
			Transferable data = evt.getTransferable(); 
			if (data.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) { 
				files = (List<File>) data.getTransferData(DataFlavor.javaFileListFlavor); 
				for (File file : files) { 
					listModel.addElement(file); 
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
	private class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnSave){
				for (File file : files) { 
					admin.addMusic(file);
				}
			}
			else if(e.getSource() == btnCancel)
				setVisible(false);
			
		}
	}
}
