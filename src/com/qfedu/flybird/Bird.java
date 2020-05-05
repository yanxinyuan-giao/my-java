package com.qfedu.flybird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {
	// ��Ա����
	// 8, 0-7
	BufferedImage[] images = new BufferedImage[8];
	int x;
	int y;
	int index;
	BufferedImage image;
	// ��ΪС��������
	int g = 10;
	// С��ĳ��ٶ�
	double v = 0;
	// С���ڴ�ֱ�����ϵ�λ��
	double s;
	// С������亯��ִ��һ������Ҫ��ʱ��
	double t = 0.15;
	// ���ϵĳ��ٶ�
	int upSpeed = 16;
	int width;
	int height;

	// ���췽��
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

	// ��Ա����
	public void fly() {
		index++;
		image = images[index / 10 % 8];
	}

	public void down() {
		// С���������ٶ�
		v = v - g * t;
		// С��������λ��
		s = v * t - g * t * t / 2;
		// С��������µ�yֵ
		y = (int) (y - s);
	}

	public void up() {
		v = upSpeed;
	}

	/*
	 * ��������� �����϶� ���� �ܵ�
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
		// ���ж�С���Ƿ�����˹ܵ�
		if (x + width >= c.x && x <= c.x + c.width) {
			// С������˷�϶����ײ���˹ܵ�
			if (y <= c.height / 2 - 72 + c.y || y + height >= c.height / 2 + c.y + 72) {
				return true;
			}
		}

		return false;
	}

}
