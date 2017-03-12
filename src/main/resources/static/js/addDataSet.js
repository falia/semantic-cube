/**
 * Created by piraujo on 11/03/2017.
 */


$( document ).ready(function() {

    function distributionAdded(rowElement) {

    }

    function distributionRemoved(rowElement) {

    }

    $('#addTag').on("click",function () {
        var tag = "<fieldset>"
        "<div>Tag:</div>" +
        "<input type=\"text\" class=\"tag form-control\" name=\"tag"+ cpttag +"\"><br>" +
        "</fieldset>";
        $('#tags').after(tag);
    });

    var config = {
        rowClass : 'distribution',
        addRowId : 'addsource',
        removeRowClass : 'removeDistribution',
        formId : 'datasetForm',
        rowContainerId : 'distributionListContainer',
        indexedPropertyName : 'resourceList',
        indexedPropertyMemberNames : 'type,url,descr,format,size',
        rowAddedListener : distributionAdded,
        rowRemovedListener : distributionRemoved,
    };

    new DynamicListHelper(config);
});
