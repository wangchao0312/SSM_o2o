package com.imooc.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.o2o.dao.ShopCategoryDao;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	//Service层只用来简单的转发，那么要Service层来干嘛呢？
	//能够让controller与dao解耦  缓存重要的一个环节
	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition){
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}


}
