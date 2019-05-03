package musicPlayer.run;

import java.util.List;

import musicPlayer.controller.PlayerController;
import musicPlayer.model.vo.Music;
import musicPlayer.model.vo.Administrator;
import musicPlayer.view.PlayerView;

public class MusicPlayerRun {
	private static List<Music> allMusic;

	public static void main(String[] args) {
//		StoreMusic sm = new StoreMusic("C:\\Workspaces\\java_workspace\\java_Mini_Project\\musics");
		Administrator sm = new Administrator("musics");
		allMusic = sm.getAllMusic();
		new PlayerView(new PlayerController());
	}

}
