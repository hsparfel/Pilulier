$(document).ready(function() {
	$('.btnPlus').click(function() {
		sessionStorage.setItem("rdvIdMedecin", $('#idMedecin').val());
		sessionStorage.setItem("rdvDate", $('#date').val());
		sessionStorage.setItem("rdvHeure", $('#heure').val());
		sessionStorage.setItem("rdvCommentaire", $('#commentaire').val());
	});

	// $('.btnCancel').click(function() {
	// sessionStorage.removeItem("specialiteNom");
	// });
	

});