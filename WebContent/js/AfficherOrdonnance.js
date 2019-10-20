$(document).ready(function() {
    $('.btnPlus').click(function() {
        //adapter a ordonnance
        sessionStorage.setItem("rdvIdMedecin", $('#idMedecin').val());
        sessionStorage.setItem("rdvDate", $('#date').val());
        sessionStorage.setItem("rdvHeure", $('#heure').val());
        sessionStorage.setItem("rdvCommentaire", $('#commentaire').val());
    });

    // $('.btnCancel').click(function() {
    // sessionStorage.removeItem("specialiteNom");
    // });

    for (let i=1; i<4;i++) {
        //analyses
        $('#btnModifierAnalyse'+i).click(function() {
            $('#analyse'+i+'Ligne1').removeClass("d-none");
            $('#ajouterCommentaireAnalyse'+i).removeClass("d-none");
            $('#btnModifierAnalyse'+i).addClass("d-none");
            $('#btnSupprimerAnalyse'+i).addClass("d-none");
            $('#recapAnalyse'+i).addClass("d-none");
            $('#btnValiderAnalyse'+i).removeClass("d-none");
            $('#btnAnnulerAnalyse'+i).removeClass("d-none");
            if ($('#commentAnalyse'+i).text()!=""){
                $('#commentAnalyse'+i).removeClass("d-none");
            }
        });

        $('#btnAnnulerAnalyse'+i).click(function() {
            $('#analyse'+i+'Ligne1').addClass("d-none");
            $('#ajouterCommentaireAnalyse'+i).addClass("d-none");
            $('#btnModifierAnalyse'+i).removeClass("d-none");
            $('#btnSupprimerAnalyse'+i).removeClass("d-none");
            $('#recapAnalyse'+i).removeClass("d-none");
            $('#btnValiderAnalyse'+i).addClass("d-none");
            $('#btnAnnulerAnalyse'+i).addClass("d-none");
            $('#commentAnalyse'+i).addClass("d-none");

        });

        //examens
        $('#btnModifierExamen'+i).click(function() {
            $('#examen'+i+'Ligne1').removeClass("d-none");
            $('#ajouterCommentaireExamen'+i).removeClass("d-none");
            $('#btnModifierExamen'+i).addClass("d-none");
            $('#btnSupprimerExamen'+i).addClass("d-none");
            $('#recapExamen'+i).addClass("d-none");
            $('#btnValiderExamen'+i).removeClass("d-none");
            $('#btnAnnulerExamen'+i).removeClass("d-none");
            if ($('#commentExamen'+i).text()!=""){
                $('#commentExamen'+i).removeClass("d-none");
            }
        });

        $('#btnAnnulerExamen'+i).click(function() {
            $('#examen'+i+'Ligne1').addClass("d-none");
            $('#ajouterCommentaireExamen'+i).addClass("d-none");
            $('#btnModifierExamen'+i).removeClass("d-none");
            $('#btnSupprimerExamen'+i).removeClass("d-none");
            $('#recapExamen'+i).removeClass("d-none");
            $('#btnValiderExamen'+i).addClass("d-none");
            $('#btnAnnulerExamen'+i).addClass("d-none");
            $('#commentExamen'+i).addClass("d-none");
        });
    }
    for (let i=1; i<6;i++) {
        //prescriptions
        $('#btnModifierPrescription'+i).click(function() {
            $('#prescription'+i+'Ligne1').removeClass("d-none");
            $('#prescription'+i+'Ligne2').removeClass("d-none");
            $('#ajouterCommentairePrescription'+i).removeClass("d-none");
            $('#btnModifierPrescription'+i).addClass("d-none");
            $('#btnSupprimerPrescription'+i).addClass("d-none");
            $('#recapPrescription'+i).addClass("d-none");
            $('#btnValiderPrescription'+i).removeClass("d-none");
            $('#btnAnnulerPrescription'+i).removeClass("d-none");
            if ($('#commentPrescription'+i).text()!=""){
                $('#commentPrescription'+i).removeClass("d-none");
            }
        });

        $('#btnAnnulerPrescription'+i).click(function() {
            $('#prescription'+i+'Ligne1').addClass("d-none");
            $('#prescription'+i+'Ligne2').addClass("d-none");
            $('#ajouterCommentairePrescription'+i).addClass("d-none");
            $('#btnModifierPrescription'+i).removeClass("d-none");
            $('#btnSupprimerPrescription'+i).removeClass("d-none");
            $('#recapPrescription'+i).removeClass("d-none");
            $('#btnValiderPrescription'+i).addClass("d-none");
            $('#btnAnnulerPrescription'+i).addClass("d-none");
            $('#commentPrescription'+i).addClass("d-none");
        });
    }





});