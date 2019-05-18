$(document).ready(function() {
	$('#btnModifier').click(function() {
		$('#valid').removeClass("d-none");
		$('#cancel').removeClass("d-none");
		$('#btnModifier').addClass("d-none");
		$('#btnSupprimer').addClass("d-none");
		$('#nomFrequence').removeAttr("disabled");
		});
	
});