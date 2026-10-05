<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head><title>Thanh toán</title></head>
<body>
<h2>Thanh toán đơn hàng</h2>

<c:if test="${not empty error}">
    <p style="color:#b91c1c;background:#fee2e2;padding:8px;"><c:out value="${error}"/></p>
</c:if>

<h3>Đơn hàng của bạn</h3>
<table border="1" cellpadding="6" cellspacing="0" style="border-collapse:collapse">
    <tr><th>Tên sách</th><th>Đơn giá</th><th>SL</th><th>Thành tiền</th></tr>
    <c:forEach var="it" items="${cart.items}">
    <tr>
        <td><c:out value="${it.title}"/></td>
        <td><fmt:formatNumber value="${it.price}" minFractionDigits="2" maxFractionDigits="2"/></td>
        <td>${it.quantity}</td>
        <td><fmt:formatNumber value="${it.subtotal}" minFractionDigits="2" maxFractionDigits="2"/></td>
    </tr>
    </c:forEach>
    <tr>
        <td colspan="3" align="right"><b>Tổng:</b></td>
        <td><b><fmt:formatNumber value="${cart.total}" minFractionDigits="2" maxFractionDigits="2"/></b></td>
    </tr>
</table>

<h3>Thông tin nhận hàng</h3>
<form action="${ctx}/user/checkout" method="post">
    <p><label>Họ tên người nhận:<br>
        <input type="text" name="receiverName" value="<c:out value='${param.receiverName}'/>" required maxlength="100" size="40"></label></p>
    <p><label>Số điện thoại:<br>
        <input type="text" name="phone" value="<c:out value='${param.phone}'/>" required pattern="0[0-9]{9}" title="10 chữ số, bắt đầu bằng 0" size="20"></label></p>
    <p><label>Địa chỉ giao hàng:<br>
        <input type="text" name="address" value="<c:out value='${param.address}'/>" required maxlength="255" size="60"></label></p>
    <p><label>Ghi chú:<br>
        <textarea name="note" rows="3" cols="60" maxlength="255"><c:out value="${param.note}"/></textarea></label></p>
    <p><b>Phương thức thanh toán:</b>
        <label><input type="radio" name="payment" value="COD" checked> Thanh toán khi nhận hàng (COD)</label></p>
    <p>
        <a href="${ctx}/user/cart">&larr; Quay lại giỏ hàng</a> &nbsp;
        <button type="submit">Đặt hàng</button>
    </p>
</form>
</body>
</html>
