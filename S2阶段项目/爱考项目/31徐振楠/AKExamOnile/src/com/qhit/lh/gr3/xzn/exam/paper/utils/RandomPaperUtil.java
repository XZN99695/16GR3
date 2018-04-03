package com.qhit.lh.gr3.cyh.exam.paper.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.qhit.lh.gr3.cyh.exam.common.utils.RandomNumber;
import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;

public class RandomPaperUtil {
	//根据用户需求随机数量,最大值每个特定数据集合的size,最小值1拿到随机数集合
public static Set<TWrittenTest> getNum(
		List<TWrittenTest> dan1list,
		List<TWrittenTest> dan2list,
		List<TWrittenTest> dan3list,
		List<TWrittenTest> duo1list,
		List<TWrittenTest> duo2list,
		List<TWrittenTest> duo3list,
		int maxdan1,int maxdan2,int maxdan3,int maxduo1,int maxduo2,int maxduo3
		) {
	//拿到随机数集合
	List<Integer> dan1indexList=RandomNumber.getRandomNum(maxdan1, 1, dan1list.size());
	List<Integer> dan2indexList=RandomNumber.getRandomNum(maxdan2, 1, dan2list.size());
	List<Integer> dan3indexList=RandomNumber.getRandomNum(maxdan3, 1, dan3list.size());
	List<Integer> duo1indexList=RandomNumber.getRandomNum(maxduo1, 1, duo1list.size());
	List<Integer> duo2indexList=RandomNumber.getRandomNum(maxduo2, 1, duo2list.size());
	List<Integer> duo3indexList=RandomNumber.getRandomNum(maxduo3, 1, duo3list.size());
	//用随机数集合从数据集合里取出随机的试题
	Set<TWrittenTest> randompaper=new HashSet<TWrittenTest>();
	//放进总一张试卷里
	getQuestionByNum(dan1list, dan1indexList,randompaper);
	getQuestionByNum(dan2list, dan2indexList,randompaper);
	getQuestionByNum(dan3list, dan3indexList,randompaper);
	getQuestionByNum(duo1list, duo1indexList,randompaper);
	getQuestionByNum(duo2list, duo2indexList,randompaper);
	getQuestionByNum(duo3list, duo3indexList,randompaper);
	
	return randompaper;
}
//用随机数集合从数据集合里取出随机的试题,放进总一张试卷里
public static void getQuestionByNum(List<TWrittenTest> list,List<Integer> randnumlist,Set<TWrittenTest> randompaper){
	if (randnumlist==null) {
		return;
	}
	for (int i: randnumlist) {
		System.out.println(i);
		TWrittenTest w=list.get(i-1);
		System.out.println(w.toString());
		System.out.println("randompaper.size():"+randompaper.size());
		randompaper.add(w);
	}
}
}
