<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listeDurees }">
	<div class="list-group">
		<div class="list-group-item active">Liste des durees</div>
		<c:forEach items="${ listeDurees }" var="duree">
			<a href="AfficherDuree?id=${ duree.id }" class="list-group-item">${ duree.nom }</a>
		</c:forEach>
	</div>
</c:if>

