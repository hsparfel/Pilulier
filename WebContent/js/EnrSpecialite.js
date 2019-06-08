$(document).ready(function() {

	$('.btnSubmit').click(function() {
		sessionStorage.setItem("medecinSpecialite",$('#nomSpecialite').val());
	});
	
//$('.btnCancel').click(function() {
//	sessionStorage.removeItem("specialiteNom");
	//});
});