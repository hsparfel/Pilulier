$(document).ready(function () {

    $('.btnSubmit').click(function () {
        sessionStorage.setItem("medecinSpecialite", $('#nomSpecialite').val());
    });

});