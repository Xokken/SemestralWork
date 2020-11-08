<%@tag description="form for registration users" pageEncoding="UTF-8" %>
<footer>
    <ul class="nav justify-content-end">
        <li class="nav-item">
            <a class="nav-link active" href="${pageContext.request.contextPath}/gates/home">Главная</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/gates/profile">Профиль</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Опросы</a>
        </li>
        <form action="${pageContext.request.contextPath}/gates/logout" method="post" hidden="true" id="log">
        </form>
        <button form="log" type="submit" name="logout" value="logout" type="button" class="btn btn-danger">Выход</button>
    </ul>
</footer>