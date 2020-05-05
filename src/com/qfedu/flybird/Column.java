package com.qfedu.flybird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * 管道口随机生成思路：
 * 首先规定两种极限情况：口在最上面，距离窗口顶端100；口在最下面，距离地面100
 * 管道口随机生成改变的也只是管道的y值
 * 求出两种极限情况的y值
 */

/**
 * 管道类
 * 
 * @author Lenovo
 *
 */
public class Column {
	// 成员变量
	BufferedImage image;
	int x;
	int y;
	int width;
	int height;

	// 静态变量，只能用类名调用，公共的变量
	static int count;

	// 构造方法
	public Column() {
		if (count >= 2) {
			count = 0;
		}

		try {
			image = ImageIO.read(getClass().getResource("column.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		width = image.getWidth();
		height = image.getHeight();

		x = 500 + count * 300;
		/*
		 * 两种极限Y值 上部：172-height/2 下部：328-height/2 最小的Y值比最大的Y值小206
		 */
		y = (int) (Math.random() * 156) + 172 - height / 2;

		count++;
	}

	// 成员方法
	public void step() {
		x--;
		if (x <= -width) {
			x = 600 - width;
			y = (int) (Math.random() * 156) + 172 - height / 2;
		}
	}

}
