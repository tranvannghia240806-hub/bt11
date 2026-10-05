<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Trang chủ</title>
    <style>
        .grid{display:grid;grid-template-columns:repeat(auto-fill,minmax(300px,1fr));gap:16px}
        .card{background:#fff;border-radius:8px;padding:14px;box-shadow:0 1px 4px #0002;display:flex;gap:14px}
        .card img{width:100px;height:140px;object-fit:cover;border-radius:4px}
        .card p{margin:3px 0;font-size:.92em}
        .pager{text-align:center;margin:24px 0}
        .pager a,.pager span{display:inline-block;padding:6px 12px;margin:2px;border:1px solid #bbb;border-radius:4px;text-decoration:none;color:#333}
        .pager span{background:#1e3a8a;color:#fff;border-color:#1e3a8a}
    </style>
</head>
<body>
<h2>Danh sách sách</h2>
<div class="grid">
<c:forEach var="b" items="${books}">
    <div class="card">
        <img src="${ctx}/images/${b.coverImage}" alt="">
        <div>
            <p><b>Tiêu đề: <a href="${ctx}/book-detail?id=${b.bookid}"><c:out value="${b.title}"/></a></b></p>
            <p>Mã isbn: ${b.isbn}</p>
            <p>Publisher: <c:out value="${b.publisher}"/></p>
            <p>Publisher_date: ${b.publishDate}</p>
            <p>Quantity: ${b.quantity}</p>
            <p>Giá: <fmt:formatNumber value="${b.price}" minFractionDigits="2" maxFractionDigits="2"/></p>
            <c:if test="${b.quantity > 0}">
            <form action="${ctx}/user/cart" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="bookId" value="${b.bookid}">
                <input type="number" name="qty" value="1" min="1" max="${b.quantity}" style="width:60px">
                <button type="submit">Thêm vào giỏ</button>
            </form>
            </c:if>
            <c:if test="${b.quantity <= 0}"><p style="color:#b91c1c">Hết hàng</p></c:if>
        </div>
    </div>
</c:forEach>
</div>

<div class="pager">
    <c:if test="${page > 1}"><a href="${ctx}/home?page=${page - 1}">&laquo;</a></c:if>
    <c:forEach begin="1" end="${totalPages}" var="p">
        <c:choose>
            <c:when test="${p == page}"><span>${p}</span></c:when>
            <c:otherwise><a href="${ctx}/home?page=${p}">${p}</a></c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${page < totalPages}"><a href="${ctx}/home?page=${page + 1}">&raquo;</a></c:if>
</div>
</body>
</html>
