<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
	  <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <title>萌宠商城</title>


	  <link href="/Shopping/css/bootstrap.min.css" rel="stylesheet">
	  <link href="/Shopping/css/style.css" rel="stylesheet">
	  <script src="/Shopping/js/jquery.min.js" type="text/javascript"></script>
	  <script src="/Shopping/js/bootstrap.min.js" type="text/javascript"></script>
	  <script src="/Shopping/js/layer.js" type="text/javascript"></script>
	  <script src="/Shopping/js/html5shiv.min.js" type="text/javascript"></script>
	  <script src="/Shopping/js/respond.min.js" type="text/javascript"></script>
	  <link id="layuicss-skinlayercss" href="http://localhost:8080//Shopping/js/skin/default/layer.css?v=3.0.11110" rel="stylesheet">

  </head>
  <body>
    <!--导航栏部分-->
	<jsp:include page="include/header.jsp"/>
	<!-- 中间内容 -->
	<div class="container-fluid">
		<div class="row">
			<!-- 控制栏 -->
			<div class="col-sm-3 col-md-2 sidebar sidebar-1">
				<ul class="nav nav-sidebar">
					<li class="list-group-item-diy"><a href="#productArea1">一、宠物 </a></li>
					<li class="list-group-item-diy"><a href="#productArea2">1、猫咪</a></li>
					<li class="list-group-item-diy"><a href="#productArea3">2、狗狗</a></li>
					<li class="list-group-item-diy"><a href="#productArea4">3、兔子</a></li>
					<li class="list-group-item-diy"><a href="#productArea5">4、其他宠物</a></li>
					<li class="list-group-item-diy"><a href="#productArea6">二、宠物用品</a></li>
					<li class="list-group-item-diy"><a href="#productArea7">1、生活用品</a></li>
					<li class="list-group-item-diy"><a href="#productArea8">2、食物</a></li>
					<li class="list-group-item-diy"><a href="#productArea9">3、玩具</a></li>
				</ul>
			</div>
			<!-- 控制内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="jumbotron">
					<h1>欢迎来到萌宠商城</h1>
					<p>balabalabala</p>
				</div>

				<div name="productArea1" class="row pd-10" id="productArea1"></div>
				<div name="productArea2" class="row" id="productArea2"></div>
				<div name="productArea3" class="row" id="productArea3"></div>
                <div name="productArea4" class="row" id="productArea4"></div>
				<div name="productArea5" class="row" id="productArea5"></div>
				<div name="productArea6" class="row" id="productArea6"></div>
				<div name="productArea7" class="row" id="productArea7"></div>
				<div name="productArea8" class="row" id="productArea8"></div>
				<div name="productArea9" class="row" id="productArea9"></div>

			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2">
				<jsp:include page="include/foot.jsp"/>
			</div>
		</div>
	</div>

  <script type="text/javascript">

	  var loading = layer.load(0);

      var productType = new Array;
      productType[1] = "一、宠物猫咪";
      productType[2] = "1、猫咪";
      productType[3] = "2、狗狗";
      productType[4] = "3、兔子";
      productType[5] = "4、其他宠物";
      productType[6] = "二、宠物用品";
      productType[7] = "1、生活用品";
      productType[8] = "2、食物";
      productType[9] = "3、玩具";
      /* 这个和div都可以动态添加 */

	  listProducts();

	  function listProducts() {
          if("${currentUser.id}" == null || "${currentUser.id}" == undefined || "${currentUser.id}" ==""){
              var allProduct = getAllProducts();
              var allPet = getAllPets();
          }/* else if (" ${currentUser.id}"!=null&&"${currentUser.id}" !=""){
              var allProduct = getAllProductsandRecommand();
          } */
          var mark = new Array;
          mark[1] = 0;
          mark[2] = 0;
          mark[3] = 0;
          mark[4] = 0;
          mark[5] = 0;
          mark[6] = 0;
          mark[7] = 0;
          mark[8] = 0;
          mark[9] = 0;
          for(var i=0;i<allProduct.length;i++){
              if(!(allProduct[i].type == null || allProduct[i].type == undefined || allProduct[i].type =="")) {
            	  var html = "";
    			  var imgURL = "/Shopping/img/"+allProduct[i].id+".jpg";
    			  html += '<div class="col-sm-4 col-md-4" >'+
    					  '<div class="boxes pointer" onclick="productDetail('+allProduct[i].id+')">'+
    					  '<div class="big bigimg">'+
    					  '<img src="'+imgURL+'">'+
    					  '</div>'+
    					  '<p class="product-name">'+allProduct[i].name+'</p>'+
    					  '<p class="product-price">¥'+allProduct[i].price+'</p>'+
    					  '</div>'+
    					  '</div>';
    			  /* 上面这个，到时候可以写个iframe或者是component */
                  var id = "productArea"+allProduct[i].type;
                  var productArea = document.getElementById(id);
                  if(mark[allProduct[i].type] == 0){
                      html ='<hr/><h1>'+productType[allProduct[i].type]+'</h1><hr/>'+html;
                      mark[allProduct[i].type] = 1;
                  }
                  productArea.innerHTML += html;
                  }
		  }
          for(var i=0;i<allPet.length;i++){
              if(!(allPet[i].type == null || allPet[i].type == undefined || allPet[i].type =="")) {
            	  var html = "";
    			  var imgURL = "/Shopping/img/"+allPet[i].id+".jpg";
    			  html += '<div class="col-sm-4 col-md-4" >'+
    					  '<div class="boxes pointer" onclick="productDetail('+allPet[i].id+')">'+
    					  '<div class="big bigimg">'+
    					  '<img src="'+imgURL+'">'+
    					  '</div>'+
    					  '<p class="product-name">'+allPet[i].name+'</p>'+
    					  '<p class="product-price">¥'+allPet[i].price+'</p>'+
    					  '</div>'+
    					  '</div>';
    			  /* 上面这个，到时候可以写个iframe或者是component */
                  var id = "productArea"+allPet[i].type;
                  var petArea = document.getElementById(id);
                  if(mark[allPet[i].type] == 0){
                      html ='<hr/><h1>'+productType[allPet[i].type]+'</h1><hr/>'+html;
                      mark[allProduct[i].type] = 1;
                  }
                  petArea.innerHTML += html;
                  }
		  }
		  layer.close(loading);
	  }
	  function getAllProducts() {
		  var allProducts = null;
		  var nothing = {};
		  $.ajax({
			  async : false, //设置同步
			  type : 'POST',
              url : '/Shopping/getAllProducts',
			  data : nothing,
			  dataType : 'json',
			  success : function(result) {
				  if (result!=null) {
					  allProducts = result.allProducts;
				  }
				  else{
					  layer.alert('查询错误');
				  }
			  },
			  error : function(resoult) {
				  layer.alert('查询错误');
			  }
		  });
		  //划重点划重点，这里的eval方法不同于prase方法，外面加括号
		  allProducts = eval("("+allProducts+")");
		  return allProducts;
	  }
	  function getAllPets() {
		  var allPets = null;
		  var nothing = {};
		  $.ajax({
			  async : false, //设置同步
			  type : 'POST',
              url : '/Shopping/getAllPets',
			  data : nothing,
			  dataType : 'json',
			  success : function(result) {
				  if (result!=null) {
					  allPets = result.allPets;
				  }
				  else{
					  layer.alert('查询错误');
				  }
			  },
			  error : function(resoult) {
				  layer.alert('查询错误');
			  }
		  });
		  //划重点划重点，这里的eval方法不同于prase方法，外面加括号
		  allPes = eval("("+allPets+")");
		  return allPets;
		  
		  //空的时候会返回数值。加一个转换吧
	  }

	  function productDetail(id) {
		  var product = {};
		  var jumpResult = '';
		  product.id = id;
		  $.ajax({
			  async : false, //设置同步
			  type : 'POST',
			  url : '/Shopping/productDetail',
			  data : product,
			  dataType : 'json',
			  success : function(result) {
				  jumpResult = result.result;
			  },
			  error : function(resoult) {
				  layer.alert('查询错误');
			  }
		  });

		  if(jumpResult == "success"){
			  window.location.href = "/Shopping/product_detail";
		  }
	  }
	  function petDetail(id) {
		  alert("petdetail");
		  var pet = {};
		  var jumpResult = '';
		  product.id = id;
		  $.ajax({
			  async : false, //设置同步
			  type : 'POST',
			  url : '/Shopping/petDetail',
			  data : pet,
			  dataType : 'json',
			  success : function(result) {
				  jumpResult = result.result;
			  },
			  error : function(resoult) {
				  layer.alert('查询错误');
			  }
		  });

		  if(jumpResult == "success"){
			  window.location.href = "/Shopping/pet_detail";
		  }
	  }
	  
	 /*  function getAllProductsandRecommand() {
          var allProducts = null;
          var nothing = {};
          var user = {};
          user.userId = "${currentUser.id}";
          $.ajax({
              async : false, //设置同步
              type : 'POST',
              url : '/Shopping/getAllProductsandRecomand',
              data : user,
              dataType : 'json',
              success : function(result) {
                  if (result!=null) {
                      allProducts = result.allProducts;
                  }
                  else{
                      layer.alert('查询错误');
                  }
              },
              error : function(resoult) {
                  layer.alert('查询错误');
              }
          });
          //划重点划重点，这里的eval方法不同于prase方法，外面加括号
          allProducts = eval("("+allProducts+")");
          return allProducts;
      } */

  </script>


	<div class="layui-layer-move"></div>


  </body>
</html>