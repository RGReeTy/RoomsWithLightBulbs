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

let roomList;
$(document).ready(function () {
    $("#btnSubmit").click(function (e) {
        e.preventDefault();
        $("#show2").find("tr").remove();
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
                roomList = data;
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


//jquery to find user's country by ip
$('body').on('click', '#showRoom', function (event) {
    event.preventDefault();
    $.ajax({
        url: "https://get.geojs.io/v1/ip/geo.js",
        dataType: "jsonp",
        jsonpCallback: "geoip",
        success: function (data) {
            usersCountry = data.country;
            console.log(usersCountry);
        },
        error: function () {
            $(this).replaceWith("Problem to find where are you from..")
        }
    });
});


$(document).ready(function () {
    $("#show2").on('click', '#showRoom', function () {
        let currentRow = $(this).closest("tr");

        let col1 = currentRow.find(".roomsName").text();
        let col2 = currentRow.find(".country").text();
        let col3 = currentRow.find(".lightStatus").text();
        let data = col1 + "\n" + col2 + "\n" + col3;

        alert(data);
    });
});


async function enterTheRoom(roomName, roomCountry, light) {
    let usersCountry = 'POLAND';
    $.ajax({
        url: "https://get.geojs.io/v1/ip/geo.js",
        dataType: "jsonp",
        jsonpCallback: "geoip",
        success: function (data) {
            console.log("user country = " + data.country);
            usersCountry = data.country;
            console.log("user's country = " + usersCountry);

            if (usersCountry !== roomCountry) {
                alert("You can't enter this room!");
            } else {
                var myWindow1 = window.open('', roomName, 'height=200,width=500');
                myWindow1.document.write('<!DOCTYPE html>\n\
<title>' + roomName + '</title>\n\
<p>Light is ' + light + '<button onclick="myWindow2();">Изменить текст кнопки с "Открыть" на "Нажать"</button>\n\
<scr' + 'ipt>function myWindow2() {window.opener.document.getElementById("showRoom").innerHTML = "Нажать"; window.close();}</scr' + 'ipt>');
            }
        }

    });


}
