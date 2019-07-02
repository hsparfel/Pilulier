
$(document).ready(
		function() {
			// function main(){
			// $('#listBtnRdv').click();
			// console.log("ici");
			// $('#listPrescription').slideToggle("slow");
			// $('#listPrise').slideToggle("slow");
		    //$('#listBtnRdv').click();
			 //console.log("ici");
			// }
			// main();
		    sessionStorage.clear();
		    
			window.onload = function() {
				horloge('div_horloge');
				
			};

			function horloge(el) {
				if (typeof el == "string") {
					el = document.getElementById(el);
				}
				function actualiser() {
					var date = new Date();
					var str = "";
					switch (date.getDay()) {
					case 0:
						str += "Dimanche";
						break;
					case 1:
						str += "Lundi";
						break;
					case 2:
						str += "Mardi";
						break;
					case 3:
						str += "Mercredi";
						break;
					case 4:
						str += "Jeudi";
						break;
					case 5:
						str += "Vendredi";
						break;
					case 6:
						str += "Samedi";
						break;
					}
					
					str+=" "+date.getDate()+" ";
					
					switch (date.getMonth()) {
					case 0:
						str += "Janvier";
						break;
					case 1:
						str += "Fevrier";
						break;
					case 2:
						str += "Mars";
						break;
					case 3:
						str += "Avril";
						break;
					case 4:
						str += "Mai";
						break;
					case 5:
						str += "Juin";
						break;
					case 6:
						str += "Juillet";
						break;
					case 7:
						str += "Aout";
						break;
					case 8:
						str += "Septembre";
						break;
					case 9:
						str += "Octobre";
						break;
					case 10:
						str += "Novembre";
						break;
					case 11:
						str += "Decembre";
						break;
										
					}
str+=" "+date.getFullYear()+"  -  "
					str += (date.getHours() < 10 ? '0' : '') + date.getHours();
					str += ':' + (date.getMinutes() < 10 ? '0' : '')
							+ date.getMinutes();
					str += ':' + (date.getSeconds() < 10 ? '0' : '')
							+ date.getSeconds();
					el.innerHTML = str;
				}
				actualiser();
				setInterval(actualiser, 1000);
			}

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

			$('#listBtnAnalyse').click(function() {
                $('#listAnalyse').slideToggle("slow");
            });
			$('#listBtnExamen').click(function() {
                $('#listExamen').slideToggle("slow");
            });
			
			
			
			$('.btn-prise').click(function() {

				$('#btnSubmit' + this.id).click();
			});

		});