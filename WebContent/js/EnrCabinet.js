$(document).ready(function() {
	$('.btnSubmit').click(function() {
		sessionStorage.setItem("medecinCabinet",$('#nomCabinet').val());
	});
	
//$('.btnCancel').click(function() {
//	sessionStorage.removeItem("specialiteNom");
	//});
});