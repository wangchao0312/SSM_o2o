package com.imooc.o2o.util;

public class PathUtil {
	
	
	public static String getImgBasePath() {
		String basePath = "";
			basePath = "D:/projectdev/image";
	
		return basePath;
	}
	public static String getShopImagePath(long shopId) {
	
		String imagePath="/upload/item/shop/"+shopId+"/";
		
		return imagePath;
	}
}
