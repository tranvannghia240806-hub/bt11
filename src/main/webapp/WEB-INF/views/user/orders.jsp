<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Lịch sử đơn hàng</title>
    <style>
        .tabs a{display:inline-block;padding:6px 12px;margin:2px;border:1px solid #999;border-radius:16px;text-decoration:none;color:#333}
        .tabs a.active{background:#2563eb;color:#fff;border-color:#2563eb}
        .order{border:1px solid #ccc;border-radius:6px;padding:12px;margin:12px 0}
        .badge{padding:2px 10px;border-radius:12px;color:#fff;font-size:.9em}
        .st0{background:#6b7280}.st1{background:#2563eb}.st2{background:#7c3aed}.st3{background:#0891b2}
        .st4{background:#d97706}.st5{background:#16a34a}.st6{background:#dc2626}.st7{background:#9a3412}
    </style>
</head>
<body>
<h2>Lịch sử đặt hàng</h2>

<c:if test="${not empty success}">
    <p style="color:#166534;background:#dcfce7;padding:8px;">Đặt hàng thành công! Mã đơn hàng của bạn: <b>#<c:out value="${success}"/></b></p>
</c:if>

<div class="tabs">
    <a href="${ctx}/user/orders" class="${currentStatus == null ? 'active' : ''}">Tất cả (${allCount})</a>
    <c:forEach var="st" items="${statuses}">
        <a href="${ctx}/user/orders?status=${st.code}" class="${currentStatus == st.code ? 'active' : ''}">
            ${st.label} (${empty counts[st.code] ? 0 : counts[st.code]})
        </a>
    </c:forEach>
</div>

<c:if test="${empty orders}"><p>Không có đơn hàng nào.</p></c:if>

<c:forEach var="o" items="${orders}">
<div class="order">
    <p>
        <b>Đơn #${o.orderId}</b> &nbsp;
        <span class="badge st${o.status}">${o.statusLabel}</span> &nbsp;
        <small>${o.orderDate.toLocalDate()} ${o.orderDate.toLocalTime().withNano(0)}</small>
    </p>
    <p>Người nhận: <c:out value="${o.receiverName}"/> - ${o.phone}<br>
       Địa chỉ: <c:out value="${o.address}"/><br>
       Thanh toán: ${o.paymentMethod}
       <c:if test="${not empty o.note}"><br>Ghi chú: <c:out value="${o.note}"/></c:if></p>
    <table border="1" cellpadding="6" cellspacing="0" style="border-collapse:collapse">
        <tr><th>Tên sách</th><th>Đơn giá</th><th>SL</th><th>Thành tiền</th></tr>
        <c:forEach var="it" items="${o.items}">
        <tr>
            <td><c:out value="${it.bookTitle}"/></td>
            <td><fmt:formatNumber value="${it.price}" minFractionDigits="2" maxFractionDigits="2"/></td>
            <td>${it.quantity}</td>
            <td><fmt:formatNumber value="${it.subtotal}" minFractionDigits="2" maxFractionDigits="2"/></td>
        </tr>
        </c:forEach>
    </table>
    <p><b>Tổng tiền: <fmt:formatNumber value="${o.totalAmount}" minFractionDigits="2" maxFractionDigits="2"/></b></p>
</div>
</c:forEach>
</body>
</html>
