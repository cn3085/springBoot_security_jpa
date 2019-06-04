<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri = "http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>home.jsp</h2>
	<hr>
	
	<div>message : ${message}</div>
	<hr>
	<c:if test="${pageContext.request.userPrincipal.name !=null}">
		welcome ${pageContext.request.userPrincipal.name}!<br>
		<!-- 스프링의 form 태그를 이용해서 form으로 logout 요청을 보낸다. 그러면 CSRF를 히든으로 추가해서 스프링이 logout을 처리해준다 -->
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="logout" />
		</form:form>
	</c:if>
	<hr>
	
	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<a href="<c:url value="/login"/>">login</a>
	</c:if>
	
</body>
</html>