package com.qhit.lh.gr3.cyh.exam.common.utils;

import java.util.ArrayList;
import java.util.List;

public class RandomNumber {

	// 根据用户需求的随机数数量,使用最小值1,最大值某一数据集合的size,获得1~size区间内的用户需求数量的随机数//加油!!!
	public static List<Integer> getRandomNum(int max, int minNum, int maxNum) {
		// TODO Auto-generated method stub
		// 获得最多的循环次数maxNum,以便循环添加
		if (max==0) {
			return null;
		}
		int maxcs = max;
		// 随机数集合
		List<Integer> data = new ArrayList<Integer>();
		// 初始最大值为max,最小值为1
		for (int i = 0; i < maxcs; i++) {
			// 拿到第一个随机数
			int result = minNum + (int) (Math.random() * ((maxNum - minNum) + 1));
			// 如果data集合为空的话
			if (data == null) {
				// 把第一个值添加进去
				data.add(result);
				// 如果不为空,循环检查
			} else {
				// 先循环检查
				for (int num : data) {
					// 如果data集合里已经保存过相同的值
					if (result == num) {
						// 把result设置为0,结束当前循环,不用再用为0的值接着循环判断了
						result = 0;
						break;
					}
				}
				// 走到这一步证明已经检查完了,如果外部result值变成0了,则证明data数据集合里有相同的值了,则不能添加重复的,要将自增函数增加一,再次开始循环
				if (result == 0) {
					maxcs++;
				} else {
					// 如果result还是原值,则证明data数据集合里没有相同的值,可以往data数据集合里添加数据了
					data.add(result);
				}
			}

		}
		return data;
	}

}
