package com.imooc.o2o.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
/**
 * 通过shop_id 查询店铺商品类别
 */
	List<ProductCategory> queryProductCategoryList(Long shopId);
	
	/**
	 * 批量新增商品类别
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategroyList) ;
	
	
	/**
	 * 删除指定商品类别数据
	 * 
	 */
	int deleteProductCategory(@Param("productCategoryId")long productCategoryId,@Param("shopId")long shopId);
	
}
