<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- <jsp:useBean id="kisallatok" class="hu.alkfejl.model.bean.Kisallat" scope="request"/> --%>

<html>
<head>
    <title>Kisallatok</title>
</head>

<body>
<jsp:include page="ListController"/>
<h2>Kisállatok</h2>
<div class="container">
    <table border="2">
        <tr>
            <th>Nev</th>
            <th>Fajta</th>
            <th>Kor</th>
            <th>Modosit</th>
        </tr>
        <c:forEach var="item" items="${requestScope.kisallatok}">
            <tr>
                <td>${item.nev}</td>
                <td>${item.fajta}</td>
                <td>${item.kor}</td>
                <td><a href="${pageContext.request.contextPath}/UpdateController?id=${item.id}">Frissit</a></td>
            </tr>
        </c:forEach>
    </table>
    <form action="index.jsp" method="GET">
        <input type="text" name="allatSzures">
        <button value="Szures" type="submit">Szures</button>
    </form>
    <br/>
    <form action = "uj_allat.jsp" method="GET">
        <button value="Uj allat" type="submit">Uj allat</button>
    </form>
</div>
</body>
</html>
