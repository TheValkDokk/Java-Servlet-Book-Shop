<%@page import="java.util.ArrayList"%>
<%@page import="dao.CategoryDTO"%>
<%@page import="dao.bookDTO"%>
<%@page import="java.util.List"%>
<%@page import="dao.bookDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.userDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="dao" class="dao.bookDAO" scope="request"></jsp:useBean>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
            <meta name="google-signin-client_id" content="367271086946-45b9dap999mv1be9268stcvham2u5h3h.apps.googleusercontent.com">
            <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
            <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
            <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
            <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
            <link rel="stylesheet" href="css/welcome.css">
            <title>Welcome Page</title>
        </head>
        <body>
            <span>
                <img alt="banner" src="https://mythbank.com/wp-content/uploads/2019/10/homepagebanner-marvel.jpg" class="banner">
            </span>
            <div class="container" style="max-width: 95%;">
                <span class="searchAr">
                    <i class="fa fa-search"></i>
                    <input value="${search}" oninput="searchByName(this)" id="seaid"  type="text"  class="search" name="search" placeholder="Search.." autocomplete="off">
            </span>
            <div class="rightBt">
                <%
                    userDTO user = (userDTO) session.getAttribute("LOGIN_USER");
                    if (user != null) {
                        if (user.getRoleID().equalsIgnoreCase("AD")) {
                %>
                <div class="welUser">
                    <p id="uName" hidden><%=user.getName()%></p>
                    <button onclick="document.getElementById('edit').style.display = 'block'" style="width:auto;">Create</button>
                    <div id="edit" class="modal">
                        <div class="modal-content animate">
                            <jsp:include page="/WEB-INF/addBook.jsp"></jsp:include>
                            </div>
                        </div>
                        <a href="/BookShop/logout" >Logout</a>
                    </div>
                <%
                } else {
                %>
                <div class="welUser">
                    Hello: <%=user.getName()%>    
                    <button style="border: none; background-image: none; box-shadow: none;" type="button" data-toggle="modal" data-target="#cartBt"><h1><i class="fa fa-shopping-cart"></i></h1></button>
                    <a href="/BookShop/logout" >Logout</a>
                </div>
                <%
                    }
                } else {
                %>
                <button onclick="document.getElementById('loginBt').style.display = 'block'" style="width:auto;">Login</button>
                <button onclick="document.getElementById('createBt').style.display = 'block'" style="width:auto;">Create</button>
                <%
                    }
                %>
            </div>
        </div>
        <div class="container" style="max-width: 1980px;">
            <div id="loginBt" class="modal">
                <div class="modal-content animate">
                    <jsp:include page="/WEB-INF/login.jsp"/>
                </div>
            </div>
            <%
                if (user != null && user.getRoleID().equalsIgnoreCase("US")) {
            %>
            <div id="cartBt" class="modal">
                <div class="modal-content animate" style="width: 75%">
                    <jsp:include page="WEB-INF/Cart.jsp"/>
                </div>
            </div>
            <%
                }
            %>
            <div id="createBt" class="modal">
                <div class="modal-content animate" style="width:40%;">
                    <jsp:include page="/WEB-INF/createUser.jsp"/>
                </div>
            </div>
        </div>

        <div class="container main-body" >
            <div class="ccateTab">
                <jsp:include page="WEB-INF/CategoryTab.jsp"/>
            </div>
            <div class="row item-card" id="content">
                <c:forEach items="${BookList}" var="o">
                    <c:if test="${o.isStatus() == true}">
                        <div class="book">
                            <div class="card" id="${o.getId()}">
                                <c:if test="${o.getQuantity() > 0}">
                                    <div class="frontcard">
                                        <img class="bookImg" src="${o.getImg()}" alt="Book Cover" style="width:100%">
                                    </div>
                                    <div class="backcard">
                                        <a class="bookTitle" href="detail?pid=${o.getId()}" title="View Product" class="title">${o.getName()}</a>
                                        <p class="bookQuantity">${o.getQuantity()} in stock</p>
                                        <p class="bookPub">Pub: ${o.getPub()}</p>
                                        <p>Cover: ${o.getCover()}</p>
                                        <p>Writer: ${o.getWrite()} </p>
                                        <p>Penciler: ${o.getPen()}</p>
                                        <p style="display:none;" class="bookPrice">${o.getPrice()}</p>
                                        <p style="display:none;" class="bookID">${o.getId()}</p>
                                        <%
                                            if (user == null) {
                                        %>
                                        <p><button onclick="document.getElementById('loginBt').style.display = 'block'" style="width:-webkit-fill-available;">Buy $${o.getPrice()} <br /> Login To Buy</button></p>
                                                <%
                                                } else {
                                                %>
                                        <button class="cart-button" onclick="addtoCart(${o.getId()})" style="width:-webkit-fill-available;">
                                            Buy $${o.getPrice()}
                                        </button>
                                        <%
                                            }
                                        %>
                                    </div>
                                </c:if>
                                <c:if test="${o.getQuantity() <= 0}">
                                    <div class="frontcard">
                                        <img class="bookImg" src="${o.getImg()}" alt="Book Cover" style="width:100%; filter: grayscale(1);">
                                    </div>
                                    <div class="backcard">
                                        <a class="bookTitle" style="font-size: 14px;" title="View Product" class="title">${o.getName()}</a>
                                        <p class="bookQuantity">${o.getQuantity()} in stock</p>
                                        <p class="bookPub">Pub: ${o.getPub()}</p>
                                        <p>Cover: ${o.getCover()}</p>
                                        <p>Writer: ${o.getWrite()} </p>
                                        <p>Penciler: ${o.getPen()}</p>
                                        <p style="display:none;" class="bookPrice">${o.getPrice()}</p>
                                        <p style="display:none;" class="bookID">${o.getId()}</p>
                                        <p><button style="width:-webkit-fill-available">Sold Out</button>
                                        </p>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </body>
    <script type="text/javascript" src="JavaScript/main.js"></script>
</html>
<script src="https://apis.google.com/js/platform.js" async defer></script>