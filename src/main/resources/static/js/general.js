$(document).ready(function () {
    let map = L.map('map', {
        center: [51.1642292, 10.4541194],
        zoom: 6
    });
    L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        maxZoom: 18,
        id: 'mapbox/streets-v11',
        tileSize: 512,
        zoomOffset: -1,
        accessToken: 'pk.eyJ1IjoicGx1c21pZCIsImEiOiJjanF3cndqY3MxOTdmNDRtaHhzM3d6ZmtmIn0.wdF1uSS0OSPggWzVQGum2Q'
    }).addTo(map);
    let markers = [];

    function search() {
        let deviceId = $('#search').val();
        if (deviceId === '') {
            deviceId = 'all';
        }
        $.ajax('./position/show/' + deviceId, {
            success: function (result) {
                console.log(result);
                if (result.length === 0) {
                    alert('This device ID doesn\'t exist or has never sent a location.');
                    return;
                }
                for (let marker of markers) {
                    marker.remove();
                }
                markers = [];
                for (let pos of result) {
                    markers.push(L.marker([pos.lat, pos.lon], {
                        title: pos.timestamp
                    }).addTo(map));
                }
            },
            error: function (xhr, status, error) {
                console.error(status, error);
                alert('There was a problem requesting the Device ID. Please try again.');
            }
        });
    }

    search();
});