<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- prescription3  -->
<c:if test="${ myPrescription3 != null }">
	<div id="prescription3">
		<div id="prescription3Ligne1" class="form-group row d-none">
			<label for="idMedicament3"
				class="col-1 col-form-label font-weight-lighter text-right">Medicament</label>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-eyedropper"></i>
						</div>
					</div>
					<select id="idMedicament3" name="idMedicament3" required="required"
						class="custom-select">
						<option value="${ myPrescription3.medicament.id }" selected>${ myPrescription3.medicament.nom }</option>
						<c:forEach items="${ listeMedicaments }" var="medicament">
							<c:if
								test="${ medicament.id != myPrescription3.medicament.id }">
								<option value="${ medicament.id }">${ medicament.nom }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<a href="EnregistrerMedicament"> <span
				class="fa fa-plus-square-o fa-2x plusMedicament3"></span>
			</a> <label for="quantiteDose3"
				class="col-1 col-form-label font-weight-lighter text-right">Dose</label>
			<div class="col-1">
				<input id="quantiteDose3" name="quantiteDose3" type="number"
					class="form-control" step="0.5" value="${ myPrescription3.nbDose }" min="0">
			</div>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-spoon"></i>
						</div>
					</div>
					<select id="idDose3" name="idDose3" class="custom-select"
						required="required">
						<option value="${ myPrescription3.dose.id }" selected>${ myPrescription3.dose.nom }</option>
						<c:forEach items="${ listeDoses }" var="dose">
							<c:if
								test="${ dose.id != myPrescription3.dose.id }">
								<option value="${ dose.id }">${ dose.nom }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<a href="EnregistrerDose"> <span
				class="fa fa-plus-square-o fa-2x plusDose3"></span>
			</a>
		</div>
		<div id="prescription3Ligne2" class="form-group row d-none">
			<label for="quantiteFrequence3"
				class="col-1 col-form-label font-weight-lighter text-right ">Fréquence</label>
			<div class="col-1">
				<input id="quantiteFrequence3" name="quantiteFrequence3"
					type="number" class="form-control" value="${ myPrescription3.nbFrequence }" min="0">
			</div>
			<label for="idFrequence3" class=" col-form-label ">fois par</label>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-clock-o"></i>
						</div>
					</div>
					<select id="idFrequence3" name="idFrequence3" required="required"
						class="custom-select">
						<option value="${ myPrescription3.frequence }" selected>${ myPrescription3.frequence.name }</option>
						<c:forEach items="${ listeFrequences }" var="frequence">
							<c:if
								test="${ frequence != myPrescription3.frequence }">
								<option value="${ frequence }">${ frequence.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>

			<div id="freqRadio3" class="col-2 form-group row d-none ">
				<div class="col-1">
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio3" id="frequenceRadio3_0" type="radio"
								class="custom-control-input" value="1"> <label
								for="frequenceRadio3_0" class="custom-control-label">Matin</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio3" id="frequenceRadio3_1" type="radio"
								class="custom-control-input" value="2"> <label
								for="frequenceRadio3_1" class="custom-control-label">Midi</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio3" id="frequenceRadio3_2" type="radio"
								class="custom-control-input" value="3"> <label
								for="frequenceRadio3_2" class="custom-control-label">Soir</label>
						</div>
					</div>
				</div>
			</div>
			<div id="freqCheck3" class="col-2 form-group row d-none">
				<div class="col-1">
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox3_0" id="frequenceCheckbox3_0"
								type="checkbox" class="custom-control-input" value="1">
							<label for="frequenceCheckbox3_0" class="custom-control-label">Matin</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox3_1" id="frequenceCheckbox3_1"
								type="checkbox" class="custom-control-input" value="2">
							<label for="frequenceCheckbox3_1" class="custom-control-label">Midi</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox3_2" id="frequenceCheckbox3_2"
								type="checkbox" class="custom-control-input" value="3">
							<label for="frequenceCheckbox3_2" class="custom-control-label">Soir</label>
						</div>
					</div>
				</div>
			</div>
			<label class="col-1 col-form-label text-right ">pendant</label>
			<div class="col-1">
				<input id="nbDuree3" name="nbDuree3" type="number"
					class="form-control" required="required" value="${ myPrescription3.nbDuree }" min="0">
			</div>
			<div class="col-2">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-calendar-plus-o"></i>
						</div>
					</div>
					<select id="idDuree3" name="idDuree3" class="custom-select"
						required="required">
						<option value="${ myPrescription3.duree }" selected>${ myPrescription3.duree.name }</option>
						<c:forEach items="${ listeDurees }" var="duree">
							<c:if
								test="${ duree != myPrescription3.duree }">
								<option value="${ duree }">${ duree.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="form-group row">
			<c:if test="${ myPrescription3.commentaire == null }">
				<button type="button" id="ajouterCommentairePrescription3"
					class="col-sm-2  btn btn-default btn-sm text-warning text-left d-none">+
					Commentaire</button>
			</c:if>
			<c:if test="${ myPrescription3.commentaire != null }">
				<div id="commentPrescription3" class="col-5 d-none">
					<div class="input-group">
						<div class="input-group-prepend">
							<div class="input-group-text">
								<i class="fa fa-pencil"></i>
							</div>
						</div>
						<textarea id="commentairePrescription3"
							name="commentairePrescription3" class="form-control "
							maxlength="300">${ myPrescription3.commentaire }</textarea>
						<button type="button" name="btnMoins"
							id="enleverCommentairePrescription3"
							class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
					</div>
				</div>
			</c:if>
			<div id="recapPrescription3" class="col-7 ">${ myPrescription3.medicament.nom }
				- ${ myPrescription3.nbDose } ${ myPrescription3.dose.nom }, ${ myPrescription3.nbFrequence }
				fois par ${ myPrescription3.frequence.name}
				<c:if
					test="${ myPrescription3.matin==1 || myPrescription3.midi==1 ||myPrescription3.soir==1 }">
						( <c:if test="${ myPrescription3.matin ==1}">matin</c:if>
					<c:if test="${ myPrescription3.midi ==1}">midi</c:if>
					<c:if test="${ myPrescription3.soir ==1}">soir</c:if> )</c:if>
				pendant ${ myPrescription3.nbDuree} ${ myPrescription3.duree.name}
			</div>
			<div class="col-3 ">
				<button type="button" id="btnValiderPrescription3"
					class="btn  btn-sm btn-outline-success d-none">Enregistrer</button>
				<button type="button" id="btnModifierPrescription3"
					class="btn  btn-sm btn-outline-success btnMasques d-none">Modifier</button>
				<button type="button" id="btnAnnulerPrescription3"
					class="btn  btn-sm btn-outline-danger d-none">Annuler</button>
				<button type="button" id="btnSupprimerPrescription3"
					class="btn  btn-sm btn-outline-danger btnMasques d-none">Supprimer</button>
			</div>
		</div>
		<hr>
	</div>
</c:if>
<!-- fin prescription3 -->


