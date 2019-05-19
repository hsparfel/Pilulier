$(document).ready(function() {
	$('#btnModifier').click(function() {
		$('.btnMasques').removeClass("d-none");
		$('.btnAffiches').addClass("d-none");
		$('.champDesactive').removeAttr("disabled");
	});
});