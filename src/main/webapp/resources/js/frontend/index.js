$(function() {
	//定义访问后台，获取头条列表以及一级列表的URL 
    var url = '/o2o/frontend/listmainpageinfo';

    $.getJSON(url, function (data) {
        if (data.success) {
        	//获取的从后台传过来的头条列表
            var headLineList = data.headLineList;
            var swiperHtml = '';
            headLineList.map(function (item, index) {
                swiperHtml += ''
                            + '<div class="swiper-slide img-wrap">'
                            +      '<img class="banner-img" src="'+ item.lineImg +'" alt="'+ item.lineName +'">'
                            + '</div>';
            });
            $('.swiper-wrapper').html(swiperHtml);//将轮播图组赋值给前端界面
            $(".swiper-container").swiper({
                autoplay: 3000,//自动轮换时间为3秒
                autoplayDisableOnInteraction: false//用户对轮播图进行操作时，是否关闭自动轮换
            });
            var shopCategoryList = data.shopCategoryList;
            var categoryHtml = '';
            shopCategoryList.map(function (item, index) {
                categoryHtml += ''
                             +  '<div class="col-50 shop-classify" data-category='+ item.shopCategoryId +'>'//链接里的参数
                             +      '<div class="word">'
                             +          '<p class="shop-title">'+ item.shopCategoryName +'</p>'//前端展示
                             +          '<p class="shop-desc">'+ item.shopCategoryDesc +'</p>'//前端展示
                             +      '</div>'
                             +      '<div class="shop-classify-img-warp">'
                             +          '<img class="shop-img" src="'+ item.shopCategoryImg +'">'//前端展示
                             +      '</div>'
                             +  '</div>';
            });
            $('.row').html(categoryHtml);
        }
    });
//打开侧边栏
    $('#me').click(function () {
        $.openPanel('#panel-left-demo');
    });

    $('.row').on('click', '.shop-classify', function (e) {//点击类别按钮 shop-classify  响应事件   电机div的class为 shop-classify 即执行该函数
        var shopCategoryId = e.currentTarget.dataset.category;//data-category对应的参数值
        var newUrl = '/o2o/frontend/shoplist?parentId=' + shopCategoryId;
        window.location.href = newUrl;
    });

});
