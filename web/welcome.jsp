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
            <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
            <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
            <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
            <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

            <link rel="stylesheet" href="css/welcome.css">
            <title>Welcome Page</title>
        </head>
        <body>
            <span>
                <img alt="banner" src="https://mythbank.com/wp-content/uploads/2019/10/homepagebanner-marvel.jpg" class="banner">
            </span>
            <div class="container">
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
                    Hello: <%=user.getName()%>
                    <button onclick="document.getElementById('edit').style.display = 'block'" style="width:auto;">Create</button>
                    <div id="edit" class="modal">
                        <div class="modal-content animate">
                            <jsp:include page="addBook.jsp"></jsp:include>
                            </div>
                        </div>
                        <a href="/BookShop/logout" >Logout</a>
                    </div>
                <%
                } else {
                %>
                <div class="welUser">
                    Hello: <%=user.getName()%>
                    <button type="button" data-toggle="modal" data-target="#cartBt"><h1><i class=" fa fa-shopping-cart"></i></h1></button>
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
        <div class="container">
            <div id="loginBt" class="modal">
                <div class="modal-content animate">
                    <jsp:include page="login.jsp"/>
                </div>
            </div>
            <%
                if (user != null) {
            %>
            <div id="cartBt" class="modal">
                <div class="modal-content animate" style="width: 75%">
                    <div class="row cartRow">
                        <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="p-2 px-3 text-uppercase">Book</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Price</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Quantity</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Remove</div>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody id="CartItemList">
                                    </tbody>
                                </table>
                            </div>
                            <!-- End -->
                        </div>
                    </div>
                    <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Payment</div>
                                <div class="p-4">
                                    <ul class="list-unstyled mb-4">
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Books Price</strong><strong id="priceofall">0</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Shipment</strong><strong>Free ship</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">VAT</strong><strong>10 $</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total Price</strong>
                                            <h5 class="font-weight-bold" id="priceoftotal">0</h5>
                                        </li>
                                    </ul><a href="buy" class="btn btn-dark rounded-pill py-2 btn-block">Checkout</a>
                                </div>
                            </div>
                </div>
            </div>
            <%
                }
            %>
            <div id="createBt" class="modal">
                <div class="modal-content animate">
                    <jsp:include page="createUser.jsp"/>
                </div>
            </div>
        </div>

        <div class="container main-body">
            <div class="ccateTab">
                <jsp:include page="CategoryTab.jsp"/>
            </div>
                <div class="row item-card" id="content">

                <c:forEach items="${BookList}" var="o">
                    <c:if test="${o.isStatus() == true}">
                        <div class="book">
                            
                            <div class="card" id="${o.getId()}">
                                <div class="frontcard">
                                    <img class="bookImg" src="${o.getImg()}" alt="Book Cover" style="width:100%">
                                </div>
                                <div class="backcard">
                                    <a class="bookTitle" href="detail?pid=${o.getId()}" title="View Product" class="title">${o.getName()}</a>
                                    <p class="bookQuantity">${o.getQuantity()} in stock</p>
                                    <p class="bookPub">Pub: ${o.getPub()}</p>
                                    <p style="display:none;" class="bookPrice">${o.getPrice()}</p>
                                    <p style="display:none;" class="bookID">${o.getId()}</p>
                                    
                                    <%
                                        if (user == null) {
                                    %>
                                    <p><button>Buy $${o.getPrice()} <br /> Login To Buy</button></p>
                                            <%
                                            } else {
                                            %>
                                    <button onclick="addtoCart(${o.getId()})">Buy $${o.getPrice()}</button>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                                
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </body>
    <script type="text/javascript" src="JavaScript/main.js"></script>
</html>

