$(document).ready(function() {
	
	     // listeners

            // verif des données saisies dans cp
            $('#cpCabinet').focusout(function (event) {
                // def du regex
                let regexCp = /^(\(?\+?[0-9]*\)?)?[0-9_\- \(\)]*$/g;
                if (!regexCp.test($('#cpCabinet').val()) || $('#cpCabinet').val().length!=5) {
                    $('#textHelpBlockCpCabinet').removeClass("d-none");
                    $('#cpCabinet').focus();
                } else {
                    $('#textHelpBlockCpCabinet').addClass("d-none");
                }
            });
            
            $('.btnSubmit').click(function() {
                sessionStorage.setItem("medecinCabinet",$('#nomCabinet').val());
            });
});