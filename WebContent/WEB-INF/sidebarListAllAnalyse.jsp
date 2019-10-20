<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listeAnalyses }">
	<div class="list-group">
		<div class="list-group-item active">Liste des analyses</div>
		<c:forEach items="${ listeAnalyses }" var="analyse">
			<a href="AfficherAnalyse?id=${ analyse.id }" class="list-group-item">${ analyse.nom }</a>
		</c:forEach>
	</div>
</c:if>

