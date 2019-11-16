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
    zoom: 14
});
passengerMarker = tomtom.L.marker(passengerInitCoordinates, {
    icon: passengerIcon
}).addTo(map);



function convertToLatLon(coordinateString) {
    var result = coordinateString.split(',');
    return {
        lat: parseFloat(result[0]),
        lng: parseFloat(result[1])
    };
}

function drawPassengerMarkerOnMap(geoResponse) {

    if (geoResponse && geoResponse.address && geoResponse.address.freeformAddress) {
        map.removeLayer(passengerMarker);
        var popupContent = geoResponse.address.freeformAddress;
        passengerMarker = tomtom.L.marker(convertToLatLon(geoResponse.position), {
            icon: passengerIcon
        }).addTo(map).bindPopup(popupContent).openPopup();
    }
}
map.on('click', function(event) {
    var position = event.latlng;

    tomtom.reverseGeocode().position(position).go().then(function(results) {
        var coord = results.position.split(",")
        var coordinates = {
            x: parseFloat(coord[0]),
            y: parseFloat(coord[1])
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




let promise = fetch("http://localhost:8080/points");
promise.then(
    (result) => result.json()
).then((result) => {
    for (i in result) {
        tomtom.L.marker([result[i].x, result[i].y], {
            icon: passengerIcon
        }).addTo(map);
    }
});