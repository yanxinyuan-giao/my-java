package com.qfedu.flybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * ��Ϸ�࣬�����򴰿������Ԫ��
 * 
 * @author Lenovo
 *
 */
public class BirdGame extends JPanel {
	// ��Ա����
	// ������Ʊ���ͼƬ
	BufferedImage bg;
	// get Ready����ͼƬ
	BufferedImage start;
	// ��Ϸ����ͼƬ
	BufferedImage over;
	// �������
	Ground ground;
	// �ܵ�����
	Column[] columns = new Column[2];

	// С�����
	Bird bird;
	// ״̬��ʶ��
	// 0��ʾ��Ϸ��ʼ���棬1��ʾ��Ϸ���н��棬2��ʾ��Ϸ��������
	int state;

	// �ɼ�����
	int score;

	// ���췽��
	public BirdGame() {
		// ͨ��IO���ķ�ʽ��ȡ��ͼƬ
		try {
			bg = ImageIO.read(getClass().getResource("bg.png"));
			start = ImageIO.read(getClass().getResource("start.png"));
			over = ImageIO.read(getClass().getResource("gameover.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ground = new Ground();
		bird = new Bird();
		columns[0] = new Column();
		columns[1] = new Column();

		// ����Ĭ�ϵ���Ϸ״̬����Ϸ��ʼ����
		state = 0;
	}

	// ��Ա����
	// paint������ϵͳ���ú�����ϵͳ���Զ�ִ�У��û������ֶ�����
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		// ��ͼƬ����������ȥ
		g.drawImage(bg, 0, 0, null);
		switch (state) {
		// ��Ϸ��ʼ����
		case 0:
			g.drawImage(start, 0, 0, null);
			g.drawImage(ground.image, ground.x, ground.y, null);
			g.drawImage(bird.image, bird.x, bird.y, null);
			break;
		// ��Ϸ���н���
		case 1:
			g.drawImage(columns[0].image, columns[0].x, columns[0].y, null);
			g.drawImage(columns[1].image, columns[1].x, columns[1].y, null);
			g.drawImage(ground.image, ground.x, ground.y, null);
			g.drawImage(bird.image, bird.x, bird.y, null);
			break;
		// ��Ϸ��������
		case 2:
			g.drawImage(over, 0, 0, null);
			break;

		default:
			break;
		}

		Font font = new Font(Font.SERIF, Font.BOLD, 40);

		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(score + "", 40, 60);
	}

	public void action() {
		// ���������
		this.addMouseListener(new MyMouseListener());

		while (true) {
			switch (state) {
			case 0:
				ground.step();
				bird.fly();

				break;
			case 1:
				ground.step();
				bird.fly();
				bird.down();
				if (bird.hitSky() || bird.hitGround() || bird.hitColumn(columns[0]) || bird.hitColumn(columns[1])) {
					state = 2;
				} else {
					columns[0].step();
					columns[1].step();

					if (bird.x == columns[0].x + columns[0].width) {
						score++;
					}

					if (bird.x == columns[1].x + columns[1].width) {
						score++;
					}
				}

				break;
			case 2:
				break;

			default:
				break;
			}

			// �����������Զ�ִ��һ��paint����
			repaint();

			try {
				// ��ʱ��
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * MyMouseListener�Զ���������������ܹ���������һϵ�ж����������������¡��ƶ� MouseAdapterϵͳ�����࣬�����������
	 * 
	 * @author Lenovo
	 *
	 */
	class MyMouseListener extends MouseAdapter {

		/**
		 * ����갴�µ�ʱ��mousePressed�������Զ�ִ��
		 * ���磬����Ϸ��ʼ��������꣬mousePressed����ִ�У�����switchѡ��ṹ��ִ��case 0�е���䣬��stateֵ��Ϊ1��������Ϸ����״̬
		 */

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mousePressed(e);

			switch (state) {
			case 0:
				state = 1;
				break;

			case 1:
				bird.up();
				break;

			case 2:
				state = 0;
				score = 0;
				bird = new Bird();
				columns[0] = new Column();
				columns[1] = new Column();
				break;

			default:
				break;
			}
		}
	}

}
