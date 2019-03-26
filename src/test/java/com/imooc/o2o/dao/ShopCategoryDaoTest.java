package com.imooc.o2o.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.ShopCategoryService;

public class ShopCategoryDaoTest extends BaseTest{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
@Autowired	
private ShopCategoryService test;

	@Test
	public void testQueryShopCategory() {
		//List<ShopCategory> list=shopCategoryDao.queryShopCategory(new ShopCategory());
//		ShopCategory son=new ShopCategory();
//		ShopCategory parent=new ShopCategory();
//		parent.setShopCategoryId(1L);
//		son.setParent(parent);
//		List<ShopCategory> list=shopCategoryDao.queryShopCategory(son);
		
		List<ShopCategory> list=test.getShopCategoryList(null);
		
		System.out.println(list.size());
		
		System.out.println(list);
	}
	
	
	
	
	
}
