<%-- 
    Document   : login
    Created on : Jun 19, 2021, 1:40:53 PM
    Author     : Dokk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="css/login.css">
        <title>Login</title>
    </head>
    <body>

        <form action="LoginController" method="POST">
            <div class="imgcontainer">
                <img src="https://picsum.photos/250" alt="Avatar" class="avatar">
            </div>
            <div class="container">
                <label for="userID"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="userID" max="20" required>
                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>
                <div>
                    <input type="checkbox" checked="checked" name="remember">Remember me
                </div>
                <div class="loginGr">
                    <button type="submit">Login</button>
                    <button type="reset">Reset</button>
                    <button class="g-signin2" data-onsuccess="onSignIn" style="border: none; background-image: none; box-shadow: none;"></button>
                </div>
            </div>
        </form>
        <span class="container signin" style="text-align-last: center;">
            <span><p style="display: inline;">Don't have an account?</p></span>
            <span><button onclick="callReg()" style="width:auto;">Create Account</button></span>
        </span>
    </body>
</html>
<script>
    function callReg() {
        document.getElementById('loginBt').style.display = 'none';
        document.getElementById('createBt').style.display = 'block';
    }

    function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        var id = profile.getId();
        id = id.substring(0, 19);
        var name = profile.getName();
        googleUser.disconnect();
        $.ajax({
            url: "/BookShop/GoogleLoginController",
            type: "post",
            traditional: true,
            data: {
                id: id,
                name: name
            },
            success: function (data) {
                window.location.replace("/BookShop/home");
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }
</script>
