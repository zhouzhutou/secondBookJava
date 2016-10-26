/**
 * 
 */
$(document).ready(function(){
	var slider=$(".automatic-slider").unslider(
			{
				animation: 'horizontal',
				autoplay: true,
				arrows: false
			});
	//设置轮播图像的位置
	imagePos();
	//左滑动图片
	var indexSmall;
	var length=$('#unslider-wrap li').length;
	//console.log(length);
	$("#unslider-wrap").delegate('li','swipeLeft',function(){
		indexSmall=$(this).index();
		if(indexSmall+1==length)
		{
			slider.unslider('animate:first').unslider('setIndex:0');
		}else{
			slider.unslider('next').unslider('setIndex:'+(indexSmall+1));
		}
	});
	//右滑动图片
	$("#unslider-wrap").delegate('li','swipeRight',function(){
		indexSmall=$(this).index();
		if(indexSmall-1<0)
		{
			slider.unslider('animate:last').unslider('setIndex:'+(length-1));
		}else{
			slider.unslider('prev').unslider('setIndex:'+(indexSmall-1));
		}
	});
	var index;//定义全局index
	//点击放大图片
	$("#unslider-wrap").delegate('li','tap',function(){
		var win=$(window);
		$(".imageContainer").css({
			'height':win.height()+'px',
			'width':win.width()+'px',
			'background-color':'black',
			'position':'absolute',
			'top':'0',
			'left':'0'
		}).show();
		index=$(this).index();
		loadBigImage($(this));
		//点击缩小图片
		$(".imageContainer").bind('tap',function(){
			$(this).hide();
		});
	});
	//定义变量
	var canvaObj=$("#canvasImg");
	var canvaDom=$("#canvasImg")[0];
   //向左滑动大图
	$(".imageContainer").bind('swipeLeft',function(){
		index++;
		if(index>length-1)
		{
			index=0;
		}
		var li=$("#unslider-wrap").children('li').eq(index);
		loadBigImage(li,function(){
			canvaDom.addEventListener("webkitAnimationEnd",function(){
				canvaObj.removeClass("animated bounceInRight");
				canvaDom.removeEventListener("webkitAnimationEnd",true);
			},false);
			canvaObj.addClass("animated bounceInRight");
		});
	});
	//向右滑动图片
	$(".imageContainer").bind('swipeRight',function(){
		index--;
		if(index<0)
		{
			index=length-1;
		}
		var li=$("#unslider-wrap").children('li').eq(index);
		loadBigImage(li,function(){
			canvaDom.addEventListener("webkitAnimationEnd",function(){
				canvaObj.removeClass("animated bounceInLeft");
				canvaDom.removeEventListener("webkitAnimationEnd",true);
			},false);
			canvaObj.addClass("animated bounceInLeft");
		});
	});
});

var imagePos=function(){
	var win=$(window);
	var imgRealHeight=parseInt(win.height()*0.45);
	var imgRealWidth=imgRealHeight*0.8;
	var marginLR=(win.width()-imgRealWidth)/2;
	$(".unslider-wrap li").each(function(index,element){
		var e=$(this).find($("img"));
		e.css({
			'height':imgRealHeight, 'width':imgRealWidth, 'margin-left':marginLR, 'margin-right':marginLR
		});
	});
}
//加载大图片
var loadBigImage=function(e,callback){
	var imgSrc=e.find("img").attr("src");
	var imgObj=new Image();
	imgObj.src=imgSrc;
	imgObj.onload=function()
	{
		var c=document.getElementById("canvasImg");
		var ctx=c.getContext('2d');
		ctx.drawImage(imgObj,0,0,c.width,c.height);
	}
	callback&&callback();
}
