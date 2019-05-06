package musicPlayer.view;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * 재사용 가능한 프레임클래스를 미리 생성함.
 * 
 * @author shqkel1863
 *
 */
public class MyFrame extends JFrame {
	
	public MyFrame(){}
	
	public MyFrame(int x,int y, int width, int height){
		this(x, y, width, height, false, "MyFrame", null);
	}
	
	public MyFrame(int x,int y, int width, int height, boolean resizable, String title, String icon){
		this.setTitle(title);
		this.setBounds(x, y, width, height);
		this.setResizable(resizable);
		
		if(icon != null)
			this.setIconImage(new ImageIcon(icon).getImage());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);	//JFrame 기본 LayoutManger는 BorderLayout이다.
	}
}
