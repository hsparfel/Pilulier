<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listeFrequences }">
	<div class="list-group">
		<div class="list-group-item active">Liste des frequences</div>
		<c:forEach items="${ listeFrequences }" var="frequence">
			<a href="AfficherFrequence?id=${ frequence.id }" class="list-group-item">${ frequence.nom }</a>
		</c:forEach>
	</div>
</c:if>

