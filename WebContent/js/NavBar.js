$(document).ready(function () {
    $('.rdv').click(function () {
        sessionStorage.setItem("pagePrecedente","EnregistrerRdv");
    });
    
    $('.cabinet').click(function () {
        sessionStorage.setItem("pagePrecedente","EnregistrerCabinet");
    });
    
    $('.medecin').click(function () {
        sessionStorage.setItem("pagePrecedente","EnregistrerMedecin");
    });
    
    $('.specialite').click(function () {
        sessionStorage.setItem("pagePrecedente","EnregistrerSpecialite");
    });
    
    $('.dose').click(function () {
        sessionStorage.setItem("pagePrecedente","EnregistrerDose");
    });
    
    $('.medicament').click(function () {
        sessionStorage.setItem("pagePrecedente","EnregistrerMedicament");
    });
    
    $('.ordonnance').click(function () {
        sessionStorage.setItem("pagePrecedente","EnregistrerOrdonnance");
    });
    
});
    
    
    
    
    
    
    
    
