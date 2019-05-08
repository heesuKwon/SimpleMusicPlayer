package musicPlayer.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javazoom.jl.decoder.JavaLayerException;
import musicPlayer.common.PausablePlayer;
import musicPlayer.model.vo.Music;

public class PlayListView extends JPanel{

	private int index = 0;					//마우스 재생시의 인덱스 값
	private int clickbf = 0;				//버튼 재생시의 인덱스 값	
	private int state = 0;			
	//0 정지 상태 1 재생 상태 2 일시 정지 상태 3 마우스 정지 상태 4 마우스 재생 상태 5 마우스 일시정지 상태
	private JButton play;					//재생, 일시정지 버튼
	private JButton stop;					//정지버튼
	private JButton next;					//다음버튼
	private JButton back;					//이전버튼
	private JLabel back_;					//"|◁"
	private JLabel play_;					//"▷","❚❚"
	private JLabel stop_;					//"■"
	private JLabel next_;					//"▷|"
	private DefaultListModel<String> m;		//JList에 담을 음악 제목
	private JList l;						//JList 그 자체
	private JPanel title;					//음악 재생시 타이틀 부분
	private JLabel titlej;					//타이틀 그 자체
	private ArrayList<String> path;			//절대 경로 리스트
	private List<FileInputStream> input;	//절대경로 입력받는 리스트
	private List<PausablePlayer> player;	//입력받은 mp3파일 넣은 리스트

	public PlayListView(List<Music> playList) throws JavaLayerException, FileNotFoundException {
		//f = new MyFrame(400, 100, 900, 500);
		setBounds(0, 100, 400, 400);
		setLayout(null);

		//재생 정지 다음 이전 버튼
		JPanel button = new JPanel();
		TitledBorder buttonBorder = new TitledBorder(new LineBorder(Color.black), null);
		button.setBorder(buttonBorder);
		button.setBounds(0, 320, 400, 60);
		button.setLayout(new GridLayout(1, 4));
		back = new JButton();
		play = new JButton();
		stop = new JButton();
		next = new JButton();
		back_ = new JLabel("|◁");
		back_.setAlignmentX(Label.CENTER_ALIGNMENT);
		play_ = new JLabel("▷");
		play_.setAlignmentX(Label.CENTER_ALIGNMENT);
		stop_ = new JLabel("■");
		stop_.setAlignmentX(Label.CENTER_ALIGNMENT);
		next_ = new JLabel("▷|");
		next_.setAlignmentX(Label.CENTER_ALIGNMENT);
		back.add(back_);
		play.add(play_);
		stop.add(stop_);
		next.add(next_);
		button.add(back);
		button.add(play);
		button.add(stop);
		button.add(next);
		
		//이바는 잘 모르겠는데 일단 냅두겠습니다. 수정할게요
		JPanel bar = new JPanel();
		bar.setBounds(0, 270, 400, 50);
		TitledBorder barBorder = new TitledBorder(new LineBorder(Color.black), null);
		bar.setBorder(barBorder);
		bar.setLayout(null);
//		JPanel sound_ = new JPanel();
//		sound_.setBounds(50, 280, 380, 5);
//		TitledBorder sound_Border = new TitledBorder(new LineBorder(Color.black), null);
//		sound_.setBorder(sound_Border);
//		sound_.setLayout(null);
		
		//mp3 파일을 넣음
		path = new ArrayList<String>();
		input = new ArrayList<FileInputStream>();
		player = new ArrayList<PausablePlayer>(); 
		for(int i=0;i<playList.size();i++) {
			path.add(playList.get(i).getPath());
			input.add(new FileInputStream(path.get(i)));
			player.add(new PausablePlayer(input.get(i)));
		}
		//재생했을 때 제목이 보여질 부분
		title = new JPanel();
		title.setBounds(0, 230, 400, 40);
		TitledBorder title_Border = new TitledBorder(new LineBorder(Color.black), null);
		title.setBorder(title_Border);
		titlej = new JLabel();
		titlej.setFont(new Font("맑은 고딕", Font.ITALIC, 10));
		titlej.setAlignmentX(Label.CENTER_ALIGNMENT);
		title.add(titlej);

		//버튼 눌렀을 때 이벤트 발생
		back.addActionListener(new EventHandler());
		play.addActionListener(new EventHandler());
		stop.addActionListener(new EventHandler());
		next.addActionListener(new EventHandler());

		//타이틀을 리스트에 넣어줌
		m = new DefaultListModel<>();
		
		for(int i=0;i<playList.size();i++) {
			m.addElement(playList.get(i).getTitle());
		}
		l = new JList(m);
		JScrollPane scr=new JScrollPane(l);
		scr.setBounds(0, 0, 395, 230);
//		l.setBounds(0, 0, 400, 230);
		l.addListSelectionListener(new MyListSelectionListener());
		l.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		add(scr);
		add(title);
//		add(l);
//		add(sound_);
		add(bar);
		add(button);
		setVisible(true);

	}

	//재생 일시정지 정지 다음 이전에 대한 버튼 기능
	//state = 0 정지 상태
	//state = 1 버튼 시작 상태
	//state = 2 버튼 일시 정지 상태
	//state = 3 클릭 정지 상태
	//state = 4 클릭 시작 상태
	//state = 5 클릭 일시 정지 상태
	class EventHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			

			if(e.getSource() == play) {		//재생버튼을 눌렀을경우
				if(state == 0) {			//정지상태에서 재생버튼 누르는 경우
					state = 1;
					play_.setText("❚❚");
					try {
						player.get(clickbf).play();
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					titlej.setText(m.elementAt(clickbf));
					
				}
				else if(state == 1) {		//재생상태에서 재생버튼을 누르는 경우(일시정지)
					state = 2;
					play_.setText("▷");
					player.get(clickbf).pause();
					titlej.setText(m.elementAt(clickbf));
				}
				else if(state == 2) {		//일시정지상태에서 재생버튼을 누르는 경우
					state = 1;
					play_.setText("❚❚");
					player.get(clickbf).resume();
					titlej.setText(m.elementAt(clickbf));
				}
				else if(state == 3) {		//정지상태에서 재생버튼 누르는 경우
					state = 4;
					play_.setText("❚❚");
					try {
						player.get(index).play();
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					titlej.setText(m.elementAt(index));
				}
				else if(state == 4) {		//재생상태에서 재생버튼을 누르는 경우(일시정지)
					state = 5;
					play_.setText("▷");
					player.get(index).pause();
					titlej.setText(m.elementAt(index));
				}
				else if(state == 5) {		//일시정지상태에서 재생버튼을 누르는 경우
					state = 4;
					play_.setText("❚❚");
					player.get(index).resume();
					titlej.setText(m.elementAt(index));
				}
			}
			else if(e.getSource() == stop) {		//정지버튼을 누르는 경우 어떤 상태인지 무관하게 정지
				if(state == 0) {
					state = 0;
					play_.setText("▷");
					player.get(clickbf).stop();
					player.remove(clickbf);
					input.remove(clickbf);
					try {
						input.add(clickbf, new FileInputStream(path.get(clickbf)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(clickbf, new PausablePlayer(input.get(clickbf)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					titlej.setText(m.elementAt(clickbf));
				}
				if(state == 1) {
					state = 0;
					play_.setText("▷");
					player.get(clickbf).stop();
					player.remove(clickbf);
					input.remove(clickbf);
					try {
						input.add(clickbf, new FileInputStream(path.get(clickbf)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(clickbf, new PausablePlayer(input.get(clickbf)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					titlej.setText(m.elementAt(clickbf));
				}
				if(state == 2) {
					state = 0;
					play_.setText("▷");
					player.get(clickbf).stop();
					player.remove(clickbf);
					input.remove(clickbf);
					try {
						input.add(clickbf, new FileInputStream(path.get(clickbf)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(clickbf, new PausablePlayer(input.get(clickbf)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					titlej.setText(m.elementAt(clickbf));
				}
				if(state == 3) {
					state = 3;
					play_.setText("▷");
					player.get(index).stop();
					player.remove(index);
					input.remove(index);
					try {
						input.add(index, new FileInputStream(path.get(index)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(index, new PausablePlayer(input.get(index)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					titlej.setText(m.elementAt(index));
				}
				if(state == 4) {
					state = 3;
					play_.setText("▷");
					player.get(index).stop();
					player.remove(index);
					input.remove(index);
					try {
						input.add(index, new FileInputStream(path.get(index)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(index, new PausablePlayer(input.get(index)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					titlej.setText(m.elementAt(index));
				}
				if(state == 5) {
					state = 3;
					play_.setText("▷");
					player.get(index).stop();
					player.remove(index);
					input.remove(index);
					try {
						input.add(index, new FileInputStream(path.get(index)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(index, new PausablePlayer(input.get(index)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					titlej.setText(m.elementAt(index));
				}

			}
			else if(e.getSource() == next) {		//다음버튼 누르는 경우
				if(state == 0) {					//정지상태에서 다음을 누르면 정지상태는 유지하면서 곡만 넘어간다.
					clickbf++;
					play_.setText("▷");
					if(clickbf > player.size()-1) {
						clickbf = 0;
					}
					titlej.setText(m.elementAt(clickbf));
				}
				else if(state == 1) {				//재생상태에서 다음을 누르면 재생되고 있던 곡은 멈추고 다음곡 재생
					player.get(clickbf).stop();
					player.remove(clickbf);
					input.remove(clickbf);
					try {
						input.add(clickbf, new FileInputStream(path.get(clickbf)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(clickbf, new PausablePlayer(input.get(clickbf)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					clickbf++;
					play_.setText("❚❚");
					if(clickbf > player.size()-1) {
						clickbf = 0;
					}
					try {
						player.get(clickbf).play();
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					titlej.setText(m.elementAt(clickbf));
				}
				else if(state == 2) {		//일시정지 상태에서 다음을 누르면 정지 상태 유지하면서 다음곡으로 넘어감
					state = 0;
					player.get(clickbf).stop();
					player.remove(clickbf);
					input.remove(clickbf);
					try {
						input.add(clickbf, new FileInputStream(path.get(clickbf)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(clickbf, new PausablePlayer(input.get(clickbf)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					clickbf++;
					play_.setText("▷");
					if(clickbf > player.size()-1) {
						clickbf = 0;
					}
					titlej.setText(m.elementAt(clickbf));
				}
				else if(state == 3) {		//정지상태에서 다음을 누르면 정지상태는 유지하면서 곡만 넘어간다.
					index++;
					play_.setText("▷");
					if(index > player.size()-1) {
						index = 0;
					}
					titlej.setText(m.elementAt(index));
				}
				else if(state == 4) {		//재생상태에서 다음을 누르면 재생되고 있던 곡은 멈추고 다음곡 재생
					player.get(index).stop();
					player.remove(index);
					input.remove(index);
					try {
						input.add(index, new FileInputStream(path.get(index)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(index, new PausablePlayer(input.get(index)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					play_.setText("❚❚");
					index++;
					if(index > player.size()-1) {
						index = 0;
					}
					try {
						player.get(index).play();
					} catch (JavaLayerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					titlej.setText(m.elementAt(index));
				}
				else if(state == 5) {		//일시정지 상태에서 다음을 누르면 정지 상태 유지하면서 다음곡으로 넘어감
					state = 3;
					player.get(index).stop();
					player.remove(index);
					input.remove(index);
					try {
						input.add(index, new FileInputStream(path.get(index)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(index, new PausablePlayer(input.get(index)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					index++;
					play_.setText("▷");
					if(index > player.size()-1) {
						index = 0;
					}
					titlej.setText(m.elementAt(index));
				}
			}
			else if(e.getSource() == back) {		//다음과 마찬가지인데 이전으로 바뀜
				if(state == 0) {
					clickbf--;
					play_.setText("▷");
					if(clickbf < 0) {
						clickbf = player.size()-1;
					}
					titlej.setText(m.elementAt(clickbf));
				}
				else if(state == 1) {
					player.get(clickbf).stop();
					player.remove(clickbf);
					input.remove(clickbf);
					try {
						input.add(clickbf, new FileInputStream(path.get(clickbf)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(clickbf, new PausablePlayer(input.get(clickbf)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					clickbf--;
					play_.setText("❚❚");
					if(clickbf < 0) {
						clickbf = player.size()-1;
					}
					try {
						player.get(clickbf).play();
					} catch (JavaLayerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					titlej.setText(m.elementAt(clickbf));
				}
				else if(state == 2) {
					state = 0;
					player.get(clickbf).stop();
					player.remove(clickbf);
					input.remove(clickbf);
					try {
						input.add(clickbf, new FileInputStream(path.get(clickbf)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(clickbf, new PausablePlayer(input.get(clickbf)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					clickbf--;
					play_.setText("▷");
					if(clickbf < 0) {
						clickbf = player.size()-1;
					}
					titlej.setText(m.elementAt(clickbf));
				}
				else if(state == 3) {
					index--;
					play_.setText("▷");
					if(index < 0) {
						index = player.size()-1;
					}
					titlej.setText(m.elementAt(index));
				}
				else if(state == 4) {
					player.get(index).stop();
					player.remove(index);
					input.remove(index);
					try {
						input.add(index, new FileInputStream(path.get(index)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(index, new PausablePlayer(input.get(index)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					index--;
					play_.setText("❚❚");
					if(index < 0) {
						index = player.size()-1;
					}
					try {
						player.get(index).play();
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					titlej.setText(m.elementAt(index));
				}
				else if(state == 5) {
					state = 3;
					player.get(index).stop();
					player.remove(index);
					input.remove(index);
					try {
						input.add(index, new FileInputStream(path.get(index)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						player.add(index, new PausablePlayer(input.get(index)));
					} catch (JavaLayerException e1) {
						e1.printStackTrace();
					}
					index--;
					play_.setText("▷");
					if(index < 0) {
						index = player.size()-1;
					}
					titlej.setText(m.elementAt(index));
				}

			}


		}

	}

	class MyListSelectionListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			//상태와는 무관하게 리스트를 누르면 선택된 리스트를 재생한다.
			if(state == 0) {
				state = 4;
				player.get(clickbf).stop();
				player.remove(clickbf);
				input.remove(clickbf);
				try {
					input.add(clickbf, new FileInputStream(path.get(clickbf)));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					player.add(clickbf, new PausablePlayer(input.get(clickbf)));
				} catch (JavaLayerException e1) {
					e1.printStackTrace();
				}
				play_.setText("❚❚");
				try {
					player.get(l.getSelectedIndex()).play();
				} catch (JavaLayerException e1) {
					e1.printStackTrace();
				}
				index = l.getSelectedIndex();
				titlej.setText(m.elementAt(index));
			}
			else if(state == 1) {
				state = 4;
				player.get(clickbf).stop();
				player.remove(clickbf);
				input.remove(clickbf);
				try {
					input.add(clickbf, new FileInputStream(path.get(clickbf)));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					player.add(clickbf, new PausablePlayer(input.get(clickbf)));
				} catch (JavaLayerException e1) {
					e1.printStackTrace();
				}
				play_.setText("❚❚");
				try {
					player.get(l.getSelectedIndex()).play();
				} catch (JavaLayerException e1) {
					e1.printStackTrace();
				}
				titlej.setText(m.elementAt(index));
			}
			else if(state == 2) {
				state = 4;
				player.get(clickbf).stop();
				player.remove(clickbf);
				input.remove(clickbf);
				try {
					input.add(clickbf, new FileInputStream(path.get(clickbf)));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					player.add(clickbf, new PausablePlayer(input.get(clickbf)));
				} catch (JavaLayerException e1) {
					e1.printStackTrace();
				}
				play_.setText("❚❚");
				try {
					player.get(l.getSelectedIndex()).play();
				} catch (JavaLayerException e1) {
					e1.printStackTrace();
				}
				titlej.setText(m.elementAt(index));
			}
			else if(state == 3) {
				state = 4;
				player.get(index).stop();
				player.remove(index);
				input.remove(index);
				try {
					input.add(index, new FileInputStream(path.get(index)));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					player.add(index, new PausablePlayer(input.get(index)));
				} catch (JavaLayerException e1) {
					e1.printStackTrace();
				}
				play_.setText("❚❚");
				try {
					player.get(l.getSelectedIndex()).play();
				} catch (JavaLayerException e1) {
					e1.printStackTrace();
				}
				index = l.getSelectedIndex();
				titlej.setText(m.elementAt(index));
			}
			else if(state == 4) {
				state = 4;
				player.get(index).stop();
				player.remove(index);
				input.remove(index);
				try {
					input.add(index, new FileInputStream(path.get(index)));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					player.add(index, new PausablePlayer(input.get(index)));
				} catch (JavaLayerException e1) {
					e1.printStackTrace();
				}
				play_.setText("❚❚");
				try {
					player.get(l.getSelectedIndex()).play();
				} catch (JavaLayerException e1) {
					e1.printStackTrace();
				}
				index = l.getSelectedIndex();
				titlej.setText(m.elementAt(index));
			}
			else if(state == 5) {
				state = 4;
				player.get(index).stop();
				player.remove(index);
				input.remove(index);
				try {
					input.add(index, new FileInputStream(path.get(index)));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					player.add(index, new PausablePlayer(input.get(index)));
				} catch (JavaLayerException e1) {
					e1.printStackTrace();
				}
				play_.setText("❚❚");
				try {
					player.get(l.getSelectedIndex()).play();
				} catch (JavaLayerException e1) {
					e1.printStackTrace();
				}
				index = l.getSelectedIndex();
				titlej.setText(m.elementAt(index));


			}


		}
	}

//	public static void main(String[] args) {
//		try {
//			new PlayListView();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (JavaLayerException e) {
//			e.printStackTrace();
//		}
//
//	}

}
