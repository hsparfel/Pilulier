<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listeRdvs }">
	<div class="list-group">
		<button id="listBtnRdv"  class="list-group-item active">Mes rdvs</button>
		<div id="listRdv">
		<c:forEach items="${ listeRdvs }" var="rdv">
			<a href="AfficherRdv?id=${ rdv.id }" class="list-group-item">${ rdv.getMedecin().getNom()}-
				${ rdv.getDate()} à ${ rdv.getHeure()}<br>${ rdv.getMedecin().getCabinet().getNom()} </a>
		</c:forEach>
	</div>
	</div>
</c:if>

