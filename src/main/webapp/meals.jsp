<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<title>Meals list</title>
</head>
<body>
<div>
	<h2><a href="index.html">Home</a></h2>
</div>

<div>
	<h2>User list</h2>
</div>

<table>
	<c:forEach items="${meals}" var="meal">
		<c:if test="${meal.isExceed()}">
			<tr style="background-color: red;">
		</c:if>

		<c:if test="${!meal.isExceed()}">
			<tr style="background-color: green;">
		</c:if>

		<td>${meal.getId()}</td>

		<c:set var="cleanedDateTime" value="${fn:replace(meal.getDateTime(), 'T', ' ')}"/>
		<td>${cleanedDateTime}</td>

		<td>${meal.getDescription()}</td>
		<td>${meal.getCalories()}</td>

		</tr>
	</c:forEach>
</table>

<hr>

<form method="post">
	ID: <input type="text" name="id"/>
	New description: <input type="text" name="desc"/>
	<input type="submit" value="Update"/>
</form>

</body>
</html>
