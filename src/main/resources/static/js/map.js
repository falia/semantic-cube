
var parser = new ol.format.WMTSCapabilities();
var map;

fetch('http://wmts1.geoportail.lu/opendata/wmts/1.0.0/WMTSCapabilities.xml').then(function(response) {
    return response.text();
}).then(function(text) {
    var result = parser.read(text);
    var options = ol.source.WMTS.optionsFromCapabilities(result,
        {layer: 'ortho_2016', matrixSet: 'EPSG:4326'});

    map = new ol.Map({
        layers: [
            new ol.layer.Tile({
                source: new ol.source.OSM(),
                opacity: 0.7
            }),
            new ol.layer.Tile({
                opacity: 1,
                source: new ol.source.WMTS(options)
            })
        ],
        target: 'map',
        view: new ol.View({
            projection: 'EPSG:4326',
            center: [5.84441104566029,49.8625812370655],
            zoom: 5
        })
    });
});

/*
var osmTile = new ol.layer.Tile({
    source: new ol.source.OSM()
})

var vectorSource = new ol.source.Vector({
    projection: 'EPSG:4326',
    url: 'json/parks',
    format: new ol.format.GeoJSON()
});

// a vector layer to render the source
var vectorLayer = new ol.layer.Vector({
    source: vectorSource
});

var view = new ol.View({
    projection: 'EPSG:4326',
    center: [5.84441104566029,49.8625812370655],
    zoom: 16
});

// the vector layer gets added like a raster layer
var map = new ol.Map({
    target: 'map',
    layers: [osmTile, vectorLayer],
    view: view
});*/
