/**
 * Created by piraujo on 11/03/2017.
 */
$( document ).ready(function() {
    var cptsource=2;
    var cpttag=3;
    $('#addsource').on("click", function () {
       var dataSource = "<fieldset>" +
           "<div>Datasource:</div>" +
           "<div>Type:</div>" +
           "<input type=\"text\" class=\"type\" name=\"type"+cptsource+"\"><br>" +
            "<div>URL:</div>" +
            "<input type=\"text\" class=\"url\" name=\"url"+cptsource+"\"><br>" +
           "</fieldset>";
       $('#dataFieldSet').append(dataSource);
   });
    $('#addTag').on("click",function () {
        var tag = "<fieldset>" +
        "<div>Tag:</div>" +
        "<input type=\"text\" name=\"tag"+ cpttag +"\"><br>" +
        "</fieldset>";
        $('#tags').append(tag);
    });
    $('#submitButton').on("click",function () {
        var json = "{ \"Dataset\":" +
            "{\"title\":"+ "\""+$('.title').val() +"\"" +
            "\",description\" :"+ "\"" +$('.descr').val() + "\""+
            "\",distribution\" :[";
        $('.type').each(function () {
            json = json + "{\"type\":"+ "\""+ $(this).val()+ "\""+
               ",\"url\":"+ "\""+$('.url').val()+ "\"" + "}";
        });
        //json = json + ;
       $.ajax({
           type: "POST",
           url: "serverUrl",
           data: formData,
           success: function(){},
           dataType: "json",
           contentType : "application/json"
       });
   });
});
