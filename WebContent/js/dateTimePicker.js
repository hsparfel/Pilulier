$(document).ready(function() {
	$('#input').datetimepicker({ footer: true, modal: true });
	$('#date-fr').bootstrapMaterialDatePicker({ format : 'DD/MM/YYYY HH:mm', lang : 'fr', weekStart : 1, cancelText : 'ANNULER' });
});