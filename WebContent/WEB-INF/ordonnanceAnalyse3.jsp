<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- analyse3 -->
							<c:if test="${ myAnalyse3 != null }">
								<div id="analyse3">
									<div id="analyse3Ligne1" class="form-group row  d-none">
										<label for="idAnalyse3"
											class="col-1 col-form-label font-weight-lighter text-right">Analyse</label>
										<div class="col-3">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-flask"></i>
													</div>
												</div>
												<select id="idAnalyse3" name="idAnalyse3"
													required="required" class="custom-select">
													<option value="${ myAnalyse3.id }" selected>${ myAnalyse3.nom.name }</option>
													<c:forEach items="${ listeAnalyses }" var="analyse">
														<c:if test="${ analyse.id != myAnalyse3.id }">
															<option value="${ analyse.id }">${ analyse.nom.name }</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
										<label for="idCabinetAnalyse3"
											class="col-1 col-form-label font-weight-lighter text-right">Lieu</label>
										<div class="col-3">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-institution"></i>
													</div>
												</div>
												<select id="idCabinetAnalyse3" name="idCabinetAnalyse3"
													required="required" class="custom-select">
													<option value="${ myAnalyse3.cabinet.id }" selected>${ myAnalyse3.cabinet.nom }</option>
													<c:forEach items="${ listeCabinets }" var="cabinet">
														<c:if test="${ cabinet.id != myAnalyse3.cabinet.id }">
															<option value="${ cabinet.id }">${ cabinet.nom }</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
										<a href="EnregistrerCabinet"> <span
											class="fa fa-plus-square-o fa-2x btnPlus"></span>
										</a> <label for="dateAnalyse3"
											class="col-1 col-form-label font-weight-lighter text-right">Date
										</label>
										<div class="col-2">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-calendar"></i>
													</div>
												</div>
												<input type="text" id="dateAnalyse3" class="form-control"
													data-lang="fr" data-years="2019-2030"
													data-format="DD/MM/YYYY" name="dateAnalyse3"
													placeholder="Sélectionner" maxlength="10"
													value="${ myAnalyse3.date }" />
											</div>
											<span id="textHelpBlockDateAnalyse3"
												class="form-text text-muted d-none">ex: 04/07/2019</span>
										</div>
									</div>
									<div class="form-group row">
										<c:if test="${ myAnalyse3.commentaire == null }">
											<button type="button" id="ajouterCommentaireAnalyse3"
												class="col-sm-2  btn btn-default btn-sm text-warning text-left d-none">+
												Commentaire</button>
										</c:if>
										<c:if test="${ myAnalyse3.commentaire != null }">
											<div id="commentAnalyse3" class="col-5 d-none">
												<div class="input-group">
													<div class="input-group-prepend">
														<div class="input-group-text">
															<i class="fa fa-pencil"></i>
														</div>
													</div>
													<textarea id="commentaireAnalyse3"
														name="commentaireAnalyse3" class="form-control "
														maxlength="300">${ myAnalyse3.commentaire }</textarea>
													<button type="button" name="btnMoins"
														id="enleverCommentaireAnalyse3"
														class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
												</div>
											</div>
										</c:if>
										<div id="recapAnalyse3" class="col-7 ">${ myAnalyse3.nom.name }
											- ${ myAnalyse3.cabinet.nom } - ${ myAnalyse3.date}</div>
										<div class="col-3 ">
											<button type="button" id="btnValiderAnalyse3"
												class="btn  btn-sm btn-outline-success d-none">Enregistrer</button>
											<button type="button" id="btnModifierAnalyse3"
												class="btn  btn-sm btn-outline-success btnMasques d-none">Modifier</button>
											<button type="button" id="btnAnnulerAnalyse3"
												class="btn  btn-sm btn-outline-danger d-none">Annuler</button>
											<button type="button" id="btnSupprimerAnalyse3"
												class="btn  btn-sm btn-outline-danger btnMasques d-none">Supprimer</button>

										</div>
									</div>
									<hr>
								</div>
							</c:if>
							<!-- fin analyse3 -->


