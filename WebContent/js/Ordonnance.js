$(document).ready(function () {
    // def du regex
    let regexDate = /^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/;
    // verif des données saisies dans dates
    $('#date').change(function (event) {

        if (!regexDate.test($('#date').val())) {
            $('#textHelpBlockDate').removeClass("d-none");
            $('#date').focus();
        } else {
            $('#textHelpBlockDate').addClass("d-none");
        }
    });

    $('#dateAnalyse1').change(function (event) {
        if (!regexDate.test($('#dateAnalyse1').val())) {
            $('#textHelpBlockDateAnalyse1').removeClass("d-none");
            $('#dateAnalyse1').focus();
        } else {
            $('#textHelpBlockDateAnalyse1').addClass("d-none");
        }
    });

    $('#dateAnalyse2').change(function (event) {
        if (!regexDate.test($('#dateAnalyse2').val())) {
            $('#textHelpBlockDateAnalyse2').removeClass("d-none");
            $('#dateAnalyse2').focus();
        } else {
            $('#textHelpBlockDateAnalyse2').addClass("d-none");
        }
    });

    $('#dateAnalyse3').change(function (event) {
        if (!regexDate.test($('#dateAnalyse3').val())) {
            $('#textHelpBlockDateAnalyse3').removeClass("d-none");
            $('#dateAnalyse3').focus();
        } else {
            $('#textHelpBlockDateAnalyse3').addClass("d-none");
        }
    });

    $('#dateExamen1').change(function (event) {
        if (!regexDate.test($('#dateExamen1').val())) {
            $('#textHelpBlockDateExamen1').removeClass("d-none");
            $('#dateExamen1').focus();
        } else {
            $('#textHelpBlockDateExamen1').addClass("d-none");
        }
    });
    $('#dateExamen2').change(function (event) {
        if (!regexDate.test($('#dateExamen2').val())) {
            $('#textHelpBlockDateExamen2').removeClass("d-none");
            $('#dateExamen2').focus();
        } else {
            $('#textHelpBlockDateExamen2').addClass("d-none");
        }
    });
    $('#dateExamen3').change(function (event) {
        if (!regexDate.test($('#dateExamen3').val())) {
            $('#textHelpBlockDateExamen3').removeClass("d-none");
            $('#dateExamen3').focus();
        } else {
            $('#textHelpBlockDateExamen3').addClass("d-none");
        }
    });



    //ordonnance
    $('#ajouterCommentaire').click(function () {
        $('#comment').removeClass('d-none');
        $('#ajouterCommentaire').addClass('d-none');
    });
    $('#enleverCommentaire').click(function () {
        $('#ajouterCommentaire').removeClass('d-none');
        $('#comment').addClass('d-none');
        $('#commentaire').val("");
    });
    //  boutons ajouterx3
    $('#ajouterPrescription').click(function () {
        console.log("prescription");
        console.log($('.nbPrescription').val());
        if (parseInt($('.nbPrescription').val())+1<=5) {
            console.log("dedans");
            if ($('#prescription1').hasClass('d-none')) {
                $('#prescription1').removeClass('d-none');
            } else {
                if ($('#prescription2').hasClass('d-none')) {
                    $('#prescription2').removeClass('d-none');
                } else {
                    if ($('#prescription3').hasClass('d-none')) {
                        $('#prescription3').removeClass('d-none');
                    } else {
                        if ($('#prescription4').hasClass('d-none')) {
                            $('#prescription4').removeClass('d-none');
                        } else {
                            $('#prescription5').removeClass('d-none');
                        }
                    }
                }
            }
            $('.nbPrescription').val(parseInt($('.nbPrescription').val())+1)
            if (parseInt($('.nbPrescription').val())==5) {
                $(this).addClass('d-none');
            }
        }
    });
    $('#ajouterAnalyse').click(function () {
        console.log($('.nbAnalyse').val());
        if (parseInt($('.nbAnalyse').val())+1<=3) {
            if ($('#analyse1').hasClass('d-none')) {
                $('#analyse1').removeClass('d-none');
            } else {
                if ($('#analyse2').hasClass('d-none')) {
                    $('#analyse2').removeClass('d-none');
                } else {
                    $('#analyse3').removeClass('d-none');
                } 
            }
        }
        $('.nbAnalyse').val(parseInt($('.nbAnalyse').val())+1)
        if (parseInt($('.nbAnalyse').val())==3) {
            $(this).addClass('d-none');
        }
    });
    $('#ajouterExamen').click(function () {
        if (parseInt($('.nbExamen').val())+1<=3) {
            if ($('#examen1').hasClass('d-none')) {
                $('#examen1').removeClass('d-none');
            } else {
                if ($('#examen2').hasClass('d-none')) {
                    $('#examen2').removeClass('d-none');
                } else {
                    $('#examen3').removeClass('d-none');
                } 
            }
            $('.nbExamen').val(parseInt($('.nbExamen').val())+1)
            if (parseInt($('.nbExamen').val())==3) {
                $(this).addClass('d-none');
            }
        }
    });
    //  fin boutons ajouterx3
    //fin ordonnance

    //prescription

    // boucle prescriptionx5
    //prescription i

    let commentairePrescription = [];

    for (let i=1; i<6;i++) {
        commentairePrescription[i]=false;
        //afficher ou masquer radio et check buttons
        $('#idFrequence'+i).on('change',function() {togglePriseJour()});
        $('#quantiteFrequence'+i).on('change',function() {togglePriseJour()});
        function togglePriseJour() {
            let frequence = $('#idFrequence'+i+' option:selected').text();
            let qteFrequence = $('#quantiteFrequence'+i).val();
            if (frequence == "jour" && qteFrequence <3 && qteFrequence >0) {
                if (qteFrequence == "1") {
                    $('#freqRadio'+i).removeClass("d-none");
                    $('#freqCheck'+i+' :checkbox').prop('checked', false).parent().removeClass('active');
                    $('#freqCheck'+i).addClass("d-none");
                    $('#frequenceCheckbox'+i+'_0').attr("disabled", false);
                    $('#frequenceCheckbox'+i+'_1').attr("disabled", false);
                    $('#frequenceCheckbox'+i+'_2').attr("disabled", false);
                } else {
                    $('#freqCheck'+i).removeClass("d-none");
                    $('#freqRadio'+i+' :radio').prop('checked', false).parent().removeClass('active');
                    $('#freqCheck'+i+' :checkbox').prop('checked', false).parent().removeClass('active');
                    $('#freqRadio'+i).addClass("d-none");
                }
            } else {
                $('#freqRadio'+i+' :radio').prop('checked', false).parent().removeClass('active');
                $('#freqCheck'+i+' :checkbox').prop('checked', false).parent().removeClass('active');
                $('#freqRadio'+i).addClass("d-none");
                $('#freqCheck'+i).addClass("d-none");
                $('#frequenceCheckbox'+i+'_0').attr("disabled", false);
                $('#frequenceCheckbox'+i+'_1').attr("disabled", false);
                $('#frequenceCheckbox'+i+'_2').attr("disabled", false);
            }
        }
        //fin afficher ou masquer radio et check buttons
        //verif nb checkbox cochés
        $('#freqCheck'+i).click(function () {
            let nbCheckboxChecked = 0;
            if ($('#frequenceCheckbox'+i+'_0').is(':checked')) {
                nbCheckboxChecked++;
            }
            if ($('#frequenceCheckbox'+i+'_1').is(':checked')) {
                nbCheckboxChecked++;
            }
            if ($('#frequenceCheckbox'+i+'_2').is(':checked')) {
                nbCheckboxChecked++;
            }
            if (nbCheckboxChecked==$('#quantiteFrequence'+i).val()){
                for (j=0;j<3;j++) {
                    if (!$('#frequenceCheckbox'+i+'_'+j).is(':checked')) {
                        $('#frequenceCheckbox'+i+'_'+j).attr("disabled", true);
                    }
                }
            } else {
                $('#frequenceCheckbox'+i+'_0').attr("disabled", false);
                $('#frequenceCheckbox'+i+'_1').attr("disabled", false);
                $('#frequenceCheckbox'+i+'_2').attr("disabled", false);
            }
        });
        //fin verif nb checkbox cochés
        //afficher ou masquer commentaire
        $('#ajouterCommentairePrescription'+i).click(function () {
            $('#commentPrescription'+i).removeClass('d-none');
            $('#ajouterCommentairePrescription'+i).addClass('d-none');
            commentairePrescription[i]=true;
        });
        $('#enleverCommentairePrescription'+i).click(function () {
            $('#ajouterCommentairePrescription'+i).removeClass('d-none');
            $('#commentPrescription'+i).addClass('d-none');
            $('#commentairePrescription'+i).val("");
            commentairePrescription[i]=false;
        });
        //fin afficher ou masquer commentaire
        //action boutons  x3
        $('#btnValiderPrescription'+i).click(function () {
console.log("prq?");
            //verif si les 7 champs de base sont remplis
            if ($('#idMedicament'+i).val()==null){
                $('#idMedicament'+i).focus();
                return;
            }
            if ($('#quantiteDose'+i).val()=="0"){
                $('#quantiteDose'+i).focus();
                return;
            }
            if ($('#idDose'+i).val()==null){
                $('#idDose'+i).focus();
                return;
            }
            if ($('#quantiteFrequence'+i).val()=="0"){
                $('#quantiteFrequence'+i).focus();
                return;
            }
            if ($('#idFrequence'+i).val()==null){
                $('#idFrequence'+i).focus();
                return;
            }
            if ($('#nbDuree'+i).val()=="0"){
                $('#nbDuree'+i).focus();
                return;
            }
            if ($('#idDuree'+i).val()==null){
                $('#idDuree'+i).focus();
                return;
            }
//          verif quand 1 prise par jour si radio bien checké
            let detailPrise ="";
            if($('#quantiteFrequence'+i).val()==1 && $('#idFrequence'+i+' option:selected').text()=="jour"){
                if (!$('#frequenceRadio'+i+'_0').is(':checked') 
                        && !$('#frequenceRadio'+i+'_1').is(':checked') 
                        && !$('#frequenceRadio'+i+'_2').is(':checked')) {
                    $('#frequenceRadio'+i+'_0').focus();
                    return;
                } else {
                    detailPrise+=" (";
                    if ($('#frequenceRadio'+i+'_0').is(':checked')) {
                        detailPrise+="matin";
                    }
                    if ($('#frequenceRadio'+i+'_1').is(':checked')) {
                        detailPrise+="midi";
                    }
                    if ($('#frequenceRadio'+i+'_2').is(':checked')) {
                        detailPrise+="soir";
                    }
                    detailPrise+=")"}        
            }
//          verif quand 2 prises par jour 2 checkbox bien cochés
            if($('#quantiteFrequence'+i).val()==2 && $('#idFrequence'+i+' option:selected').text()=="jour"){
                let verifSaisie2 = 0;
                if ($('#frequenceCheckbox'+i+'_0').is(':checked')) {
                    verifSaisie2++;
                }
                if ($('#frequenceCheckbox'+i+'_1').is(':checked')) {
                    verifSaisie2++;
                }
                if ($('#frequenceCheckbox'+i+'_2').is(':checked')) {
                    verifSaisie2++;
                }
                if (verifSaisie2!=2) {
                    if (!$('#frequenceCheckbox'+i+'_2').is(':checked')) {
                        $('#frequenceCheckbox'+i+'_2').focus();
                    }
                    if (!$('#frequenceCheckbox'+i+'_1').is(':checked')) {
                        $('#frequenceCheckbox'+i+'_1').focus();
                    }
                    if (!$('#frequenceCheckbox'+i+'_0').is(':checked')) {
                        $('#frequenceCheckbox'+i+'_0').focus();
                    }
                    return;
                } else {
                    let detailPriseStarted=false;
                    detailPrise+=" (";
                    if ($('#frequenceCheckbox'+i+'_0').is(':checked')) {
                        detailPrise+="matin";
                        detailPriseStarted=true;
                    }
                    if ($('#frequenceCheckbox'+i+'_1').is(':checked')) {
                        if (detailPriseStarted) {
                            detailPrise+=", midi";   
                        } else {
                            detailPrise+="midi";
                            detailPriseStarted=true;
                        }
                    }
                    if ($('#frequenceCheckbox'+i+'_2').is(':checked')) {
                        if (detailPriseStarted) {
                            detailPrise+=", soir";   
                        } else {
                            detailPrise+="soir"; 
                        }
                    }
                    detailPrise+=") ";
                }
            }
            //traitement si tout est ok
            $('#recapPrescription'+i).text(""+$('#idMedicament'+i+' option:selected').text()+" - "+$('#quantiteDose'+i).val()+" "
                    +$('#idDose'+i+' option:selected').text()+", "+$('#quantiteFrequence'+i).val()+" fois par "
                    +$('#idFrequence'+i+' option:selected').text()+detailPrise+" pendant "+$('#nbDuree'+i).val()+" "+$('#idDuree'+i+' option:selected').text());
            $('#btnModifierPrescription'+i).removeClass('d-none');
            $('#btnValiderPrescription'+i).addClass('d-none');
            $('#recapPrescription'+i).removeClass('d-none');
            $('#prescription'+i+'Ligne1').addClass('d-none');
            $('#prescription'+i+'Ligne2').addClass('d-none');
            $('#ajouterCommentairePrescription'+i).addClass('d-none');
            $('#commentPrescription'+i).addClass('d-none');
            $('#prescription'+i+' label').hide();
            $('.btnSubmitAndCancelPrescription'+i).removeClass('d-none');

            $('.btnSubmitAndCancel').removeClass('d-none');

        });
        $('#btnModifierPrescription'+i).click(function () {
            $('#btnValiderPrescription'+i).removeClass('d-none');
            $('#btnModifierPrescription'+i).addClass('d-none');
            $('#recapPrescription'+i).addClass('d-none');
            $('#prescription'+i+'Ligne1').removeClass('d-none');
            $('#prescription'+i+'Ligne2').removeClass('d-none');
            if (!commentairePrescription[i]) {
                $('#ajouterCommentairePrescription'+i).removeClass('d-none');
            } else {
                $('#commentPrescription'+i).removeClass('d-none');
            }
            $('#prescription'+i+' label').show();






            if( $('.nbPrescription').val()==="0" && $('.nbAnalyse').val()==="0" && $('.nbExamen').val()==="0"){
                console.log("ici");
                $('.btnSubmitAndCancel').addClass('d-none');
            }
        });
        $('#btnAnnulerPrescription'+i).click(function () {
            $('#recapPrescription'+i).text("");
            $('#idMedicament'+i).prop('selectedIndex',0);
            $('#quantiteDose'+i).val("0");
            $('#idDose'+i).prop('selectedIndex',0);
            $('#quantiteFrequence'+i).val("0");
            $('#idFrequence'+i).prop('selectedIndex',0);
            $('#nbDuree'+i).val("0");
            $('#idDuree'+i).prop('selectedIndex',0);
            $('#btnModifierPrescription'+i).click();
            $('#idFrequence'+i).change();
            $('#prescription'+i).addClass('d-none');
            $('.nbPrescription').val(parseInt($('.nbPrescription').val())-1)
            $('#btnValiderPrescription'+i).removeClass('d-none');
            $('#btnModifierPrescription'+i).addClass('d-none');
            $('#enleverCommentairePrescription'+i).click();
            if (parseInt($('.nbPrescription').val())<5){
                $('#ajouterPrescription').removeClass('d-none');
            }
            if( $('.nbPrescription').val()==="0" && $('.nbAnalyse').val()==="0" && $('.nbExamen').val()==="0"){
                console.log("ici");
                $('.btnSubmitAndCancel').addClass('d-none');
            }
        });
        //fin action boutons  x3
    }//fin boucle for
    // fin boucle prescriptionx5

    // boucle analysex3
    //analyse i

    let commentaireAnalyse = [];

    for (let i=1; i<4;i++) {
        commentaireAnalyse[i]=false;

        //afficher ou masquer commentaire
        $('#ajouterCommentaireAnalyse'+i).click(function () {
            $('#commentAnalyse'+i).removeClass('d-none');
            $('#ajouterCommentaireAnalyse'+i).addClass('d-none');
            commentaireAnalyse[i]=true;
        });
        $('#enleverCommentaireAnalyse'+i).click(function () {
            $('#ajouterCommentaireAnalyse'+i).removeClass('d-none');
            $('#commentAnalyse'+i).addClass('d-none');
            $('#commentaireAnalyse'+i).val("");
            commentaireAnalyse[i]=false;
        });
        //fin afficher ou masquer commentaire
        //action boutons  x3
        $('#btnValiderAnalyse'+i).click(function () {

            //verif si les 3 champs de base sont remplis
            if ($('#idAnalyse'+i).val()==null){
                $('#idAnalyse'+i).focus();
                return;
            }
            if ($('#idCabinetAnalyse'+i).val()==null){
                $('#idCabinetAnalyse'+i).focus();
                return;
            }
            if ($('#dateAnalyse'+i).val()==""){
                $('#dateAnalyse'+i).focus();
                return;
            }
            //traitement si tout est ok
            $('#recapAnalyse'+i).text(""+$('#idAnalyse'+i+' option:selected').text()+" - "
                    +$('#idCabinetAnalyse'+i+' option:selected').text()
                    +" - "+$('#dateAnalyse'+i).val());
            $('#btnModifierAnalyse'+i).removeClass('d-none');
            $('#btnValiderAnalyse'+i).addClass('d-none');
            $('#recapAnalyse'+i).removeClass('d-none');
            $('#analyse'+i+'Ligne1').addClass('d-none');

            $('#ajouterCommentaireAnalyse'+i).addClass('d-none');
            $('#commentAnalyse'+i).addClass('d-none');
            $('#analyse'+i+' label').hide();
            $('.btnSubmitAndCancelAnalyse'+i).removeClass('d-none');
            $('.btnSubmitAndCancel').removeClass('d-none');
        });
        $('#btnModifierAnalyse'+i).click(function () {

            $('#btnValiderAnalyse'+i).removeClass('d-none');
            $('#btnModifierAnalyse'+i).addClass('d-none');
            $('#recapAnalyse'+i).addClass('d-none');
            $('#analyse'+i+'Ligne1').removeClass('d-none');
            if (!commentaireAnalyse[i]) {
                $('#ajouterCommentaireAnalyse'+i).removeClass('d-none');
            } else {
                $('#commentAnalyse'+i).removeClass('d-none');
            }
            $('#analyse'+i+' label').show();
            if( $('.nbPrescription').val()==="0" && $('.nbAnalyse').val()==="0" && $('.nbExamen').val()==="0"){
                console.log("ici");
                $('.btnSubmitAndCancel').addClass('d-none');
            }
        });
        $('#btnAnnulerAnalyse'+i).click(function () {

            $('#recapAnalyse'+i).text("");
            $('#idAnalyse'+i).prop('selectedIndex',0);
            $('#idCabinetAnalyse'+i).prop('selectedIndex',0);
            //$('#dateAnalyse'+i).val("Sélectionner");
            $('#dateAnalyse'+i).val("");
            //$('#dateAnalyse'+i).attr("placeholder", "Sélectionner").placeholder();
            $('#btnModifierAnalyse'+i).click();
            $('#analyse'+i).addClass('d-none');
            $('.nbAnalyse').val(parseInt($('.nbAnalyse').val())-1)
            $('#btnValiderAnalyse'+i).removeClass('d-none');
            $('#btnModifierAnalyse'+i).addClass('d-none');
            $('#enleverCommentaireAnalyse'+i).click();
            if (parseInt($('.nbAnalyse').val())<3){
                $('#ajouterAnalyse').removeClass('d-none');
            }

            console.log($('.nbPrescription').val());
            console.log($('.nbAnalyse').val());
            console.log($('.nbPrescription').val());

            if( $('.nbPrescription').val()==="0" && $('.nbAnalyse').val()==="0" && $('.nbExamen').val()==="0"){
                console.log("ici");
                $('.btnSubmitAndCancel').addClass('d-none');
            }
        });
        //fin action boutons  x3
    }//fin boucle for
    // fin boucle analysex3

    // boucle examenx3
    //examen i

    let commentaireExamen = [];

    for (let i=1; i<4;i++) {
        commentaireExamen[i]=false;

        //afficher ou masquer commentaire
        $('#ajouterCommentaireExamen'+i).click(function () {
            $('#commentExamen'+i).removeClass('d-none');
            $('#ajouterCommentaireExamen'+i).addClass('d-none');
            commentaireExamen[i]=true;
        });
        $('#enleverCommentaireExamen'+i).click(function () {
            $('#ajouterCommentaireExamen'+i).removeClass('d-none');
            $('#commentExamen'+i).addClass('d-none');
            $('#commentaireExamen'+i).val("");
            commentaireExamen[i]=false;
        });
        //fin afficher ou masquer commentaire
        //action boutons  x3
        $('#btnValiderExamen'+i).click(function () {
            console.log("valider examen");
            //verif si les 3 champs de base sont remplis
            if ($('#idExamen'+i).val()==null){
                $('#idExamen'+i).focus();
                return;
            }
            if ($('#idCabinetExamen'+i).val()==null){
                $('#idCabinetExamen'+i).focus();
                return;
            }
            if ($('#dateExamen'+i).val()==""){
                $('#dateExamen'+i).focus();
                return;
            }
            //traitement si tout est ok
            $('#recapExamen'+i).text(""+$('#idExamen'+i+' option:selected').text()+" - "
                    +$('#idCabinetExamen'+i+' option:selected').text()
                    +" - "+$('#dateExamen'+i).val());
            $('#btnModifierExamen'+i).removeClass('d-none');
            $('#btnValiderExamen'+i).addClass('d-none');
            $('#recapExamen'+i).removeClass('d-none');
            $('#examen'+i+'Ligne1').addClass('d-none');

            $('#ajouterCommentaireExamen'+i).addClass('d-none');
            $('#commentExamen'+i).addClass('d-none');
            $('#examen'+i+' label').hide();
            $('.btnSubmitAndCancelExamen'+i).removeClass('d-none');
            $('.btnSubmitAndCancel').removeClass('d-none');
        });
        $('#btnModifierExamen'+i).click(function () {
            console.log("modifier examen");
            $('#btnValiderExamen'+i).removeClass('d-none');
            $('#btnModifierExamen'+i).addClass('d-none');
            $('#recapExamen'+i).addClass('d-none');
            $('#examen'+i+'Ligne1').removeClass('d-none');
            if (!commentaireExamen[i]) {
                $('#ajouterCommentaireExamen'+i).removeClass('d-none');
            } else {
                $('#commentExamen'+i).removeClass('d-none');
            }
            $('#examen'+i+' label').show();
            if( $('.nbPrescription').val()==="0" && $('.nbAnalyse').val()==="0" && $('.nbExamen').val()==="0"){
                console.log("ici");
                $('.btnSubmitAndCancel').addClass('d-none');
            }
        });
        $('#btnAnnulerExamen'+i).click(function () {
            console.log("annuler examen");
            $('#recapExamen'+i).text("");
            $('#idExamen'+i).prop('selectedIndex',0);
            $('#idCabinetExamen'+i).prop('selectedIndex',0);
            //$('#dateExamen'+i).val("Sélectionner");
            $('#dateExamen'+i).val("");
            //$('#dateExamen'+i).attr("placeholder", "Sélectionner").placeholder();
            $('#btnModifierExamen'+i).click();
            $('#examen'+i).addClass('d-none');
            $('.nbExamen').val(parseInt($('.nbExamen').val())-1)
            $('#btnValiderExamen'+i).removeClass('d-none');
            $('#btnModifierExamen'+i).addClass('d-none');
            $('#enleverCommentaireExamen'+i).click();
            if (parseInt($('.nbExamen').val())<3){
                $('#ajouterExamen').removeClass('d-none');
            }

            if( $('.nbPrescription').val()==="0" && $('.nbAnalyse').val()==="0" && $('.nbExamen').val()==="0"){
                console.log("ici");
                $('.btnSubmitAndCancel').addClass('d-none');
            }
        });
        //fin action boutons  x3
    }//fin boucle for
    // fin boucle analysex3

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
    //fin date du jour

    //
    $('.btnPlus').click(function () {
        console.log("plus");
        // sauvegarde des donnees temporaires formulaires
        sessionStorage.setItem("idMedecin", $("#idMedecin option:selected").text());
        sessionStorage.setItem("date", $('#date').val());
        sessionStorage.setItem("commentaire", $('#commentaire').val());
        sessionStorage.setItem("nbPrescription", $('#nbPrescription').val());
        sessionStorage.setItem("nbAnalyse", $('#nbAnalyse').val());
        sessionStorage.setItem("nbExamen", $('#nbExamen').val());

        for (i=1;i<6;i++) {
            sessionStorage.setItem("idMedicament"+i, $("#idMedicament"+i+" option:selected").text());
            sessionStorage.setItem("quantiteDose"+i, $("#quantiteDose"+i).val());
            sessionStorage.setItem("idDose"+i, $("#idDose"+i+" option:selected").text());
            sessionStorage.setItem("quantiteFrequence"+i, $("#quantiteFrequence"+i).val());
            sessionStorage.setItem("idFrequence"+i, $("#idFrequence"+i+" option:selected").text());
            sessionStorage.setItem("frequenceRadio"+i+"_0", $("#frequenceRadio"+i+"_0").val());
            sessionStorage.setItem("frequenceRadio"+i+"_1", $("#frequenceRadio"+i+"_1").val());
            sessionStorage.setItem("frequenceRadio"+i+"_2", $("#frequenceRadio"+i+"_2").val());
            sessionStorage.setItem("frequenceCheckbox"+i+"_0", $("#frequenceCheckbox"+i+"_0").val());
            sessionStorage.setItem("frequenceCheckbox"+i+"_1", $("#frequenceCheckbox"+i+"_1").val());
            sessionStorage.setItem("frequenceCheckbox"+i+"_2", $("#frequenceCheckbox"+i+"_2").val());
            sessionStorage.setItem("nbDuree"+i, $("#nbDuree"+i).val());
            sessionStorage.setItem("idDuree"+i, $("#idDuree"+i+" option:selected").text());
            sessionStorage.setItem("commentairePrescription"+i, $("#commentairePrescription"+i).val());

            if (i<4){
                sessionStorage.setItem("idAnalyse"+i, $("#idAnalyse"+i+" option:selected").text());
                sessionStorage.setItem("idCabinetAnalyse"+i, $("#idCabinetAnalyse"+i+" option:selected").text());
                sessionStorage.setItem("dateAnalyse"+i, $("#dateAnalyse"+i).val());
                sessionStorage.setItem("commentaireAnalyse"+i, $("#commentaireAnalyse"+i).val());

                sessionStorage.setItem("idExamen"+i, $("#idExamen"+i+" option:selected").text());
                sessionStorage.setItem("idCabinetExamen"+i, $("#idCabinetExamen"+i+" option:selected").text());
                sessionStorage.setItem("dateExamen"+i, $("#dateExamen"+i).val());
                sessionStorage.setItem("commentaireExamen"+i, $("#commentaireExamen"+i).val());
            }

        }



        // sauvegarde de l'url pour retour
        let chemin = $(location).attr('pathname');
        let chemin2 = chemin.substr(1, chemin.length - 1);
        let indiceSlash = chemin2.indexOf("/");
        let page = chemin2.substr(indiceSlash + 1, chemin.length - indiceSlash);
        sessionStorage.setItem("pagePrecedente", page);
    });

    // affichage des données temporaires
    console.log("donnees temp");
    $('[name=idMedecin] option').filter(function () {
        return ($(this).text() == sessionStorage.getItem("idMedecin"));
    }).prop('selected', true);
    $('#date').val(sessionStorage.getItem("date"));
    $('#commentaire').val(sessionStorage.getItem("commentaire"));
    if (sessionStorage.getItem("nbPrescription")!=null){
        if (!sessionStorage.getItem("nbPrescription").isEmpty()){
            $('#nbPrescription').val(sessionStorage.getItem("nbPrescription"));}
    }
    if (sessionStorage.getItem("nbAnalyse")!=null){
        if (!sessionStorage.getItem("nbAnalyse").isEmpty()){
            $('#nbAnalyse').val(sessionStorage.getItem("nbAnalyse"));}
    }
    if (sessionStorage.getItem("nbExamen")!=null){
        if (!sessionStorage.getItem("nbExamen").isEmpty()){
            $('#nbExamen').val(sessionStorage.getItem("nbExamen"));}
    }

    for (i=1;i<6;i++) {

        $("[name=idMedicament"+i+"] option").filter(function () {
            return ($(this).text() == sessionStorage.getItem("idMedicament"+i));
        }).prop('selected', true);
        $("#quantiteDose"+i).val(parseFloat(sessionStorage.getItem("quantiteDose"+i)));
        $("[name=idDose"+i+"] option").filter(function () {
            return ($(this).text() == sessionStorage.getItem("idDose"+i));
        }).prop('selected', true);
        $("#quantiteFrequence"+i).val(sessionStorage.getItem("quantiteFrequence"+i));
        $("[name=idFrequence"+i+"] option").filter(function () {
            return ($(this).text() == sessionStorage.getItem("idFrequence"+i));
        }).prop('selected', true);
        $("frequenceRadio"+i+"_0").val(sessionStorage.getItem("frequenceRadio"+i+"_0"));
        $("frequenceRadio"+i+"_1").val(sessionStorage.getItem("frequenceRadio"+i+"_1"));
        $("frequenceRadio"+i+"_2").val(sessionStorage.getItem("frequenceRadio"+i+"_2"));
        $("frequenceCheckbox"+i+"_0").val(sessionStorage.getItem("frequenceCheckbox"+i+"_0"));
        $("frequenceCheckbox"+i+"_1").val(sessionStorage.getItem("frequenceCheckbox"+i+"_1"));
        $("frequenceCheckbox"+i+"_2").val(sessionStorage.getItem("frequenceCheckbox"+i+"_2"));
        $("nbDuree"+i).val(sessionStorage.getItem("nbDuree"+i));
        $("[name=idDuree"+i+"] option").filter(function () {
            return ($(this).text() == sessionStorage.getItem("idDuree"+i));
        }).prop('selected', true);
        $("commentairePrescription"+i).val(sessionStorage.getItem("commentairePrescription"+i));

        if (i<4){

            $("[name=idAnalyse"+i+"] option").filter(function () {
                return ($(this).text() == sessionStorage.getItem("idAnalyse"+i));
            }).prop('selected', true);
            $("[name=idCabinetAnalyse"+i+"] option").filter(function () {
                return ($(this).text() == sessionStorage.getItem("idCabinetAnalyse"+i));
            }).prop('selected', true);
            $("dateAnalyse"+i).val(sessionStorage.getItem("dateAnalyse"+i));
            $("commentaireAnalyse"+i).val(sessionStorage.getItem("commentaireAnalyse"+i));

            $("[name=idExamen"+i+"] option").filter(function () {
                return ($(this).text() == sessionStorage.getItem("idExamen"+i));
            }).prop('selected', true);
            $("[name=idCabinetExamen"+i+"] option").filter(function () {
                return ($(this).text() == sessionStorage.getItem("idCabinetExamen"+i));
            }).prop('selected', true);
            $("dateExamen"+i).val(sessionStorage.getItem("dateExamen"+i));
            $("commentaireExamen"+i).val(sessionStorage.getItem("commentaireExamen"+i));

        }

    }
    });