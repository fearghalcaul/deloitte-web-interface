<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style><%@include file="/WEB-INF/views/css/home.css"%></style>


<body>
<div class = "center">
	<div>
		<form method="post" action="/add">
			<input name="username" type="hidden" value="${requestScope.username}"></input>
			<input name="password" type="hidden" value="${requestScope.password}"></input>
			<input type="text" name="task" placeholder="your task" value="${requestScope.task}"/>
			<input type="submit" value="Add task"/>
		</form> 
	</div>
	
	<div>
		<c:forEach items="${requestScope.tasks}" var="item">
		<div class = "center2">
		    ${item.description}<br>
		    <div>
				<form method="post" action="/update">
					<input name="description" type="hidden" value="${item.description}"></input>
					<input name="username" type="hidden" value="${requestScope.username}"></input>
					<input name="password" type="hidden" value="${requestScope.password}"></input>
					<input name="password" type="hidden" value="${item.description}"></input>
			              <c:choose>
			                 <c:when test="${item.checked=='on'}">
			                   <input type="checkbox" id="chk" name="checkbox" value="${item.checked}" checked="checked"/>
			                 </c:when>
			                 <c:otherwise>
			                     <input type="checkbox" id="chk" name="checkbox" value="${item.checked}"/>
			                 </c:otherwise>
			        </c:choose>
					<input type="submit" value="Update"/>
				</form>
			</div>
			<div>
				<form method="post" action="/delete">
					<input name="username" type="hidden" value="${requestScope.username}"></input>
					<input name="password" type="hidden" value="${requestScope.password}"></input>
					<br>
					${item.date}
					<input name="task" type="hidden" value="${item.description}"></input>
					<input type="submit" value="Delete"/>
				</form> 
			</div>
				</div>
		</c:forEach>
	</div>

</div>
</body>