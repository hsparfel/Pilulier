<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- prescription4  -->
<c:if test="${ myPrescription4 != null }">
	<div id="prescription4">
		<div id="prescription4Ligne1" class="form-group row d-none">
			<label for="idMedicament4"
				class="col-1 col-form-label font-weight-lighter text-right">Medicament</label>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-eyedropper"></i>
						</div>
					</div>
					<select id="idMedicament4" name="idMedicament4" required="required"
						class="custom-select">
						<option value="${ myPrescription4.medicament.id }" selected>${ myPrescription4.medicament.nom }</option>
						<c:forEach items="${ listeMedicaments }" var="medicament">
							<c:if
								test="${ medicament.id != myPrescription4.medicament.id }">
								<option value="${ medicament.id }">${ medicament.nom }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<a href="EnregistrerMedicament"> <span
				class="fa fa-plus-square-o fa-2x plusMedicament4"></span>
			</a> <label for="quantiteDose4"
				class="col-1 col-form-label font-weight-lighter text-right">Dose</label>
			<div class="col-1">
				<input id="quantiteDose4" name="quantiteDose4" type="number"
					class="form-control" step="0.5" value="${ myPrescription4.nbDose }" min="0">
			</div>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-spoon"></i>
						</div>
					</div>
					<select id="idDose4" name="idDose4" class="custom-select"
						required="required">
						<option value="${ myPrescription4.dose.id }" selected>${ myPrescription4.dose.nom }</option>
						<c:forEach items="${ listeDoses }" var="dose">
							<c:if
								test="${ dose.id != myPrescription4.dose.id }">
								<option value="${ dose.id }">${ dose.nom }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<a href="EnregistrerDose"> <span
				class="fa fa-plus-square-o fa-2x plusDose4"></span>
			</a>
		</div>
		<div id="prescription4Ligne2" class="form-group row d-none">
			<label for="quantiteFrequence4"
				class="col-1 col-form-label font-weight-lighter text-right ">Fréquence</label>
			<div class="col-1">
				<input id="quantiteFrequence4" name="quantiteFrequence4"
					type="number" class="form-control" value="${ myPrescription4.nbFrequence }" min="0">
			</div>
			<label for="idFrequence4" class=" col-form-label ">fois par</label>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-clock-o"></i>
						</div>
					</div>
					<select id="idFrequence4" name="idFrequence4" required="required"
						class="custom-select">
						<option value="${ myPrescription4.frequence }" selected>${ myPrescription4.frequence.name }</option>
						<c:forEach items="${ listeFrequences }" var="frequence">
							<c:if
								test="${ frequence != myPrescription4.frequence }">
								<option value="${ frequence }">${ frequence.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>

			<div id="freqRadio4" class="col-2 form-group row d-none ">
				<div class="col-1">
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio4" id="frequenceRadio4_0" type="radio"
								class="custom-control-input" value="1"> <label
								for="frequenceRadio4_0" class="custom-control-label">Matin</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio4" id="frequenceRadio4_1" type="radio"
								class="custom-control-input" value="2"> <label
								for="frequenceRadio4_1" class="custom-control-label">Midi</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio4" id="frequenceRadio4_2" type="radio"
								class="custom-control-input" value="3"> <label
								for="frequenceRadio4_2" class="custom-control-label">Soir</label>
						</div>
					</div>
				</div>
			</div>
			<div id="freqCheck4" class="col-2 form-group row d-none">
				<div class="col-1">
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox4_0" id="frequenceCheckbox4_0"
								type="checkbox" class="custom-control-input" value="1">
							<label for="frequenceCheckbox4_0" class="custom-control-label">Matin</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox4_1" id="frequenceCheckbox4_1"
								type="checkbox" class="custom-control-input" value="2">
							<label for="frequenceCheckbox4_1" class="custom-control-label">Midi</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox4_2" id="frequenceCheckbox4_2"
								type="checkbox" class="custom-control-input" value="3">
							<label for="frequenceCheckbox4_2" class="custom-control-label">Soir</label>
						</div>
					</div>
				</div>
			</div>
			<label class="col-1 col-form-label text-right ">pendant</label>
			<div class="col-1">
				<input id="nbDuree4" name="nbDuree4" type="number"
					class="form-control" required="required" value="${ myPrescription4.nbDuree }" min="0">
			</div>
			<div class="col-2">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-calendar-plus-o"></i>
						</div>
					</div>
					<select id="idDuree4" name="idDuree4" class="custom-select"
						required="required">
						<option value="${ myPrescription4.duree }" selected>${ myPrescription4.duree.name }</option>
						<c:forEach items="${ listeDurees }" var="duree">
							<c:if
								test="${ duree != myPrescription4.duree }">
								<option value="${ duree }">${ duree.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="form-group row">
			<c:if test="${ myPrescription4.commentaire == null }">
				<button type="button" id="ajouterCommentairePrescription4"
					class="col-sm-2  btn btn-default btn-sm text-warning text-left d-none">+
					Commentaire</button>
			</c:if>
			<c:if test="${ myPrescription4.commentaire != null }">
				<div id="commentPrescription4" class="col-5 d-none">
					<div class="input-group">
						<div class="input-group-prepend">
							<div class="input-group-text">
								<i class="fa fa-pencil"></i>
							</div>
						</div>
						<textarea id="commentairePrescription4"
							name="commentairePrescription4" class="form-control "
							maxlength="300">${ myPrescription4.commentaire }</textarea>
						<button type="button" name="btnMoins"
							id="enleverCommentairePrescription4"
							class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
					</div>
				</div>
			</c:if>
			<div id="recapPrescription4" class="col-7 ">${ myPrescription4.medicament.nom }
				- ${ myPrescription4.nbDose } ${ myPrescription4.dose.nom }, ${ myPrescription4.nbFrequence }
				fois par ${ myPrescription4.frequence.name}
				<c:if
					test="${ myPrescription4.matin==1 || myPrescription4.midi==1 ||myPrescription4.soir==1 }">
						( <c:if test="${ myPrescription4.matin ==1}">matin</c:if>
					<c:if test="${ myPrescription4.midi ==1}">midi</c:if>
					<c:if test="${ myPrescription4.soir ==1}">soir</c:if> )</c:if>
				pendant ${ myPrescription4.nbDuree} ${ myPrescription4.duree.name}
			</div>
			<div class="col-3 ">
				<button type="button" id="btnValiderPrescription4"
					class="btn  btn-sm btn-outline-success d-none">Enregistrer</button>
				<button type="button" id="btnModifierPrescription4"
					class="btn  btn-sm btn-outline-success btnMasques d-none">Modifier</button>
				<button type="button" id="btnAnnulerPrescription4"
					class="btn  btn-sm btn-outline-danger d-none">Annuler</button>
				<button type="button" id="btnSupprimerPrescription4"
					class="btn  btn-sm btn-outline-danger btnMasques d-none">Supprimer</button>
			</div>
		</div>
		<hr>
	</div>
</c:if>
<!-- fin prescription4 -->


