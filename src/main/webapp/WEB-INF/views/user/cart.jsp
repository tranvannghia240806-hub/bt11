<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head><title>Giỏ hàng</title></head>
<body>
<h2>Giỏ hàng của bạn</h2>

<c:if test="${not empty cartMsg}">
    <p style="color:#b45309;background:#fef3c7;padding:8px;"><c:out value="${cartMsg}"/></p>
</c:if>

<c:choose>
<c:when test="${cart.empty}">
    <p>Giỏ hàng đang trống. <a href="${ctx}/home">Tiếp tục mua sắm</a></p>
</c:when>
<c:otherwise>
<table border="1" cellpadding="8" cellspacing="0" style="border-collapse:collapse;width:100%">
    <tr>
        <th>Ảnh</th><th>Tên sách</th><th>Đơn giá</th><th>Số lượng</th><th>Thành tiền</th><th></th>
    </tr>
    <c:forEach var="it" items="${cart.items}">
    <tr>
        <td><img src="${ctx}/images/${it.coverImage}" alt="" width="60"></td>
        <td><a href="${ctx}/book-detail?id=${it.bookId}"><c:out value="${it.title}"/></a></td>
        <td><fmt:formatNumber value="${it.price}" minFractionDigits="2" maxFractionDigits="2"/></td>
        <td>
            <form action="${ctx}/user/cart" method="post" style="display:inline">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="bookId" value="${it.bookId}">
                <input type="number" name="qty" value="${it.quantity}" min="1" max="${it.stock}" style="width:70px">
                <button type="submit">Cập nhật</button>
            </form>
            <br><small>Còn ${it.stock} cuốn</small>
        </td>
        <td><fmt:formatNumber value="${it.subtotal}" minFractionDigits="2" maxFractionDigits="2"/></td>
        <td>
            <form action="${ctx}/user/cart" method="post" onsubmit="return confirm('Xóa sách này khỏi giỏ?')">
                <input type="hidden" name="action" value="remove">
                <input type="hidden" name="bookId" value="${it.bookId}">
                <button type="submit">Xóa</button>
            </form>
        </td>
    </tr>
    </c:forEach>
    <tr>
        <td colspan="4" align="right"><b>Tổng cộng (${cart.totalQuantity} cuốn):</b></td>
        <td colspan="2"><b><fmt:formatNumber value="${cart.total}" minFractionDigits="2" maxFractionDigits="2"/></b></td>
    </tr>
</table>

<p>
    <a href="${ctx}/home">&larr; Tiếp tục mua sắm</a> &nbsp;
    <form action="${ctx}/user/cart" method="post" style="display:inline" onsubmit="return confirm('Xóa toàn bộ giỏ hàng?')">
        <input type="hidden" name="action" value="clear">
        <button type="submit">Xóa tất cả</button>
    </form>
    &nbsp; <a href="${ctx}/user/checkout"><button type="button">Thanh toán</button></a>
</p>
</c:otherwise>
</c:choose>
</body>
</html>
