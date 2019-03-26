package com.imooc.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.util.ImageUtil;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按照名字来定义执行顺序
public class ProductImgDaoTest extends BaseTest{
	
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test//此为testA
	public void testABatchInsertProductImg() throws Exception {
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("图片1");
		productImg1.setImgDesc("测试图片1");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(3L);
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("图片2");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(4L);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		System.out.println("###############################");
		System.out.println("effectedNum="+effectedNum);
		System.out.println("###############################");

	}
	

	
	
	@Test
	public void testCDeleteProductImgByProductId() throws Exception{
		long productId=3L;
		int effectedNum=productImgDao.deleteProductImgByProductId(productId);
		System.out.println(effectedNum);
		
	}
	
	
	@Test
	public void testqueryProductImgList() throws Exception{
		List<ProductImg> productImgList=productImgDao.queryProductImgList(4L);

		System.out.println(productImgList.size());
	}
	
}
