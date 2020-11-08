<%@tag description="form for registration users" pageEncoding="UTF-8" %>
<div class="btn-group btn-group-toggle" data-toggle="buttons">
    <label class="btn btn-primary active btn1">
        <input type="radio" name="options" id="option1" checked> Вход
    </label>
    <label class="btn btn-primary btn2">
        <input type="radio" name="options" id="option2"> Регистрация
    </label>
</div>
<div class="reg">
    <form action="registration" method="post">
        <div class="form-group">
            <label for="inputName1">Your name</label>
            <input type="text" class="form-control" name="inputNS" id="inputName1" required>
        </div>
        <div class="form-group col-md-6">
            <label for="inputEmail1">Email</label>
            <input type="email" class="form-control"  name="inputES" id="inputEmail1" required>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputPassword1">Password</label>
                <input type="password" class="form-control" name="inputPSOne" id="inputPassword1" required>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword3">Password</label>
                <input type="password" class="form-control" name="inputPST" id="inputPassword3" required>
            </div>
        </div>
        <div class="form-group">
            <label for="inputAge1">Your age</label>
            <input type="number" class="form-control" name="inputA" min="6" max="108" id="inputAge1" required>
        </div>
        <button type="submit" class="btn btn-primary">Sign up</button>
    </form>
</div>
<div class="log">
    <form action="registration" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputEmail2">Email</label>
                <input type="email" class="form-control" name="inputEmailLog" id="inputEmail2" required>
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword6">Password</label>
                <input type="password" class="form-control" name="inputPL" id="inputPassword6" required>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Log in</button>
    </form>
</div>
<script> $(document).ready(function () {
    $(".reg").hide();
    $(".btn2").click(function () {
        $(".log").hide();
        $(".reg").show()
    });
    $(".btn1").click(function () {
        $(".reg").hide();
        $(".log").show()
    });
});</script>