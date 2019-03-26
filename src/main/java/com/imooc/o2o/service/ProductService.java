package com.imooc.o2o.service;

import java.io.InputStream;
import java.util.List;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exception.ProductOperationException;

public interface ProductService {
/**
 * 添加商品信息及图片处理,缩略图和商品详情
 */
	ProductExecution addProduct(Product product,ImageHolder thumbnail,List<ImageHolder> productImgList) 
					throws ProductOperationException;
	
	
	Product getProductById(Long productId);
	
	
	ProductExecution modifyProduct(Product product,ImageHolder thumbnail,List<ImageHolder> productImgList) 
			throws ProductOperationException;
	
	
	ProductExecution getProductList(Product productCondition,int pageIndex,int pageSize);
}
