<%-- 
    Document   : addBook
    Created on : Jun 23, 2021, 9:47:39 PM
    Author     : Dokk
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>-->
        <jsp:useBean id="dao" class="dao.bookDAO"></jsp:useBean>
            <script>
                $(document).ready(function () {
                    $('[data-toggle="tooltip"]').tooltip();
                });

                $(function () {
                    $(document).on('click', 'a[data-role=update]', function () {
                        $("#Edite").remove();
                        var id = $(this).data('id');
                        var name = $('#' + id).children('td[data-target=name]').text();
                        var price = $('#' + id).children('td[data-target=price]').text();
                        var quantity = $('#' + id).children('td[data-target=quantity]').text();
                        var avail = $('#' + id).children('td[data-target=status]').text();
                        var img = $('#' + id).children('td[data-target=img]').text();
                        var cover = $('#' + id).children('td[data-target=cover]').text();
                        var pen = $('#' + id).children('td[data-target=pen]').text();
                        var pub = $('#' + id).children('td[data-target=pub]').text();
                        var write = $('#' + id).children('td[data-target=write]').text();
                        var cateID = $('#' + id).children('td[data-target=cateID]').text();
                        var dercs = $('#' + id).children('td[data-target=dercs]').text();
                        $('#id').val(id);
                        $('#bookname').val(name);
                        $('#price').val(price);
                        $('#quantity').val(quantity);
                        if (avail.trim() == "Active") {
                            $('#avail').prop('checked', true);
                        }
                        if (avail.trim() != "Active") {
                            $('#avail').prop('checked', false);
                        }
                        $('#img').val(img);
                        $('#cover').val(cover);
                        $('#pen').val(pen);
                        $('#pub').val(pub);
                        $('#write').val(write);
                        $('#cateID').val(cateID);
                        $('#dercs').val(dercs);
                    });
                });



                function loadEdit() {
                    $.ajaxSetup({
                        cache: false
                    });
                    var value = $('#EditForm').serialize();
                    $.ajax({
                        url: "/BookShop/EditController",
                        type: "post", //send it through get method
                        data: value,
                        success: function (data) {
                            var row = document.getElementById("editContent");
                            row.innerHTML = data;
                            var f = document.getElementById("editedSpan");
                            f.innerHTML = "<a id='Edite' style='color:green'>Updated!</a>";
                        },
                        error: function (xhr) {
                        }
                    });
                }
            </script>
            <link rel="stylesheet" href="css/add.css">
            <title>Edit Book</title>
        </head>
        <body>
            <div class="container-xl addBody">
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-6">
                                    <h2>Manage <b>Book</b></h2>
                                </div>
                                <div class="col-sm-6 btgr">
                                    <a href="/BookShop/home" class="btn btn-danger"><i class="material-icons">&#xE15C;</i> <span>Reload ALL DATA</span></a>
                                    <a href="#editBookModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Book</span></a>					
                                </div>
                            </div>
                        </div>
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Status</th>
                                    <th>ID</th>
                                    <th>Book Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Category</th>
                                    <th>Published</th>
                                    <th>Writer</th>
                                    <th>Cover</th>
                                    <th>Penciler</th>
                                    <td>Action</td>
                                </tr>
                            </thead>
                            <tbody id="editContent">
                            <c:forEach items="${BookList}" var="b">
                                <tr id="${b.getId()}">
                                    <td data-target = "status">
                                        <c:if test="${b.isStatus() == 'true'}"><p style="color:green">Active</p></c:if>
                                        <c:if test="${b.isStatus() == 'false'}"><p style="color:red">Inactive</p></c:if>
                                        </td>
                                        <td data-target = "id">${b.getId()}</td>
                                    <td data-target = "name">${b.getName()}</td>
                                    <td data-target = "price">${b.getPrice()}</td>
                                    <td data-target = "quantity">${b.getQuantity()}</td>
                                    <td data-target = "cateID">${b.getCateID()}</td>
                                    <td hidden data-target = "img"><p hidden>${b.getImg()}</p></td>
                                    <td hidden data-target = "dercs"><p hidden>${b.getDercs()}</p></td>
                                    <td data-target = "pub">${b.getPub()}</td>
                                    <td data-target = "write">${b.getWrite()}</td>
                                    <td data-target = "cover">${b.getCover()}</td>
                                    <td data-target = "pen">${b.getPen()}</td>
                                    <td>
                                        <a href="#editBookModal" data-role="update" data-id="${b.getId()}" id="EditButtonStart" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>        
        </div>
        <!-- Edit Modal HTML -->
        <div id="editBookModal" class="modal fade">
            <div class="modal-dialog editbox" style="display: flex;">
                <div class="modal-content editContent">
                    <form id="EditForm">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Book</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Book ID</label>
                                <input type="text" class="form-control" id="id" name="id" required>
                            </div>
                            <div class="form-group">
                                <label>Book Name</label>
                                <input type="text" class="form-control" id="bookname" name="name" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input type="number" class="form-control" id="price" name="price" required>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input type="number" class="form-control" id="quantity" name="quantity" required>
                            </div>
                            <div class="form-group">
                                <label>Category</label>
                                <input type="text" class="form-control" id="cateID" name="cateID" required>
                            </div>
                            <div class="form-group">
                                <label>Available</label>
                                <input type="checkbox" class="form-control" id="avail" name="status">
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input type="text" class="form-control" id="img" name="img" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <input type="text" class="form-control" id="dercs" name="descr">
                            </div>
                            <div class="form-group">
                                <label>Published</label>
                                <input type="text" class="form-control" id="pub" name="pub">
                            </div>
                            <div class="form-group">
                                <label>Writer</label>
                                <input type="text" class="form-control" id="write" name="write">
                            </div>
                            <div class="form-group">
                                <label>Cover</label>
                                <input type="text" class="form-control" id="cover" name="cover">
                            </div>
                            <div class="form-group">
                                <label>Penciler</label>
                                <input type="text" class="form-control" id="pen" name="pen">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" onclick="loadEdit();" class="btn btn-info" id="SaveEdit" value="Save"/>
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <span id="editedSpan" style="text-align: center"></span>
                        </div>
                    </form>
                </div>  
            </div>
        </div>
    </body>
</html>
