package com.imooc.o2o.web.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.AreaService;
import com.imooc.o2o.service.ShopCategoryService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class ShopListController {

	
	@Autowired
	ShopCategoryService shopCategoryService;
	@Autowired
	AreaService areaService;
	@Autowired
	ShopService shopService;
	
	/**
	 * 返回商品列表页面里的  ShopCategory列表 （二级（从首页的类别选项点击进来）或者一级（从首页的全部商品点击进来）），以及区域信息列表
	 */
	@RequestMapping(value = "/listshopspageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShopsPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		long parentId = HttpServletRequestUtil.getLong(request, "parentId");
		List<ShopCategory> shopCategoryList = null;
		if (parentId != -1) {
			try {//如果parentId存在 咋获取该一级shopCategory下的二级shopCategory
				ShopCategory shopCategoryCondition =new ShopCategory();
				ShopCategory parent=new ShopCategory();
				parent.setShopCategoryId(parentId);
				shopCategoryCondition.setParent(parent);		
				shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		} else {
			try {//如果parentId不存在  则取出所有一级shopCategory
				shopCategoryList = shopCategoryService.getShopCategoryList(null);
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		}
		modelMap.put("shopCategoryList", shopCategoryList);
		
		List<Area> areaList = null;
		try {//获取区域列表信息
			areaList = areaService.getAreaList();
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
			return modelMap;
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}
	
	/**
	 * 获取指定条件下的店铺列表
	 */
	@RequestMapping(value = "/listshops", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShops(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		if ((pageIndex > -1) && (pageSize > -1)) {
			//获取一级类别Id
			long parentId = HttpServletRequestUtil.getLong(request, "parentId");			
			long shopCategoryId = HttpServletRequestUtil.getLong(request,
					"shopCategoryId");//获取二级类别Id
			int areaId = HttpServletRequestUtil.getInt(request, "areaId");
			String shopName = HttpServletRequestUtil.getString(request,
					"shopName");
			Shop shopCondition = compactShopCondition4Search(parentId,
					shopCategoryId, areaId, shopName);
			ShopExecution se = shopService.getShopList(shopCondition,
					pageIndex, pageSize);
			modelMap.put("shopList", se.getShopList());
			modelMap.put("count", se.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex");
		}

		return modelMap;
	}
	
	private Shop compactShopCondition4Search(long parentId,
			long shopCategoryId, int areaId, String shopName) {
		Shop shopCondition = new Shop();
		if (parentId != -1L) {
			//如果parentId存在 则获取该一级shopCategory下的二级shopCategory的所有店铺列表
			ShopCategory parentCategory = new ShopCategory();
			parentCategory.setShopCategoryId(parentId);
			ShopCategory child=new ShopCategory();
			child.setParent(parentCategory);
			shopCondition.setShopCategory(child);
		}
		if (shopCategoryId != -1L) {
			//查询某个二级 下面的店铺列表
			ShopCategory shopCategory = new ShopCategory();
			shopCategory.setShopCategoryId(shopCategoryId);
			shopCondition.setShopCategory(shopCategory);
		}
		if (areaId != -1L) {
			Area area = new Area();
			area.setAreaId(areaId);
			shopCondition.setArea(area);
		}

		if (shopName != null) {
			shopCondition.setShopName(shopName);
		}
		shopCondition.setEnableStatus(1);//必须是为1的  才能在前端显示出来
		return shopCondition;
	}
	
}
