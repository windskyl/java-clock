package Algorithm;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;

public class apple extends JFrame {

	NewPanel np;

	class NewPanel extends JPanel implements Runnable {

		double endpi = Math.PI / 180;// Բ����
		double hourlen = 130;// ʱ�볤��
		double minutelen = 150;// ���볤��
		double secondlen = 230;// ���볤��

		double hourx, houry, minx, miny, secx, secy;

		int hour,minute,second;
		int yuanxinx = 290, yuanxiny = 290;// Բ������
		Calendar c;

		public void paint(Graphics g) {
			// ���õ�super.paint(g),�ø�����һЩ��ǰ�Ĺ�������ˢ����Ļ
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
				g.drawImage(icon.getImage(), initx, inity, getSize().width, getSize().height, this);// ͼƬ���Զ�����
			// g.drawImage(icon.getImage(), x, y, this);// ͼƬ�����Զ�����
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

		this.setSize(600, 600); // ��ʼ���ڵĴ�С
		this.setLocationRelativeTo(null); // ���ô��ھ���
		np = new NewPanel();
		this.getContentPane().add(np); // �������ӵ�JFrame��
		//np.paintComponent(this.getGraphics());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new apple();

	}
}