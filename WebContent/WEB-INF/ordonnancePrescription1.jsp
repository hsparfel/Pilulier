<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- prescription1  -->
<c:if test="${ myPrescription1 != null }">
	<div id="prescription1">
		<div id="prescription1Ligne1" class="form-group row d-none">
			<label for="idMedicament1"
				class="col-1 col-form-label font-weight-lighter text-right">Medicament</label>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-eyedropper"></i>
						</div>
					</div>
					<select id="idMedicament1" name="idMedicament1" required="required"
						class="custom-select">
						<option value="${ myPrescription1.medicament.id }" selected>${ myPrescription1.medicament.nom }</option>
						<c:forEach items="${ listeMedicaments }" var="medicament">
							<c:if
								test="${ medicament.id != myPrescription1.medicament.id }">
								<option value="${ medicament.id }">${ medicament.nom }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<a href="EnregistrerMedicament"> <span
				class="fa fa-plus-square-o fa-2x plusMedicament1"></span>
			</a> <label for="quantiteDose1"
				class="col-1 col-form-label font-weight-lighter text-right">Dose</label>
			<div class="col-1">
				<input id="quantiteDose1" name="quantiteDose1" type="number"
					class="form-control" step="0.5" value="${ myPrescription1.nbDose }" min="0">
			</div>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-spoon"></i>
						</div>
					</div>
					<select id="idDose1" name="idDose1" class="custom-select"
						required="required">
						<option value="${ myPrescription1.dose.id }" selected>${ myPrescription1.dose.nom }</option>
						<c:forEach items="${ listeDoses }" var="dose">
							<c:if
								test="${ dose.id != myPrescription1.dose.id }">
								<option value="${ dose.id }">${ dose.nom }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<a href="EnregistrerDose"> <span
				class="fa fa-plus-square-o fa-2x plusDose1"></span>
			</a>
		</div>
		<div id="prescription1Ligne2" class="form-group row d-none">
			<label for="quantiteFrequence1"
				class="col-1 col-form-label font-weight-lighter text-right ">Fréquence</label>
			<div class="col-1">
				<input id="quantiteFrequence1" name="quantiteFrequence1"
					type="number" class="form-control" value="${ myPrescription1.nbFrequence }" min="0">
			</div>
			<label for="idFrequence1" class=" col-form-label ">fois par</label>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-clock-o"></i>
						</div>
					</div>
					<select id="idFrequence1" name="idFrequence1" required="required"
						class="custom-select">
						<option value="${ myPrescription1.frequence }" selected>${ myPrescription1.frequence.name }</option>
						<c:forEach items="${ listeFrequences }" var="frequence">
							<c:if
								test="${ frequence != myPrescription1.frequence }">
								<option value="${ frequence }">${ frequence.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>

			<div id="freqRadio1" class="col-2 form-group row d-none ">
				<div class="col-1">
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio1" id="frequenceRadio1_0" type="radio"
								class="custom-control-input" value="1"> <label
								for="frequenceRadio1_0" class="custom-control-label">Matin</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio1" id="frequenceRadio1_1" type="radio"
								class="custom-control-input" value="2"> <label
								for="frequenceRadio1_1" class="custom-control-label">Midi</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio1" id="frequenceRadio1_2" type="radio"
								class="custom-control-input" value="3"> <label
								for="frequenceRadio1_2" class="custom-control-label">Soir</label>
						</div>
					</div>
				</div>
			</div>
			<div id="freqCheck1" class="col-2 form-group row d-none">
				<div class="col-1">
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox1_0" id="frequenceCheckbox1_0"
								type="checkbox" class="custom-control-input" value="1">
							<label for="frequenceCheckbox1_0" class="custom-control-label">Matin</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox1_1" id="frequenceCheckbox1_1"
								type="checkbox" class="custom-control-input" value="2">
							<label for="frequenceCheckbox1_1" class="custom-control-label">Midi</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox1_2" id="frequenceCheckbox1_2"
								type="checkbox" class="custom-control-input" value="3">
							<label for="frequenceCheckbox1_2" class="custom-control-label">Soir</label>
						</div>
					</div>
				</div>
			</div>
			<label class="col-1 col-form-label text-right ">pendant</label>
			<div class="col-1">
				<input id="nbDuree1" name="nbDuree1" type="number"
					class="form-control" required="required" value="${ myPrescription1.nbDuree }" min="0">
			</div>
			<div class="col-2">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-calendar-plus-o"></i>
						</div>
					</div>
					<select id="idDuree1" name="idDuree1" class="custom-select"
						required="required">
						<option value="${ myPrescription1.duree }" selected>${ myPrescription1.duree.name }</option>
						<c:forEach items="${ listeDurees }" var="duree">
							<c:if
								test="${ duree != myPrescription1.duree }">
								<option value="${ duree }">${ duree.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="form-group row">
			<c:if test="${ myPrescription1.commentaire == null }">
				<button type="button" id="ajouterCommentairePrescription1"
					class="col-sm-2  btn btn-default btn-sm text-warning text-left d-none">+
					Commentaire</button>
			</c:if>
			<c:if test="${ myPrescription1.commentaire != null }">
				<div id="commentPrescription1" class="col-5 d-none">
					<div class="input-group">
						<div class="input-group-prepend">
							<div class="input-group-text">
								<i class="fa fa-pencil"></i>
							</div>
						</div>
						<textarea id="commentairePrescription1"
							name="commentairePrescription1" class="form-control "
							maxlength="300">${ myPrescription1.commentaire }</textarea>
						<button type="button" name="btnMoins"
							id="enleverCommentairePrescription1"
							class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
					</div>
				</div>
			</c:if>
			<div id="recapPrescription1" class="col-7 ">${ myPrescription1.medicament.nom }
				- ${ myPrescription1.nbDose } ${ myPrescription1.dose.nom }, ${ myPrescription1.nbFrequence }
				fois par ${ myPrescription1.frequence.name}
				<c:if
					test="${ myPrescription1.matin==1 || myPrescription1.midi==1 ||myPrescription1.soir==1 }">
						( <c:if test="${ myPrescription1.matin ==1}">matin</c:if>
					<c:if test="${ myPrescription1.midi ==1}">midi</c:if>
					<c:if test="${ myPrescription1.soir ==1}">soir</c:if> )</c:if>
				pendant ${ myPrescription1.nbDuree} ${ myPrescription1.duree.name}
			</div>
			<div class="col-3 ">
				<button type="button" id="btnValiderPrescription1"
					class="btn  btn-sm btn-outline-success d-none">Enregistrer</button>
				<button type="button" id="btnModifierPrescription1"
					class="btn  btn-sm btn-outline-success btnMasques d-none">Modifier</button>
				<button type="button" id="btnAnnulerPrescription1"
					class="btn  btn-sm btn-outline-danger d-none">Annuler</button>
				<button type="button" id="btnSupprimerPrescription1"
					class="btn  btn-sm btn-outline-danger btnMasques d-none">Supprimer</button>
			</div>
		</div>
		<hr>
	</div>
</c:if>
<!-- fin prescription1 -->


