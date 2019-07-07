<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listeUtilisateurs }">
	<div class="list-group">
		<div class="list-group-item active">Liste des utilisateurs</div>
		<c:forEach items="${ listeUtilisateurs }" var="utilisateur">
			<a href="Accueil" class="list-group-item">${ utilisateur.nom }</a>
		</c:forEach>
	</div>
</c:if>

