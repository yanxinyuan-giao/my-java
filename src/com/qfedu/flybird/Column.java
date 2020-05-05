package com.qfedu.flybird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * �ܵ����������˼·��
 * ���ȹ涨���ּ�����������������棬���봰�ڶ���100�����������棬�������100
 * �ܵ���������ɸı��Ҳֻ�ǹܵ���yֵ
 * ������ּ��������yֵ
 */

/**
 * �ܵ���
 * 
 * @author Lenovo
 *
 */
public class Column {
	// ��Ա����
	BufferedImage image;
	int x;
	int y;
	int width;
	int height;

	// ��̬������ֻ�����������ã������ı���
	static int count;

	// ���췽��
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
		 * ���ּ���Yֵ �ϲ���172-height/2 �²���328-height/2 ��С��Yֵ������YֵС206
		 */
		y = (int) (Math.random() * 156) + 172 - height / 2;

		count++;
	}

	// ��Ա����
	public void step() {
		x--;
		if (x <= -width) {
			x = 600 - width;
			y = (int) (Math.random() * 156) + 172 - height / 2;
		}
	}

}
