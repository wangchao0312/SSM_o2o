/**
 * 
 */

$(function() {
	var shopId=getQueryString('shopId');
	var isEdit=shopId?true:false;//判断从common.js中获取的shopId，如果为空就为注册，不为空就是修改
	var initUrl = '/o2o/shopadmin/getshopinitinfo';
	var registerShopUrl = '/o2o/shopadmin/registershop';
	var shopInfoUrl='/o2o/shopadmin/getshopbyid?shopId='+shopId;
	
	var editShopUrl='/o2o/shopadmin/modifyshop';
	  if(!isEdit)//判断是注册还是修改
		  {
			getShopInitInfo();
		  }
	  else
		  {
		  getShopInfo(shopId);
		  }
	
	//根据传递过来的 shopId的值 来进行查询，访问"/o2o/shopadmin/getshopbyid?shopId="+shopId;
	//
	function getShopInfo(shopId) {
		$.getJSON(shopInfoUrl, function(data) {
			if (data.success) {
				var shop = data.shop;
				$('#shop-name').val(shop.shopName);//给html页面上的元素赋值
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
				//
				var shopCategory = '<option data-id="'
						+ shop.shopCategory.shopCategoryId + '" selected>'
						+ shop.shopCategory.shopCategoryName + '</option>';
				var tempAreaHtml = '';
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(shopCategory);//将shopCAtegory传递给Html页面上的shopcategory
				$('#shop-category').attr('disabled','disabled');//不能让用户选择
				$('#area').html(tempAreaHtml);//同理
				$('#area').attr('data-id',shop.areaId);
			}
		});
	}
	
	function getShopInitInfo() {
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item, index) {
					tempHtml += '<option data-id="' + item.shopCategoryId
							+ '">' + item.shopCategoryName + '</option>';
				});

				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});

				$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});

		$('#submit').click(
				function() {
					var shop = {};
					if(isEdit)//如果是编辑店铺信息，就需要传递shopId过去
						shop.shopId=shopId;
					shop.shopName = $('#shop-name').val();
					shop.shopAddr = $('#shop-addr').val();
					shop.phone = $('#shop-phone').val();
					shop.shopDesc = $('#shop-desc').val();

					shop.shopCategory = {
						shopCategoryId : $('#shop-category').find('option')
								.not(function() {
									return !this.selected;
								}).data('id')
					};
					shop.area = {
						areaId : $('#area').find('option').not(function() {
							return !this.selected;
						}).data('id')
					};

					var shopImg = $("#shop-img")[0].files[0];
					var formData = new FormData();
					formData.append('shopImg', shopImg);
					formData.append('shopStr', JSON.stringify(shop));
					
					var verifyCodeActual = $('#j_captcha').val();
					if (!verifyCodeActual) {
						$.toast('请输入验证码！');
						return;
					}
					formData.append('verifyCodeActual',verifyCodeActual);
					$.ajax({
						url : (isEdit?editShopUrl:registerShopUrl),//判断转到哪个界面？
						type : 'POST',
						// contentType: "application/x-www-form-urlencoded;
						// charset=utf-8",
						data : formData,
						contentType : false,
						processData : false,
						cache : false,
						success : function(data) {
							if (data.success) {
								$.toast('提交成功！');
							} else {
								$.toast('提交失败！' + data.errMsg);
							}
							//$('#captcha_img').click();
						}

					});

				});
	}
})