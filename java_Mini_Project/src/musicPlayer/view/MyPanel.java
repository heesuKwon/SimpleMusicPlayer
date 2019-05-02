package musicPlayer.view;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * 재사용 가능한 패널클래스를 미리 생성함.
 * 
 * @author shqkel1863
 *
 */
public class MyPanel extends JPanel {
	public MyPanel(){}
	
	public MyPanel(int x,int y, int width, int height){
		this(x, y, width, height, null, null, null);
	}
			
	public MyPanel(int x,int y, int width, int height, String borderTitle, Color borderColor, Color bgColor){
		this.setBounds(x, y, width, height);
		
		if(borderColor==null)
			borderColor = Color.black;
		this.setBorder(new TitledBorder(new LineBorder(borderColor),borderTitle));
		
		if(bgColor != null)
			this.setBackground(bgColor);	
		
	}
}
