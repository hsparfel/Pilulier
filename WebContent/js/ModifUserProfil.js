
$(document).ready(
		function() {
			
		    //remplir l'age
		    
		    let dateNaissance = $('#dateNaissance').html();
		    let dateDuJour=new Date(Date.now());
		    let jourNaissance = dateNaissance.substr(0,2);
		    console.log("jour naiss: "+jourNaissance);
		    let moisNaissance = dateNaissance.substr(3,2);
            console.log("mois naiss: "+moisNaissance);
             let anneeNaissance = dateNaissance.substr(6,4);
            console.log("annee naiss: "+anneeNaissance);
            dateNaissance=anneeNaissance+"-"+moisNaissance+"-"+jourNaissance;
            dateNaissanceFormatee=new Date(dateNaissance);
            
            var ageDifMs = dateDuJour - dateNaissanceFormatee.getTime();
            var ageDate = new Date(ageDifMs); // miliseconds from epoch
            let age= Math.abs(ageDate.getUTCFullYear() - 1970);
            
            console.log("age: "+age);
            
            $('#myAge').html(age+" ans");
            
            
            
            
		    //fin remplir l'age
		    
		    
		    
		    
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

			$('#btnModifier').click(function() {
			    console.log("url");
			 // sauvegarde de l'url pour retour
                let chemin = $(location).attr('pathname');
                let chemin2 = chemin.substr(1, chemin.length - 1);
                let indiceSlash = chemin2.indexOf("/");
                let page = chemin2.substr(indiceSlash + 1, chemin.length - indiceSlash);
                sessionStorage.setItem("pagePrecedente", page);
                
            });
		});