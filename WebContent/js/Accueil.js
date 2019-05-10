$(document).ready(function() {
/*	$('#boutonplus').click(function() {
		$('#newuser').removeClass("d-none");
		$('.listeUserExistant').addClass("d-none");
		$('#boutonplus').addClass("d-none");
	});*/
	$('.userexistant').click(function() {
		$('#ExistingUserSubmit'+this.id).click();
	});
});