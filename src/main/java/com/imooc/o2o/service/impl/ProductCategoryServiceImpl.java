package com.imooc.o2o.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.exception.ProductCategoryOperationException;
import com.imooc.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

	@Autowired
	ProductCategoryDao productCategoryDao;
	@Autowired
	ProductDao productDao;
	
	public List<ProductCategory> queryProductCategoryList(Long shopId){
		return productCategoryDao.queryProductCategoryList(shopId);
	}
	
	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(
			List<ProductCategory> productCategoryList) throws RuntimeException {
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao
						.batchInsertProductCategory(productCategoryList);
				if (effectedNum <= 0) {
					throw new RuntimeException("店铺类别失败");
				} else {

					return new ProductCategoryExecution(
							ProductCategoryStateEnum.SUCCESS,productCategoryList);
				}

			} catch (Exception e) {
				throw new RuntimeException("batchAddProductCategory error: "
						+ e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(
					ProductCategoryStateEnum.EMPTY_LIST);
		}

	}

	@Override
	@Transactional//因为里面有俩个步骤 所以需要加事务来管理
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		// TODO 将此类别下的商品	种类置为空
		//解除tb_product里的商品与该productCategoryId的关联
		
		
		
		try {
			int effectedNum=productDao.updateProductCategoryToNull(productCategoryId);
			if(effectedNum<0){
				throw new RuntimeException("商品类别更新失败");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			throw new RuntimeException("商品类别更新失败");

		}
		//删除该productCategory
		
		try {
			int effectedNum=productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(effectedNum<=0)
			{
				throw new ProductCategoryOperationException("商品类别删除失败");
			}else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ProductCategoryOperationException("删除失败");		
			}
		
		
	}
	

	
	
	
}
