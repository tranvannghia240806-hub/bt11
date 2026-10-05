<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title"/> - Quản trị</title>
    <style>
        body{font-family:Segoe UI,Arial,sans-serif;margin:0;color:#222;background:#f3f4f6}
        header{background:#065f46;padding:0 24px}
        header a{color:#fff;text-decoration:none;padding:14px 12px;display:inline-block}
        main{max-width:1100px;margin:20px auto;padding:0 16px;min-height:60vh}
        footer{background:#111827;color:#d1d5db;text-align:center;padding:16px;font-size:.9em}
    </style>
    <sitemesh:write property="head"/>
</head>
<body>
<header>
    <a href="${ctx}/admin">Trang quản trị</a>
    <a href="${ctx}/admin/books">Quản lý sách</a>
    <a href="${ctx}/admin/authors">Quản lý tác giả</a>
    <a href="${ctx}/home">Về trang chủ</a>
    <a href="${ctx}/logout">Đăng xuất</a>
</header>
<main><sitemesh:write property="body"/></main>
<footer>Họ tên: Trần Văn Nghĩa &nbsp;|&nbsp; MSSV: 24110287 &nbsp;|&nbsp; Mã đề: 01</footer>
</body>
</html>
