<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="list-group">
	<button id="listBtnRdv" class="list-group-item active">Mes
		rendez-vous</button>
	<c:if test="${!empty listeRdvs }">
		<div id="listRdv">
			<c:forEach items="${ listeRdvs }" var="rdv">
				<a href="AfficherRdv?id=${ rdv.id }" class="list-group-item">${ rdv.medecin.nom} -
					${ rdv.date} à ${ rdv.heure}<br>${ rdv.medecin.cabinet.nom}</a>
			</c:forEach>
		</div>
	</c:if>
</div>
<br>
