$(function() {
	$('[data-sidenav]').sidenav();
	function setCurrentSlide(ele, index) {
		$(".swiper1 .swiper-slide").removeClass("selected");
		ele.addClass("selected");
	}
	var swiper1 = new Swiper('.swiper1', {
		slidesPerView: 5.5,
		paginationClickable: true, //此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
		spaceBetween: 10, //slide之间的距离（单位px）。
		freeMode: true, //默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
		loop: false, //是否可循环
		onTab: {
			slideChangeTransitionEnd: function(){
				alert(this.activeIndex);//切换结束时，告诉我现在是第几个slide
			}
		}
	});

	swiper1.slides.each(function(index, val) {
		var ele = $(this);
		ele.on("click", function() {
			setCurrentSlide(ele, index);
			swiper2.slideTo(index, 500, false);
			var myVideo = document.getElementsByTagName('video');
			for (var i=0;i<=myVideo.length;i++){
				if (myVideo[i]!==undefined){
					if (myVideo[i].play){
						myVideo[i].pause();
						myVideo[i].currentTime = 0;
					}
				}
			}
		});
	});

	var swiper2 = new Swiper('.swiper2', {
		//freeModeSticky  设置为true 滑动会自动贴合  
		direction: 'horizontal', //Slides的滑动方向，可设置水平(horizontal)或垂直(vertical)。
		loop: false,
		//					effect : 'fade',//淡入
		//effect : 'cube',//方块
		effect: 'coverflow', //3D流
		// effect : 'flip',//3D翻转
		autoHeight: true, //自动高度。设置为true时，wrapper和container会随着当前slide的高度而发生变化。
		coverflowEffect: {
			rotate: 60,
			stretch: 10,
			depth: 60,
			modifier: 5,
			slideShadows : true
		},
		onSlideChangeEnd: function(swiper) { //回调函数，swiper从一个slide过渡到另一个slide结束时执行。
			var n = swiper.activeIndex;
			setCurrentSlide($(".swiper1 .swiper-slide").eq(n), n);
			swiper1.slideTo(n, 500, false);
		}
	});
});
$(function() {
	//创建MeScroll对象
	init();
});
function common(ele,type){
	$.ajax({
		url: '/trends/trendsAllOther',
		data:{"type":type},
		beforeSend :function(xmlHttp){
			xmlHttp.setRequestHeader("If-Modified-Since","0");
			xmlHttp.setRequestHeader("Cache-Control","no-cache");
		},
		success:function (re) {
			var listDom = document.getElementById(ele);
			for (var i = 0; i < re.length; i++) {
				var newObj = re[i];
				var str = '';
				if (newObj.trends_img===""&&newObj.trends_video!==""){
					str += '<div class="card text-white bg-info">' +
						'<div class="card-header">' + newObj.trends_head +
						'</div>' +
						'<video class="videoIndex" controls  src="'+newObj.trends_video+'"' +
						'style="width: auto;max-width:100%;height: 400px;z-index: 99"></video>' +
						'<div class="card-body">' +
						'<h5 class="card-title">' +
						newObj.trends_head+
						'</h5>' +
						'<samp class="card-text">' +
						newObj.trends_info +
						'</samp>' +
						'<p class="card-text">' +
						'<small class="text-muted">' +
						newObj.trends_outtime +
						' &nbsp;&nbsp;&nbsp;<i class="fa fa-user text-danger"></i>' +
						'</small>' +
						newObj.wall_user.yb_usernick +
						'<small>' +
						'</small></p>' +
						'<p class="card_text">' +
						'<small>点赞数:<span id="'+newObj.trends_id+'1">' +
						newObj.trends_praise_number+
						'</span>&nbsp;&nbsp;&nbsp;<a id="'+newObj.trends_id+'2" href="javascript:void(0);" onclick="num('+newObj.wall_user.id+','+newObj.trends_id+','+newObj.trends_id+'1,'+newObj.trends_id+'2)" style="display: inline-block"><i class="fa fa-thumbs-o-up fa-2x"></i></a>' +
						'</small>' +
						'</p><a href="/trends/list_card.html/?id='+newObj.trends_id+'&user_id='+newObj.wall_user.id+'" class="btn btn-primary">查看详情</a>' +
						'</div>' +
						'</div>';
				}
				else if (newObj.trends_img!==""&&newObj.trends_video===""){
					str += '<div class="card text-white bg-info">' +
						'<div class="card-header">' + newObj.trends_head +
						'</div>' +
						'<img style="width: 50%;" class="card-img-top img-thumbnail rounded mx-auto d-block" ' +
						'src="'+newObj.trends_img+'" alt="Card image cap">' +
						'<div class="card-body">' +
						'<h5 class="card-title">' +
						newObj.trends_head+
						'</h5>' +
						'<samp class="card-text">' +
						newObj.trends_info +
						'</samp>' +
						'<p class="card-text">' +
						'<small class="text-muted">' +
						newObj.trends_outtime +
						' &nbsp;&nbsp;&nbsp;<i class="fa fa-user text-danger"></i>' +
						'</small>' +
						newObj.wall_user.yb_usernick +
						'<small>' +
						'</small></p>' +
						'<p class="card_text">' +
						'<small>点赞数:<span id="'+newObj.trends_id+'1">' +
						newObj.trends_praise_number+
						'</span>&nbsp;&nbsp;&nbsp;<a id="'+newObj.trends_id+'2" href="javascript:void(0);" onclick="num('+newObj.wall_user.id+','+newObj.trends_id+','+newObj.trends_id+'1,'+newObj.trends_id+'2)" style="display: inline-block"><i class=" fa fa-thumbs-o-up fa-2x"></i></a>' +
						'</small>' +
						'</p><a href="/trends/list_card.html/?id='+newObj.trends_id+'&user_id='+newObj.wall_user.id+'" class="btn btn-primary">查看详情</a>' +
						'</div>' +
						'</div>';
					}
					else {
					str += '<div class="card text-white bg-info">' +
						'<div class="card-header">' + newObj.trends_head +
						'</div>' +
						'<div class="card-body">' +
						'<h5 class="card-title">' +
						newObj.trends_head+
						'</h5>' +
						'<samp class="card-text">' +
						newObj.trends_info +
						'</samp>' +
						'<p class="card-text">' +
						'<small class="text-muted">' +
						newObj.trends_outtime +
						' &nbsp;&nbsp;&nbsp;<i class="fa fa-user text-danger"></i>' +
						'</small>' +
						newObj.wall_user.yb_usernick +
						'<small>' +
						'</small></p>' +
						'<p class="card_text">' +
						'<small>点赞数:<span id="'+newObj.trends_id+'1">' +
						newObj.trends_praise_number+
						'</span>&nbsp;&nbsp;&nbsp;<a id="'+newObj.trends_id+'2" href="javascript:void(0);" onclick="num('+newObj.wall_user.id+','+newObj.trends_id+','+newObj.trends_id+'1,'+newObj.trends_id+'2)" style="display: inline-block"><i class=" fa fa-thumbs-o-up fa-2x"></i></a>' +
						'</small>' +
						'</p><a href="/trends/list_card.html/?id='+newObj.trends_id+'&user_id='+newObj.wall_user.id+'" class="btn btn-primary">查看详情</a>' +
						'</div>' +
						'</div>';
					}
					var liDom = document.createElement('li');
					liDom.setAttribute('class', 'list-group-item');
					liDom.setAttribute("id",''+newObj.trends_id+'');
					liDom.innerHTML = str;
					listDom.append(liDom); //加在列表的前面,下拉刷新
			}
		}
	})
}
function init() {
	common("list-tr",1);
	common("list-ex",2);
	common("list-pe",3);
	common("list-fo",4);
	common("list-st",5);
	common("list-ad",6);
	common("list-ot",7);
}

function num(user_id,trends_id,id,i_id) {
	var nu = document.getElementById(id);
	var i = document.getElementById(i_id);
	var url = "/trends/updateById/"+trends_id;
	$.ajax({
		url:url,
		data:{"num":nu.innerText,"user_id":user_id},
		success:function (date) {
			if (date.status===1){
				if (date.num!==nu.innerText){
					i.style.color = "#0099CC";
					i.style.fontSize = "20px";
					nu.innerText = date.num;
				}
				i.style.color = "#00FF23";
				i.style.fontSize = "10px";
			}
		}
	});
}

