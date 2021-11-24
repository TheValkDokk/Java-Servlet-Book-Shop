<%-- 
    Document   : createUser
    Created on : Jun 19, 2021, 3:08:26 PM
    Author     : Dokk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/welcome.css">
        <title>Create New User</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            <div class="container">
                <h1>Register</h1>
                <p>Please fill in this form to create an account.</p>
                <hr>

                <label for="userID"><b>UserName</b></label>
                <input type="text" placeholder="Enter Email" name="userID" id="userID" required>

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" id="psw" required>

                <label for="confirm"><b>Repeat Password</b></label>
                <input type="password" placeholder="Repeat Password" name="confirm" id="psw-repeat" required>
                
                <label for="fullName"><b>Full Name</b></label>
                <input type="text" placeholder="Enter FullName" name="fullName" id="fullName" required>

                <label for="roleID"><b>Role ID</b></label>
                <input type="text" placeholder="Enter RoleID" name="roleID" id="roleID" required>
                
                <hr>

                <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
                <button type="submit" class="registerbtn">Register</button>
            </div>

            <div class="container signin">
                <p>Already have an account? <a href="document.getElementById('loginBt').style.display='block">Sign in</a>.</p>
            </div>
        </form>
    </body>
</html>
