<%@page import="dao.userDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        userDTO user = (userDTO) session.getAttribute("LOGIN_USER");
        if (user == null || !user.getRoleID().equalsIgnoreCase("US")) {
    %>
    <script>
        window.location.replace("/BookShop/home");
    </script>
    <%
    } else {
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div id="cartBodyHere">
            <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Delivery Information</div>
            <div class="container">
                <form id="billingAddr" method="POST" action="TotalCheckOutController">
                    <p id="uName" hidden=""> <%=user.getName()%> </p>
                    <h4 id="uPhone"> User Phone Number (10 number): <input id="iPhone" type="tel" name="phoneBill" pattern="[0-9]{10}" value="<%=user.getPhone()%>"></h4>
                    <p hidden id="uHidPhone"><%=user.getPhone()%></p>
                    <h4 id="uAddr"> User Address: <input id="iAddr" type="text" name="addrBill" value="<%=user.getAddr()%>"></input> </h4>
                </form>
            </div>
            <div id="thisCartContain">
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
                            <div id="emptyCar" class="col-sm-12 empty-cart-cls text-center"> <img src="https://i.imgur.com/dCdflKN.png" width="130" height="130" class="img-fluid mb-4 mr-3">
                                <h3><strong>Your Cart is Empty</strong></h3>
                                <h4>Add something to make me happy :)</h4> <button id="conShop" data-dismiss="modal" class="btn btn-primary cart-btn-transform m-3" data-abc="true">Continue shopping</button>
                            </div>
                        </div>
                        <!-- End -->
                    </div>
                </div>
                <div class="cartPayment">
                    <div class="Vouncher">
                        <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Voucher</div>
                        <div class="p-4">
                            <div class="input-group mb-4 border rounded-pill p-2">
                                <input id="vouncherInput" type="text" placeholder="Enter Vouncher" aria-describedby="button-addon3" class="form-control border-0">
                                <div class="input-group-append border-0">
                                    <button onclick="useVouncher()" id="button-addon3" type="button" class="btn btn-dark px-4 rounded-pill"><i class="fa fa-gift mr-2"></i>Apply</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="height: fit-content; text-align: -webkit-center;" class="Paypal" style="text-align: -webkit-center;"><jsp:include page="/WEB-INF/paypal.jsp"/></div>
                    <div class="total" style="height: fit-content;">
                        <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Payment</div>
                        <div class="p-4">
                            <ul class="list-unstyled mb-4" style="line-height: 5;">
                                <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Books Price</strong><strong id="priceofall">0</strong></li>
                                <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Shipment</strong><strong>Free ship</strong></li>
                                <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">VAT</strong><strong>10%</strong></li>
                                <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total Price</strong>
                                    <h5 class="font-weight-bold" id="priceoftotal">0</h5>
                                </li>
                            </ul><button class="btn btn-dark rounded-pill py-2 btn-block" style="height: 60px;" id="BuBt" onclick="checkBilling()">Checkout</button>
                        </div>
                    </div>

                </div>
                <style>
                    .cartPayment {
                        display: grid;
                        grid-template-columns: repeat(5, 1fr);
                        grid-template-rows: repeat(4, 1fr);
                        grid-column-gap: 0px;
                        grid-row-gap: 0px;
                    }

                    .Vouncher { grid-area: 1 / 1 / 2 / 4; }
                    .Paypal { grid-area: 2 / 1 / 6 / 4; }
                    .total { grid-area: 1 / 4 / 6 / 6; }
                </style>
                <form action="TotalCheckOutController">
                    <input type="hidden" name="myField" id="thisBookField" value=""/>
                </form>
            </div>
        </div>

    </body>
    <%
        }
    %>
</html>