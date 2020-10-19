<%-- 
    Document   : shoppinglist
    Created on : Oct 18, 2020, 7:24:47 PM
    Author     : 818396
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        
        <p>Hello, ${name}</p> <a href="/Week05Lab/shoppingList?action=logout">Log out</a>
        
        
        <h1>List</h1>
        
         <form method="post" action="">
            Add item: <input type="text" name="addItem" value=""/>
            <input type="submit" value="Add"/>
            <input type="hidden" name="action" value="add"/>
        </form>
        
        
        <form method="post" action="">
            <c:forEach items="${itemList}" var="item">
                <label><input type="radio" name="item" value="${item}">${item}</label><br>
            </c:forEach>
            <c:if test="${fn:length(itemList) > 0}">
            <label><input type="submit" value="delete"/></label>
            <label><input type="hidden" name="action" value="delete"/></label>
            </c:if>
        </form>
        
    </body>
</html>
