$(document).ready(function() {
    //recup url page precedente
    console.log(sessionStorage.getItem("pagePrecedente"));
    $('#pagePrecedente').val(sessionStorage.getItem("pagePrecedente"));
    
    //poids
    $('#poids').val($('#curseurPoids').val()); 
    $('#curseurPoids').on('input', function() {
        $('#poids').val($('#curseurPoids').val());
        calculerImc();
    });
    $('#poids').on('mouseout', function() {
        $('#curseurPoids').val($('#poids').val());
        calculerImc();
    });
    //taille
    $('#taille').val($('#curseurTaille').val()); 
    $('#curseurTaille').on('input', function() {
        $('#taille').val($('#curseurTaille').val());
        calculerImc();
    });
    $('#taille').on('mouseout', function() {
        $('#curseurTaille').val($('#taille').val());
        calculerImc();
    });

    //imc
  //remplir l'age
    
    let dateNaissance = $('#dateDeNaissance').html();
    let dateDuJourAge=new Date(Date.now());
    let jourNaissance = dateNaissance.substr(0,2);
    console.log("jour naiss: "+jourNaissance);
    let moisNaissance = dateNaissance.substr(3,2);
    console.log("mois naiss: "+moisNaissance);
     let anneeNaissance = dateNaissance.substr(6,4);
    console.log("annee naiss: "+anneeNaissance);
    dateNaissance=anneeNaissance+"-"+moisNaissance+"-"+jourNaissance;
    dateNaissanceFormatee=new Date(dateNaissance);
    
    var ageDifMs = dateDuJourAge - dateNaissanceFormatee.getTime();
    var ageDate = new Date(ageDifMs); // miliseconds from epoch
    let age= Math.abs(ageDate.getUTCFullYear() - 1970);
    
    console.log("age: "+age);
    $('#myAge').val(age);
    //let age=40;// a remplacer plus tard par la recup de l'age de la bd
    let sexe="f"// a remplacer plus tard par la recup du sexe de la bd
        calculerImc();

//  date du jour

    let dateDuJourRaw=new Date(Date.now());
    let dd=dateDuJourRaw.getDate();
    let mm=dateDuJourRaw.getMonth()+1;
    //console.log(dateDuJourRaw);
    //console.log(dateDuJourRaw.getMonth());
    let yyyy=dateDuJourRaw.getFullYear();
    if(dd<10) 
    {
        dd='0'+dd;
    } 

    if(mm<10) 
    {
        mm='0'+mm;
    } 

    let dateDuJour=dd+"/"+mm+"/"+yyyy;
    console.log(dateDuJour);

    $('#date').val(dateDuJour);



//  fonctions
    function calculerImc() {
        $('#imc').val(($('#poids').val()/Math.pow($('#taille').val()/100,2)).toFixed(2));
        let commentaire;
        let imc = $('#imc').val();
  console.log(imc);
        if (sexe==="M") {

            switch (true) {
            case age<16:
                $('#imcComm').val("hors contexte");
            break;
            case age<17:
                switch (true) {
                case imc<19:
                    $('#imcComm').val("sous-poids");
                break;
                case imc<=24:
                    $('#imcComm').val("poids normal");
                break;
                default:
                    $('#imcComm').val("surpoids");
                }
            break;
            case age<19:
                switch (true) {
                case imc<20:
                    $('#imcComm').val("sous-poids");
                break;
                case imc<=25:
                    $('#imcComm').val("poids normal");
                break;
                default:
                    $('#imcComm').val("sur-poids");
                }
            break;
            case age<25:
                switch (true) {
                case imc<21:
                    $('#imcComm').val("sous-poids");
                break;
                case imc<=26:
                    $('#imcComm').val("poids normal");
                break;
                default:
                    $('#imcComm').val("sur-poids");
                }
            break;
            case age<35:
                switch (true) {
                case imc<22:
                    $('#imcComm').val("sous-poids");
                break;
                case imc<=27:
                    $('#imcComm').val("poids normal");
                break;
                default:
                    $('#imcComm').val("sur-poids");
                }
            break;
            case age<55:
                switch (true) {
                case imc<23:
                    $('#imcComm').val("sous-poids");
                break;
                case imc<=28:
                    $('#imcComm').val("poids normal");
                break;
                default:
                    $('#imcComm').val("sur-poids");
                }
            break;
            case age<65:
                switch (true) {
                case imc<24:
                    $('#imcComm').val("sous-poids");
                break;
                case imc<=29:
                    $('#imcComm').val("poids normal");
                break;
                default:
                    $('#imcComm').val("sur-poids");
                }
            break;
            default: 
                switch (true) {
                case imc<25:
                    $('#imcComm').val("sous-poids");
                break;
                case imc<=30:
                    $('#imcComm').val("poids normal");
                break;
                default:
                    $('#imcComm').val("sur-poids");
                }
            }
        } else {
            switch (true) {
            case age<16:
                $('#imcComm').val("hors contexte");
            break;
            case age<25:
                switch (true) {
                case imc<19:
                    $('#imcComm').val("sous-poids");
                break;
                case imc<=24:
                    $('#imcComm').val("poids normal");
                break;
                default:
                    $('#imcComm').val("sur-poids");
                }
            break;
            case age<35:
                switch (true) {
                case imc<20:
                    $('#imcComm').val("sous-poids");
                break;
                case imc<=25:
                    $('#imcComm').val("poids normal");
                break;
                default:
                    $('#imcComm').val("sur-poids");
                }
            break;
            case age<45:
                switch (true) {
                case imc<21:
                    $('#imcComm').val("sous-poids");
                break;
                case imc<=26:
                    $('#imcComm').val("poids normal");
                break;
                default:
                    $('#imcComm').val("sur-poids");
                }
            break;
            case age<55:
                switch (true) {
                case imc<22:
                    $('#imcComm').val("sous-poids");
                break;
                case imc<=27:
                    $('#imcComm').val("poids normal");
                break;
                default:
                    $('#imcComm').val("sur-poids");
                }
            break;
            case age<65:
                switch (true) {
                case imc<23:
                    $('#imcComm').val("sous-poids");
                break;
                case imc<=28:
                    $('#imcComm').val("poids normal");
                break;
                default:
                    $('#imcComm').val("sur-poids");
                }
            break;
            default: 
                switch (true) {
                case imc<25:
                    $('#imcComm').val("sous-poids");
                break;
                case imc<=30:
                    $('#imcComm').val("poids normal");
                break;
                default:
                    $('#imcComm').val("sur-poids");
                }
            }
        }  
    }






});