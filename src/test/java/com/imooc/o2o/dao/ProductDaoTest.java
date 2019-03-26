package com.imooc.o2o.dao;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest{
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Test
	public void testAInsertProduct()   throws Exception {
		Shop shop1 = new Shop();
		shop1.setShopId(33L);
		Shop shop2 = new Shop();
		shop2.setShopId(34L);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(2L);
		ProductCategory pc2 = new ProductCategory();
		pc2.setProductCategoryId(3L);
		ProductCategory pc3 = new ProductCategory();
		pc3.setProductCategoryId(4L);
		Product product1 = new Product();
		product1.setProductName("测试1");
		product1.setProductDesc("测试Desc1");
		product1.setImgAddr("test1");
		product1.setPriority(0);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(pc1);
		Product product2 = new Product();
		product2.setProductName("测试2");
		product2.setProductDesc("测试Desc2");
		product2.setImgAddr("test2");
		product2.setPriority(0);
		product2.setEnableStatus(0);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setShop(shop1);
		product2.setProductCategory(pc2);
		Product product3 = new Product();
		product3.setProductName("测试3");
		product3.setProductDesc("测试Desc3");
		product3.setImgAddr("test3");
		product3.setPriority(0);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setShop(shop2);
		product3.setProductCategory(pc3);
		System.out.println("#####################");
		 System.out.println("返回的值"+productDao.insertProduct(product1)); 
		 System.out.println(product1.getProductId());
			System.out.println("#####################");
			System.out.println("#####################");

		System.out.println("返回的值"+productDao.insertProduct(product2));
		 System.out.println(product2.getProductId());

		System.out.println("#####################");
		System.out.println("#####################");

	 System.out.println("返回的值"+productDao.insertProduct(product3));
	 System.out.println(product3.getProductId());

		System.out.println("#####################");

	}
	
	@Test
	public void testQueryProductByProductId() throws Exception{
		Product product=productDao.queryProductById(22L);
		System.out.println(product.getProductImgList().size());
		productImgDao.deleteProductImgByProductId(22L);
		System.out.println(product.getProductImgList().size());

		
	}
	
	@Test
	public void testDUpdataProduct() {

			Product product = new Product();
			product.setProductId(18L);
			Shop shop=new Shop();
			shop.setShopId(34L);
			product.setShop(shop);
			product.setProductName("第18个产品");
			int effectedNum = productDao.updateProduct(product);
			
	}
	
	@Test
	public void testQueryProductList() {
		Product product = new Product();
		List<Product> productList = productDao.queryProductList(product, 0, 3);
		System.out.println(productList);
		
		//assertEquals(3, productList.size());
		int count = productDao.queryProductCount(product);
		System.out.println("count="+count);
		//assertEquals(4, count);
		product.setProductName("茶");
		productList = productDao.queryProductList(product, 0, 3);
		System.out.println(productList);
		//assertEquals(3, productList.size());
		//count = productDao.queryProductCount(product);
		//assertEquals(3, count);
		Shop shop = new Shop();
		shop.setShopId(31L);
		product.setShop(shop);
		productList = productDao.queryProductList(product, 0, 3);
		//assertEquals(1, productList.size());
//		count = productDao.queryProductCount(product);
		//assertEquals(1, count);
		System.out.println(productList);
	}
	
	@Test
	public void testUpdateProductCategoryIdToNull() {
		int effected=productDao.updateProductCategoryToNull(11L);
		System.out.println(effected);
	}
	
}
