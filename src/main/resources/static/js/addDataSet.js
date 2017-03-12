/**
 * Created by piraujo on 11/03/2017.
 */
$( document ).ready(function() {
    $(function(){
        $('.selectpicker').selectpicker();
    });
    var cptsource=2;
    var cpttag=3;
    $('#addsource').on("click", function () {
       var dataSource = "<fieldset>" +
           "<div>Datasource:</div>" +
           "<div>Type:</div>" +
           "<input type=\"text\" class=\"type form-control\" name=\"type"+cptsource+"\"><br>" +
            "<div>URL:</div>" +
            "<input type=\"text\" class=\"url form-control\" name=\"url"+cptsource+"\"><br>" +
           "</fieldset>";
       $('#dataFieldSet').append(dataSource);
   });
    $('#addTag').on("click",function () {
        var tag = "<fieldset>" +
        "<div>Tag:</div>" +
        "<input type=\"text\" class=\"form-control\" name=\"tag"+ cpttag +"\"><br>" +
        "</fieldset>";
        $('#tags').append(tag);
    });
    $('#submitButton').on("click",function () {
        var json2 = {
            title: $('.title').val(),
            description: $('.descr').val(),
            distribution:[],
            tags:[]
        };
        var cptType=0;
        $('.type').each(function () {
            var tmp ={
                type: "temp",
                url: "temp"
            };
            json2.distribution.push(tmp);
            json2.distribution[cptType].type = $(this).val();
            cptType++;
        });
        var cpturl=0;
        $('.url').each(function () {
            json2.distribution[cpturl].url = $(this).val();
            cpturl++;
        });
        var cpttag=0;
        $('.tag').each(function () {
            var tmp ={
                tag:"tmp"
            };
            json2.tags.push(tmp);
            json2.tags[cpttag].tag = $(this).val();
            cpttag++;
        });
        var formData = JSON.stringify(json2);
        $.post( "datasetupload", formData )
            .done(function() {
                $('#dataset').append("<div class=\"alert alert-success\" role=\"alert\"><strong>Well done!</strong> You successfully uploaded a new dataset</div>");
            });
       /*$.ajax({
           type: "POST",
           url: "datasetupload",
           data: formData,
           success: function(){$('#dataset').append("<div class=\"alert alert-success\" role=\"alert\"><strong>Well done!</strong> You successfully uploaded a new dataset</div>")},
           dataType: "json",
           contentType : "application/json"
       });*/
   });
});
