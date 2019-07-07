$(document).ready(function(){

    
    let compteur= parseInt($('.cptr:last').html());
    let ligneTabPoids=[];
    let tabPoids=[];
    let ligneTabTaille=[];
    let tabTaille=[];
    let ligneTabImc=[];
    let tabImc=[];

    for (i=0;i<=compteur;i++){

        ligneTabPoids[1]=parseFloat($('#ligneTableau'+i).children().eq(0).html());
        let tempDate = $('#ligneTableau'+i).children().eq(3).html()
        let tempDateAnnee = tempDate.substring(6,10);
        let tempDateMois = tempDate.substring(3,5);
        let tempDateJour = tempDate.substring(0,2);
        tempDateFormatte=tempDateAnnee+"-"+tempDateMois+"-"+tempDateJour;
        let tempDateFormatteInt=parseInt(tempDateFormatte);
        ligneTabPoids[0]=tempDateFormatteInt;
        tabPoids[i]= ligneTabPoids;
        ligneTabPoids=[];

        ligneTabTaille[1]=parseFloat($('#ligneTableau'+i).children().eq(1).html());
        tempDate = $('#ligneTableau'+i).children().eq(3).html()
        tempDateAnnee = tempDate.substring(6,10);
        tempDateMois = tempDate.substring(3,5);
        tempDateJour = tempDate.substring(0,2);
        tempDateFormatte=tempDateAnnee+"-"+tempDateMois+"-"+tempDateJour;
        tempDateFormatteInt=parseInt(tempDateFormatte);
        ligneTabTaille[0]=tempDateFormatteInt;
        tabTaille[i]= ligneTabTaille;
        ligneTabTaille=[];

        ligneTabImc[1]=parseFloat($('#ligneTableau'+i).children().eq(2).html());
        tempDate = $('#ligneTableau'+i).children().eq(3).html()
        tempDateAnnee = tempDate.substring(6,10);
        tempDateMois = tempDate.substring(3,5);
        tempDateJour = tempDate.substring(0,2);
        tempDateFormatte=tempDateAnnee+"-"+tempDateMois+"-"+tempDateJour;
        tempDateFormatteInt=parseInt(tempDateFormatte);
        ligneTabImc[0]=tempDateFormatteInt;
        tabImc[i]= ligneTabImc;
        ligneTabImc=[];
    }

    $.jqplot('chartdivPoids',  [tabPoids],
            { title:'Poids',
        axesDefaults: {labelRenderer: $.jqplot.CanvasAxisLabelRenderer},
        seriesDefaults: {rendererOptions: {smooth: true}},
        axes:{xaxis: {pad: 0,renderer:$.jqplot.DateAxisRenderer},
            yaxis: {min:0,label: "Kgs"}},
            series:[{color:'#FF5733'}]
            });
    $.jqplot('chartdivTaille',  [tabTaille],
            { title:'Taille',
        axesDefaults: {labelRenderer: $.jqplot.CanvasAxisLabelRenderer},
        seriesDefaults: {rendererOptions: {smooth: true}},
        axes:{xaxis: {pad: 0,renderer:$.jqplot.DateAxisRenderer},
            yaxis: {min:30,label: "cms"}},
            series:[{color:'#3379FF'}]
            });
    $.jqplot('chartdivImc',  [tabImc],
            { title:'IMC',
        axesDefaults: {labelRenderer: $.jqplot.CanvasAxisLabelRenderer},
        seriesDefaults: {rendererOptions: {smooth: true}},
        axes:{xaxis: {pad: 0,renderer:$.jqplot.DateAxisRenderer},
            yaxis: {min:0,label: "Ind"}},
            series:[{color:'#33FF5A'}]
            });
});