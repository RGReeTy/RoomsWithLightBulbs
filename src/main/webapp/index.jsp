<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Rooms with Light Bulbs</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
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
                        for (let i in data.roomList) {
                            trHTML += '<tr><td>' + data.roomList[i].roomsName +
                                '\t</td><td>' + data.roomList[i].country +
                                '\t</td><td>' + data.roomList[i].lightStatus +
                                '\t</td><td>' + "Button" +
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

    </script>
</head>
<body>

<div align="center">
    <form class="" method="POST" action="Controller" onclick="loadAllCountries()">
        <div>
            <input name="command" type="hidden" value="ADD_NEW_ROOM"/>
        </div>
        <div>
            <input type="text" name="name"
                   placeholder="Enter room's 'Title' here"
                   pattern="[A-Za-z0-9]{1,}" title="name"/>
        </div>
        <div>
            <label for="dropdownlist"></label>
            <select id="dropdownlist" name="country">
                <option>Choose a country..</option>
            </select>
        </div>
        <div>
            <input name="command" type="hidden" value="ADD_NEW_ROOM"/>
            <input class="button" type="submit" name="ADD_NEW_ROOM"
                   value="Create new room">
        </div>
    </form>
</div>
<br>
<hr>


<div class="dataTable_wrapper" align="center">
    <form class="form-inline">
        <div class="btn-group">
            <button type="submit" id="btnSubmit">Load all rooms
            </button>
        </div>
    </form>
    <table id="show2">


    </table>
</div>

<!-- <script src="resources/js/scripts.js"></script> -->
</body>
</html>
