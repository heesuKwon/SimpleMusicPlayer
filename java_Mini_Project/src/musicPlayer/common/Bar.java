package musicPlayer.common;

import javax.swing.JProgressBar;

public class Bar extends Thread{

	private JProgressBar bar;
	private int seconds;
	private int playerStatus;
	private final static int PLAYING = 1;
	private final static int PAUSED = 2;
	private final static int FINISHED = 3;
	private int min;

	public Bar(JProgressBar bar, int seconds, int playerStatus, int min) {
		this.bar = bar;
		this.seconds = seconds;
		this.playerStatus = playerStatus;
		this.min = min;
	}
	@Override
	public void run() {

		try {
			bar.setValue(min);
			bar.setMaximum(seconds);
			while(!Thread.currentThread().isInterrupted()) {

				for(int i=min; i<=seconds; i++) {
					System.out.println(i);

					bar.setValue(i);
					min = i;
					Thread.sleep(1000);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(playerStatus == PAUSED) {
				bar.setValue(min);
			}
			else if(playerStatus == FINISHED) {
				min = 0;
				bar.setValue(min);
			}
		}

	}
	
}