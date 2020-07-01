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
        $.ajax({
            type: 'POST',
            url: '/RoomsWithLightBulbs/ajax?command=ALL_ROOMS',
            dataType: "json",
            success: function (data) {
                let trHTML = '<tr>' +
                    '            <th>Title</th>\t' +
                    '            <th>Country</th>\t' +
                    '            <th>Light is</th>\t' +
                    '            <th>Button</th>\t' +
                    '        </tr>';
                roomList = data.roomList;
                for (let i in data.roomList) {
                    trHTML += '<tr><td>' + data.roomList[i].roomsName +
                        '\t</td><td>' + data.roomList[i].country +
                        '\t</td><td>' + data.roomList[i].lightStatus +
                        '\t</td><td>' + "<button type=\"submit\" id=\"showRoom\">Enter</button>" +
                        '\t</td></tr>';
                }
                $("#show2").append(trHTML);
            },
            error: function (e) {
                $("#txtHint").html(e.responseText);
            }
        });
    });
})