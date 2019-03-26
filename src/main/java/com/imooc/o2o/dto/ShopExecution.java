package com.imooc.o2o.dto;

import java.util.List;

import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;

public class ShopExecution {
	private int state;//结果状态
	private String stateInfo;//状态标识
	private int count;//店铺数量
	private Shop shop;//增删改的时候需要用到
	private List<Shop> shopList;//shop列表 查询店铺列表的时候使用
	
	
	public ShopExecution() {}
	
	//店铺操作失败的时候的构造器,只返回对象，不返回Shop对象
	 public ShopExecution(ShopStateEnum stateEnum) {
		 this.state=stateEnum.getState();
		 this.stateInfo=stateEnum.getStateInfo();
	 }
	 //店铺操作成功的时候使用的构造器，返回Shop对象
	 public ShopExecution(ShopStateEnum stateEnum,Shop shop) {
		 this.state=stateEnum.getState();
		 this.stateInfo=stateEnum.getStateInfo();
		 this.shop=shop;
	 }
	 //操作成功的时候的构造器，返回Shop列表
	 public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList) {
		 this.state=stateEnum.getState();
		 this.stateInfo=stateEnum.getStateInfo();
		 this.shopList=shopList;
	 }

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	 
}
