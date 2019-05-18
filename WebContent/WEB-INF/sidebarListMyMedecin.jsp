<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listeMedecins }">
	<div class="list-group">
		<button id="listBtnMedecin"  class="list-group-item active">Mes medecins</button>
		<div id="listMedecin">
		<c:forEach items="${ listeMedecins }" var="medecin">
			<a href="AfficherMedecin?id=${ medecin.id }" class="list-group-item">${ medecin.getNom()}-
				${ medecin.getSpecialite().getNom()}<br>${ medecin.getCabinet().getNom()} </a>
		</c:forEach>
	</div>
	</div>
</c:if>

