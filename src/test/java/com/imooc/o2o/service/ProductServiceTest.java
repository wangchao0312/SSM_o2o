package com.imooc.o2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ProductStateEnum;

public class ProductServiceTest extends BaseTest{

	@Autowired
	private ProductService productService;
	
	@Test
	public void testAddProduct() throws FileNotFoundException {
		Product product=new Product();
		Shop shop=new Shop();
		shop.setShopId(33L);
		ProductCategory productCategory=new ProductCategory();
		productCategory.setProductCategoryId(11L);
		product.setShop(shop);
		product.setProductCategory(productCategory);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		product.setPriority(20);
		product.setProductName("测试商品1");
		product.setProductDesc("描述啦啦啦");
		
		File thumbnail=new File("C:\\Users\\王家笑笑\\Desktop\\1.jpg");
		
		File productImg1=new File("C:\\Users\\王家笑笑\\Desktop\\2.jpg");
		
		File productImg2=new File("C:\\Users\\王家笑笑\\Desktop\\3.jpg");
		
		InputStream is=new FileInputStream(thumbnail);
		InputStream is1=new FileInputStream(productImg1);
		InputStream is2=new FileInputStream(productImg2);
		
		List<ImageHolder> productImgList=new ArrayList<>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		
		ImageHolder ig=new ImageHolder(thumbnail.getName(), is);
		ProductExecution px=productService.addProduct(product, ig, productImgList);
		System.out.println(px.getStateInfo());
		
	}
	
	
	
	@Test 
	public void testModifyProduct() throws FileNotFoundException {
		
		Product product=new Product();
		Shop shop=new Shop();
		shop.setShopId(33L);
		ProductCategory productCategory=new ProductCategory();
		productCategory.setProductCategoryId(11L);
		product.setShop(shop);
		product.setProductId(22L);
		product.setProductCategory(productCategory);
		product.setLastEditTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		product.setPriority(888);
		product.setProductName("测试商品1666");
		product.setProductDesc("描述啦啦啦666");
		
		File thumbnail=new File("C:\\Users\\王家笑笑\\Desktop\\9.jpg");
		
		File productImg1=new File("C:\\Users\\王家笑笑\\Desktop\\10.jpg");
		
		File productImg2=new File("C:\\Users\\王家笑笑\\Desktop\\15.jpg");
		
		InputStream is=new FileInputStream(thumbnail);
		InputStream is1=new FileInputStream(productImg1);
		InputStream is2=new FileInputStream(productImg2);
		
		List<ImageHolder> productImgList=new ArrayList<>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		
		ImageHolder ig=new ImageHolder(thumbnail.getName(), is);
		ProductExecution px=productService.modifyProduct(product, ig, productImgList);
		System.out.println(px.getStateInfo());
	}
	
}
