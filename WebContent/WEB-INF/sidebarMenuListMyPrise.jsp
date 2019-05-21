<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${!empty listePrises }">
	<div class="list-group">
		<button id="listBtnPrise"  class="list-group-item active">Mes prises</button>
		<div id="listPrise">
		<c:forEach items="${ listePrises }" var="prise">
			<a href="AfficherPrise?id=${ prise.id }" class="list-group-item">${ prise.medicamentm.nom}-
				${ prise.datePrise} à ${ prise.heurePrise}</a>
		</c:forEach>
	</div>
	</div>
</c:if>

