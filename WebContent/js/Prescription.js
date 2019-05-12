$(document).ready(function() {
	$('#idFrequence').on('change', function() {
		let frequence = $('#idFrequence option:selected').text();
		console.log("choix frequence: " + frequence)
		if (frequence == "jour") {
			$('#freqRadio').removeClass("d-none");
			$('#freqCheck').removeClass("d-none");
			console.log("vrai");
		} else {
			$('#freqRadio').addClass("d-none");
			$('#freqCheck').addClass("d-none");
			console.log("faux");
		}
	});
});