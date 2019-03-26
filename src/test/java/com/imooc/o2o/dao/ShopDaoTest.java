package com.imooc.o2o.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.exception.ShopOperationException;

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;

	@Test
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(2L);
		area.setAreaId(3);
		shopCategory.setShopCategoryId(2L);
		shop.setArea(area);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setShopName("mytest11");
		shop.setShopDesc("mytest11");
		shop.setShopAddr("testaddr11");
		shop.setPhone("138105245261");
		shop.setShopImg("test11");

		shop.setCreateTime(new Date());
		shop.setCreateTime(new Date());

		shop.setEnableStatus(0);
		shop.setAdvice("审核中");
		int effectdNum = shopDao.insertShop(shop);
		System.out.println("effectedNum=" + effectdNum);
	}
	
	
	@Test
	public void testUpadteShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopName("测试描述");
		shop.setShopDesc("测试地址");
		shop.setLastEditTime(new Date());
		int effectdNum = shopDao.updateShop(shop);
		System.out.println("effectedNum=" + effectdNum);
	}
	
	@Test
	public void testQueryByShopId() {
		long shop_id=31L;
		Shop shop=shopDao.queryByShopId(shop_id);
//		System.out.println(shop.getArea().getAreaName());
		System.out.println(shop.getOwner().getName());
	}
	
	
	
	@Test
	public void testQueryShopList() {
		Shop shopCondition=new Shop();
//		PersonInfo owner=new PersonInfo();
//		owner.setUserId(1L);
//		shopCondition.setOwner(owner);
	
		ShopCategory parent=new ShopCategory();
		parent.setShopCategoryId(58L);
		
		ShopCategory sc=new ShopCategory();
		sc.setParent(parent);
		
		shopCondition.setShopCategory(sc);
		
		shopCondition.setShopName("王超");

		System.out.println("店铺列表"+shopDao.queryShopList(shopCondition, 0, 5));
		//System.out.println(shopDao.queryShopCount(shopCondition));
	}

}
