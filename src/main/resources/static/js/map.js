tomtom.setProductInfo('KT5Fjv06WL9YWbIw7a9x6ZZp1WxBwGvM', '4.47.6');

var passengerInitCoordinates = [52.360306, 4.876935];
var passengerIcon = tomtom.L.icon({
    iconUrl: '/static/map-sdk/images/ic_map_poi_048-black.png',
    iconSize: [30, 30],
    iconAnchor: [15, 30],
    popupAnchor: [0, -30]
});
var passengerMarker;

var map = tomtom.L.map('map', {
    key: 'KT5Fjv06WL9YWbIw7a9x6ZZp1WxBwGvM',
    center: passengerInitCoordinates,
    zoom: 14,
});
layer = L.layerGroup().addTo(map);
passengerMarker = tomtom.L.marker(passengerInitCoordinates, {
    icon: passengerIcon
}).addTo(layer);



function convertToLatLon(coordinateString) {
    var result = coordinateString.split(',');
    return {
        lat: parseFloat(result[0]),
        lng: parseFloat(result[1])
    };
}

function drawPassengerMarkerOnMap(geoResponse) {

    if (geoResponse && geoResponse.address && geoResponse.address.freeformAddress) {

        var popupContent = geoResponse.address.freeformAddress;
        passengerMarker = tomtom.L.marker(convertToLatLon(geoResponse.position), {
            icon: passengerIcon
        }).addTo(layer).bindPopup(popupContent).openPopup();
    }
}
map.on('click', function(event) {
    var position = event.latlng;

    tomtom.reverseGeocode().position(position).go().then(function(results) {
        var coord = results.position.split(",")
        var coordinates = {

            lat: (coord[0]),
            lng: (coord[1])
        };

        fetch("http://localhost:8080/points", {
            method: 'PUT',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(coordinates)
        });
        drawPassengerMarkerOnMap(results);
    });


});





map.on('moveend',(e)=>{
                        let url = new URL("http://localhost:8080/points");
                        let params = {
                            northEast_x: e.target.getBounds()._northEast.lat,
                            northEast_y: e.target.getBounds()._northEast.lng,
                            southWest_x: e.target.getBounds()._southWest.lat,
                            southWest_y: e.target.getBounds()._southWest.lng
                         };

                        url.search = new URLSearchParams(params).toString()
                        let promise = fetch(url);


                        promise.then(result => result.json()).
                                then(result =>{
                                    layer.clearLayers();
                                    for (i in result) {
                                            tomtom.L.marker([result[i].lat, result[i].lng], {
                                                icon: passengerIcon
                                            }).addTo(layer)
                                              .bindPopup('A pretty CSS3 popup.<br> Easily customizable.');
                                        }
                                });

                });
