package musicPlayer.common;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyUtil {
	
	public static void changePanel(JFrame f, JPanel old, JPanel new_) {
		f.remove(old);
		f.add(new_);
		
		//다시 그려주기(중요!)
		f.repaint();
	}

}
