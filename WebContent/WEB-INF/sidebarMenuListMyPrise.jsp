<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="list-group">
	<c:if test="${!empty listePrises }">
		<button id="listBtnPrise" class="list-group-item active">Mes
			prises du Jour</button>
		<div id="listPrise">
			<c:forEach items="${ listePrises }" var="prise">

				<c:if test="${prise.effectue }">
					<button id="Prise${ prise.id }"
						class="list-group-item btn-block btn-prise text-left disabled">
						<div class="fa fa-check fa-2x"></div>
						${ prise.prescription.nbDose} ${ prise.prescription.dose.nom} ${ prise.prescription.medicament.nom}
						- ${ prise.heure}
					</button>
					<form action="ModifUserProfilAction" method="post" class="d-none">
						<input name="idSubmit" type="text" value="${ prise.id }" /> <input
							id="btnSubmitPrise${ prise.id }" name="submit"
							value=${ prise.id } type="submit" class="btn btn-outline-danger " />
					</form>
				</c:if>
				<c:if test="${!prise.effectue }">
					<button id="Prise${ prise.id }"
						class="list-group-item btn-block btn-prise text-left">
						<div class="fa fa-bell-o fa-2x"></div>
						${ prise.prescription.nbDose} ${ prise.prescription.dose.nom} ${ prise.prescription.medicament.nom}
						- ${ prise.heure}
					</button>
					<form action="ModifUserProfilAction" method="post" class="d-none">
						<input name="idSubmit" type="text" value="${ prise.id }" /> <input
							id="btnSubmitPrise${ prise.id }" name="submit"
							value=${ prise.id } type="submit" class="btn btn-outline-danger " />
					</form>
				</c:if>

			</c:forEach>
		</div>
	</c:if>
</div>

