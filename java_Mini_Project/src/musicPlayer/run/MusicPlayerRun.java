package musicPlayer.run;

import musicPlayer.controller.PlayerController;
import musicPlayer.view.MainViewFrame2;

public class MusicPlayerRun {

	public static void main(String[] args) {
		new MainViewFrame2(new PlayerController());
	}

}
