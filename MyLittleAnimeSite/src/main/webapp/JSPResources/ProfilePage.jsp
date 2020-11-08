<%--
  Created by IntelliJ IDEA.
  User: Xokken
  Date: 04.11.2020
  Time: 6:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<t:head></t:head>
<t:footer></t:footer>
    <body>
        <div class="container profile">
            <div class="center">
                <t:profile></t:profile>
                <t:btnImg></t:btnImg>
            </div>
            <div class="container container-girls">
            <c:forEach var="person" items="${personList}">
                <div class="center girl">
                    <td><img src="http://127.0.0.1:8887/${person.img}"
                             class="img-thumbnail"
                             width="200px"sizes=""
                             height="200px"></td>
                    <p class="text-center">${person.name}</p>
                    <p class="text-center">${person.world}</p>
                    <form action="${pageContext.request.contextPath}/gates/profile" method="post" hidden="true" id="delete">
                    </form>
                    <button form="delete" type="submit" name="delete" value="${person.name}" type="button" class="btn btn-danger">Отказаться</button>
                </div>
            </c:forEach>
            </div>
        </div>
    </body>
</html>
