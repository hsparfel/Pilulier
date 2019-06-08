$(document).ready(function() {
	
	$('.btnSubmit').click(function() {
		sessionStorage.setItem("medecinNom",$('#nomMedecin').val());
		sessionStorage.setItem("associerMedecinNom",$('#nomMedecin').val());
		sessionStorage.setItem("medecinSpecialite",$('#idSpecialite option:selected').text());
		sessionStorage.setItem("medecinCabinet",$('#idCabinet option:selected').text());
		sessionStorage.setItem("medecinTelephone",$('#nomTelephone').val());
		sessionStorage.setItem("medecinMail",$('#nomEmail').val());
	});
	
	$('.btnPlus').click(function() {
		sessionStorage.setItem("medecinNom",$('#nomMedecin').val());
		sessionStorage.setItem("associerMedecinNom",$('#nomMedecin').val());
		sessionStorage.setItem("medecinSpecialite",$('#idSpecialite option:selected').text());
		sessionStorage.setItem("medecinCabinet",$('#idCabinet option:selected').text());
		sessionStorage.setItem("medecinTelephone",$('#nomTelephone').val());
		sessionStorage.setItem("medecinMail",$('#nomEmail').val());
	});
	
	
	$('[name=idSpecialite] option').filter(function() { 
        return ($(this).text() == sessionStorage.getItem("medecinSpecialite")); 
    }).prop('selected', true);
	
	$('[name=idCabinet] option').filter(function() { 
        return ($(this).text() == sessionStorage.getItem("medecinCabinet")); 
    }).prop('selected', true);
	
	$('#nomMedecin').val(sessionStorage.getItem("medecinNom"));
	$('#nomTelephone').val(sessionStorage.getItem("medecinTelephone"));
	$('#nomEmail').val(sessionStorage.getItem("medecinMail"));
	
//$('.btnCancel').click(function() {
//	sessionStorage.removeItem("specialiteNom");
	//});
});