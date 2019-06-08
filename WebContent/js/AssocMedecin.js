$(document).ready(function() {
	$('[name=idMedecin] option').filter(function() { 
        return ($(this).text() == sessionStorage.getItem("associerMedecinNom")); 
    }).prop('selected', true);
	
	$('.btnSubmit').click(function() {
		sessionStorage.removeItem("associerMedecinNom");
	});
		
	$('.btnCancel').click(function() {
		sessionStorage.removeItem("associerMedecinNom");
	});
	
	$('.btnPlus').click(function() {
		sessionStorage.removeItem("medecinNom");
		sessionStorage.removeItem("associerMedecinNom");
		sessionStorage.removeItem("medecinSpecialite");
		sessionStorage.removeItem("medecinCabinet");
		sessionStorage.removeItem("medecinTelephone");
		sessionStorage.removeItem("medecinMail");
	});
});

