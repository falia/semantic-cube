$(document).ready(function(){

    $("#searchButton").click(function(){
        var val = $("#searchField").val();

        $.get( "/search/find?search=" + val, function( data ) {
            $(".main").html( data );
        }).fail(function(){
            alert("An error occurred.")
        });
    });

    $(function(){
        $('.selectpicker').selectpicker();
    });

    $("#searchField").autocomplete({
        source: function( request, response ) {
            $.ajax( {
                url: "search/autocomplete",
                dataType: "jsonp",
                data: {
                    term: request.term,
                    lang: $('#countryselector').val()
                },
                success: function( data ) {
                    response( data );
                }
            } );
        },
        minLength: 2
    });
});