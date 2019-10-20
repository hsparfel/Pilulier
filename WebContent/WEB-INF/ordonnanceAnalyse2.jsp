<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- analyse2 -->
							<c:if test="${ myAnalyse2 != null }">
								<div id="analyse2">
									<div id="analyse2Ligne1" class="form-group row  d-none">
										<label for="idAnalyse2"
											class="col-1 col-form-label font-weight-lighter text-right">Analyse</label>
										<div class="col-3">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-flask"></i>
													</div>
												</div>
												<select id="idAnalyse2" name="idAnalyse2"
													required="required" class="custom-select">
													<option value="${ myAnalyse2.id }" selected>${ myAnalyse2.nom.name }</option>
													<c:forEach items="${ listeAnalyses }" var="analyse">
														<c:if test="${ analyse.id != myAnalyse2.id }">
															<option value="${ analyse.id }">${ analyse.nom.name }</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
										<label for="idCabinetAnalyse2"
											class="col-1 col-form-label font-weight-lighter text-right">Lieu</label>
										<div class="col-3">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-institution"></i>
													</div>
												</div>
												<select id="idCabinetAnalyse2" name="idCabinetAnalyse2"
													required="required" class="custom-select">
													<option value="${ myAnalyse2.cabinet.id }" selected>${ myAnalyse2.cabinet.nom }</option>
													<c:forEach items="${ listeCabinets }" var="cabinet">
														<c:if test="${ cabinet.id != myAnalyse2.cabinet.id }">
															<option value="${ cabinet.id }">${ cabinet.nom }</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
										<a href="EnregistrerCabinet"> <span
											class="fa fa-plus-square-o fa-2x btnPlus"></span>
										</a> <label for="dateAnalyse2"
											class="col-1 col-form-label font-weight-lighter text-right">Date
										</label>
										<div class="col-2">
											<div class="input-group">
												<div class="input-group-prepend">
													<div class="input-group-text">
														<i class="fa fa-calendar"></i>
													</div>
												</div>
												<input type="text" id="dateAnalyse2" class="form-control"
													data-lang="fr" data-years="2019-2030"
													data-format="DD/MM/YYYY" name="dateAnalyse2"
													placeholder="Sélectionner" maxlength="10"
													value="${ myAnalyse2.date }" />
											</div>
											<span id="textHelpBlockDateAnalyse2"
												class="form-text text-muted d-none">ex: 04/07/2019</span>
										</div>
									</div>
									<div class="form-group row">
										<c:if test="${ myAnalyse2.commentaire == null }">
											<button type="button" id="ajouterCommentaireAnalyse2"
												class="col-sm-2  btn btn-default btn-sm text-warning text-left  d-none">+
												Commentaire</button>
										</c:if>
										<c:if test="${ myAnalyse2.commentaire != null }">
											<div id="commentAnalyse2" class="col-5 d-none">
												<div class="input-group">
													<div class="input-group-prepend">
														<div class="input-group-text">
															<i class="fa fa-pencil"></i>
														</div>
													</div>
													<textarea id="commentaireAnalyse2"
														name="commentaireAnalyse2" class="form-control "
														maxlength="300">${ myAnalyse2.commentaire }</textarea>
													<button type="button" name="btnMoins"
														id="enleverCommentaireAnalyse2"
														class="btn btn-outline-danger fa fa-minus-square-o fa-2x"></button>
												</div>
											</div>
										</c:if>
										<div id="recapAnalyse2" class="col-7 ">${ myAnalyse2.nom.name }
											- ${ myAnalyse2.cabinet.nom } - ${ myAnalyse2.date}</div>
										<div class="col-3 ">
											<button type="button" id="btnValiderAnalyse2"
												class="btn  btn-sm btn-outline-success d-none">Enregistrer</button>
											<button type="button" id="btnModifierAnalyse2"
												class="btn  btn-sm btn-outline-success btnMasques d-none">Modifier</button>
											<button type="button" id="btnAnnulerAnalyse2"
												class="btn  btn-sm btn-outline-danger d-none">Annuler</button>
											<button type="button" id="btnSupprimerAnalyse2"
												class="btn  btn-sm btn-outline-danger btnMasques d-none">Supprimer</button>

										</div>
									</div>
									<hr>
								</div>
							</c:if>
							<!-- fin analyse2 -->


