package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.beust.jcommander.Parameter;
import com.imooc.o2o.entity.Product;

public interface ProductDao {
	
	/**
	 * 插入商品
	 */
	int insertProduct(Product product);
	
	/**
	 * 通过productId查询唯一商品信息
	 */
	Product queryProductById(long productId);
	
	/**
	 * 查询商品列表   查询条件为商品名（模糊搜索）、商品状态、店铺Id，商品类别      组合查询
	 */
	List<Product> queryProductList(@Param("productCondition")Product productCondition,
			@Param("rowIndex")int rowindex,@Param("pageSize")int pageSize);

	/**
	 * 查询对应条件的商品总数
	 */
	int queryProductCount(@Param("productCondition")Product productCondition);
	
	/**
	 * 更新商品信息
	 * 
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);
	
	
	/**
	 * 删除商品类别之前 将此商品类别下的所有商品productCategoryId置为空
	 */
	
	int updateProductCategoryToNull(long productCategoryId);
}
