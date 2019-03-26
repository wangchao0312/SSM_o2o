package com.imooc.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exception.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;

@Service // spring把它当作service层管理 自动注入下面俩个参数
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	// 1.处理缩略图，获取缩略图存储路径之后赋值给product
	// 2.将product写入tb_product，并获取product_id
	// 3.结合product_id批量处理图片
	// 4.将批量图片插入数据表中
	@Override
	@Transactional // 必须通过spring事务管理 管理这四个步骤，任何一部出错，立刻回滚
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationException {
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);// 默认为上架
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品失败:" + e.toString());
			}
			if (productImgList != null && productImgList.size() > 0) {
				addProductImgs(product, productImgList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}

		// TODO Auto-generated method stub
	}

	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}

	/**
	 * 批量添加图片
	 * 
	 * @param product
	 * @param productImgs
	 */
	private void addProductImgs(Product product, List<ImageHolder> productImgs) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<>();
		for (ImageHolder productImageHolder : productImgs) {
			String imgAddr = ImageUtil.generateThumbnail(productImageHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setProductId(product.getProductId());
			productImg.setImgAddr(imgAddr);
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0)
					throw new ProductOperationException("创建商品详情图片失败");

			} catch (Exception e) {
				throw new ProductOperationException("创建商品详情图片失败");
			}

		}

	}

	@Override
	public Product getProductById(Long productId) {
		// TODO Auto-generated method stub
		return productDao.queryProductById(productId);
	}

	@Override
	@Transactional
	// 1.若缩略图参数有值，处理缩略图,若原来的缩略图有值，则删除并赋新值
	// 2.详情图同理
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationException {
		// TODO Auto-generated method stub

		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setLastEditTime(new Date());

			if (thumbnail != null) {
				Product tempProduct = productDao.queryProductById(product.getProductId());
				if (tempProduct.getImgAddr() != null)
					ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());

				addThumbnail(product, thumbnail);
			}
			if (productImgList != null && productImgList.size() > 0) {
				deleteProductImgList(product.getProductId());
				addProductImgs(product, productImgList);
			}
			try {
				int effectedNum = productDao.updateProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("更新信息失败");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			} catch (Exception e) {
				throw new ProductOperationException("更新信息失败");
			}

		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}

	}

	public void deleteProductImgList(Long productId) {
		List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
		for (ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		productImgDao.deleteProductImgByProductId(productId);
	}

	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		
		

			int rowIndex = PageCalculator.calculatorRowIndex(pageIndex, pageSize);
			List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
			int count = productDao.queryProductCount(productCondition);
			ProductExecution pe = new ProductExecution();
			pe.setProductList(productList);
			pe.setCount(count);
			return pe;
		
		
	}
	
	
	
	
	

}
