package com.qfedu.flybird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {
	// 成员变量
	// 8, 0-7
	BufferedImage[] images = new BufferedImage[8];
	int x;
	int y;
	int index;
	BufferedImage image;
	// 因为小鸟有重力
	int g = 10;
	// 小鸟的初速度
	double v = 0;
	// 小鸟在垂直方向上的位移
	double s;
	// 小鸟的下落函数执行一次所需要的时间
	double t = 0.15;
	// 向上的初速度
	int upSpeed = 16;
	int width;
	int height;

	// 构造方法
	public Bird() {
		for (int i = 0; i < 8; i++) {
			try {
				images[i] = ImageIO.read(getClass().getResource(i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		image = images[0];

		x = 120;
		y = 220;

		width = image.getWidth();
		height = image.getHeight();
	}

	// 成员方法
	public void fly() {
		index++;
		image = images[index / 10 % 8];
	}

	public void down() {
		// 小鸟下落后的速度
		v = v - g * t;
		// 小鸟下落后的位移
		s = v * t - g * t * t / 2;
		// 小鸟下落后新的y值
		y = (int) (y - s);
	}

	public void up() {
		v = upSpeed;
	}

	/*
	 * 三种情况： 窗口上端 地面 管道
	 */

	public boolean hitSky() {
		if (y == 0) {
			return true;
		}

		return false;
	}

	public boolean hitGround() {
		if (y + height >= 500) {
			return true;
		}

		return false;
	}

	public boolean hitColumn(Column c) {
		// 先判断小鸟是否进入了管道
		if (x + width >= c.x && x <= c.x + c.width) {
			// 小鸟进入了缝隙还是撞击了管道
			if (y <= c.height / 2 - 72 + c.y || y + height >= c.height / 2 + c.y + 72) {
				return true;
			}
		}

		return false;
	}

}
