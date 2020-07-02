let working = false;

async function loadAllCountries() {
    if (working) {
        return false;
    }
    working = true;
    let dataToGetCountries = new FormData();
    dataToGetCountries.append("command", "ALL_COUNTRIES");
    let response = await fetch("/RoomsWithLightBulbs/ajax", {
        method: 'POST',
        body: dataToGetCountries,
    });

    if (response.ok) {
        let json = await response.json();
        const $select = $("#dropdownlist");
        $select.find("option").remove();
        for (let i in json.country) {
            $("<option>").val(json.country[i]).text(json.country[i]).appendTo($select);
        }
    } else {
        alert("ERROR: " + response.status);
    }
}


$(document).ready(function () {
    $("#btnSubmit").click(function (e) {
        e.preventDefault();
        $("#show2").find("tr").remove();
        $("#show2").find("tbody").remove();
        $("#show2").find("thead").remove();
        $.ajax({
            type: 'POST',
            url: '/RoomsWithLightBulbs/ajax?command=ALL_ROOMS',
            dataType: "json",
            success: function (data) {
                let trHTML = '<thead><tr>' +
                    '            <th>Title</th>\t' +
                    '            <th>Country</th>\t' +
                    '            <th>Light is</th>\t' +
                    '            <th>Button</th>\t' +
                    '        </tr></thead><tbody>';
                for (let i in data.roomList) {
                    trHTML += '<tr class=\"temp\"><td class=\"roomsName\">' + data.roomList[i].roomsName +
                        '\t</td><td class=\"country\">' + data.roomList[i].country +
                        '\t</td><td class=\"lightStatus\">' + data.roomList[i].lightStatus +
                        '\t</td><td><button type=\"submit\" id=\"showRoom\"' +
                        '>Enter</button>' +
                        '\t</td></tr>';
                }
                trHTML += '</tbody>';
                $("#show2").append(trHTML);
            },
            error: function (e) {
                $("#show2").html(e.responseText);
            }
        });
    });
});


let localRoomName;
let localCountry;
let localLight;
$(document).ready(function () {
    $("#show2").on('click', '#showRoom', function () {
        let currentRow = $(this).closest("tr");

        localRoomName = currentRow.find(".roomsName").text();
        localCountry = currentRow.find(".country").text();
        localLight = currentRow.find(".lightStatus").text();
        //let data = localRoomName + "\n" + localCountry + "\n" + localLight;
        //alert(data);
    });
});


async function enterTheRoom(roomName, roomCountry, light) {
    if (usersCountry === roomCountry.toUpperCase().trim()) {
        $('.poup').fadeIn();
        $("#text span").append(localLight);
    } else {
        alert("You can't enter this room!");
    }
}

function changeValue() {
    return localLight = localLight !== true;

}

let usersCountry;
//jquery to find user's country by ip
$('body').on('click', '#showRoom', function (event) {
    event.preventDefault();
    $.ajax({
        url: "https://get.geojs.io/v1/ip/geo.js",
        dataType: "jsonp",
        jsonpCallback: "geoip",
        success: function (data) {
            usersCountry = data.country.toUpperCase().trim();
            console.log(usersCountry);
            enterTheRoom(localRoomName, localCountry, localLight);
        },
        error: function () {
            $(this).replaceWith("Problem to find where are you from..")
        }
    });
});


$(document).ready(function () {
    $('.poup .close').click(function () {
        $('.poup').fadeOut();
    });
});


window.onload = function () {
    let a = document.getElementById('switch');
    a.onclick = function () {
        if (this.innerHTML == 'true') {
            this.innerHTML = 'false';
        } else {
            this.innerHTML = 'true';
        }
        return false;
    }
};

