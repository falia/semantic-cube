$(document).ready(function () {

    function executeSparql() {
        var val = $("#query").val();
        if (val != null && val != '') {
            val = encodeURIComponent(val);
            $.get("/sparql/endpoint?format=" + $("#format").find(":selected").text() + "&query=" + val, function (data) {
                var code = $("#content").find("code");
                code.text(data);

                code.each(function (i, block) {
                    hljs.highlightBlock(block);
                });

            }).fail(function () {
                $("#content").text("An error occurred.");
            });
        }
    }

/*    $("#format").change(function () {
        executeSparql()
    });*/

    $("#sparqlSubmitButton").click(function () {
        executeSparql()
    });

});