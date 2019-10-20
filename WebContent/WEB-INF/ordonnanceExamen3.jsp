<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- examen3 -->
								<c:if test="${ myExamen3 != null }">
									<div id="examen3">
										<div id="examen3Ligne1" class="form-group row d-none">
											<label for="idExamen3"
												class="col-1 col-form-label font-weight-lighter text-right">Examen</label>
											<div class="col-3">
												<div class="input-group">
													<div class="input-group-prepend">
														<div class="input-group-text">
															<i class="fa fa-stethoscope"></i>
														</div>
													</div>
													<select id="idExamen3" name="idExamen3" required="required"
														class="custom-select">
														<option value="${ myExamen3.id }" selected>${ myExamen3.nom.name }</option>
														<c:forEach items="${ listeExamens }" var="examen">
															<c:if test="${ examen.id != myExamen3.id }">
																<option value="${ examen.id }">${ examen.nom.name }</option>
															</c:if>
														</c:forEach>
													</select>
												</div>
											</div>
											<label for="idCabinetExamen3"
												class="col-1 col-form-label font-weight-lighter text-right">Lieu</label>
											<div class="col-3">
												<div class="input-group">
													<div class="input-group-prepend">
														<div class="input-group-text">
															<i class="fa fa-institution"></i>
														</div>
													</div>
													<select id="idCabinetExamen3" name="idCabinetExamen3"
														required="required" class="custom-select">
														<option value="${ myExamen3.cabinet.id }" selected>${ myExamen3.cabinet.nom }</option>
														<c:forEach items="${ listeCabinets }" var="cabinet">
															<c:if test="${ cabinet.id != myExamen3.cabinet.id }">
																<option value="${ cabinet.id }">${ cabinet.nom }</option>
															</c:if>
														</c:forEach>
													</select>
												</div>
											</div>
											<a href="EnregistrerCabinet"> <span
												class="fa fa-plus-square-o fa-2x btnPlus"></span>
											</a> <label for="date1"
												class="col-1 col-form-label font-weight-lighter text-right">Date
											</label>
											<div class="col-2">
												<div class="input-group">
													<div class="input-group-prepend">
														<div class="input-group-text">
															<i class="fa fa-calendar"></i>
														</div>
													</div>
													<input type="text" id="dateExamen3" class="form-control"
														data-lang="fr" data-years="2019-2030"
														data-format="DD/MM/YYYY" name="dateExamen3"
														placeholder="Sélectionner" maxlength="10"
														value="${ myExamen3.date }" />
												</div>
												<span id="textHelpBlockDateExamen3"
													class="form-text text-muted d-none">ex: 04/07/2019</span>
											</div>
										</div>
										<div class="form-group row">
											<c:if test="${ myExamen3.commentaire == null }">
												<button type="button" id="ajouterCommentaireExamen3"
													class="col-sm-2  btn btn-default btn-sm text-warning text-left d-none">+
													Commentaire</button>
											</c:if>
											<c:if test="${ myExamen3.commentaire != null }">
												<div id="commentExamen3" class="col-5 d-none">
													<div class="input-group">
														<div class="input-group-prepend">
															<div class="input-group-text">
																<i class="fa fa-pencil"></i>
															</div>
														</div>
														<textarea id="commentaireExamen3"
															name="commentaireExamen3" class="form-control "
															maxlength="300">${ myExamen3.commentaire }</textarea>
														<button type="button" name="btnMoins"
															id="enleverCommentaireExamen3"
															class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
													</div>
												</div>
											</c:if>
											<div id="recapExamen3" class="col-7">${ myExamen3.nom.name }
												- ${ myExamen3.cabinet.nom } - ${ myExamen3.date}</div>
											<div class="col-3 ">
												<button type="button" id="btnValiderExamen3"
													class="btn  btn-sm btn-outline-success d-none">Enregistrer</button>
												<button type="button" id="btnModifierExamen3"
													class="btn  btn-sm btn-outline-success btnMasques d-none">Modifier</button>
												<button type="button" id="btnAnnulerExamen3"
													class="btn  btn-sm btn-outline-danger d-none">Annuler</button>
												<button type="button" id="btnSupprimerExamen3"
													class="btn  btn-sm btn-outline-danger btnMasques d-none">Supprimer</button>
											</div>
										</div>
										<hr>
									</div>
								</c:if>
								<!-- fin examen3 -->