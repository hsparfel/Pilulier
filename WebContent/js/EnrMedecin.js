$(document)
        .ready(
                function () {

                    // listeners

                    // verif des données saisies dans nomTelephone
                    $('#nomTelephone').change(function (event) {

                        // def du regex
                        let regexTelephone = /^[0-9]{10}$/;
                        if (!regexTelephone.test($('#nomTelephone').val()) || $('#nomTelephone').val().length != 10) {
                            $('#textHelpBlockNomTelephone').removeClass("d-none");
                            $('#nomTelephone').focus();
                        } else {
                            $('#textHelpBlockNomTelephone').addClass("d-none");
                        }
                    });

                    $('#nomEmail')
                            .change(
                                    function (event) {

                                        // def du regex
                                        let regexTelephone = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
                                        if (!regexTelephone.test($('#nomEmail').val()) || $('#nomEmail').val().length != 10) {
                                            $('#textHelpBlockNomEmail').removeClass("d-none");
                                            $('#nomEmail').focus();
                                        } else {
                                            $('#textHelpBlockNomEmail').addClass("d-none");
                                        }
                                    });

                    $('.btnSubmit').click(function () {
                        // sauvegarde des donnees temporaires formulaires
                        sessionStorage.setItem("associerMedecinNom", $('#nomMedecin').val());
                        sessionStorage.setItem("medecinNom", $('#nomMedecin').val());
                        sessionStorage.removeItem("medecinSpecialite");
                        sessionStorage.removeItem("medecinCabinet");
                        sessionStorage.removeItem("medecinTelephone");
                        sessionStorage.removeItem("medecinMail");
                    });

                    $('.btnPlus').click(function () {
                        // sauvegarde des donnees temporaires formulaires
                        sessionStorage.setItem("medecinNom", $('#nomMedecin').val());
                        sessionStorage.setItem("associerMedecinNom", $('#nomMedecin').val());
                        sessionStorage.setItem("medecinSpecialite", $('#idSpecialite option:selected').text());
                        sessionStorage.setItem("medecinCabinet", $('#idCabinet option:selected').text());
                        sessionStorage.setItem("medecinTelephone", $('#nomTelephone').val());
                        sessionStorage.setItem("medecinMail", $('#nomEmail').val());

                        // sauvegarde de l'url pour retour
                        let chemin = $(location).attr('pathname');
                        let chemin2 = chemin.substr(1, chemin.length - 1);
                        let indiceSlash = chemin2.indexOf("/");
                        let page = chemin2.substr(indiceSlash + 1, chemin.length - indiceSlash);
                        sessionStorage.setItem("pagePrecedente", page);
                    });

                    // affichage des données temporaires
                    $('[name=idSpecialite] option').filter(function () {
                        return ($(this).text() == sessionStorage.getItem("medecinSpecialite"));
                    }).prop('selected', true);

                    $('[name=idCabinet] option').filter(function () {
                        return ($(this).text() == sessionStorage.getItem("medecinCabinet"));
                    }).prop('selected', true);

                    $('#nomMedecin').val(sessionStorage.getItem("medecinNom"));
                    $('#nomTelephone').val(sessionStorage.getItem("medecinTelephone"));
                    $('#nomEmail').val(sessionStorage.getItem("medecinMail"));

                });