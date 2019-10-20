<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listeExamens }">
	<div class="list-group">
		<div class="list-group-item active">Liste des examens</div>
		<c:forEach items="${ listeExamens }" var="examen">
			<a href="AfficherExamen?id=${ examen.id }" class="list-group-item">${ examen.nom }</a>
		</c:forEach>
	</div>
</c:if>

