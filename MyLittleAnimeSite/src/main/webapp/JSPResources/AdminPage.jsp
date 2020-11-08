<%--
  Created by IntelliJ IDEA.
  User: Xokken
  Date: 08.11.2020
  Time: 3:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<t:head>m  </t:head>
<body>
<form action="${pageContext.request.contextPath}/admin" name="" method="post">
    <div>
        <input placeholder="name" name="nameAdd" type="text" >
        <input placeholder="world" name="worldAdd" type="text" >
        <input placeholder="path img" name="imgAdd" type="text" >
        <button type="submit" name="add" value="add">Добавить</button>
    </div>
</form>
    <div class="container container-girls">
        <c:forEach var="person" items="${personListAdmin}">
            <div class="center girl">
                <img src="http://127.0.0.1:8887/${person.img}"
                     class="img-thumbnail"
                     width="200px"sizes=""
                     height="200px">
                <p class="text-center">${person.name}</p>
                <p class="text-center">${person.world}</p>
                <form action="${pageContext.request.contextPath}/admin" method="post" hidden="true" id="${person.name}1">
                    <input type="text" name="personNameDel" value="${person.name}" hidden="true">
                    <input type="text" name="personWorldDel" value="${person.img}" hidden="true">
                    <input type="text" name="personImgDel" value="${person.world}" hidden="true">
                </form>
                <button form="${person.name}1" type="submit" name="delete" value="${person}" type="button" class="btn btn-danger">Удалить</button>
            </div>
        </c:forEach>
    </div>
</body>
</html>
