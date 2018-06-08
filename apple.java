package Algorithm;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;

public class apple extends JFrame {

	NewPanel np;

	class NewPanel extends JPanel implements Runnable {

		double endpi = Math.PI / 180;// 圆周率
		double hourlen = 130;// 时针长度
		double minutelen = 150;// 分针长度
		double secondlen = 230;// 秒针长度

		double hourx, houry, minx, miny, secx, secy;

		int hour,minute,second;
		int yuanxinx = 290, yuanxiny = 290;// 圆心坐标
		Calendar c;

		public void paint(Graphics g) {
			// 调用的super.paint(g),让父类做一些事前的工作，如刷新屏幕
			super.paint(g);
			this.paintComponent(g);
			
			c = Calendar.getInstance();

			hour = c.get(Calendar.HOUR_OF_DAY);
			if (hour >= 12)
				hour -= 12;
			minute = c.get(Calendar.MINUTE);
			second = c.get(Calendar.SECOND);
			
			hourx = hourlen * Math.sin(hour * endpi * 360 / 12) + yuanxinx;
			houry = yuanxiny - hourlen * Math.cos(hour * endpi * 360 / 12);
			minx = minutelen * Math.sin(minute * endpi * 360 / 60) + yuanxinx;
			miny = yuanxiny - minutelen * Math.cos(minute * endpi * 360 / 60);
			secx = secondlen * Math.sin(second * endpi * 360 / 60) + yuanxinx;
			secy = yuanxiny - secondlen * Math.cos(second * endpi * 360 / 60);
			
			g.setColor(Color.black);
			g.drawLine(yuanxinx, yuanxiny, (int)hourx, (int)houry);
			g.setColor(Color.black);
			g.drawLine(yuanxinx, yuanxiny, (int)minx, (int)miny);
			g.setColor(Color.red);
			g.drawLine(yuanxinx, yuanxiny, (int)secx, (int)secy);
		}

		public NewPanel() {

			Thread t = new Thread(this);
			t.start();
		}

		public void paintComponent(Graphics g) {
			int initx = 0, inity = 0;
			ImageIcon icon = new ImageIcon("C:\\Users\\apple\\eclipse-workspace\\Hello\\src\\Algorithm\\clock.jpg");
			if (g != null)
				g.drawImage(icon.getImage(), initx, inity, getSize().width, getSize().height, this);// 图片会自动缩放
			// g.drawImage(icon.getImage(), x, y, this);// 图片不会自动缩放
		}

		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.repaint();
			}
		}
	}

	apple() {
		super();

		this.setSize(600, 600); // 初始窗口的大小
		this.setLocationRelativeTo(null); // 设置窗口居中
		np = new NewPanel();
		this.getContentPane().add(np); // 将面板添加到JFrame上
		//np.paintComponent(this.getGraphics());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new apple();

	}
}