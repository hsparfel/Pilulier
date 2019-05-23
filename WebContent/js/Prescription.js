$(document).ready(

function() {
	console.log("ici");
	$('#idFrequence').on('change',function() {togglePriseJour()});
	$('#quantiteFrequence').on('change',function() {togglePriseJour()});

	function togglePriseJour() {
		console.log("dans fonction");
		let frequence = $('#idFrequence option:selected').text();
		let qteFrequence = $('#quantiteFrequence').val();
		console.log("choix frequence: " + frequence);
		console.log("nb frequence: " + qteFrequence);
		console.log(typeof 'qteFrequence');

		if (frequence == "jour" && qteFrequence <4) {
			if (qteFrequence == "1") {
				console.log("unique");
				$('#freqRadio').removeClass("d-none");
				$(":checkbox").prop('checked', false).parent()
						.removeClass('active');
				$('#freqCheck').addClass("d-none");
			} else if (qteFrequence == "2"||qteFrequence == "3") {
				console.log("multiple");
				$('#freqCheck').removeClass("d-none");
				$(":radio").prop('checked', false).parent().removeClass('active');
				$('#freqRadio').addClass("d-none");
			}
			console.log("vrai");
		} else {
			$(":radio").prop('checked', false).parent().removeClass('active');
			$(":checkbox").prop('checked', false).parent().removeClass('active');
			$('#freqRadio').addClass("d-none");
			$('#freqCheck').addClass("d-none");
			console.log("faux");
		}
	}

});