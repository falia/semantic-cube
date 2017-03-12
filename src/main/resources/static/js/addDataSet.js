/**
 * Created by piraujo on 11/03/2017.
 */
$( document ).ready(function() {
    var cptsource=2;
    var cpttag=3;
    $('#addsource').on("click", function () {
       var dataSource = "<fieldset class=\"fieldset\">" +
           "<div>Datasource:</div>" +
           "<div>Type:</div>" +
           "<input type=\"text\" class=\"type form-control\" name=\"type"+cptsource+"\"><br>" +
           "<div>URL:</div>" +
           "<input type=\"text\" class=\"url form-control\" name=\"url"+cptsource+"\"><br>" +
           "<div>Description:</div>"+
           "<input class=\"datadescr form-control\" type=\"text\" name=\"descr"+cptsource+"\"><br>" +
           "<div> Format:</div>"+
           "<input class=\"format form-control\" type=\"text\" name=\"format"+cptsource+"\"><br>" +
           "<div>Size:</div>"+
           "<input class= \"size form-control\" type=\"text\" name=\"size"+cptsource+"\"><br>"+
           "</fieldset>";
       $('#dataFieldSet').after(dataSource);
   });
    $('#addTag').on("click",function () {
        var tag = "<fieldset>" +
        "<div>Tag:</div>" +
        "<input type=\"text\" class=\"tag form-control\" name=\"tag"+ cpttag +"\"><br>" +
        "</fieldset>";
        $('#tags').after(tag);
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
                url: "temp",
                description:"temp",
                format:"temp",
                size:"temp"
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
        var cptdescr=0;
        $('.datadescr').each(function () {
            json2.distribution[cptdescr].description = $(this).val();
            cptdescr++;
        });
        var cptformat=0;
        $('.format').each(function () {
            json2.distribution[cptformat].format = $(this).val();
            cptformat++;
        });
        var cptsize=0;
        $('.size').each(function () {
            json2.distribution[cptsize].size = $(this).val();
            cptsize++;
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
