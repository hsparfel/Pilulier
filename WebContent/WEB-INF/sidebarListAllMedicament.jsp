<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listeMedicaments }">
	<div class="list-group">
		<div class="list-group-item active">Liste des medicaments</div>
		<c:forEach items="${ listeMedicaments }" var="medicament">
			<a href="AfficherMedicament?id=${ medicament.id }" class="list-group-item">${ medicament.nom }</a>
		</c:forEach>
	</div>
</c:if>

