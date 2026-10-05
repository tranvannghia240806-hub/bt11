<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><sitemesh:write property="title"/> - BookStore</title>
    <style>
        body{font-family:Segoe UI,Arial,sans-serif;margin:0;color:#222;background:#f7f7f9}
        header{background:#1e3a8a;padding:0 24px}
        header nav{display:flex;gap:6px;align-items:center;flex-wrap:wrap}
        header a{color:#fff;text-decoration:none;padding:14px 12px;display:inline-block}
        header a:hover{background:#2748b0}
        header .sp{flex:1}
        main{max-width:1100px;margin:20px auto;padding:0 16px;min-height:60vh}
        footer{background:#111827;color:#d1d5db;text-align:center;padding:16px;font-size:.9em}
        button{cursor:pointer}
    </style>
    <sitemesh:write property="head"/>
</head>
<body>
<header>
    <nav>
        <a href="${ctx}/home">Trang chủ</a>
        <a href="${ctx}/home">Sản phẩm</a>
        <c:if test="${not empty sessionScope.user and sessionScope.user.admin}">
            <a href="${ctx}/admin">Trang quản trị</a>
        </c:if>
        <span class="sp"></span>
        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <a href="${ctx}/login">Đăng nhập</a>
            </c:when>
            <c:otherwise>
                <a href="${ctx}/user/cart">Giỏ hàng (${empty sessionScope.cart ? 0 : sessionScope.cart.totalQuantity})</a>
                <a href="${ctx}/user/orders">Đơn hàng của tôi</a>
                <a href="${ctx}/logout">Đăng xuất (<c:out value="${sessionScope.user.fullname}"/>)</a>
            </c:otherwise>
        </c:choose>
    </nav>
</header>
<main>
    <sitemesh:write property="body"/>
</main>
<footer>
    Họ tên: Trần Văn Nghĩa &nbsp;|&nbsp; MSSV: 24110287 &nbsp;|&nbsp; Mã đề: 01
</footer>
</body>
</html>
