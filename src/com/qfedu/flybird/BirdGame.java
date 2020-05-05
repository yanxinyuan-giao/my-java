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
 * 游戏类，用来向窗口中添加元素
 * 
 * @author Lenovo
 *
 */
public class BirdGame extends JPanel {
	// 成员变量
	// 蓝天白云背景图片
	BufferedImage bg;
	// get Ready背景图片
	BufferedImage start;
	// 游戏结束图片
	BufferedImage over;
	// 地面对象
	Ground ground;
	// 管道对象
	Column[] columns = new Column[2];

	// 小鸟对象
	Bird bird;
	// 状态标识字
	// 0表示游戏开始界面，1表示游戏运行界面，2表示游戏结束界面
	int state;

	// 成绩变量
	int score;

	// 构造方法
	public BirdGame() {
		// 通过IO流的方式获取到图片
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

		// 代表默认的游戏状态是游戏开始界面
		state = 0;
	}

	// 成员方法
	// paint方法是系统内置函数，系统会自动执行，用户不能手动调用
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		// 将图片画到窗口中去
		g.drawImage(bg, 0, 0, null);
		switch (state) {
		// 游戏开始界面
		case 0:
			g.drawImage(start, 0, 0, null);
			g.drawImage(ground.image, ground.x, ground.y, null);
			g.drawImage(bird.image, bird.x, bird.y, null);
			break;
		// 游戏运行界面
		case 1:
			g.drawImage(columns[0].image, columns[0].x, columns[0].y, null);
			g.drawImage(columns[1].image, columns[1].x, columns[1].y, null);
			g.drawImage(ground.image, ground.x, ground.y, null);
			g.drawImage(bird.image, bird.x, bird.y, null);
			break;
		// 游戏结束界面
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
		// 添加鼠标监听
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

			// 本质上是再自动执行一次paint方法
			repaint();

			try {
				// 延时器
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * MyMouseListener自定义的鼠标监听器，能够监听鼠标的一系列动作，比如点击、按下、移动 MouseAdapter系统内置类，是鼠标适配器
	 * 
	 * @author Lenovo
	 *
	 */
	class MyMouseListener extends MouseAdapter {

		/**
		 * 当鼠标按下的时候，mousePressed方法被自动执行
		 * 例如，在游戏开始界面点击鼠标，mousePressed方法执行，根据switch选择结构，执行case 0中的语句，将state值变为1，进入游戏运行状态
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
