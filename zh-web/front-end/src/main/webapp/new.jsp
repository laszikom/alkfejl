<%@ page import="model.Race" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>${requestScope.title}</title>
</head>
<body>

<jsp:useBean id="race" class="model.Race" scope="request"/>

<form action="${pageContext.request.contextPath}/ChampionshipController" method="post">
    <input type="hidden" name="id" value="${race.ID}"/>

    <div class="form-group">
        <label for="name">Name</label>
        <input required type="text" class="form-control" name="name" id="name" placeholder="Enter Race Name" value="${race.name}"/>
    </div>

    <div class="form-group">
        <label for="country">Country</label>
        <input required type="text" class="form-control" name="country" id="country" placeholder="Enter Country" value="${race.country}"/>
    </div>

    <div class="form-group">
        <label for="date">Date</label>
        <input required type="date" class="form-control" name="date" id="date" placeholder="Race Date" value="${race.date}"/>
    </div>

    <div class="form-group">
        <label for="guests">Number of Spectators</label>
        <c:choose>
            <c:when test="${race.ID == 0}">
                <input type="text" class="form-control" name="guests" id="guests" placeholder="No. of Guests" value="${race.guests}"
                       disabled/>
            </c:when>
            <c:otherwise>
                <input type="text" class="form-control" name="guests" id="guests" placeholder="No. of Guests" value="${race.guests}"/>
            </c:otherwise>
        </c:choose>
    </div>

    <button value="Save" type="submit">Save</button>
</form>
</body>
</html>
