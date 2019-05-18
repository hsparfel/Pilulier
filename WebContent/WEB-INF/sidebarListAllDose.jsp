<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listeDoses }">
	<div class="list-group">
		<div class="list-group-item active">Liste des doses</div>
		<c:forEach items="${ listeDoses }" var="dose">
			<a href="AfficherDose?id=${ dose.id }" class="list-group-item">${ dose.nom }</a>
		</c:forEach>
	</div>
</c:if>

