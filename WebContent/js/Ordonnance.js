$(document)
        .ready(
                function () {
                    
                    let frequence = $('#idFrequence option:selected').text();
                    let qteFrequence = $('#quantiteFrequence').val();
                    console.log("choix frequence: " + frequence);
                    console.log("nb frequence: " + qteFrequence);
                    console.log(typeof 'qteFrequence');

                    if (frequence == "jour" && qteFrequence < 4) {
                        if (qteFrequence == "1") {
                            console.log("unique");
                            $('#freqRadio').removeClass("d-none");
                            $(":checkbox").prop('checked', false).parent().removeClass('active');
                            $('#freqCheck').addClass("d-none");
                        } else  {
                            console.log("multiple");
                            $('#freqCheck').removeClass("d-none");
                            $(":radio").prop('checked', false).parent().removeClass('active');
                            $('#freqRadio').addClass("d-none");
                        }
                        console.log("vrai");
                    } else {
                        $(":radio").prop('checked', false).parent().removeClass('active');
                        $(":checkbox").prop('checked', false).parent().removeClass('active');
                        $('#freqRadio').addClass("d-none");
                        $('#freqCheck').addClass("d-none");
                        console.log("faux");
                    }
                    
                    
                    
                    
                    
                    
                    $('#ajouterPrescription')
                            .click(
                                    function () {
                                     /*   $('.detailPrescription').append(
                                                '<div class="prescr" id="prescription' + (parseInt($('.nbPrescription').text()) + 1) + '"></div>');
                                        // ajout ligne medicament
                                        $('.prescr')
                                                .append(
                                                        '<div class="form-group row"><label for="idMedicament' + (parseInt($('.nbPrescription').text()) + 1) + '" class="col-1 col-form-label font-weight-lighter text-right">Medicament</label><div class="col-3"><select id="idMedicament' + (parseInt($('.nbPrescription').text()) + 1) + '" name="idMedicament' + (parseInt($('.nbPrescription').text()) + 1) + '" required="required" class="custom-select"><option disabled selected>Sélectionner</option><c:forEach items="'
                                                                + '${ listeMedicaments }'
                                                                + '" var="medicament"><option value="'
                                                                + '${ medicament.id }'
                                                                + '">${ medicament.nom }</option></c:forEach></select></div><a href="EnregistrerMedicament"><span class="fa fa-plus-square-o fa-2x"></span></a></div>');
                                        // ajout ligne dose
                                        $('.prescr')
                                                .append(
                                                        '<div class="form-group row"><label for="quantiteDose' + (parseInt($('.nbPrescription').text()) + 1) + '" class="col-1 col-form-label font-weight-lighter text-right">Dose</label><div class="col-2"><div class="input-group"><div class="input-group-prepend"><div class="input-group-text"><i class="fa fa-spoon"></i></div></div><input id="quantiteDose' + (parseInt($('.nbPrescription').text()) + 1) + '" name="quantiteDose' + (parseInt($('.nbPrescription').text()) + 1) + '" type="number" required="required" class="form-control" step="0.5" value="0" min="0"></div></div><div class="col-3"><select id="idDose' + (parseInt($('.nbPrescription').text()) + 1) + '" name="idDose' + (parseInt($('.nbPrescription').text()) + 1) + '" class="custom-select" required="required"><option disabled selected>Sélectionner dose</option><c:forEach items="${ listeDoses }" var="dose"><option value="${ dose.id }">${ dose.nom }</option></c:forEach></select></div><a href="EnregistrerDose"> <span class="fa fa-plus-square-o fa-2x"></span></a></div>');
                                        // ajout ligne frequence
                                        $('.prescr')
                                                .append(
                                                        '<div class="form-group row"><label for="quantiteFrequence' + (parseInt($('.nbPrescription').text()) + 1) + '" class="col-1 col-form-label font-weight-lighter text-right ">Fréquence</label><div class="col-2"><div class="input-group"><div class="input-group-prepend"><div class="input-group-text"><i class="fa fa-clock-o"></i></div></div><input id="quantiteFrequence' + (parseInt($('.nbPrescription').text()) + 1) + '" name="quantiteFrequence' + (parseInt($('.nbPrescription').text()) + 1) + '" type="number" class="form-control" required="required" value="0" min="0"></div></div><label for="idFrequence' + (parseInt($('.nbPrescription').text()) + 1) + '" class="col-1 col-form-label ">fois par</label><div class="col-2"><select id="idFrequence' + (parseInt($('.nbPrescription').text()) + 1) + '" name="idFrequence' + (parseInt($('.nbPrescription').text()) + 1) + '" required="required" class="custom-select"><option disabled selected>Sélectionnez</option><c:forEach items="${ listeFrequences }" var="frequence"><option value="${ frequence.id }">${ frequence.nom }</option></c:forEach></select></div><a href="EnregistrerFrequence"> <span class="fa fa-plus-square-o fa-2x"></span></a><div id="freqRadio' + (parseInt($('.nbPrescription').text()) + 1) + '" class="form-group row d-none"><div class="col-4"></div><div class="col-8"><div class="custom-controls-stacked"><div class="custom-control custom-radio"><input name="frequenceRadio' + (parseInt($('.nbPrescription').text()) + 1) + '" id="frequenceRadio_0' + (parseInt($('.nbPrescription').text()) + 1) + '" type="radio" class="custom-control-input" value="1"><label for="frequenceRadio_0' + (parseInt($('.nbPrescription').text()) + 1) + '" class="custom-control-label">Matin</label></div></div><div class="custom-controls-stacked"><div class="custom-control custom-radio"><input name="frequenceRadio' + (parseInt($('.nbPrescription').text()) + 1) + '" id="frequenceRadio_1' + (parseInt($('.nbPrescription').text()) + 1) + '" type="radio" class="custom-control-input" value="2"><label for="frequenceRadio_1' + (parseInt($('.nbPrescription').text()) + 1) + '" class="custom-control-label">Midi</label></div></div><div class="custom-controls-stacked"><div class="custom-control custom-radio"><input name="frequenceRadio' + (parseInt($('.nbPrescription').text()) + 1) + '" id="frequenceRadio_2' + (parseInt($('.nbPrescription').text()) + 1) + '" type="radio" class="custom-control-input" value="3"><label for="frequenceRadio_2' + (parseInt($('.nbPrescription').text()) + 1) + '" class="custom-control-label">Soir</label></div></div></div></div><div id="freqCheck' + (parseInt($('.nbPrescription').text()) + 1) + '" class="form-group row d-none"><div class="col-4"></div><div class="col-8"><div class="custom-controls-stacked"><div class="custom-control custom-checkbox"><input name="frequenceCheckbox_0' + (parseInt($('.nbPrescription').text()) + 1) + '" id="frequenceCheckbox_0' + (parseInt($('.nbPrescription').text()) + 1) + '" type="checkbox" class="custom-control-input" value="1"><label for="frequenceCheckbox_0' + (parseInt($('.nbPrescription').text()) + 1) + '" class="custom-control-label">Matin</label></div></div><div class="custom-controls-stacked"><div class="custom-control custom-checkbox"><input name="frequenceCheckbox_1' + (parseInt($('.nbPrescription').text()) + 1) + '" id="frequenceCheckbox_1' + (parseInt($('.nbPrescription').text()) + 1) + '" type="checkbox" class="custom-control-input" value="2"><label for="frequenceCheckbox_1' + (parseInt($('.nbPrescription').text()) + 1) + '" class="custom-control-label">Midi</label> </div></div><div class="custom-controls-stacked"><div class="custom-control custom-checkbox"><input name="frequenceCheckbox_2' + (parseInt($('.nbPrescription').text()) + 1) + '" id="frequenceCheckbox_2' + (parseInt($('.nbPrescription').text()) + 1) + '" type="checkbox" class="custom-control-input" value="3"><label for="frequenceCheckbox_2' + (parseInt($('.nbPrescription').text()) + 1) + '" class="custom-control-label">Soir</label></div> </div></div></div></div>');
                                        // ajout ligne duree
                                        $('.prescr')
                                                .append(
                                                        '<div class="form-group row"><label for="nbDuree' + (parseInt($('.nbPrescription').text()) + 1) + '" class="col-1 col-form-label font-weight-lighter   text-right">Duree</label><label class="col-1 col-form-label ">pendant</label><div class="col-2"><div class="input-group"><div class="input-group-prepend"><div class="input-group-text"><i class="fa fa-calendar-plus-o"></i></div></div><input id="nbDuree' + (parseInt($('.nbPrescription').text()) + 1) + '" name="nbDuree' + (parseInt($('.nbPrescription').text()) + 1) + '" type="number" class="form-control" required="required" value="0" min="0"></div></div><div class="col-2"><select id="idDuree' + (parseInt($('.nbPrescription').text()) + 1) + '" name="idDuree' + (parseInt($('.nbPrescription').text()) + 1) + '" class="custom-select" required="required"><option disabled selected>Sélectionner</option><c:forEach items="${ listeDurees }" var="duree"><option value="${ duree.id }">${ duree.nom }</option></c:forEach></select></div><a href="EnregistrerDuree"> <span class="fa fa-plus-square-o fa-2x"></span></a></div>');

                                        $('.nbPrescription').text(parseInt($('.nbPrescription').text()) + 1);*/

                                    });
                    $('#ajouterAnalyse').click(function () {

                    });
                    $('#ajouterExamen').click(function () {

                    });
                });