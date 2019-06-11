$(document).ready(function() {
	$('.btnPlus').click(function() {
		sessionStorage.setItem("rdvDate",$('#date').val());
		sessionStorage.setItem("rdvHeure",$('#heure').val());
	});
	
//$('.btnCancel').click(function() {
//	sessionStorage.removeItem("specialiteNom");
	//});
});