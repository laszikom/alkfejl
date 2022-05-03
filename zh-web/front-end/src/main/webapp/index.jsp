<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>F1 Championship Administrator</title>
</head>

<body>
<jsp:include page="/ChampionshipController"/>
<h2>${requestScope.title}</h2>

<div class="container">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Country</th>
            <th scope="col">Date</th>
            <th scope="col">Spectators</th>
            <th scope="col">Controls</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${requestScope.races}">
            <tr>
                <td>${item.name}</td>
                <td>${item.country}</td>
                <td>${item.date}</td>
                <td>${item.guests}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/UpdateController?id=${item.ID}">Update</a>
                    <a href="${pageContext.request.contextPath}/DeleteController?id=${item.ID}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<form action="${pageContext.request.contextPath}/index.jsp" method="get">
    <input type="checkbox" id="needPastRaces" name="needPastRaces" value="True">
    <label for="needPastRaces">Previous Races</label><br>

    <button value="List Races" type="submit">List Races</button>
</form>
<br>

<a href="new.jsp">Add new Race</a>
</body>
</html>
