package com.qfedu.flybird;

import javax.swing.JFrame;

/**
 * ����һ������Ҫ�����Ϸ����
 * 
 * @author Lenovo
 *
 */
public class StartGame {
	// ��Ա����
	public static void main(String[] args) {
		// ���ڶ���
		JFrame frame = new JFrame();
		// ���ô��ڵĴ�С
		frame.setSize(400, 670);
		// ���ô��ڵ�Ĭ��λ��,����
		frame.setLocationRelativeTo(null);
		// ���ùر���Ϸ���Զ�ֹͣ����ִ��
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ����һ��game����
		BirdGame game = new BirdGame();
		// ��������ӵ�������
		frame.add(game);

		// ʹ���ڽ�����ʾ
		frame.setVisible(true);

		game.action();

	}

}
