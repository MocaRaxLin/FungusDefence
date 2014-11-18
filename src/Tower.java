import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class Tower implements Runnable {
	JLabel label_bullet[] = new JLabel[3];// 砲彈Label
	private boolean control = true;
	private int tower_locationX;// 位置X
	private int tower_locationY;// 位置Y
	private int type;// 種類變數
	private int direction;// 方向變數
	private int attack;
	
	// 定義攻擊 
	public static final int NORMAL_ATTACK = 10;
	public static final int TEEMO_ATTACK = 50;
	public static final int ROCK_ATTACK = 300;
	// 定義種類
	public static final int NORMAL = 0;
	public static final int TEEMO = 1;
	public static final int ROCK = 2;
	// 定義方向 
	public static final int DERECTION_RIGHT = 1;
	public static final int DERECTION_LEFT = 2;
	public static final int DERECTION_UP = 3;
	public static final int DERECTION_DOWN = 4;

	public Tower(int x, int y, int type, int direction) {

		tower_locationX = x;
		tower_locationY = y;
		this.type = type;
		this.direction = direction;
		if (type == NORMAL) {
			for (int i = 0; i < 3; i++) {
				label_bullet[i] = new JLabel();
				label_bullet[i].setIcon(new ImageIcon(getClass().getResource(
						"img/bullet.png")));
				label_bullet[i].setBounds(tower_locationX + 10,
						tower_locationY + 20, 20, 20);
			}
			this.attack = NORMAL_ATTACK;
		} else if (type == TEEMO) {
			for (int i = 0; i < 3; i++) {
				label_bullet[i] = new JLabel();
				label_bullet[i].setIcon(new ImageIcon(getClass().getResource(
						"img/bullet2.png")));
				label_bullet[i].setBounds(tower_locationX + 10,
						tower_locationY + 20, 20, 20);
			}
			this.attack = TEEMO_ATTACK;
		} else if (type == ROCK) {
			label_bullet[0] = new JLabel();
			label_bullet[1] = new JLabel();
			label_bullet[2] = new JLabel();
			label_bullet[0].setIcon(new ImageIcon(getClass().getResource(
					"img/bullet3.png")));
			label_bullet[0].setBounds(0, 0, 20, 20);
			this.attack = ROCK_ATTACK;
		}
	}

	public void run() {
		try {
			for (;;) {
				Thread.sleep(40);
				if (type == NORMAL) {

					if (direction == DERECTION_DOWN) {
						if (label_bullet[1].getLocation().y <= tower_locationY + 100) {
							label_bullet[1].setLocation(
									label_bullet[1].getLocation().x,
									label_bullet[1].getLocation().y + 1);
							label_bullet[0].setLocation(
									label_bullet[0].getLocation().x - 1,
									label_bullet[0].getLocation().y + 1);
							label_bullet[2].setLocation(
									label_bullet[2].getLocation().x + 1,
									label_bullet[2].getLocation().y + 1);
						} else {
							label_bullet[0].setLocation(tower_locationX + 10,
									tower_locationY + 20);
							label_bullet[1].setLocation(tower_locationX + 10,
									tower_locationY + 20);
							label_bullet[2].setLocation(tower_locationX + 10,
									tower_locationY + 20);
						}

					} else if (direction == DERECTION_UP) {
						if (label_bullet[1].getLocation().y >= tower_locationY - 30) {
							label_bullet[1].setLocation(
									label_bullet[1].getLocation().x,
									label_bullet[1].getLocation().y - 1);
							label_bullet[0].setLocation(
									label_bullet[0].getLocation().x - 1,
									label_bullet[0].getLocation().y - 1);
							label_bullet[2].setLocation(
									label_bullet[2].getLocation().x + 1,
									label_bullet[2].getLocation().y - 1);
						} else {
							label_bullet[0].setLocation(tower_locationX + 10,
									tower_locationY + 20);
							label_bullet[1].setLocation(tower_locationX + 10,
									tower_locationY + 20);
							label_bullet[2].setLocation(tower_locationX + 10,
									tower_locationY + 20);
						}

					} else if (direction == DERECTION_RIGHT) {
						if (label_bullet[1].getLocation().x <= tower_locationX + 90||label_bullet[2].getLocation().x <= tower_locationX + 90||label_bullet[0].getLocation().x <= tower_locationX + 90) {
							label_bullet[1].setLocation(
									label_bullet[1].getLocation().x + 1,
									label_bullet[1].getLocation().y);
							label_bullet[0].setLocation(
									label_bullet[0].getLocation().x + 1,
									label_bullet[0].getLocation().y + 1);
							label_bullet[2].setLocation(
									label_bullet[2].getLocation().x + 1,
									label_bullet[2].getLocation().y - 1);
						} else {
							label_bullet[0].setLocation(tower_locationX + 10,
									tower_locationY + 20);
							label_bullet[1].setLocation(tower_locationX + 10,
									tower_locationY + 20);
							label_bullet[2].setLocation(tower_locationX + 10,
									tower_locationY + 20);
						}
 
					} else if (direction == DERECTION_LEFT) {
						if (label_bullet[1].getLocation().x >= tower_locationX - 80||label_bullet[0].getLocation().x >= tower_locationX - 80||label_bullet[2].getLocation().x >= tower_locationX - 80) {
							label_bullet[1].setLocation(
									label_bullet[1].getLocation().x - 1,
									label_bullet[1].getLocation().y);
							label_bullet[0].setLocation(
									label_bullet[0].getLocation().x - 1,
									label_bullet[0].getLocation().y + 1);
							label_bullet[2].setLocation(
									label_bullet[2].getLocation().x - 1,
									label_bullet[2].getLocation().y - 1);
						} else {
							label_bullet[0].setLocation(tower_locationX + 10,
									tower_locationY + 20);
							label_bullet[1].setLocation(tower_locationX + 10,
									tower_locationY + 20);
							label_bullet[2].setLocation(tower_locationX + 10,
									tower_locationY + 20);
						}

					}
				} else if (type == TEEMO) {
					if (direction == DERECTION_DOWN) {
						if (control) {
							Thread t1 = new Thread() {
								public void run() {
									try {
										for (;;) {
											Thread.sleep(5000);
											label_bullet[0].setLocation(
													tower_locationX - 10,
													tower_locationY + 100);
											Thread.sleep(5000);
											label_bullet[1].setLocation(
													tower_locationX + 10,
													tower_locationY + 100);
											Thread.sleep(5000);
											label_bullet[2].setLocation(
													tower_locationX + 30,
													tower_locationY + 100);
										}

									} catch (Exception e) {

									}
								}
							};
							t1.start();

							control = false;
						}

					} else if (direction == DERECTION_UP) {
						if (control) {
							Thread t2 = new Thread() {
								public void run() {
									try {
										for (;;) {
											Thread.sleep(5000);
											label_bullet[0].setLocation(
													tower_locationX,
													tower_locationY - 10);
											Thread.sleep(5000);
											label_bullet[1].setLocation(
													tower_locationX + 20,
													tower_locationY - 10);
											Thread.sleep(5000);
											label_bullet[2].setLocation(
													tower_locationX + 40,
													tower_locationY - 10);
										}

									} catch (Exception e) {

									}
								}
							};
							t2.start();
							control = false;
						}

					} else if (direction == DERECTION_RIGHT) {
						if (control) {
							Thread t3 = new Thread() {
								public void run() {
									try {
										for (;;) {
											Thread.sleep(5000);
											label_bullet[0].setLocation(
													tower_locationX + 80,
													tower_locationY);
											Thread.sleep(5000);
											label_bullet[1].setLocation(
													tower_locationX + 80,
													tower_locationY + 20);
											Thread.sleep(5000);
											label_bullet[2].setLocation(
													tower_locationX + 80,
													tower_locationY + 40);
										}

									} catch (Exception e) {

									}
								}
							};
							t3.start();

							control = false;
						}
					} else if (direction == DERECTION_LEFT) {
						if (control) {
							Thread t4 = new Thread() {
								public void run() {
									try {
										for (;;) {
											Thread.sleep(5000);
											label_bullet[0].setLocation(
													tower_locationX - 60,
													tower_locationY);
											Thread.sleep(5000);
											label_bullet[1].setLocation(
													tower_locationX - 60,
													tower_locationY + 20);
											Thread.sleep(5000);
											label_bullet[2].setLocation(
													tower_locationX - 60,
													tower_locationY + 40);
										}

									} catch (Exception e) {

									}
								}
							};
							t4.start();
							control = false;
						}
					}

				} else if (type == ROCK) {
					if (direction == DERECTION_DOWN) {
						
						if (label_bullet[0].getLocation().x >= 115) {
							label_bullet[0].setLocation(
									label_bullet[0].getLocation().x - 8,
									label_bullet[0].getLocation().y);
						} else {
							label_bullet[0].setVisible(false);
							Thread.sleep(5000);
							label_bullet[0].setLocation(450,
									tower_locationY + 100);
							label_bullet[0].setVisible(true);
						}

					} else if (direction == DERECTION_UP) {

						if (label_bullet[0].getLocation().x >= 115) {
							label_bullet[0].setLocation(
									label_bullet[0].getLocation().x - 8,
									label_bullet[0].getLocation().y);
						} else {
							label_bullet[0].setVisible(false);
							Thread.sleep(5000);
							label_bullet[0].setLocation(450,
									tower_locationY - 10);
							label_bullet[0].setVisible(true);
						}

					} else if (direction == DERECTION_RIGHT) {
						if (label_bullet[0].getLocation().y >= 230) {
							label_bullet[0].setLocation(
									label_bullet[0].getLocation().x,
									label_bullet[0].getLocation().y - 8);
						} else {
							label_bullet[0].setVisible(false);
							Thread.sleep(5000);
							label_bullet[0].setLocation(tower_locationX + 80,
									450);
							label_bullet[0].setVisible(true);
						}
					} else if (direction == DERECTION_LEFT) {
						if (label_bullet[0].getLocation().y >= 90) {
							label_bullet[0].setLocation(
									label_bullet[0].getLocation().x,
									label_bullet[0].getLocation().y - 8);
						} else {
							label_bullet[0].setVisible(false);
							Thread.sleep(5000);
							label_bullet[0].setLocation(tower_locationX - 60,
									270);
							label_bullet[0].setVisible(true);
						}
					}
				}
				try {
					for (int j = 0; j < 10; j++) {
						if (Gui.touched(label_bullet[0], Gui.lb_monster1[j])) {
							Gui.lb_monster1[j].setHP(Gui.lb_monster1[j].getHP()
									- attack);
							if (Gui.lb_monster1[j].getHP() > 0) {
								Gui.lb_monster1[j].setVisible(false);
								Thread.sleep(30);
								Gui.lb_monster1[j].setVisible(true);
								Thread.sleep(30);
								Gui.lb_monster1[j].setVisible(false);
								Thread.sleep(30);
								Gui.lb_monster1[j].setVisible(true);
							} else {
							}
							if (type == ROCK) {
								// label_bullet[0].setLocation(0,0);
							} else {
								System.out.println("fuck u"
										+ label_bullet[0].getLocation().x + " "
										+ label_bullet[0].getLocation().y);
								label_bullet[0].setLocation(
										tower_locationX + 10,
										tower_locationY + 30);
								System.out.println("fuck u"
										+ label_bullet[0].getLocation().x + " "
										+ label_bullet[0].getLocation().y);
							}
							System.out.println(Gui.lb_monster1[j].getHP());
						}
					}
				} catch (Exception e) {
				}
				try {
					for (int j = 0; j < 10; j++) {
						if (Gui.touched(label_bullet[1], Gui.lb_monster1[j])) {
							Gui.lb_monster1[j].setHP(Gui.lb_monster1[j].getHP()
									- attack);
							if (Gui.lb_monster1[j].getHP() > 0) {
								Gui.lb_monster1[j].setVisible(false);
								Thread.sleep(30);
								Gui.lb_monster1[j].setVisible(true);
								Thread.sleep(30);
								Gui.lb_monster1[j].setVisible(false);
								Thread.sleep(30);
								Gui.lb_monster1[j].setVisible(true);
							} else {
							}
							if (type == ROCK) {
								// label_bullet[0].setLocation(0,0);
							} else {
								label_bullet[1].setLocation(
										tower_locationX + 10,
										tower_locationY + 30);
							}
							System.out.println(Gui.lb_monster1[j].getHP());
						}
					}
				} catch (Exception e) {
				}
				try {
					for (int j = 0; j < 10; j++) {
						if (Gui.touched(label_bullet[2], Gui.lb_monster1[j])) {
							Gui.lb_monster1[j].setHP(Gui.lb_monster1[j].getHP()
									- attack);
							if (Gui.lb_monster1[j].getHP() > 0) {
								Gui.lb_monster1[j].setVisible(false);
								Thread.sleep(30);
								Gui.lb_monster1[j].setVisible(true);
								Thread.sleep(30);
								Gui.lb_monster1[j].setVisible(false);
								Thread.sleep(30);
								Gui.lb_monster1[j].setVisible(true);
							} else {
							}
							if (type == ROCK) {
								// label_bullet[0].setLocation(0,0);
							} else {
								label_bullet[2].setLocation(
										tower_locationX + 10,
										tower_locationY + 30);
							}
							System.out.println(Gui.lb_monster1[j].getHP());
						}
					}
				} catch (Exception e) {
				}
			}
		} catch (InterruptedException e) {
		}
	}
}
