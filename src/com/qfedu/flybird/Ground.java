package com.qfedu.flybird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 地面类
 * 
 * @author Lenovo
 *
 */
public class Ground {
	// 成员变量
	// ground图片
	BufferedImage image;
	// 地面的x坐标
	int x;
	// 地面的y坐标
	int y;

	// 构造方法
	public Ground() {
		try {
			image = ImageIO.read(getClass().getResource("ground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		x = 0;
		y = 500;
	}

	// 成员方法
	public void step() {
		x--;
		if (x <= -100) {
			x = 0;
		}
	}
}
