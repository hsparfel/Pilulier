$(document).ready(function() {
	$('#btnModifier').click(function() {
		$('#valid').removeClass("d-none");
		$('#cancel').removeClass("d-none");
		$('#btnModifier').addClass("d-none");
		$('#btnSupprimer').addClass("d-none");
		$('#idMedecin').removeAttr("disabled");
		$('#date').removeAttr("disabled");
		$('#heure').removeAttr("disabled");
	});

});