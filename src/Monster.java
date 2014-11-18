import java.util.ResourceBundle.Control;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Monster extends JLabel implements Runnable {
	private int hp;
	private int speed;
	private int startDelay;
	public Monster(int hp, int speed,int startDelay) {
		super();
		
		this.hp = hp;
		this.speed = speed;
		this.startDelay=startDelay;
	}
	public void setHP(int hp) {
		this.hp=hp;
	}
	public int getHP() {
		return hp;
	}
	public int getSpeed() {
		return speed;
	}
	public void  run() {
		setVisible(false);
		try {
			Thread.sleep(startDelay);
			setVisible(true);
			for (;;) {
				if (hp > 0) {
					Thread.sleep(30);
					if (getLocation().x < 435 && getLocation().y <= 100) {
						setLocation(getLocation().x + speed, getLocation().y);
					} else if (getLocation().x >= 435 && getLocation().y < 250) {
						setLocation(getLocation().x, getLocation().y + speed);
					} else if (getLocation().x >= 150 && getLocation().y < 280
							&& getLocation().y >= 250) {
						setLocation(getLocation().x - speed, getLocation().y);
					} else if (getLocation().x < 150 && getLocation().y < 420) {
						setLocation(getLocation().x, getLocation().y + speed);
					} else if (getLocation().x < 455 && getLocation().y >= 420) {
						setLocation(getLocation().x + speed, getLocation().y);
					} else if (getLocation().x >= 455 && getLocation().y >= 420) {
						
						setLocation(-100,-100);
						setVisible(false);
						Gui.home_hp-=20;
						if(Gui.home_hp<=0){
							JOptionPane.showMessageDialog(null, "你輸了，跟阿Q一樣廢!");
							System.exit(0);
						}
						Gui.label_hp.setText("<html>目前血量<br><html>"+Gui.home_hp);
						Gui.killed++;
						break;
						// setLocation(125 , 88);
					}
				} else {
					setVisible(false);
					setLocation(-100,-100);
					Gui.killed++;
					break;
				}
			}
			if(Gui.killed==3){
				Gui.control=true;
			}else if(Gui.killed==8){
				Gui.control=true;
			}else if(Gui.killed==15){
				Gui.control=true;
			}else if(Gui.killed==24){
				Gui.control=true;
			}else if(Gui.killed==34){
				Gui.control=true;
			}
		} catch (InterruptedException e) {

			
		}
		
	}

}
