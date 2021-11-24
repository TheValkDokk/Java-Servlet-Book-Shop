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
        <form id="regForm" action="CreateUserController" method="POST">
            <div class="container" id="resIDFom">
                <h1>Register</h1>
                <p>Please fill in this form to create an account.</p>
                <hr>

                <label for="userID"><b>UserName</b></label>
                <input maxlength="20" type="text" placeholder="Enter Email" name="userID" max="20" autocomplete="off" id="userID" required>
                <div id='message'></div>

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" max="20" name="password" id="psw" required>

                <label for="confirm"><b>Repeat Password</b></label>
                <input type="password" placeholder="Repeat Password" max="20" name="confirm" id="psw-repeat" required>


                <label for="fullName"><b>Full Name</b></label>
                <input type="text" placeholder="Enter FullName" max="50" name="fullName" id="fullName" required>

                <label for="roleID"><b>Role ID (US for user, AD for admin)</b></label>
                <input type="text" placeholder="Enter RoleID" max="2" name="roleID" id="roleID" required>

                <label for="addr"><b>Address</b></label>
                <input type="text" placeholder="Enter Address" max="20" name="addr" id="addr" required>

                <label for="roleID"><b>Phone Number</b></label>
                <input type="tel" placeholder="Enter Phone Number" name="phone" id="phone" max="10" pattern="[0-9]{10}" required title="10 Number">

                <hr>

                <p>By creating an account you agree to our <a href="javascript:curios()">Terms & Privacy</a>.</p>
                <button type="button" onclick="vaild()" class="registerbtn">Register</button>
            </div>
        </form>
        <div class="container signin">
            <p id="alre">Already have an account?</p>
            <span><button onclick="callLog()" style="width:auto;">Login</button></span>
        </div>
    </body>
    <script>
        function callLog() {
            document.getElementById('createBt').style.display = 'none';
            document.getElementById('loginBt').style.display = 'block';
        }
        var check = false;
        var checkf = false;
        $('#userID').on('keyup', function () {
            $.ajaxSetup({
                cache: false
            });
            var mail = $(this).val();
            $.ajax({
                url: '/BookShop/checkDupController',
                type: 'post',
                data: {
                    "id": mail
                },
                success: function (data) {
                    var row = document.getElementById("message");
                    row.innerHTML = data;
                },
                error: function (xhr) {
                }
            });
        });

        $('#psw, #psw-repeat').on('keyup', function () {
            if ($('#psw').val() == $('#psw-repeat').val()) {
                $('#psw-repeat').css('background-color', 'white');
                check = true;
            } else {
                $('#psw-repeat').css('background-color', 'red');
                check = false;
            }
        });
        
        function curios(){
            alert("You are a curios one i see :3");
        }
        
        function vaild() {
            if (check && document.getElementById('mailYes').textContent==='UserName Available') {
                var f = $('#regForm').serialize();
                console.log(f);
                $.ajaxSetup({
                    cache: false
                });
                $.ajax({
                    url: '/BookShop/CreateUserController',
                    type: 'post',
                    data: {
                        "i": f
                    },
                    success: function (data) {
                        $('#resIDFom').remove();
                        var row = document.getElementById("alre");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                    }
                });
            } else {
                alert('Invaild Data Input!!');
            }
        }
    </script>
    <style>
        .loader {
            border: 16px solid #f3f3f3; /* Light grey */
            border-top: 16px solid #3498db; /* Blue */
            border-radius: 50%;
            width: 120px;
            height: 120px;
            animation: spin 2s linear infinite;
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    </style>
</html>

