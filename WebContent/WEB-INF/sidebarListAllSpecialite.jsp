<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listeSpecialites }">
	<div class="list-group">
		<div class="list-group-item active">Liste des specialites</div>
		<c:forEach items="${ listeSpecialites }" var="specialite">
			<a href="AfficherSpecialite?id=${ specialite.id }" class="list-group-item">${ specialite.nom }</a>
		</c:forEach>
	</div>
</c:if>

