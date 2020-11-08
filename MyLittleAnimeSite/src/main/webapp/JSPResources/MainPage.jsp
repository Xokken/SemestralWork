<%--
  Created by IntelliJ IDEA.
  User: Xokken
  Date: 04.11.2020
  Time: 0:51
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
        <div class ="worlds">
            <c:forEach var="world" items="${worldList}">
                <form action="home/girls" id="${world.name}1" method="get">
                    <ul class="list-group list-group-horizontal">
                        <li class="list-group-item">
                            <td><img src="${pageContext.request.contextPath}${world.img}"
                                                             class="img-thumbnail"
                                                             width="200px"sizes=""
                                                             height="200px"></td>
                            <input type="text" name="worldName" value="${world.name}" hidden="true">
                            <input type="text" name="worldImg" value="${world.img}" hidden="true">
                            <a onclick="document.getElementById('${world.name}1').submit(); return false;"
                               href="${pageContext.request.contextPath}/gates/home/girls">${world.name}</a></span></li>
                    </ul>
                </form>
            </c:forEach>
        </div>
        <div class="center">
            <t:profile></t:profile>
        </div>
    </div>
</body>
</html>
