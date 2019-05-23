<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="list-group">
	<button id="listBtnPrise" class="list-group-item active">Mes
		prises du Jour</button>
	<c:if test="${!empty listePrises }">
		<div id="listPrise">
			<c:forEach items="${ listePrises }" var="prise">
				<c:if
					test="${ prise.date==dateDuJour && prise.prescription.utilisateur.getNom()==login }">
					<a href="AfficherPrise?id=${ prise.id }" class="list-group-item">${ prise.prescription.medicament.nom}-
						${ prise.date} à ${ prise.heure}</a>
				</c:if>
			</c:forEach>
		</div>
	</c:if>
</div>

