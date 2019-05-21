<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:if test="${!empty listePrescriptions }">
	<div class="list-group">
		<div class="list-group-item active">Liste de mes prescriptions</div>
		<c:forEach items="${ listePrescriptions }" var="prescription">
			<a href="AfficherPrescription?id=${ prescription.id }"
				class="list-group-item">${ prescription.medicament.nom }- ${ prescription.nbDose }
				${ prescription.dose.nom }, ${ prescription.nbFrequence } fois par
				${ prescription.frequence.nom}
				<c:if test="${ prescription.matin==1 || prescription.midi==1 ||prescription.soir==1 }"><br>( <c:if test="${ prescription.matin ==1}">matin</c:if> <c:if test="${ prescription.midi ==1}">midi</c:if> <c:if test="${ prescription.soir ==1}">soir</c:if> )</c:if>
				<br>
				 du ${ prescription.dateDebut} au ${ prescription.dateFin}</a>
		</c:forEach>
	</div>
</c:if>
