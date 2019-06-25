$(document)
.ready(
        function () {
            let commentairePrescription1 = false;
            //console.log("ici");
            $('#idFrequence1').on('change',function() {togglePriseJour()});
            $('#quantiteFrequence1').on('change',function() {togglePriseJour()});

            function togglePriseJour() {
                console.log("dans fonction");
                let frequence = $('#idFrequence1 option:selected').text();
                let qteFrequence = $('#quantiteFrequence1').val();
                console.log("choix frequence: " + frequence);
                console.log("nb frequence: " + qteFrequence);
                console.log(typeof 'qteFrequence');

                if (frequence == "jour" && qteFrequence <4) {
                    if (qteFrequence == "1") {
                        console.log("unique");
                        $('#freqRadio1').removeClass("d-none");
                        $(":checkbox").prop('checked', false).parent()
                        .removeClass('active');
                        $('#freqCheck1').addClass("d-none");
                    } else if (qteFrequence == "2"||qteFrequence == "3") {
                        console.log("multiple");
                        $('#freqCheck1').removeClass("d-none");
                        $(":radio").prop('checked', false).parent().removeClass('active');
                        $('#freqRadio1').addClass("d-none");
                    }
                    console.log("vrai");
                } else {
                    $(":radio").prop('checked', false).parent().removeClass('active');
                    $(":checkbox").prop('checked', false).parent().removeClass('active');
                    $('#freqRadio1').addClass("d-none");
                    $('#freqCheck1').addClass("d-none");
                    console.log("faux");
                }
            }







            $('#btnValiderPrescription1').click(function () {
                $('#recapPrescription1').text(""+$('#idMedicament1 option:selected').text()+" - "+$('#quantiteDose1').val()+" "
                        +$('#idDose1  option:selected').text()+", "+$('#quantiteFrequence1').val()+" fois par "
                        +$('#idFrequence1  option:selected').text()+" pendant "+$('#nbDuree1').val()+" "+$('#idDuree1  option:selected').text());
                $('#btnModifierPrescription1').removeClass('d-none');
                $('#btnValiderPrescription1').addClass('d-none');
                $('#recapPrescription1').removeClass('d-none');
                $('#prescriptionLigne1').addClass('d-none');
                $('#prescriptionLigne2').addClass('d-none');
                $('#ajouterCommentairePrescription1').addClass('d-none');
                $('#commentPrescription1').addClass('d-none');
                $('#prescription1 label').hide();
                
                
            });
            $('#btnModifierPrescription1').click(function () {
                $('#btnValiderPrescription1').removeClass('d-none');
                $('#btnModifierPrescription1').addClass('d-none');
                $('#recapPrescription1').addClass('d-none');
                $('#prescriptionLigne1').removeClass('d-none');
                $('#prescriptionLigne2').removeClass('d-none');
                if (!commentairePrescription1) {
                $('#ajouterCommentairePrescription1').removeClass('d-none');
                } else {
                $('#commentPrescription1').removeClass('d-none');
                }
                $('#prescription1 label').show();
            });
            

            $('#ajouterCommentaire').click(function () {
                $('#comment').removeClass('d-none');
                $('#ajouterCommentaire').addClass('d-none');
                

            });
            $('#enleverCommentaire').click(function () {
                $('#ajouterCommentaire').removeClass('d-none');
                $('#comment').addClass('d-none');
                $('#commentaire').val("");
                
            });

            $('#ajouterCommentairePrescription1').click(function () {
                $('#commentPrescription1').removeClass('d-none');
                $('#ajouterCommentairePrescription1').addClass('d-none');
                commentairePrescription1=true;
            });
            $('#enleverCommentairePrescription1').click(function () {
                $('#ajouterCommentairePrescription1').removeClass('d-none');
                $('#commentPrescription1').addClass('d-none');
                $('#commentairePrescription1').val("");
                commentairePrescription1=false;
            });




            $('#ajouterPrescription').click(function () {
                if (parseInt($('.nbPrescription').text())+1<=5) {
                    let iterPrescription = ""+(parseInt($('.nbPrescription').text())+1);
                    let newPrescription = "#prescription"+iterPrescription;
                    $(newPrescription).removeClass('d-none');
                    $('.nbPrescription').text(parseInt($('.nbPrescription').text())+1)
                    if (parseInt($('.nbPrescription').text())==5) {
                        $(this).prop('disabled',true);
                        $(this).addClass('d-none');
                    }
                }
            });
            $('#ajouterAnalyse').click(function () {
                if (parseInt($('.nbAnalyse').text())+1<=3) {
                    let iterAnalyse = ""+(parseInt($('.nbAnalyse').text())+1);
                    let newAnalyse = "#analyse"+iterAnalyse;
                    $(newAnalyse).removeClass('d-none');
                    $('.nbAnalyse').text(parseInt($('.nbAnalyse').text())+1)
                    if (parseInt($('.nbAnalyse').text())==3) {
                        $(this).prop('disabled',true);
                        $(this).addClass('d-none');
                    }
                }
            });

            $('#ajouterExamen').click(function () {
                if (parseInt($('.nbExamen').text())+1<=3) {
                    let iterExamen = ""+(parseInt($('.nbExamen').text())+1);
                    let newExamen = "#examen"+iterExamen;
                    $(newExamen).removeClass('d-none');
                    $('.nbExamen').text(parseInt($('.nbExamen').text())+1)
                    if (parseInt($('.nbExamen').text())==3) {
                        $(this).prop('disabled',true);
                        $(this).addClass('d-none');
                    }
                }
            });

        });