package com.imooc.o2o.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.imooc.o2o.dto.ImageHolder;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {

	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();

	public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		try {
			OutputStream os = new FileOutputStream(dest);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = thumbnail.getImage().read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();

		}

		// Thumbnails.of(thumbnailInputStream).size(200,
		// 200).outputQuality(0.25f).toFile(dest);
		catch (Exception e) {
			e.printStackTrace();
		}
		return relativeAddr;
	}

	/**
	 * 生成随机文件名 当前年月日小时分钟秒钟 +5位随机数
	 * 
	 * @return
	 */
	public static String getRandomFileName() {
		// 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
		int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
		String nowTimeStr = sDateFormat.format(new Date()); // 当前时间
		return nowTimeStr + rannum;
	}

	/**
	 * 获取输入文件流的扩展名
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	
	
	
	
	
	
	/**
	 * 创建目标路径上所涉及的路径
	 * 
	 * @param args
	 * @throws IOException
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}
	/**
	 * 判断是文件路径还是目录的路径
	 * 如果是文件路径 则删除文件
	 * 如果是目录的路径 则删除目录下所有文件
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath=new File(PathUtil.getImgBasePath()+storePath);
		if(fileOrPath.exists())
		{
			if(fileOrPath.isDirectory())
			{
				File files[]=fileOrPath.listFiles();
				for(int i=0;i<files.length;i++)
					files[i].delete();
			}
			fileOrPath.delete();
				
		}
		
	}
	
	
	
	
	
	
}
