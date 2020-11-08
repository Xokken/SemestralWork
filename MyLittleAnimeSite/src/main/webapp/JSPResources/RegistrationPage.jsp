<%--
  Created by IntelliJ IDEA.
  User: Xokken
  Date: 25.10.2020
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<t:head></t:head>
    <c:if test="${sessionScope.get('auth') == 'false'}">
        <p>
                ${requestScope.get("error")}
        </p>
    </c:if>
    <body class="center">
    <t:formReg></t:formReg>
    </body>
</html>
