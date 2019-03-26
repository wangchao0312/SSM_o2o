package com.imooc.o2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exception.ShopOperationException;

public class ShopServiceTest extends BaseTest{
	
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testAddShop() throws FileNotFoundException {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(3);
		shopCategory.setShopCategoryId(1L);
		shop.setArea(area);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setShopName("王超666");
		shop.setShopDesc("王超最棒");
		shop.setShopAddr("testaddr111");
		shop.setPhone("1381052452611");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg=new File("D:\\xiao.jpg");
		InputStream is=new FileInputStream(shopImg);
		ImageHolder im=new ImageHolder(shopImg.getName(), is);
		ShopExecution se=shopService.addShop(shop, im);
		System.out.println(se.getState()+":"+se.getStateInfo()+"...."+se);
		
	}
	@Test
	public void testModifyShop()throws ShopOperationException,FileNotFoundException{
		Shop shop=new Shop();
		shop.setShopId(33L);
		shop.setShopName("啦啦啦啦啦啦啦");
		File shopImg=new File("E:\\shuiyin.jpg");
		InputStream is=new FileInputStream(shopImg);
		ImageHolder imageHolder=new ImageHolder("shuiyin.jpg", is);
		ShopExecution shopExecution=shopService.modifyShop(shop,imageHolder);
		System.out.println("新的图片地址"+shopExecution.getShop().getShopImg());
		
	}
	
	@Test
	public void testGetShopListAndCount() {
		Shop shopCondition=new Shop();
//		PersonInfo owner=new PersonInfo();
//		owner.setUserId(1L);
//		shopCondition.setOwner(owner);
		ShopCategory sc=new ShopCategory();
		sc.setShopCategoryId(3L);
		shopCondition.setShopCategory(sc);
		ShopExecution se=shopService.getShopList(shopCondition, 1, 2);
		System.out.println(se.getShopList());
		System.out.println(se.getCount());
		
	}
	
}
