package musicPlayer.run;

import java.util.List;

import musicPlayer.model.vo.Music;
import musicPlayer.model.vo.StoreMusic;

public class MusicPlayerRun {
	private static List<Music> allMusic;

	public static void main(String[] args) {
		StoreMusic sm = new StoreMusic("C:\\Workspaces\\java_workspace\\java_Mini_Project\\musics");
		allMusic = sm.getAllMusic();
	}

}
