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
    <title>基于在线推荐购物系统</title>
      <%--<link href="/Shopping/css/bootstrap.min.css" rel="stylesheet">
      <link href="/Shopping/css/style.css" rel="stylesheet">

      <script src="/Shopping/js/jquery.min.js" type="text/javascript"></script>
      <script src="/Shopping/js/ajaxfileupload.js" type="text/javascript"></script>
      <script src="/Shopping/js/bootstrap.min.js" type="text/javascript"></script>
      <script src="/Shopping/js/layer.js" type="text/javascript"></script>

    <!--[if lt IE 9]>
      <script src="/Shopping/js/html5shiv.min.js"></script>
      <script src="/Shopping/js/respond.min.js"></script>
    <![endif]-->--%>


      <link href="/Shopping/css/bootstrap.min.css" rel="stylesheet">
      <link href="/Shopping/css/style.css" rel="stylesheet">

      <%--<script src="/Shopping/js/jquery.min.js" type="text/javascript"></script>--%>


      <%--<script src="/Shopping/js/ajaxfileupload.js" type="text/javascript"></script>--%>
      <!-- [if lt IE 9]> -->
      <script src="/Shopping/js/html5shiv.min.js"></script>
      <script src="/Shopping/js/respond.min.js"></script>
      <!-- <![endif] -->

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
                    <li class="list-group-item-diy"><a href="#section1">查看所有用户<span class="sr-only">(current)</span></a></li>
                    <li class="list-group-item-diy"><a href="#section2">查看所有商品</a></li>
                    <li class="list-group-item-diy"><a href="#section3">添加宠物用品</a></li>
                    <li class="list-group-item-diy"><a href="#section4">添加宠物</a></li>
                </ul>
            </div>
            <!-- 控制内容 -->
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <div class="col-md-12">
                    <h1><a name="section1">用户信息</a></h1>
                    <hr/>
                    <table class="table table-hover center" id="userTable">
                    </table>
                </div>

                <div class="col-md-12">
                    <hr/>
                    <h1><a name="section2">商品信息</a></h1>
                    <hr/>
                    <div class="col-lg-12 col-md-12 col-sm-12" id="productArea"></div>
                    <br/>
                </div>

<!-- -----------------------------添加商品----------------------------------------------------------------------------------------- -->
                <div class="col-md-12">
                    <hr/>
                    <h1><a name="section3">添加商品</a></h1>
                    <hr/>
                    <div class="col-sm-offset-2 col-md-offest-2">
                        <!-- 表单输入 -->
                        <div  class="form-horizontal">
                            <div class="form-group">
                                <label for="productName" class="col-sm-2 col-md-2 control-label">商品名称</label>
                                <div class="col-sm-6 col-md-6">
                                    <input type="text" class="form-control" id="productName" placeholder="请输入商品的名称！" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="productDescribe" class="col-sm-2 col-md-2 control-label">商品描述</label>
                                <div class="col-sm-6 col-md-6">
                                    <textarea type="text" class="form-control" id="productDescribe" placeholder="请输入关于商品的描述！"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="keyWord" class="col-sm-2 col-md-2 control-label">关键词</label>
                                <div class="col-sm-6 col-md-6">
                                    <textarea type="text" class="form-control" id="keyWord" placeholder="请输入商品的关键词！用分号隔开！"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="productPrice" class="col-sm-2 col-md-2 control-label">商品价格</label>
                                <div class="col-sm-6 col-md-6">
                                    <input type="text" class="form-control" id="productPrice" placeholder="请输入商品的价格！" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="productCount" class="col-sm-2 col-md-2 control-label">商品数量</label>
                                <div class="col-sm-6 col-md-6">
                                    <input type="text" class="form-control" id="productCount" placeholder="请输入商品的数量！" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="productCount" class="col-sm-2 col-md-2 control-label">封面图片</label>
                                <div class="col-sm-6 col-md-6">
                                    <input type="text" class="form-control" id="productImg" placeholder="等会儿再说" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="productCount" class="col-sm-2 col-md-2 control-label">相关产品</label>
                                <div class="col-sm-6 col-md-6">
                                    <input type="text" class="form-control" id="productRelateproduct_id" placeholder="相关产品id" />
                                </div>
                            </div>                                                        
                            <div class="form-group">
                                <label for="productCategory" class="col-sm-2 col-md-2 control-label">商品类别</label>
                                <div class="col-sm-6 col-md-6">
                                    <select name="productType" class="form-control" id="productType">
                                        <option value="0">日用品</option>
                                        <option value="1">玩具</option>
                                        <option value="2">食物</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="productImgUpload" class="col-sm-2 col-md-2 control-label" accept="image/jpg">商品图片</label>
                                <div class="col-sm-6 col-md-6">
                                    <input name="productImgUpload" type="file" accept="image/jpg"  id="productImgUpload"/>
                                    <p class="help-block">上传的图片大小应为280*160大小的jpg格式的图片</p>
                                </div>
                                <%--<button class="btn btn-primary col-sm-2 col-md-2" onclick="fileUpload()">上传图片</button>--%>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-6" id="imgPreSee">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-6">
                                    <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="addProduct()">添加商品</button>
                                </div>
                            </div>
                        </div>
                        <br/>
                    </div>
                </div>
<!-- -----------------------------------添加宠物-------------------------------------------------------------------- -->
                <div class="col-md-12">
                    <hr/>
                    <h1><a name="section4">添加宠物</a></h1>
                    <hr/>
                    <div class="col-sm-offset-2 col-md-offest-2">
                   
                        <!-- 表单输入 -->
                        <div  class="form-horizontal">
                            <div class="form-group">
                                <label for="petName" class="col-sm-2 col-md-2 control-label">商品名称</label>
                                <div class="col-sm-6 col-md-6">
                                    <input type="text" class="form-control" id="petName" placeholder="请输入商品的名称！" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="petDescribe" class="col-sm-2 col-md-2 control-label">商品描述</label>
                                <div class="col-sm-6 col-md-6">
                                    <textarea type="text" class="form-control" id="petDescribe" placeholder="请输入关于商品的描述！"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="petType" class="col-sm-2 col-md-2 control-label">商品类别</label>
                                <div class="col-sm-6 col-md-6">
                                    <select name="petType" class="form-control" id="petType">
                                        <option value="0">猫咪</option>
                                        <option value="1">狗狗</option>
                                        <option value="2">其他宠物</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="petBreed" class="col-sm-2 col-md-2 control-label">品种</label>
                                <div class="col-sm-6 col-md-6">
                                    <select name="petBreed" class="form-control" id="petBreed">
                                        <option value="英短">英短</option>
                                        <option value="田园猫">田园猫</option>
                                        <option value="虎斑猫">虎斑猫</option>
                                        <option value="布偶猫">布偶猫</option>
                                        <option value="其他猫咪品种">其他品种猫咪</option>
                                        <option value="狮子狗">狮子狗</option>
                                        <option value="拉布拉多">拉布拉多</option>
                                        <option value="中华田园犬">中华田园犬</option>
                                        <option value="金毛">金毛</option>
                                        <option value="兔子">兔子</option>
                                        <option value="乌龟">乌龟</option>
                                        <option value="其他品种宠物">其他品种宠物</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="petColor" class="col-sm-2 col-md-2 control-label">颜色</label>
                                <div class="col-sm-6 col-md-6">
                                    <select name="petColor" class="form-control" id="petColor">
                                        <option value="白色">白色</option>
                                        <option value="蓝色">蓝色</option>
                                        <option value="黑色">黑色</option>
                                        <option value="黄色">黄色</option>
                                        <option value="黑白双色">黑白双色</option>
                                        <option value="白灰双色">白灰双色</option>
                                        <option value="黄白双色">黄白双色</option>
                                        <option value="黑白灰三色">黑白灰三色</option>
                                        <option value="黑白黄三色">黑白黄三色</option>
                                        <option value="黑白虎斑纹">黑白虎斑纹</option>
                                        <option value="黄白虎斑纹">黄白虎斑纹</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="petNature" class="col-sm-2 col-md-2 control-label">性格</label>
                                <div class="col-sm-6 col-md-6">
                                    <select name="petNature" class="form-control" id="petNature">
                                        <option value="安静">安静</option>
                                        <option value="活泼">活泼</option>
                                        <option value="粘人">粘人</option>
                                        <option value="护主">护主</option>
                                        <option value="傲娇">傲娇</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="birthday" class="col-sm-2 col-md-2 control-label">birthday</label>
                                <div class="col-sm-6 col-md-6">
                                    <textarea type="text" class="form-control" id="birthday" placeholder="生日，格式如2019-03-19"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="petPrice" class="col-sm-2 col-md-2 control-label">商品价格</label>
                                <div class="col-sm-6 col-md-6">
                                    <input type="text" class="form-control" id="petPrice" placeholder="请输入商品的价格！" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="petCount" class="col-sm-2 col-md-2 control-label">封面图片</label>
                                <div class="col-sm-6 col-md-6">
                                    <input type="text" class="form-control" id="petImg" placeholder="等会儿再说" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="petCount" class="col-sm-2 col-md-2 control-label">相关产品</label>
                                <div class="col-sm-6 col-md-6">
                                    <input type="text" class="form-control" id="petRelateproduct_id" placeholder="相关产品id" />
                                </div>
                            </div>                                                        

                            <div class="form-group">
                                <label for="petImgUpload" class="col-sm-2 col-md-2 control-label" accept="image/jpg">商品图片</label>
                                <div class="col-sm-6 col-md-6">
                                    <input name="productImgUpload" type="file" accept="image/jpg"  id="petImgUpload"/>
                                    <p class="help-block">上传的图片大小应为280*160大小的jpg格式的图片</p>
                                </div>
                                <%--<button class="btn btn-primary col-sm-2 col-md-2" onclick="fileUpload()">上传图片</button>--%>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-6" id="imgPreSee">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-6">
                                    <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="addPet()">添加宠物</button>
                                </div>
                            </div>
                        </div>
                        <br/>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- 尾部 -->
    <jsp:include page="include/foot.jsp"/>
    
        <div class="layui-layer-move"></div>
    
    
   <script src="/Shopping/js/bootstrap.min.js" type="text/javascript"></script>
   <script src="/Shopping/js/layer.js" type="text/javascript"></script>
   <script src="/Shopping/js/ajaxfileupload.js" type="text/javascript"></script>
   <script type="text/javascript">

        var loading = layer.load(0);
        listAllUser();
        listAllProduct();
        layer.close(loading);
        //列出所有用户
        function listAllUser() {
            var userTable = document.getElementById("userTable");
            var allUser = getAllUsers();
            userTable.innerHTML = '<tr>'+
                '<th> 用户ID </th>'+
                '<th> 用户名</th>'+
                '<th> 昵称</th>'+
                '<th> 邮箱</th>'+
                '<th> 是否删除</th>'+
                '</tr>';
            var html = "";
            for(var i=0;i<allUser.length;i++){
                html += '<tr>'+
                    '<td>'+allUser[i].id+'</td>'+
                    '<td>'+allUser[i].name+'</td>'+
                    '<td>'+allUser[i].phoneNumber+'</td>'+
                    '<td>'+allUser[i].email+'</td>'+
                    '<td>'+
                    '<button class="btn btn-primary btn-sm" type="submit" onclick="deleteUser('+allUser[i].id+')">删除</button>'+
                    '</td>'+
                    '</tr>';
            }
            userTable.innerHTML += html;
        }

        function getAllUsers() {
            var allUsers = "";
            var nothing = {};
            $.ajax({
                async : false, //设置同步
                type : 'GET',
                url : '/Shopping/getAllUser',
                data : nothing,
                dataType : 'json',
                success : function(result) {
                    if (result!=null) {
                        allUsers = result.data;
                    }
                    else{
                        layer.alert('查询所有用户错误');
                    }
                },
                error : function(result) {
                    layer.alert('查询所有用户错误');
                }
            });
            return allUsers;
        }


        function listAllProduct() {
            var productArea = document.getElementById("productArea");
            var allProduct = getAllProducts();
            var html="";
            productArea.innerHTML = '';
            for(var i=0;i<allProduct.length;i++){
                var imgURL = "/Shopping/img/"+allProduct[i].id+".jpg";
                var imgURL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553062204983&di=d4e53b2574fe335e9d2e65ade071c833&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2F9%2Fd4%2F55461315482.jpg";
                html+='<div class="col-sm-4 col-md-4 pd-5">'+
                    '<div class="boxes">'+
                    '<div class="big bigimg">'+
                    '<img src="'+imgURL+'">'+
                    '</div>'+
                    '<p class="font-styles center">'+allProduct[i].name+'</p>'+
                    '<p class="pull-right">价格：'+allProduct[i].price+'</p>'+
                    '<p class="pull-left">库存：'+allProduct[i].counts+'</p>'+
                    '<div class = "row">'+
                    '<button class="btn btn-primary delete-button" type="submit" onclick="deleteProduct('+allProduct[i].id+')">删除商品</button>'+
                    '</div>'+
                    '</div>'+
                    '</div>';
            }
            productArea.innerHTML+=html;
        }
        //列出所有商品

        function getAllProducts() {
            var allProducts = null;
            var nothing = {};
            $.ajax({
                async : false, //设置同步
                type : 'GET',
                url : '/Shopping/getAllProducts',
                data : nothing,
                dataType : 'json',
                success : function(result) {
                    if (result!=null) {
                        allProducts = result.data;
                    }
                    else{
                        layer.alert('查询所有商品错误');
                    }
                },
                error : function(result) {
                    layer.alert('查询所有商品错误');
                }
            });
            return allProducts;
        }

        function deleteUser(id) {
            var user = {};
            user.id = id;
            var deleteResult = "";
            $.ajax({
                async : false,
                type : 'DELETE',
                url : '/Shopping/deleteUser',
                data : user,
                dataType : 'json',
                success : function(result) {
                    deleteResult = result.msg;
                },
                error : function(result) {
                    layer.alert('查询用户错误');
                }
            });
            if(deleteResult != "success")
                layer.alert("删除用户出错");
            listAllUser()
        }

        function deleteProduct(id) {
            var product = {};
            product.id = id;
            var deleteResult = "";
            $.ajax({
                async : false,
                type : 'POST',
                url : '/Shopping/deleteProduct',
                data : product,
                dataType : 'json',
                success : function(result) {
                    deleteResult = result.msg;
                },
                error : function(result) {
                    layer.alert('删除商品错误');
                }
            });
            if(deleteResult != "success")
                layer.alert("删除商品出错");
            listAllProduct();
        }

        function addProduct() {
            var loadings = layer.load(0);
            var product = {};
            product.name = document.getElementById("productName").value;
            product.description = document.getElementById("productDescribe").value;
            product.img = document.getElementById("productImg").value;
            product.price = document.getElementById("productPrice").value;
            product.counts = document.getElementById("productCount").value;
            product.type = document.getElementById("productType").value;
            product.keyWord = document.getElementById("keyWord").value;
            product.relateproduct_id = document.getElementById("productRelateproduct_id").value;
            var addResult="";
            $.ajax({
                async : false,
                type : 'POST',
                url : '/Shopping/addProduct',
                dataType: 'json',
                data: product,
                success : function(result) {
                    addResult = result.msg;
                },
                error : function(result) {
                    layer.alert('添加商品错误');
                }
            });
            if(addResult == "success") {
                fileUpload();
                layer.msg('添加商品成功', {icon: 1, time: 1000});
                layer.close(loadings)
            }
            listAllProduct();
        }
        
        function addPet() {
            var loadings = layer.load(0);
            var product = {};
            product.name = document.getElementById("petName").value;
            product.description = document.getElementById("petDescribe").value;
            product.img = document.getElementById("petImg").value;
            product.price = document.getElementById("petPrice").value;
            product.type = document.getElementById("petType").value;
            product.breed = document.getElementById("petBreed").value;
            product.color = document.getElementById("petColor").value;
            product.nature = document.getElementById("petNature").value;
            product.birthday = document.getElementById("birthday").value;
            product.relateproduct_id = document.getElementById("petRelateproduct_id").value;
            var addResult="";
            $.ajax({
                async : false,
                type : 'POST',
                url : '/Shopping/addPet',
                dataType: 'json',
                data: product,
                success : function(result) {
                    addResult = result.msg;
                },
                error : function(result) {
                    layer.alert('添加宠物错误');
                }
            });
            if(addResult == "success") {
                fileUpload();
                layer.msg('添加宠物成功', {icon: 1, time: 1000});
                layer.close(loadings);
                listAllProduct();
            }else if(addResult=="nameExist"){
            	layer.alert('宠物存在');
            	layer.close(loadings);
            }
        }

        //图片上传先不管
        function fileUpload() {
/*             var results = "";
            var name = document.getElementById("productName").value;
            $.ajaxFileUpload({
                url:'/Shopping/uploadFile',
                secureuri:false ,
                fileElementId:'productImgUpload',
                type:'POST',
                dataType : 'text',
                success: function (result){
                    result = result.replace(/<pre.*?>/g, '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre style="....">text</pre>前后缀
                    result = result.replace(/<PRE.*?>/g, '');
                    result = result.replace("<PRE>", '');
                    result = result.replace("</PRE>", '');
                    result = result.replace("<pre>", '');
                    result = result.replace("</pre>", '');
                    result = JSON.parse(result);
                    results = result.result;
                    if(results == "success") {
                        layer.msg("图片上传成功", {icon: 1});
                        window.location.href = "/Shopping/control";
                        //var imgPreSee = document.getElementById("imgPreSee");
                        //var imgSrc = '/Shopping/img/'+name+'.jpg';
                        //imgPreSee.innerHTML +='<img src="'+imgSrc+')" class="col-sm-12 col-md-12 col-lg-12"/>';
                    }
                    else {
                        layer.msg("图片上传失败", {icon: 0});
                    }

                },
                error: function ()
                {
                    layer.alert("上传错误");
                }}
            ); */
        }
    </script>



 
  </body>
</html>