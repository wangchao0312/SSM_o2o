package com.imooc.o2o.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.exception.ProductCategoryOperationException;

public interface ProductCategoryService {


	
	public List<ProductCategory> queryProductCategoryList(Long shopId);
	
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList);
	
	/**
	 * 将此商品类别下的商品的类别置为空，再删除商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(@Param("productCategoryId")long productCategoryId,@Param("shopId")long shopId)
	throws ProductCategoryOperationException;

}
