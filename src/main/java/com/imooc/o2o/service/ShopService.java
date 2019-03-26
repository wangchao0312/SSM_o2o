package com.imooc.o2o.service;

import java.io.File;
import java.io.InputStream;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exception.ShopOperationException;

public interface ShopService {
	
	ShopExecution addShop(Shop shop,ImageHolder thumbnail)throws ShopOperationException;
	
	Shop getByShopId(Long shopId);
	
	ShopExecution modifyShop(Shop shop,ImageHolder thumbnail) throws ShopOperationException;
	
	
	ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
}
