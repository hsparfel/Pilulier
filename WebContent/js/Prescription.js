$(document).ready(function() {
/*	$('#boutonplus').click(function() {
		$('#newuser').removeClass("d-none");
		$('.listeUserExistant').addClass("d-none");
		$('#boutonplus').addClass("d-none");
	});*/
	
	$('#posologieFrequence').on('change', function() {
		
		let frequence = $('#posologieFrequence option:selected').text();
		
		if (frequence == "jour") {
			$('#freqRadio').removeClass("d-none");
			$('#freqCheck').removeClass("d-none");
		}
		
		
		
		
		});
		
	
});