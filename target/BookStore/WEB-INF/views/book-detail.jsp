<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head><title><c:out value="${book.title}"/></title></head>
<body>
<div style="display:flex;gap:24px;background:#fff;padding:20px;border-radius:8px;box-shadow:0 1px 4px #0002">
    <img src="${ctx}/images/${book.coverImage}" alt="" width="200">
    <div>
        <h2><c:out value="${book.title}"/></h2>
        <p>Mã isbn: ${book.isbn}</p>
        <p>Publisher: <c:out value="${book.publisher}"/></p>
        <p>Publisher_date: ${book.publishDate}</p>
        <p>Quantity: ${book.quantity}</p>
        <p>Giá: <fmt:formatNumber value="${book.price}" minFractionDigits="2" maxFractionDigits="2"/></p>
        <p><c:out value="${book.description}"/></p>
        <c:choose>
            <c:when test="${book.quantity > 0}">
                <form action="${ctx}/user/cart" method="post">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="bookId" value="${book.bookid}">
                    <input type="number" name="qty" value="1" min="1" max="${book.quantity}" style="width:70px">
                    <button type="submit">Thêm vào giỏ</button>
                </form>
            </c:when>
            <c:otherwise><p style="color:#b91c1c">Hết hàng</p></c:otherwise>
        </c:choose>
        <p><a href="${ctx}/home">&larr; Quay lại danh sách</a></p>
    </div>
</div>
</body>
</html>
