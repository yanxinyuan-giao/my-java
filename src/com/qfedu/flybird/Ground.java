package com.qfedu.flybird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * ������
 * 
 * @author Lenovo
 *
 */
public class Ground {
	// ��Ա����
	// groundͼƬ
	BufferedImage image;
	// �����x����
	int x;
	// �����y����
	int y;

	// ���췽��
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

	// ��Ա����
	public void step() {
		x--;
		if (x <= -100) {
			x = 0;
		}
	}
}
