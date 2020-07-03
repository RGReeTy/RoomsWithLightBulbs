//Function for loading countries from by ajax
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

//Built table by data from db
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
                        '\t</td><td><button type=\"submit\" id=\"showRoom\" class=\"showRoom\"' +
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

//remembering row, which was clicked
let localRoomName;
let localCountry;
let localLight;
let currentRow;
$(document).ready(function () {
    $("#show2").on('click', '#showRoom', function () {
        currentRow = $(this).closest("tr");

        localRoomName = currentRow.find(".roomsName").text();
        localCountry = currentRow.find(".country").text();
        localLight = currentRow.find(".lightStatus").text();
        let data = localRoomName + "\n" + localCountry + "\n" + localLight;
        console.log(data);
    });
});


//hiding buttons when one room is open
let buttons;//for hide buttons when room is open
async function enterTheRoom(roomName, roomCountry, light) {
    if (usersCountry === roomCountry.toUpperCase().trim()) {
        $('.poup').fadeIn();
        $("#text span").append(localLight);
        buttons = $(".showRoom");
        buttons.hide();

        // while ($(buttons).is(":hidden")) {
        //     setTimeout(executeUpdateRow(), 5000);
        // }
    } else {
        alert("You can't enter this room!");
    }
}

//getting user's country by ip from geojs.io
let usersCountry;
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

//return buttons when room close
$(document).ready(function () {
    $('.poup .close').click(function () {
        $('.poup').fadeOut();
        buttons.show();
    });
});

//changing light status by click
window.onload = function () {
    let a = document.getElementById('switch');
    a.onclick = function () {
        if (this.innerHTML == 'true') {
            this.innerHTML = 'false';
            localLight = false;
            currentRow.find(".lightStatus").text('false');
        } else {
            this.innerHTML = 'true';
            localLight = true;
            currentRow.find(".lightStatus").text('true');
        }
        sendLightStatus();
        return false;
    }
};

//send light status to db
async function sendLightStatus() {
    $.ajax({
        type: "POST",
        url: "/RoomsWithLightBulbs/ajax",
        data: "command=CHANGE_LIGHT_STATUS&localRoomName=" + localRoomName + "&localLight=" + localLight,
        success: function (resp) {
            //alert("Changing light status saved into database");
        },

        error: function () {
            console.log('Service call failed!');
        }
    });
}

//syncronize light status from db by timer
//not used, coz i think it's stupid method - fall-down-db, sure what exist better way
function executeUpdateRow() {
    $.ajax({
        type: "GET",
        url: '/RoomsWithLightBulbs/ajax',
        data: "command=UPDATE_ROOM&localRoomName=" + localRoomName,
        success: function (data) {
            let tempRoomName = data.room[0].roomsName;
            let tempCountry = data.room[0].country;
            let tempLight = data.room[0].lightStatus;

            let print = tempRoomName + "+" + tempCountry + "+" + tempLight;
            console.log(print);

            if (tempLight !== localLight) {
                currentRow.find(".lightStatus").text(tempLight);
            }
        },
        complete: function () {
            // Schedule the next request when the current one's complete
            setTimeout(executeUpdateRow, 5000);
        }
    });
}

