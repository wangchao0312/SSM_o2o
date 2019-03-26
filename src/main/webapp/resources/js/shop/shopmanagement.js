/**
 * 
 */


$(function(){
	
	var shopId=getQueryString('shopId');

	var shopInfoUrl='/o2o/shopadmin/getshopmanagementinfo?shopId='+shopId;
	$.getJSON(shopInfoUrl,function(data){
		if(data.redirect){
			window.location.href=data.url;
		}else{
			if(data.shopId!=undefined&&data.shopId!=null){
				shopId=data.shopId;
			}
			$('#shopInfo').attr('href','/o2o/shopadmin/shopoperation?shopId='+shopId);
		}
	});
});


//取得shopId的值，为了修改店铺信息所用的备用函数 根据传过来的URL的值来获取店铺的shopId
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return decodeURIComponent(r[2]);
	}
	return '';
}