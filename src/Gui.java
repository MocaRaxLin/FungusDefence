import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;

public class Gui extends JFrame implements MouseListener, ActionListener {
	playSound sound = new playSound();
	Timer timer = new Timer();
	static boolean control=true;
	JButton btn_c = new JButton("切換模式");// 切換畫面用button
	JLabel place[] = new JLabel[20];// 塔位置用label
	JLabel fungusTower[] = new JLabel[20];// 塔用label
	JLabel kind[] = new JLabel[3];// 物品窗物品用label
	JLabel home = new JLabel(new ImageIcon(getClass().getResource(
			"img/Home.png")));
	
	static JLabel label_hp;
	static JLabel n[] = new JLabel[3];// 顯示物品數量用label
	static JLabel choose = new JLabel();// 物品窗上方，顯示選擇的塔類型
	JLabel catchl = new JLabel();
	static Monster lb_monster1[] = new Monster[10];
	Thread thread[] = new Thread[10];
	Tower tower_t[] = new Tower[20];
	
	// 不同香菇的座標
	int x[] = { 50, 20, 350 };// 0~540(螢幕寬扣掉圖片寬)
	int y[] = { 250, 320, 330 };// 180~540(螢幕高扣掉調圖片高)

	static ImageIcon m_tl[] = new ImageIcon[4];// 物品窗上方塔類型圖片用label
	static ImageIcon change = new ImageIcon(
			Gui.class.getResource("img/change.png"));// 切換模式用button的圖片
	// ImageIcon img_monster1 = new
	// ImageIcon(Gui.class.getResource("img/monster1.png"));

	ImageIcon tower[] = new ImageIcon[4];// 塔防窗塔類型圖片用label
	ImageIcon test = new ImageIcon(Gui.class.getResource("img/map4.png"));// 測試用圖片

	// 物品窗中間塔用圖片------------------------------
	ImageIcon towerSign1 = new ImageIcon(Gui.class.getResource("img/tl1.png"));
	ImageIcon towerSign2 = new ImageIcon(Gui.class.getResource("img/tl2.png"));
	ImageIcon towerSign3 = new ImageIcon(Gui.class.getResource("img/tl3.png"));

	// -----------------------------------------------------------
	static int sN[] = new int[4];// 擁有的塔數量;
	static int KIND_OF_TOWER = 3;// 塔的種類;
	static int PLACE_STATUS[] = new int[20];// 放置地點的狀態，0為尚可放置，1為已被放置
	static int home_hp = 100;// 我方血量
	static final int WAIT_FOR_CLICK = 0;
	static final int LEVEL1 = 1;
	static final int LEVEL2 = 2;
	static final int LEVEL3 = 3;
	static final int LEVEL4 = 4;
	static final int LEVEL5 = 5;
	int now_level = 0;
	static int killed=0;
	// 錢幣計數-------------------------------------------------------
	JLabel coinNumber = new JLabel();
	JLabel coinCount = new JLabel();
	ImageIcon coinCount_pic = new ImageIcon(
			Gui.class.getResource("img/coinCount.png"));
	static int COIN_NUMBER = 2;
	String sCoinNumber;

	// 夾娃娃機-------------------------------------------------------
	JButton coinSlot = new JButton();
	int ctrl1_coinSlot = 0;
	int ctrl2_coinSlot = 0;
	int ctrl3_coinSlot = 0;
	ImageIcon coinSlot_pic0 = new ImageIcon(
			Gui.class.getResource("img/coinSlot0.png"));
	ImageIcon coinSlot_pic1 = new ImageIcon(
			Gui.class.getResource("img/coinSlot1.png"));
	ImageIcon coinSlot_pic2 = new ImageIcon(
			Gui.class.getResource("img/coinSlot2.png"));
	ImageIcon coinSlot_pic3 = new ImageIcon(
			Gui.class.getResource("img/coinSlot3.png"));
	ImageIcon coinSlot_pic4 = new ImageIcon(
			Gui.class.getResource("img/coinSlot4.png"));
	ImageIcon coinSlot_pic5 = new ImageIcon(
			Gui.class.getResource("img/coinSlot5.png"));
	ImageIcon catch1_pic = new ImageIcon(
			Gui.class.getResource("img/catch1.png"));
	ImageIcon catch2_pic = new ImageIcon(
			Gui.class.getResource("img/catch2.png"));
	JButton ctrl_catch = new JButton();
	ImageIcon ctrl_catch_pic0 = new ImageIcon(
			Gui.class.getResource("img/m_back.png"));
	ImageIcon ctrl_catch_pic = new ImageIcon(
			Gui.class.getResource("img/faucet.png"));
	ImageIcon ctrl_catch_pic2 = new ImageIcon(
			Gui.class.getResource("img/catchbt.png"));
	JLabel RunningFungus[] = new JLabel[3];
	ImageIcon gg_pic[] = new ImageIcon[3];
	int controll1 = 0;
	int controll2 = 0;// 版面停止設定=0可使用/=1使用中/=2...
	int controll3 = 0;// 無法一直投錢
	int controll4 = 0;// 夾取後touch失效 各種菇菇停止移動
	int down = 0;
	int dx = 5;
	int dy = 5;
	int x1 = 5;//各種菇菇的移動向量(有正負差別)
	int y1 = 5;
	int x2 = 5;
	int y2 = 5;
	int x3 = 5;
	int y3 = 5;
	int gg_X[] = new int[3];//存入夾取暫停時當下的菇菇位置
	int gg_Y[] = new int[3];
	// 起始版面

	static JPanel panel_s = new JPanel() {// 背景
		public void paintComponent(Graphics g) {// 內建畫板
			ImageIcon image_bg = new ImageIcon(
					Gui.class.getResource("img/background.png"));// pic背景
			Rectangle rect = g.getClipBounds();
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
			g.drawImage(image_bg.getImage(), 0, 0, 800, 600, null, null);// 背景
		}
	};

	// 訊息版面
	static JPanel panel_m = new JPanel() {// 背景
		public void paintComponent(Graphics g) {// 內建畫板
			ImageIcon image_bg = new ImageIcon(
					Gui.class.getResource("img/m1.png"));// pic背景
			Rectangle rect = g.getClipBounds();
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
			g.drawImage(image_bg.getImage(), 0, 0, 200, 600, null, null);// 背景
		}
	};

	// 塔防版面
	static JPanel panel_f = new JPanel() {// 背景
		public void paintComponent(Graphics g) {// 內建畫板
			ImageIcon image_bg = new ImageIcon(
					Gui.class.getResource("img/map4.png"));// pic背景
			Rectangle rect = g.getClipBounds();
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
			g.drawImage(image_bg.getImage(), 0, 0, 600, 570, null, null);// 背景
		}
	};

	// 夾娃娃版面
	static JPanel panel_g = new JPanel() {// 背景
		public void paintComponent(Graphics g) {// 內建畫板
			ImageIcon image_bg = new ImageIcon(
					Gui.class.getResource("img/g.jpg"));// pic背景
			Rectangle rect = g.getClipBounds();
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
			g.drawImage(image_bg.getImage(), 0, 0, 600, 600, null, null);// 背景
		}
	};

	// 繪製gui-------------------------------------------------------------------------
	public Gui() {
		drawGui();
		sound.playBackgroundSound();
	}

	// 設定gui
	public void drawGui() {
		// test
		sN[0] = 3;
		sN[1] = 1;
		sN[2] = 0;

		// gui基礎設定-----------------------------------------
		this.setBackground(Color.black);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Fungus Pagoda System");
		this.setResizable(false);
		this.getContentPane().setLayout(null);

		// 起始畫面設定-------------------------------------------
		panel_s.setBounds(0, 0, 800, 600);
		panel_s.setLayout(null);
		panel_s.addMouseListener(this);

		panel_s.setVisible(true);

		// 訊息畫面設定-------------------------------------------
		panel_m.setBounds(600, 0, 200, 600);
		panel_m.setLayout(null);
		btn_c.setBounds(50, 490, 100, 30);
		btn_c.setIcon(change);
	

		// 錢幣計數--------------------------------------
		sCoinNumber = Integer.toString(COIN_NUMBER);
		coinNumber.setFont(new java.awt.Font("Dialog", 1, 40));
		coinNumber.setForeground(Color.black);
		coinNumber.setText("  " + sCoinNumber);
		coinNumber.setBounds(80, 300, 100, 40);
		coinCount.setIcon(coinCount_pic);
		coinCount.setBounds(22, 300, 57, 47);
		panel_m.add(coinNumber);
		panel_m.add(coinCount);
		// seed---------------------------------
		for (int i = 0; i < 3; i++) {
			kind[i] = new JLabel();
			panel_m.add(kind[i]);

			kind[i].addMouseListener(this);
			kind[i].setBounds(30, 150 + (i * 50), 40, 40);
		}

		kind[0].setIcon(towerSign1);// 設定圖片
		kind[1].setIcon(towerSign2);
		kind[2].setIcon(towerSign3);

		// n---------------------------------------
		for (int i = 0; i < 3; i++) {
			// sN[i] = i;
			n[i] = new JLabel();
			n[i].setText("" + sN[i]);// 將擁有塔的數量設定進label中用以顯示
			n[i].setFont(new java.awt.Font("Dialog", 1, 40));// 設定字型
			n[i].setForeground(Color.black);// 設定字的顏色
			n[i].setBounds(100, 150 + (i * 50), 100, 40);
			panel_m.add(n[i]);
		}

		// choose------------------------------

		m_tl[3] = new ImageIcon(Gui.class.getResource("img/m_back.png"));// 設定圖片
		m_tl[0] = new ImageIcon(Gui.class.getResource("img/m_tl1.png"));
		m_tl[1] = new ImageIcon(Gui.class.getResource("img/m_tl2.png"));
		m_tl[2] = new ImageIcon(Gui.class.getResource("img/m_tl3.png"));

		choose.setIcon(m_tl[3]);
		choose.setBounds(50, 30, 100, 100);

		panel_m.add(choose);
		panel_m.add(btn_c);
		panel_m.setVisible(false);

		// 塔防畫面設定-------------------------------------------
		panel_f.setBounds(0, 0, 600, 600);
		panel_f.setLayout(null);
		panel_f.setVisible(false);
		panel_f.addMouseListener(this);

		tower[0] = new ImageIcon(Gui.class.getResource("img/tower1.png"));// 設定圖片
		tower[1] = new ImageIcon(Gui.class.getResource("img/tower2.png"));
		tower[2] = new ImageIcon(Gui.class.getResource("img/tower3.png"));

		for (int i = 0; i < 20; i++) {
			PLACE_STATUS[i] = 0;
			place[i] = new JLabel();
			place[i].addMouseListener(this);

			fungusTower[i] = new JLabel();
			// fungusTower[i].setIcon(tower2);

			panel_f.add(place[i]);
			panel_f.add(fungusTower[i]);
		}
		label_hp = new JLabel("<html>目前血量<br><html>" + home_hp);
		label_hp.setBounds(500, 400, 100, 50);
		panel_f.add(label_hp);
		home.setBounds(450, 390, 50, 100);
		panel_f.add(home);

		// monster-----------------

		// fungus----------------------------------------------------------
		place[0].setBounds(200, 25, 40, 40);
		place[1].setBounds(310, 25, 40, 40);

		place[2].setBounds(150, 145, 40, 40);
		place[3].setBounds(260, 145, 40, 40);
		place[4].setBounds(372, 145, 40, 40);
		place[5].setBounds(498, 145, 40, 40);

		place[6].setBounds(150, 195, 40, 40);
		place[7].setBounds(260, 195, 40, 40);
		place[8].setBounds(372, 195, 40, 40);
		place[9].setBounds(498, 195, 40, 40);

		place[10].setBounds(72, 312, 40, 40);
		place[11].setBounds(200, 312, 40, 40);
		place[12].setBounds(310, 312, 40, 40);
		place[13].setBounds(422, 312, 40, 40);

		place[14].setBounds(72, 370, 40, 40);
		place[15].setBounds(200, 370, 40, 40);
		place[16].setBounds(310, 370, 40, 40);
		place[17].setBounds(422, 370, 40, 40);

		place[18].setBounds(260, 490, 40, 40);
		place[19].setBounds(372, 490, 40, 40);

		// fungusTower------------------------------------------------------
		fungusTower[0].setBounds(200, 0, 40, 60);
		fungusTower[1].setBounds(310, 0, 40, 60);

		fungusTower[2].setBounds(148, 110, 40, 60);
		fungusTower[3].setBounds(260, 110, 40, 60);
		fungusTower[4].setBounds(372, 112, 40, 60);
		fungusTower[5].setBounds(499, 112, 40, 60);

		fungusTower[6].setBounds(148, 160, 40, 60);
		fungusTower[7].setBounds(260, 160, 40, 60);
		fungusTower[8].setBounds(372, 160, 40, 60);
		fungusTower[9].setBounds(498, 160, 40, 60);

		fungusTower[10].setBounds(72, 279, 40, 60);
		fungusTower[11].setBounds(198, 279, 40, 60);
		fungusTower[12].setBounds(310, 279, 40, 60);
		fungusTower[13].setBounds(422, 279, 40, 60);

		fungusTower[14].setBounds(72, 337, 40, 60);
		fungusTower[15].setBounds(198, 337, 40, 60);
		fungusTower[16].setBounds(310, 337, 40, 60);
		fungusTower[17].setBounds(422, 337, 40, 60);

		fungusTower[18].setBounds(260, 457, 40, 60);
		fungusTower[19].setBounds(372, 457, 40, 60);

		// 夾娃娃畫面設定------------------------------------------
				panel_g.setBounds(0, 0, 600, 600);
				panel_g.setLayout(null);
				panel_g.setVisible(false);
				catchl.setBounds(0, -280, 88, 446);
				catchl.setIcon(catch1_pic);

				for (int i = 0; i < 3; i++) {
					RunningFungus[i] = new JLabel();
					gg_pic[i] = new ImageIcon();
				}
				gg_pic[0] = new ImageIcon(Gui.class.getResource("img/gg.png"));
				gg_pic[1] = new ImageIcon(Gui.class.getResource("img/gg_teemo.png"));
				gg_pic[2] = new ImageIcon(Gui.class.getResource("img/gg_rock.png"));
				for (int i = 0; i <= 2; i++) {
					RunningFungus[i].setBounds(x[i], y[i], 100, 140);
					RunningFungus[i].setIcon(gg_pic[i]);
				}
				ctrl_catch.setBounds(490, 455, 99, 99);
				ctrl_catch.setIcon(ctrl_catch_pic0);
				ctrl_catch.addActionListener(this);
				// 投幣按鈕-------------------------------------
				coinSlot.setIcon(coinSlot_pic0);
				coinSlot.setBounds(340, 455, 137, 100);
				coinSlot.addActionListener(this);
				coinSlot.addMouseListener(this);
				panel_g.add(coinSlot);

				panel_g.add(ctrl_catch);
				panel_g.add(catchl);
				for (int i = 0; i <= 2; i++) {
					panel_g.add(RunningFungus[i]);
				}
				btn_c.addActionListener(this);
		// frame-------------------------------------

		this.getContentPane().add(panel_s);
		this.getContentPane().add(panel_m);
		this.getContentPane().add(panel_f);
		this.getContentPane().add(panel_g);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn_c) {// 切換頁面

			if (panel_f.isVisible()) {
				System.out.println("2. panel_f to panel_g is green");
				panel_f.setVisible(false);
				panel_g.setVisible(true);
			} else {
				System.out.println("3. panel_g to panel_f is green");
				panel_f.setVisible(true);
				panel_g.setVisible(false);
			}
		}
		if (e.getSource() == coinSlot) {
			sound.coin();
			if (controll3 == 0) {
				controll3 = 1;
				if (controll2 == 0) {
					controll2 = 1;
					if (panel_g.isVisible()) {
						if (COIN_NUMBER > 0) {
							COIN_NUMBER--;
							sCoinNumber = Integer.toString(COIN_NUMBER);
							coinNumber.setText("  " + sCoinNumber);
							ctrl2_coinSlot = 0;
							ctrl_catch.setIcon(ctrl_catch_pic);
							timerCoin2();
						}
					}
				}
			}
		}
		// control1=爪子狀態
				if (e.getSource() == ctrl_catch) {
					if (controll2 == 0 || controll2 == 2) {
						if (controll1 == 2) {// 控制夾娃娃機
							controll2 = 1;
							down = 1; // down = 0關/=1一次夾取動作
							controll1 = 1;// control1 =0關/=1開/=2橫向移動
						}
						if (controll1 == 1) {
							controll2 = 2;
							timeStart_catch();
							timeFungusRun();
							//sound.playRUN();
						}
					}
				}
			}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	
		if (panel_g.isVisible()) {
			ctrl2_coinSlot = 1;
			timerCoin1();
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	
		if (panel_g.isVisible()) {
			ctrl2_coinSlot = 0;
		}

	}
 
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (panel_s.isVisible()) {// 進入遊戲
			panel_s.setVisible(false);
			panel_g.setVisible(false);
			panel_m.setVisible(true);
			panel_f.setVisible(true);
			JOptionPane.showMessageDialog(null,
					"<html><h2>遊戲開始<br>按滑鼠左鍵開始遊戲<br>10秒後出怪<h2><html>");

		} else if (panel_f.isVisible()) {
			if(killed==0&&control){
				now_level=LEVEL1;
				
			}else if(killed==3&&control){
				JOptionPane.showMessageDialog(null, "<html><h2>第二關<br>按滑鼠左鍵開始遊戲<br>10秒後出怪<h2><html>");
				COIN_NUMBER+=2;
				coinNumber.setText("  "+COIN_NUMBER);
				now_level=LEVEL2;
			}else if(killed==8&&control){
				JOptionPane.showMessageDialog(null, "<html><h2>第三關<br>按滑鼠左鍵開始遊戲<br>10秒後出怪<h2><html>");
				COIN_NUMBER+=2;
				coinNumber.setText("  "+COIN_NUMBER);
				now_level=LEVEL3;
			}else if(killed==15&&control){
				JOptionPane.showMessageDialog(null, "<html><h2>第四關<br>按滑鼠左鍵開始遊戲<br>10秒後出怪<h2><html>");
				COIN_NUMBER+=2;
				coinNumber.setText("  "+COIN_NUMBER);
				now_level=LEVEL4;
			}else if(killed==24&&control){
				JOptionPane.showMessageDialog(null, "<html><h2>第五關<br>按滑鼠左鍵開始遊戲<br>10秒後出怪<h2><html>");
				COIN_NUMBER+=2;
				coinNumber.setText("  "+COIN_NUMBER);
				now_level=LEVEL5; 
			}else if(killed==34&&control){
				JOptionPane.showMessageDialog(null, "you win");
				System.exit(0);
			}
			if (now_level == LEVEL1&&control) {

				for (int i = 0; i < 3; i++) {
					lb_monster1[i] = new Monster(100, 1, 10000+500 * i);
					lb_monster1[i].setIcon(new ImageIcon(Gui.class
							.getResource("img/monster1.png")));
					lb_monster1[i].setBounds(50, 88, 50, 50);
					panel_f.add(lb_monster1[i]);
					System.out.println("monster thread start");
					thread[i] = new Thread(lb_monster1[i]);
					thread[i].start();
				} 
				control=false;
		
			} else if (now_level == LEVEL2&&control) {
				for (int i = 0; i < 5; i++) {
					lb_monster1[i] = new Monster(130, 2, 10000+500 * i);
					lb_monster1[i].setIcon(new ImageIcon(Gui.class
							.getResource("img/monster1.png")));
					lb_monster1[i].setBounds(50, 88, 50, 50);
					panel_f.add(lb_monster1[i]);
					System.out.println("monster thread start");
					thread[i] = new Thread(lb_monster1[i]);
					thread[i].start();
				}
				control=false;
			} else if (now_level == LEVEL3&&control) {
				for (int i = 0; i < 7; i++) {
					lb_monster1[i] = new Monster(160, 2, 10000+500 * i);
					lb_monster1[i].setIcon(new ImageIcon(Gui.class
							.getResource("img/monster1.png")));
					lb_monster1[i].setBounds(50, 88, 50, 50);
					panel_f.add(lb_monster1[i]);
					System.out.println("monster thread start");
					thread[i] = new Thread(lb_monster1[i]);
					thread[i].start();
				}
				control=false;
			} else if (now_level == LEVEL4&&control) {
				for (int i = 0; i < 9; i++) {
					lb_monster1[i] = new Monster(190, 3, 10000+500 * i);
					lb_monster1[i].setIcon(new ImageIcon(Gui.class
							.getResource("img/monster1.png")));
					lb_monster1[i].setBounds(50, 88, 50, 50);
					panel_f.add(lb_monster1[i]);
					System.out.println("monster thread start");
					thread[i] = new Thread(lb_monster1[i]);
					thread[i].start();
				}
				control=false;
			} else if (now_level == LEVEL5&&control) {
				for (int i = 0; i < 10; i++) {
					lb_monster1[i] = new Monster(250, 3, 10000+500 * i);
					lb_monster1[i].setIcon(new ImageIcon(Gui.class
							.getResource("img/monster1.png")));
					lb_monster1[i].setBounds(50, 88, 50, 50);
					panel_f.add(lb_monster1[i]);
					System.out.println("monster thread start");
					thread[i] = new Thread(lb_monster1[i]);
					thread[i].start();
				}
				control=false;
			} 
			for (int i = 0; i < 20; i++) {// 放置塔
				if (e.getSource() == place[i]) {
					sound.stop();
					sound.playPutSound();
					sound.playBackgroundSound();
					System.out.println("4. fungusLabel  ( " + (i)
							+ ")  has been clicked");

					if (PLACE_STATUS[i] == 0
							&& (KIND_OF_TOWER <= 2 && KIND_OF_TOWER >= 0)) {
						if (sN[KIND_OF_TOWER] > 0) {
							fungusTower[i].setIcon(tower[KIND_OF_TOWER]);
							PLACE_STATUS[i]++;
							sN[KIND_OF_TOWER]--;

							// 塔執行緒=============
							if (i == 0 || i == 1 || i == 6 || i == 7 || i == 8
									|| i == 15 || i == 16 || i == 17) {
								tower_t[i] = new Tower(
										fungusTower[i].getLocation().x,
										fungusTower[i].getLocation().y,
										KIND_OF_TOWER, Tower.DERECTION_DOWN);

							} else if (i == 2 || i == 3 || i == 4 || i == 11
									|| i == 12 || i == 13 || i == 18 || i == 19) {
								tower_t[i] = new Tower(
										fungusTower[i].getLocation().x,
										fungusTower[i].getLocation().y,
										KIND_OF_TOWER, Tower.DERECTION_UP);

							} else if (i == 5 || i == 9) {
								tower_t[i] = new Tower(
										fungusTower[i].getLocation().x,
										fungusTower[i].getLocation().y,
										KIND_OF_TOWER, Tower.DERECTION_LEFT);

							} else if (i == 10 || i == 14) {
								tower_t[i] = new Tower(
										fungusTower[i].getLocation().x,
										fungusTower[i].getLocation().y,
										KIND_OF_TOWER, Tower.DERECTION_RIGHT);

							}
							panel_f.add(tower_t[i].label_bullet[0]);
							panel_f.add(tower_t[i].label_bullet[1]);
							panel_f.add(tower_t[i].label_bullet[2]);
							Thread a = new Thread(tower_t[i]);
							a.start();
							// =====================
							correct();
							break;
						} else {
							break;
						}
					}

				}
			}
		}

		if (panel_f.isVisible()) {// 選擇塔類型
			for (int i = 0; i < 3; i++) {
				if (e.getSource() == kind[i]) {
					sound.stop();
					sound.playChooseSound();
					sound.playBackgroundSound();
					KIND_OF_TOWER = i;
					choose.setIcon(m_tl[i]);
					System.out.println("5. seedLabel  (" + (i)
							+ ")  has been clicked");
				}
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void correct() {// 重新設定數據
		KIND_OF_TOWER = 3;
		choose.setIcon(m_tl[3]);

		for (int i = 0; i < 3; i++) {
			n[i].setText("" + sN[i]);
		}
	}

	private void timeStart_catch() {
		controll1 = 2;
		ctrl_catch.setIcon(ctrl_catch_pic2);
		TimerTask tc = new TimerTask() {
			public void run() {
				if (down == 1) {
					this.cancel();
					time_catchDown();
				} else if (down == 0) {
					if (catchl.getX() > 512) {
						dx = -5;
					} else if (catchl.getX() < 0) {
						dx = 5;
					}
					catchl.setBounds(catchl.getLocation().x + dx,
							catchl.getLocation().y, 88, 446);
				}
			}
		};
		timer.schedule(tc, 10, 50);
	}
	boolean openRunning = true;// 被抓到要關掉就可以=false

	public void timeFungusRun(){// 撞來撞去
		TimerTask t1 = new TimerTask() {
			public void run() {
				if (controll4 == 1) {
					gg_X[0] = RunningFungus[0].getLocation().x;
					gg_Y[0] = RunningFungus[0].getLocation().y;
					openRunning = false;
					this.cancel();
				}
				if (openRunning = true) {
					if (RunningFungus[0].getLocation().x > 600 - 100) {
						x1 = -5;
						sound.playTouch();
					}
					if (RunningFungus[0].getLocation().x < 0) {
						x1 = 5;
						sound.playTouch();
					}
					if (RunningFungus[0].getLocation().y > 500 - 140) {
						y1 = -5;
						sound.playTouch();
					}
					if (RunningFungus[0].getLocation().y < 180) {
						y1 = 5;
						sound.playTouch();
					}
					RunningFungus[0].setBounds(RunningFungus[0].getLocation().x
							+ x1, RunningFungus[0].getLocation().y + y1, 100,
							140);
				}
			}
		};
		timer.schedule(t1, 10, 30);// 週期設定(執行的工作單元,起始毫秒,每隔幾毫秒再做一次)

		TimerTask t2 = new TimerTask() {

			public void run() {
				if (controll4 == 1) {
					openRunning = false;
					gg_X[1] = RunningFungus[1].getLocation().x;
					gg_Y[1] = RunningFungus[1].getLocation().y;
					this.cancel();
				}
				if (openRunning = true) {
					if (RunningFungus[1].getLocation().x > 600 - 100) {
						x2 = -5;
						sound.playTouch();
					}
					if (RunningFungus[1].getLocation().x < 0) {
						x2 = 5;
						sound.playTouch();
					}
					if (RunningFungus[1].getLocation().y > 500 - 140) {
						y2 = -5;
						sound.playTouch();
					}
					if (RunningFungus[1].getLocation().y < 180) {
						y2 = 5;
						sound.playTouch();
					}
					RunningFungus[1].setBounds(RunningFungus[1].getLocation().x
							+ x2, RunningFungus[1].getLocation().y + y2, 100,
							140);
				}
			}
		};
		timer.schedule(t2, 10, 20);// 週期設定(執行的工作單元,起始毫秒,每隔幾毫秒再做一次)

		TimerTask t3 = new TimerTask() {
			public void run() {
				if (controll4 == 1) {
					gg_X[2] = RunningFungus[2].getLocation().x;
					gg_Y[2] = RunningFungus[2].getLocation().y;
					openRunning = false;
					this.cancel();
				}
				if (openRunning = true) {
					if (RunningFungus[2].getLocation().x > 600 - 100) {
						x3 = -5;
						sound.playTouch();
					}
					if (RunningFungus[2].getLocation().x < 0) {
						x3 = 5;
						sound.playTouch();
					}
					/*if (RunningFungus[2].getLocation().y > 500 - 140) {
						y3 = -5;
						sound.playTouch();
					}
					if (RunningFungus[2].getLocation().y < 180) {
						y3 = 5;
						sound.playTouch();
					}*/
					RunningFungus[2].setBounds(RunningFungus[2].getLocation().x
							+ x3, RunningFungus[2].getLocation().y , 100,
							140);
				}
			}
		};
		timer.schedule(t3, 10, 6);// 週期設定(執行的工作單元,起始毫秒,每隔幾毫秒再做一次)

	}



	private void time_catchDown() {
		dy = 5;
		controll2 = 1;
		controll4 = 1;
		TimerTask td = new TimerTask() {
			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					if (touched1(catchl, RunningFungus[i])) {
						if (catchl.getY() < -280) {
							dy = 0;
							dx = -5;
							catchl.setBounds(catchl.getLocation().x,
									catchl.getLocation().y + dy, 88, 446);
							RunningFungus[i].setBounds(catchl.getLocation().x,
									catchl.getLocation().y + 410, 100, 140);
						} else {
							catchl.setIcon(catch2_pic);
							dy = -5;
							dx = 0;
							catchl.setBounds(catchl.getLocation().x,
									catchl.getLocation().y + dy, 88, 446);
							RunningFungus[i].setBounds(catchl.getLocation().x,
									catchl.getLocation().y + 410, 100, 140);
						}
					}
				}
				catchl.setBounds(catchl.getLocation().x, catchl.getLocation().y
						+ dy, 88, 446);
				if (catchl.getY() == -5) {// 在下方
					catchl.setIcon(catch2_pic);
					dy = -5;
				} else if (catchl.getY() < -280) {// 在上方(抓取後移動)
					dy = 0;
					dx = -5;
					catchl.setBounds(catchl.getLocation().x + dx,
							catchl.getLocation().y, 88, 446);
					if (catchl.getX() <= 0) {
						if (controll4 == 1) {
							for (int i = 0; i < 3; i++) {
								if (touched1(catchl, RunningFungus[i])) {
									RunningFungus[i].setBounds(100, 380, 88,
											131);
									sN[i]++;
								}
							}
						}
						correct();
						this.cancel();
						catchl.setIcon(catch1_pic);
						down = 0;
						controll1 = 0;
						controll2 = 0;
						controll3 = 0;
						controll4 = 0;
						openRunning = true;
						catchl.setBounds(0, -280, 88, 446);
						ctrl_catch.setIcon(ctrl_catch_pic0);
						for (int i = 0; i < 3; i++) {
							RunningFungus[i].setBounds(gg_X[i], gg_Y[i], 100,
									140);
						}
					}
				}
			}
		};
		timer.schedule(td, 10, 80);
	}

	private void timerCoin1() {
		TimerTask te = new TimerTask() {
			@Override
			public void run() {
				if (ctrl2_coinSlot == 1) {
					ctrl1_coinSlot++;
					if (ctrl1_coinSlot % 2 == 1)
						coinSlot.setIcon(coinSlot_pic1);
					if (ctrl1_coinSlot % 2 == 0)
						coinSlot.setIcon(coinSlot_pic2);
				} else {
					coinSlot.setIcon(coinSlot_pic0);
					this.cancel();
				}
			}
		};
		timer.schedule(te, 10, 100);
	}

	private void timerCoin2() {
		TimerTask tf = new TimerTask() {
			@Override
			public void run() {
				ctrl3_coinSlot++;
				if (ctrl3_coinSlot % 6 == 1)
					coinSlot.setIcon(coinSlot_pic1);
				if (ctrl3_coinSlot % 6 == 2)
					coinSlot.setIcon(coinSlot_pic2);
				if (ctrl3_coinSlot % 6 == 3)
					coinSlot.setIcon(coinSlot_pic3);
				if (ctrl3_coinSlot % 6 == 4)
					coinSlot.setIcon(coinSlot_pic4);
				if (ctrl3_coinSlot % 6 == 5)
					coinSlot.setIcon(coinSlot_pic5);
				if (ctrl3_coinSlot % 6 == 0)
					coinSlot.setIcon(coinSlot_pic0);
				if (ctrl3_coinSlot == 6) {
					ctrl3_coinSlot = 0;
					this.cancel();
					controll1 = 1;
					controll2 = 0;
				}
			}
		};
		timer.schedule(tf, 10, 100);
	}
	public boolean touched1(JLabel lbl1, JLabel lbl2) {
		return (lbl1.getY() - lbl2.getY() <= lbl2.getHeight()
				&& lbl2.getY() - lbl1.getY() <= lbl1.getHeight()
				&& lbl1.getX() - lbl2.getX() <= lbl2.getWidth()/2 && lbl2.getX()
				- lbl1.getX() <= lbl1.getWidth()/2);
	} 


	public static boolean touched(JLabel lbl1, JLabel lbl2) {
		return (lbl1.getY() - lbl2.getY() <= lbl2.getHeight()
				&& lbl2.getY() - lbl1.getY() <= lbl1.getHeight()
				&& lbl1.getX() - lbl2.getX() <= lbl2.getWidth() && lbl2.getX()
				- lbl1.getX() <= lbl1.getWidth());
	}
	public static class playSound {
		AudioClip sound;

		public void stop() {
			sound.stop();
		}
		
		public void coin(){
			System.out.print("soundcoin");
			sound = Applet.newAudioClip(this.getClass().getResource(
					"sound/coin.wav"));
			sound.play();
		}
		
		public void playChooseSound() {
			sound = Applet.newAudioClip(this.getClass().getResource(
					"sound/choose.wav"));
			sound.play();
		}
		
		public void playPutSound() {
			sound = Applet.newAudioClip(this.getClass().getResource(
					"sound/put.wav"));
			sound.play();
		}

		public void playHitSound1() {
			sound = Applet.newAudioClip(this.getClass().getResource(
					"sound/attack1.wav"));
			sound.play();
		}
		
		public void playHitSound2() {
			sound = Applet.newAudioClip(this.getClass().getResource(
					"sound/boom.wav"));
			sound.play();
		}
		
		public void playHitSound3() {
			sound = Applet.newAudioClip(this.getClass().getResource(
					"sound/bang.wav"));
			sound.play();
		}

		public void playTouch() {
			sound = Applet.newAudioClip(this.getClass().getResource(
					"sound/touch.wav"));
			sound.play();
		}

		public void playRUN() {
			sound = Applet.newAudioClip(this.getClass().getResource(
					"sound/flourish.wav"));
			sound.loop();
		}
		
		public void playBackgroundSound() {
			sound = Applet.newAudioClip(this.getClass().getResource(
					"sound/fight.wav"));
			sound.loop();
		}
	}// end class playSound
}