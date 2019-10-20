<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- analyse1 -->
							<c:if test="${ myAnalyse1 != null }">
								<div id="analyse1">
									<div id="analyse1Ligne1" class="form-group row  d-none">
										<label for="idAnalyse1"
											class="col-1 col-form-label font-weight-lighter text-right">Analyse</label>
										<div class="col-3">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-flask"></i>
													</div>
												</div>
												<select id="idAnalyse1" name="idAnalyse1"
													required="required" class="custom-select">


													<option value="${ myAnalyse1.id }" selected>${ myAnalyse1.nom.name }</option>
													<c:forEach items="${ listeAnalyses }" var="analyse">
														<c:if test="${ analyse.id != myAnalyse1.id }">
															<option value="${ analyse.id }">${ analyse.nom.name }</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
										<label for="idCabinetAnalyse1"
											class="col-1 col-form-label font-weight-lighter text-right">Lieu</label>
										<div class="col-3">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-institution"></i>
													</div>
												</div>
												<select id="idCabinetAnalyse1" name="idCabinetAnalyse1"
													required="required" class="custom-select">

													<option value="${ myAnalyse1.cabinet.id }" selected>${ myAnalyse1.cabinet.nom }</option>
													<c:forEach items="${ listeCabinets }" var="cabinet">
														<c:if test="${ cabinet.id != myAnalyse1.cabinet.id }">
															<option value="${ cabinet.id }">${ cabinet.nom }</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
										<a href="EnregistrerCabinet"> <span
											class="fa fa-plus-square-o fa-2x btnPlus"></span>
										</a> <label for="dateAnalyse1"
											class="col-1 col-form-label font-weight-lighter text-right">Date
										</label>
										<div class="col-2">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-calendar"></i>
													</div>
												</div>
												<input type="text" id="dateAnalyse1" class="form-control"
													data-lang="fr" data-years="2019-2030"
													data-format="DD/MM/YYYY" name="dateAnalyse1"
													placeholder="Sélectionner" maxlength="10"
													value="${ myAnalyse1.date }" />
											</div>
											<span id="textHelpBlockDateAnalyse1"
												class="form-text text-muted d-none">ex: 04/07/2019</span>
										</div>
									</div>
									<div class="form-group row">
										<c:if test="${ myAnalyse1.commentaire == null }">
											<button type="button" id="ajouterCommentaireAnalyse1"
												class="col-sm-2  btn btn-default btn-sm text-warning text-left  d-none">+
												Commentaire</button>
										</c:if>
										<c:if test="${ myAnalyse1.commentaire != null }">
											<div id="commentAnalyse1" class="col-5 d-none">
												<div class="input-group">
													<div class="input-group-prepend">
														<div class="input-group-text">
															<i class="fa fa-pencil"></i>
														</div>
													</div>
													<textarea id="commentaireAnalyse1"
														name="commentaireAnalyse1" class="form-control "
														maxlength="300">${ myAnalyse1.commentaire }</textarea>
													<button type="button" name="btnMoins"
														id="enleverCommentaireAnalyse1"
														class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
												</div>
											</div>
										</c:if>
										<div id="recapAnalyse1" class="col-7 ">${ myAnalyse1.nom.name }
											- ${ myAnalyse1.cabinet.nom } - ${ myAnalyse1.date}</div>
										<div class="col-3 ">
											<button type="button" id="btnValiderAnalyse1"
												class="btn  btn-sm btn-outline-success d-none">Enregistrer</button>
											<button type="button" id="btnModifierAnalyse1"
												class="btn  btn-sm btn-outline-success btnMasques d-none">Modifier</button>
											<button type="button" id="btnAnnulerAnalyse1"
												class="btn  btn-sm btn-outline-danger d-none">Annuler</button>
											<button type="button" id="btnSupprimerAnalyse1"
												class="btn  btn-sm btn-outline-danger btnMasques d-none">Supprimer</button>

										</div>
									</div>
									<hr>
								</div>
							</c:if>
							<!-- fin analyse1 -->


