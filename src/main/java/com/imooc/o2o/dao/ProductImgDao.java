package com.imooc.o2o.dao;

import java.util.List;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;

public interface ProductImgDao {

	
	/**
	 * 批量添加商品详情图片
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);
	
	
	/**
	 *更新商品信息 
	 */
	int updataProduct(Product product);
	
	
	/**
	 * 删除指定productId下所有的详情图片
	 */
	int deleteProductImgByProductId(long  productId);
	
	
	
	List<ProductImg> queryProductImgList(long productId);
	
}
