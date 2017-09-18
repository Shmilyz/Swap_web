<%--
  Created by IntelliJ IDEA.
  User: Shmily_Z
  Date: 2017/9/10
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Title
        sessionScope.username
    </title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());

    %>
</head>
<body>
<c:if test="${! empty sessionScope.username}">
    ${sessionScope.username}

</c:if>
<c:if test="${ empty sessionScope.username}">
    <script type="text/javascript">

        window.location.href="${APP_PATH }/";

    </script>
</c:if>

<script type="text/javascript"
        src="${APP_PATH }/static/js/jquery-3.2.1.min.js"></script>


</body>
</html>
