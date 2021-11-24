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
                <input type="text" placeholder="Enter Username" name="userID" required>
                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>
                <div>
                    <input type="checkbox" checked="checked" name="remember">Remember me
                </div>
                <div class="loginGr">
                    <button type="submit">Login</button>
                    <button type="submit">Forgot Password</button>
                    <button type="reset">Reset</button>
                </div>
            </div>
        </form>
    </body>
</html>
