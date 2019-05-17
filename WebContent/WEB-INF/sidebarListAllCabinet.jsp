<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listeCabinets }">
	<div class="list-group">
		<div class="list-group-item active">Liste des cabinets</div>
		<c:forEach items="${ listeCabinets }" var="cabinet">
			<a href="AfficherCabinet?id=${ cabinet.id }" class="list-group-item">${ cabinet.nom }<br>${ cabinet.adresse },
				${ cabinet.ville }
			</a>
		</c:forEach>
	</div>
</c:if>

