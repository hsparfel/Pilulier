<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- prescription5  -->
<c:if test="${ myPrescription5 != null }">
	<div id="prescription5">
		<div id="prescription5Ligne1" class="form-group row d-none">
			<label for="idMedicament5"
				class="col-1 col-form-label font-weight-lighter text-right">Medicament</label>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-eyedropper"></i>
						</div>
					</div>
					<select id="idMedicament5" name="idMedicament5" required="required"
						class="custom-select">
						<option value="${ myPrescription5.medicament.id }" selected>${ myPrescription5.medicament.nom }</option>
						<c:forEach items="${ listeMedicaments }" var="medicament">
							<c:if
								test="${ medicament.id != myPrescription5.medicament.id }">
								<option value="${ medicament.id }">${ medicament.nom }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<a href="EnregistrerMedicament"> <span
				class="fa fa-plus-square-o fa-2x plusMedicament5"></span>
			</a> <label for="quantiteDose5"
				class="col-1 col-form-label font-weight-lighter text-right">Dose</label>
			<div class="col-1">
				<input id="quantiteDose5" name="quantiteDose5" type="number"
					class="form-control" step="0.5" value="${ myPrescription5.nbDose }" min="0">
			</div>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-spoon"></i>
						</div>
					</div>
					<select id="idDose5" name="idDose5" class="custom-select"
						required="required">
						<option value="${ myPrescription5.dose.id }" selected>${ myPrescription5.dose.nom }</option>
						<c:forEach items="${ listeDoses }" var="dose">
							<c:if
								test="${ dose.id != myPrescription5.dose.id }">
								<option value="${ dose.id }">${ dose.nom }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<a href="EnregistrerDose"> <span
				class="fa fa-plus-square-o fa-2x plusDose5"></span>
			</a>
		</div>
		<div id="prescription5Ligne2" class="form-group row d-none">
			<label for="quantiteFrequence5"
				class="col-1 col-form-label font-weight-lighter text-right ">Fréquence</label>
			<div class="col-1">
				<input id="quantiteFrequence5" name="quantiteFrequence5"
					type="number" class="form-control" value="${ myPrescription5.nbFrequence }" min="0">
			</div>
			<label for="idFrequence5" class=" col-form-label ">fois par</label>
			<div class="col-3">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-clock-o"></i>
						</div>
					</div>
					<select id="idFrequence5" name="idFrequence5" required="required"
						class="custom-select">
						<option value="${ myPrescription5.frequence }" selected>${ myPrescription5.frequence.name }</option>
						<c:forEach items="${ listeFrequences }" var="frequence">
							<c:if
								test="${ frequence != myPrescription5.frequence }">
								<option value="${ frequence }">${ frequence.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>

			<div id="freqRadio5" class="col-2 form-group row d-none ">
				<div class="col-1">
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio5" id="frequenceRadio5_0" type="radio"
								class="custom-control-input" value="1"> <label
								for="frequenceRadio5_0" class="custom-control-label">Matin</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio5" id="frequenceRadio5_1" type="radio"
								class="custom-control-input" value="2"> <label
								for="frequenceRadio5_1" class="custom-control-label">Midi</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-radio">
							<input name="frequenceRadio5" id="frequenceRadio5_2" type="radio"
								class="custom-control-input" value="3"> <label
								for="frequenceRadio5_2" class="custom-control-label">Soir</label>
						</div>
					</div>
				</div>
			</div>
			<div id="freqCheck5" class="col-2 form-group row d-none">
				<div class="col-1">
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox5_0" id="frequenceCheckbox5_0"
								type="checkbox" class="custom-control-input" value="1">
							<label for="frequenceCheckbox5_0" class="custom-control-label">Matin</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox5_1" id="frequenceCheckbox5_1"
								type="checkbox" class="custom-control-input" value="2">
							<label for="frequenceCheckbox5_1" class="custom-control-label">Midi</label>
						</div>
					</div>
					<div class="custom-controls-stacked">
						<div class="custom-control custom-checkbox">
							<input name="frequenceCheckbox5_2" id="frequenceCheckbox5_2"
								type="checkbox" class="custom-control-input" value="3">
							<label for="frequenceCheckbox5_2" class="custom-control-label">Soir</label>
						</div>
					</div>
				</div>
			</div>
			<label class="col-1 col-form-label text-right ">pendant</label>
			<div class="col-1">
				<input id="nbDuree5" name="nbDuree5" type="number"
					class="form-control" required="required" value="${ myPrescription5.nbDuree }" min="0">
			</div>
			<div class="col-2">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<i class="fa fa-calendar-plus-o"></i>
						</div>
					</div>
					<select id="idDuree5" name="idDuree5" class="custom-select"
						required="required">
						<option value="${ myPrescription5.duree }" selected>${ myPrescription5.duree.name }</option>
						<c:forEach items="${ listeDurees }" var="duree">
							<c:if
								test="${ duree != myPrescription5.duree }">
								<option value="${ duree }">${ duree.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="form-group row">
			<c:if test="${ myPrescription5.commentaire == null }">
				<button type="button" id="ajouterCommentairePrescription5"
					class="col-sm-2  btn btn-default btn-sm text-warning text-left d-none">+
					Commentaire</button>
			</c:if>
			<c:if test="${ myPrescription5.commentaire != null }">
				<div id="commentPrescription5" class="col-5 d-none">
					<div class="input-group">
						<div class="input-group-prepend">
							<div class="input-group-text">
								<i class="fa fa-pencil"></i>
							</div>
						</div>
						<textarea id="commentairePrescription5"
							name="commentairePrescription5" class="form-control "
							maxlength="300">${ myPrescription5.commentaire }</textarea>
						<button type="button" name="btnMoins"
							id="enleverCommentairePrescription5"
							class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
					</div>
				</div>
			</c:if>
			<div id="recapPrescription5" class="col-7 ">${ myPrescription5.medicament.nom }
				- ${ myPrescription5.nbDose } ${ myPrescription5.dose.nom }, ${ myPrescription5.nbFrequence }
				fois par ${ myPrescription5.frequence.name}
				<c:if
					test="${ myPrescription5.matin==1 || myPrescription5.midi==1 ||myPrescription5.soir==1 }">
						( <c:if test="${ myPrescription5.matin ==1}">matin</c:if>
					<c:if test="${ myPrescription5.midi ==1}">midi</c:if>
					<c:if test="${ myPrescription5.soir ==1}">soir</c:if> )</c:if>
				pendant ${ myPrescription5.nbDuree} ${ myPrescription5.duree.name}
			</div>
			<div class="col-3 ">
				<button type="button" id="btnValiderPrescription5"
					class="btn  btn-sm btn-outline-success d-none">Enregistrer</button>
				<button type="button" id="btnModifierPrescription5"
					class="btn  btn-sm btn-outline-success btnMasques d-none">Modifier</button>
				<button type="button" id="btnAnnulerPrescription5"
					class="btn  btn-sm btn-outline-danger d-none">Annuler</button>
				<button type="button" id="btnSupprimerPrescription5"
					class="btn  btn-sm btn-outline-danger btnMasques d-none">Supprimer</button>
			</div>
		</div>
		<hr>
	</div>
</c:if>
<!-- fin prescription5 -->


