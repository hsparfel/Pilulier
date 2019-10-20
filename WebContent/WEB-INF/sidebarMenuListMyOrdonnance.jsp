<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="list-group">
	<c:if test="${!empty listeOrdonnances }">
		<button id="listBtnOrdonnance" class="list-group-item active">Mes
			ordonnances</button>
		<div id="listOrdonnance">
			<c:forEach items="${ listeOrdonnances }" var="ordonnance">
				<a href="AfficherOrdonnance?id=${ ordonnance.id }"
					class="list-group-item listeOrdonnance">${ ordonnance.medecin.nom} - ${ ordonnance.date}
					</a>
			</c:forEach>
		</div>
	</c:if>
</div>
<br>
