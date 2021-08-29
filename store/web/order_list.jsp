<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="css/style.css" type="text/css" />

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>

	<jsp:include page="include/header.jsp"/>

		<div class="container">
			<span class="has-error">${msg}</span>
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>我的订单</strong>
					<table class="table table-bordered">
						<c:forEach items="${page.list}" var="order">
						<tbody>
							<tr class="success">
								<th colspan="5">订单编号:${order.oid}    总计:${order.total}    下单日期:<fmt:formatDate value="${order.ordertime}" pattern="yyyy年MM月dd日 HH:mm:ss"></fmt:formatDate> </th>
							</tr>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
							</tr>
							<c:forEach items="${order.orderItemList}" var="oi">
							<tr class="active">
								<td width="60" width="40%">
									<input type="hidden" name="id" value="22">
									<img src="${oi.product.pimage}" width="70" height="60">
								</td>
								<td width="30%">
									<a target="_blank"> ${oi.product.pname}</a>
								</td>
								<td width="20%">
									￥${oi.product.shop_price}
								</td>
								<td width="10%">
									 ${oi.count}
								</td>
								<td width="15%">
									<span class="subtotal">￥${oi.subtotal}</span>
								</td>
							</tr>
							</c:forEach>
						</tbody>
						</c:forEach>

					</table>
				</div>
			</div>

			<!--分页 -->
			<div style="width:380px;margin:0 auto;margin-top:50px;">
				<ul class="pagination" style="text-align:center; margin-top:10px;">
					<c:if test="${page.currPage > 1}">
						<li class="disabled"><a href="orderServlet?method=myOrderList&currPage=${page.currPage-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					</c:if>
					<c:forEach begin="1" end="${page.totalPage}" var="currPage">
						<li  <c:if test="${page.currPage == currPage}"> class="active"</c:if> >   <a href="orderServlet?method=myOrderList&currPage=${currPage}">${currPage}</a></li>
					</c:forEach>
					<c:if test="${page.currPage < page.totalPage}">
						<li>
							<a href="orderServlet?method=myOrderList&currPage=${page.currPage+1}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:if>
				</ul>
			</div>
			<!-- 分页结束=======================        -->
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
	</body>

</html>