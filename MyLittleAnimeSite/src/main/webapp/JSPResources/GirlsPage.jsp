<%--
  Created by IntelliJ IDEA.
  User: Xokken
  Date: 05.11.2020
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<t:head></t:head>
<t:footer></t:footer>
    <body>
        <div class="container container-girls">
            <c:forEach var="person" items="${personList}">
                <div class="center girl">
                    <td><img src="http://127.0.0.1:8887/${person.img}"
                             class="img-thumbnail"
                             width="200px"
                             height="200px"></td>
                    <p>${person.name}</p>
                    <form action="girls" id="${person.name}" method="post">
                        <input type="text" name="personName" value="${person.name}" hidden="true">
                        <input type="text" name="personWorld" value="${person.img}" hidden="true">
                        <input type="text" name="personImg" value="${person.world}" hidden="true">
                        <select name="hero">
                            <option disabled selected="selected" >Выберите действие</option>
                            <option value="true">Сделать своим персонажем!</option>
                            <option value="false">Убрать из своих!</option>
                            <input type="submit" value="Принять">
                        </select>
                    </form>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
