$(document).ready(function(){

    $("#searchButton").click(function(){
        var val = $("#searchField").val();

        $.get( "/search/find?search=" + val, function( data ) {
            $(".main").html( data );
        }).fail(function(){
            alert("An error occurred.")
        });
    });

});