$(document).ready(function () {

    // listeners

    // verif des données saisies dans date
    $('#date').change(function (event) {
        // def du regex
        let regexDate = /^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/;
        if (!regexDate.test($('#date').val())) {
            $('#textHelpBlockDate').removeClass("d-none");
            $('#date').focus();
        } else {
            $('#textHelpBlockDate').addClass("d-none");
        }
    });

    // verif des données saisies dans heure
    $('#heure').focusout(function (event) {
        // def du regex
        let regexHeure = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;
        if (!regexHeure.test($('#heure').val())) {
            $('#textHelpBlockHeure').removeClass("d-none");
            $('#heure').focus();
        } else {
            $('#textHelpBlockHeure').addClass("d-none");
        }
    });

    $('.btnSubmit').click(function (event) {

        // verif des données saisies
        // def des regex
        let regexDate = /^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/;
        let regexHeure = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;

        // laisser bootstrap gerer tant qu'il n'y pas toutes les
        // infos
        if (!$.isEmptyObject($('#date').val()) && !$.isEmptyObject($('#heure').val()) && !$.isEmptyObject($('#idMedecin').val())) {
            if (!regexHeure.test($('#heure').val()) || !regexDate.test($('#date').val())) {
                event.preventDefault();
               
            } 
        } 
        // sauvegarde des donnees temporaires
        sessionStorage.removeItem("medecinNom");
        sessionStorage.removeItem("rdvDate");
        sessionStorage.removeItem("rdvHeure");
        sessionStorage.removeItem("rdvCommentaire");
    });

    $('.btnPlus').click(function () {
        // sauvegarde des donnees temporaires formulaires
        sessionStorage.setItem("rdvDate", $('#date').val());
        sessionStorage.setItem("rdvHeure", $('#heure').val());
        sessionStorage.setItem("rdvCommentaire", $('#commentaire').val());
        // sauvegarde de l'url pour retour
        let chemin = $(location).attr('pathname');
        let chemin2 = chemin.substr(1, chemin.length - 1);
        let indiceSlash = chemin2.indexOf("/");
        let page = chemin2.substr(indiceSlash + 1, chemin.length - indiceSlash);
        sessionStorage.setItem("pagePrecedente", page);
    });

    // affichage des données temporaires

    $('[name=idMedecin] option').filter(function () {
        return ($(this).text() == sessionStorage.getItem("medecinNom"));
    }).prop('selected', true);

    $('#date').val(sessionStorage.getItem("rdvDate"));
    $('#heure').val(sessionStorage.getItem("rdvHeure"));
    $('#commentaire').val(sessionStorage.getItem("rdvCommentaire"));
});