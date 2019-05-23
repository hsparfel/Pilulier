

$(document).ready(function() {
 //function main(){
	//$('#listBtnPrise').click();
	//console.log("ici");
	//$('#listPrescription').slideToggle("slow");
	//$('#listPrise').slideToggle("slow");
	//$('#listRdv').slideToggle("slow");
//	console.log("ici");
//}
	//main();
	
	$('#listBtnPrescription').click(function() {
		$('#listPrescription').slideToggle("slow");
	});

	$('#listBtnPrise').click(function() {
		console.log("ici2");
		$('#listPrise').slideToggle("slow");
	});
	$('#listBtnMedecin').click(function() {
		$('#listMedecin').slideToggle("slow");
	});
	$('#listBtnRdv').click(function() {
		$('#listRdv').slideToggle("slow");
	});
	
	$('.btn-prise').click(function() {
		console.log("iciii");
		$('#btnSubmit'+this.id).click();
	});
	
	
	
	
	
});