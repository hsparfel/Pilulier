<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="list-group">
	<c:if test="${!empty listeExamens }">
		<button id="listBtnExamen" class="list-group-item active">Mes
			Examens</button>
		<div id="listExamen">
			<c:forEach items="${ listeExamens }" var="examen">
				<a href="AfficherExamen?id=${ examen.id }" class="list-group-item">${ examen.nom.name}
					- ${ examen.date} <br>${ examen.cabinet.nom}</a>
			</c:forEach>
		</div>
	</c:if>
</div>
<br>
