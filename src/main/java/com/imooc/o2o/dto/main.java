package com.imooc.o2o.dto;

import com.imooc.o2o.enums.ShopStateEnum;

public class main {
	public static void main(String[] args) {
		ShopExecution test=new ShopExecution(ShopStateEnum.CHECK);
		System.out.println(test.getStateInfo());
	}
}
