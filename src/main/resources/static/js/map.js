
var osmTile = new ol.layer.Tile({
    source: new ol.source.OSM()
})

var vectorSource = new ol.source.Vector({
    projection: 'EPSG:4326',
    url: 'json/addresses',
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
});