package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 新增店铺，返回成功之后 主键的值
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	
	/**
	 * 更新店铺
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
	
	
	/**
	 * 查询
	 * @param shopId
	 * @return
	 */
	Shop queryByShopId(long shopId);
	
	
	/**
	 * 分页查询店铺，可输入的条件有：店铺名（模糊），店铺状态，店铺类别，区域Id,owner；
	 * @param rowIndex从第几行开始取数据
	 * @param pageSize 返回的条数     
	 * 
	 * 多个参数 需要指定唯一的Id名字
	 */
	List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,@Param("rowIndex")int rowIndex,
			@Param("pageSize")int pageSize);
	
	/**
	 * 返回总共的个数
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
}
