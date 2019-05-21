<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listePrescriptions }">
	<div class="list-group">
		<button id="listBtnPrescription"  class="list-group-item active">Mes prescriptions</button>
		<div id="listPrescription">
		<c:forEach items="${ listePrescriptions }" var="prescription">
			<a href="AfficherPrescription?id=${ prescription.id }" class="list-group-item">${ prescription.medicament.nom }
									- ${ prescription.nbDose } ${ prescription.dose.nom }, ${ prescription.nbFrequence }
									fois par ${ prescription.frequence.nom} du ${ prescription.dateDebut} au ${ prescription.dateDebut}</a>
		</c:forEach>
	</div>
	</div>
</c:if>

