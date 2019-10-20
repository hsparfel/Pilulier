<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- prescription2  -->
<c:if test="${ myPrescription2 != null }">
	<div id="prescription2">
		<div id="prescription2Ligne1" class="form-group row d-none">
			<label for="idMedicament2"
				class="col-1 col-form-label font-weight-lighter text-right">Medicament</label>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-eyedropper"></i>
						</div>
					</div>
					<select id="idMedicament2" name="idMedicament2" required="required"
						class="custom-select">
						<option value="${ myPrescription2.medicament.id }" selected>${ myPrescription2.medicament.nom }</option>
						<c:forEach items="${ listeMedicaments }" var="medicament">
							<c:if
								test="${ medicament.id != myPrescription2.medicament.id }">
								<option value="${ medicament.id }">${ medicament.nom }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<a href="EnregistrerMedicament"> <span
				class="fa fa-plus-square-o fa-2x plusMedicament2"></span>
			</a> <label for="quantiteDose2"
				class="col-1 col-form-label font-weight-lighter text-right">Dose</label>
			<div class="col-1">
				<input id="quantiteDose2" name="quantiteDose2" type="number"
					class="form-control" step="0.5" value="${ myPrescription2.nbDose }" min="0">
			</div>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-spoon"></i>
						</div>
					</div>
					<select id="idDose2" name="idDose2" class="custom-select"
						required="required">
						<option value="${ myPrescription2.dose.id }" selected>${ myPrescription2.dose.nom }</option>
						<c:forEach items="${ listeDoses }" var="dose">
							<c:if
								test="${ dose.id != myPrescription2.dose.id }">
								<option value="${ dose.id }">${ dose.nom }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<a href="EnregistrerDose"> <span
				class="fa fa-plus-square-o fa-2x plusDose2"></span>
			</a>
		</div>
		<div id="prescription2Ligne2" class="form-group row d-none">
			<label for="quantiteFrequence2"
				class="col-1 col-form-label font-weight-lighter text-right ">Fréquence</label>
			<div class="col-1">
				<input id="quantiteFrequence2" name="quantiteFrequence2"
					type="number" class="form-control" value="${ myPrescription2.nbFrequence }" min="0">
			</div>
			<label for="idFrequence2" class=" col-form-label ">fois par</label>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-clock-o"></i>
						</div>
					</div>
					<select id="idFrequence2" name="idFrequence2" required="required"
						class="custom-select">
						<option value="${ myPrescription2.frequence }" selected>${ myPrescription2.frequence.name }</option>
						<c:forEach items="${ listeFrequences }" var="frequence">
							<c:if
								test="${ frequence != myPrescription2.frequence }">
								<option value="${ frequence }">${ frequence.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>

			<div id="freqRadio2" class="col-2 form-group row d-none ">
				<div class="col-1">
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio2" id="frequenceRadio2_0" type="radio"
								class="custom-control-input" value="1"> <label
								for="frequenceRadio2_0" class="custom-control-label">Matin</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio2" id="frequenceRadio2_1" type="radio"
								class="custom-control-input" value="2"> <label
								for="frequenceRadio2_1" class="custom-control-label">Midi</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio2" id="frequenceRadio2_2" type="radio"
								class="custom-control-input" value="3"> <label
								for="frequenceRadio2_2" class="custom-control-label">Soir</label>
						</div>
					</div>
				</div>
			</div>
			<div id="freqCheck2" class="col-2 form-group row d-none">
				<div class="col-1">
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox2_0" id="frequenceCheckbox2_0"
								type="checkbox" class="custom-control-input" value="1">
							<label for="frequenceCheckbox2_0" class="custom-control-label">Matin</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox2_1" id="frequenceCheckbox2_1"
								type="checkbox" class="custom-control-input" value="2">
							<label for="frequenceCheckbox2_1" class="custom-control-label">Midi</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox2_2" id="frequenceCheckbox2_2"
								type="checkbox" class="custom-control-input" value="3">
							<label for="frequenceCheckbox2_2" class="custom-control-label">Soir</label>
						</div>
					</div>
				</div>
			</div>
			<label class="col-1 col-form-label text-right ">pendant</label>
			<div class="col-1">
				<input id="nbDuree2" name="nbDuree2" type="number"
					class="form-control" required="required" value="${ myPrescription2.nbDuree }" min="0">
			</div>
			<div class="col-2">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-calendar-plus-o"></i>
						</div>
					</div>
					<select id="idDuree2" name="idDuree2" class="custom-select"
						required="required">
						<option value="${ myPrescription2.duree }" selected>${ myPrescription2.duree.name }</option>
						<c:forEach items="${ listeDurees }" var="duree">
							<c:if
								test="${ duree != myPrescription2.duree }">
								<option value="${ duree }">${ duree.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="form-group row">
			<c:if test="${ myPrescription2.commentaire == null }">
				<button type="button" id="ajouterCommentairePrescription2"
					class="col-sm-2  btn btn-default btn-sm text-warning text-left d-none">+
					Commentaire</button>
			</c:if>
			<c:if test="${ myPrescription2.commentaire != null }">
				<div id="commentPrescription2" class="col-5 d-none">
					<div class="input-group">
						<div class="input-group-prepend">
							<div class="input-group-text">
								<i class="fa fa-pencil"></i>
							</div>
						</div>
						<textarea id="commentairePrescription2"
							name="commentairePrescription2" class="form-control "
							maxlength="300">${ myPrescription2.commentaire }</textarea>
						<button type="button" name="btnMoins"
							id="enleverCommentairePrescription2"
							class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
					</div>
				</div>
			</c:if>
			<div id="recapPrescription2" class="col-7 ">${ myPrescription2.medicament.nom }
				- ${ myPrescription2.nbDose } ${ myPrescription2.dose.nom }, ${ myPrescription2.nbFrequence }
				fois par ${ myPrescription2.frequence.name}
				<c:if
					test="${ myPrescription2.matin==1 || myPrescription2.midi==1 ||myPrescription2.soir==1 }">
						( <c:if test="${ myPrescription2.matin ==1}">matin</c:if>
					<c:if test="${ myPrescription2.midi ==1}">midi</c:if>
					<c:if test="${ myPrescription2.soir ==1}">soir</c:if> )</c:if>
				pendant ${ myPrescription2.nbDuree} ${ myPrescription2.duree.name}
			</div>
			<div class="col-3 ">
				<button type="button" id="btnValiderPrescription2"
					class="btn  btn-sm btn-outline-success d-none">Enregistrer</button>
				<button type="button" id="btnModifierPrescription2"
					class="btn  btn-sm btn-outline-success btnMasques d-none">Modifier</button>
				<button type="button" id="btnAnnulerPrescription2"
					class="btn  btn-sm btn-outline-danger d-none">Annuler</button>
				<button type="button" id="btnSupprimerPrescription2"
					class="btn  btn-sm btn-outline-danger btnMasques d-none">Supprimer</button>
			</div>
		</div>
		<hr>
	</div>
</c:if>
<!-- fin prescription2 -->


