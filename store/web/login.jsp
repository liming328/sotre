<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>


	<script type="application/javascript">

        $(function(){
            //给验证码图片绑定一个点击事件
            $("#checkImg").click(function () {

                //改变一下他本身地址，在url后面加上一个日期
                $(this).attr("src","checkImg.jpg?"+new Date());
            })
        })

	</script>
<link rel="stylesheet" href="css/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 .main_con_{
     color: red;
     font-size: 22px;
     text-align:center;
 }
 
font {
    color: #666;
    font-size: 22px;
    font-weight: normal;
    padding-right:17px;
}
 </style>
</head>
<body>



<jsp:include page="include/header.jsp"/>
	
	
	
	
	
	
<div class="container"  style="width:100%;height:460px;background:#FF2C4C url('images/loginbg.jpg') no-repeat;">
<div class="row"> 
	<div class="col-md-7">
		<!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
	</div>
	
	<div class="col-md-5">
				<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
				<font>会员登录</font>USER LOGIN

				<div>&nbsp;</div>
<form action="userServlet?method=login" method="post" class="form-horizontal">
  
 <div class="form-group">
    <label for="username" class="col-sm-2 control-label">用户名</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" name="username" id="username" value="${cookie.remeberUsername.value}" placeholder="请输入用户名">${errorMsg}
    </div>
  </div>
   <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-6">
      <input type="password" class="form-control" name="password" id="inputPassword3" placeholder="请输入密码">
    </div>
  </div>
   <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label">验证码</label>
    <div class="col-sm-3">
      <input type="text" class="form-control" name="yzm" id="inputPassword3" placeholder="请输入验证码">
    </div>
    <div class="col-sm-3">
		<img id="checkImg" src="checkImg.jpg"/>
    </div>
    
  </div>
   <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox" name="autoLogin"> 自动登录
        </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label>
          <input type="checkbox" name="remeberUsername"> 记住用户名
        </label>
      </div>
    </div>
  </div>
    <div class="container-fluid">
        <div class="main_con_">
            ${msg}
        </div>
    </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    <input type="submit"  width="100" value="登录" name="submit" border="0"
    style="background: url('./images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    height:35px;width:100px;color:white;">
    </div>
  </div>
</form>
</div>			
	</div>
</div>
</div>	

	<div style="margin-top:50px;">
			<img src="./image/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a>关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2005-2016 天虎商城 版权所有
		</div>
</body></html>