package com.imooc.o2o.util;

//计算并返回起始的排数
public class PageCalculator {
	public static int calculatorRowIndex(int pageIndex,int pageSize) {
		return (pageIndex>0)?(pageIndex-1)*pageSize:0;
	}
}
