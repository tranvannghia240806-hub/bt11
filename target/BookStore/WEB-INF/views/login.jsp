<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head><title>Đăng nhập</title></head>
<body>
<div style="max-width:360px;margin:30px auto;background:#fff;padding:24px;border-radius:8px;box-shadow:0 1px 4px #0002">
    <h2>Đăng nhập</h2>
    <c:if test="${not empty error}">
        <p style="color:#b91c1c;background:#fee2e2;padding:8px"><c:out value="${error}"/></p>
    </c:if>
    <form action="${ctx}/login" method="post">
        <p><label>Email<br><input type="email" name="email" value="<c:out value='${param.email}'/>" required style="width:100%;padding:8px"></label></p>
        <p><label>Mật khẩu<br><input type="password" name="password" required style="width:100%;padding:8px"></label></p>
        <button type="submit" style="padding:8px 20px">Đăng nhập</button>
    </form>
    <p><small>Tài khoản mẫu: user@test.com / 123456</small></p>
</div>
</body>
</html>
