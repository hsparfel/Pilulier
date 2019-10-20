<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="list-group">
	<c:if test="${!empty listeAnalyses }">
		<button id="listBtnAnalyse" class="list-group-item active">Mes
			Analyses</button>
		<div id="listAnalyse">
			<c:forEach items="${ listeAnalyses }" var="analyse">
				<a href="AfficherAnalyse?id=${ analyse.id }" class="list-group-item">${ analyse.nom.name}
					- ${ analyse.date} <br>${ analyse.cabinet.nom}</a>
			</c:forEach>
		</div>
	</c:if>
</div>
<br>
