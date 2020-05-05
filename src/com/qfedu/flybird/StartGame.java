package com.qfedu.flybird;

import javax.swing.JFrame;

/**
 * 创建一个符合要求的游戏窗口
 * 
 * @author Lenovo
 *
 */
public class StartGame {
	// 成员方法
	public static void main(String[] args) {
		// 窗口对象
		JFrame frame = new JFrame();
		// 设置窗口的大小
		frame.setSize(400, 670);
		// 设置窗口的默认位置,居中
		frame.setLocationRelativeTo(null);
		// 设置关闭游戏，自动停止程序执行
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 创建一个game对象
		BirdGame game = new BirdGame();
		// 将对象添加到窗口中
		frame.add(game);

		// 使窗口进行显示
		frame.setVisible(true);

		game.action();

	}

}
