package com.qhit.lh.gr3.cyh.exam.startExam.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.qhit.lh.gr3.cyh.exam.question.bean.TWrittenTest;

public class RandomExam {
public static List<TWrittenTest> getRandomExam(List<TWrittenTest> writtenTests,List<Integer> randNum){
	List<TWrittenTest> list=new ArrayList<TWrittenTest>();
	for(int num:randNum) {
		System.out.println(num);
		list.add(writtenTests.get(num-1));
	}
	return list;
}
}
