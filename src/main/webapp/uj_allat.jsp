<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kisallatok</title>
</head>
<body>
<c:choose>
    <c:when test="${kisallat.id == null}">
        <h2>Uj allat felvetele</h2>
    </c:when>
    <c:otherwise>
        <h2>${kisallat.nev} modositasa</h2>
    </c:otherwise>
</c:choose>

<form action="SaveController" method="get">
    <input type="hidden" name="id" value="${kisallat.id}"/>
    <table>
        <tr>
            <td>Nev</td>
            <td><input type="text" name="nev" value="${kisallat.nev}" required></td>
        </tr>
        <tr>
            <td>Fajta</td>
            <%--<td><input type="text" name="fajta" value="${kisallat.fajta}" required></td>--%>
            <td>
                <select name="fajta">
                    <option value="Kutya">Kutya</option>
                    <option value="Macska">Macska</option>
                    <option value="Tengerimalac">Tengerimalac</option>
                    <option value="Egyeb">Egyeb</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Kor</td>
            <td><input type="number" name="kor" value="${kisallat.kor}" required></td>
        </tr>
    </table>
    <button value="Mentes" type="submit">Mentes</button>
</form>

</body>
</html>
