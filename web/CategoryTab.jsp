<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
    <ul class="list-group category_block">
        <li class="list-group-item text-white">
        <button class="looklikelink" value="" onclick="searchByName(this)">All Book</button>
        </li>
        <c:forEach items="${CateList}" var="o">
            <li class="list-group-item text-white">
                <button class="looklikelink" value="${o.getName()}" onclick="searchByName(this)">${o.getName()}</button>
        </c:forEach>
    </ul>