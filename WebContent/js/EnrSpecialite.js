$(document).ready(function () {
    $('#pagePrecedente').val(sessionStorage.getItem("pagePrecedente"));
    $('.btnSubmit').click(function () {
        sessionStorage.setItem("medecinSpecialite", $('#nomSpecialite').val());
    });

});