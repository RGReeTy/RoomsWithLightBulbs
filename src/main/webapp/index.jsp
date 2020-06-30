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


        //         $(document).ready(function () {
        //             $('#selectState').on('change', function () {
        // //do something here
        // //alert($("#accountName").val());
        //                 $.ajax({
        //                     type: "POST",
        //                     url: "/RoomsWithLightBulbs/ajax/",
        //                     cache: false,
        //                     data: $(selectState).serialize(),
        //                     success: function (data) {
        //                         $('#ajaxGetUserServletResponse').text(data);
        //
        //                     }
        //                 }).done(function (responseJson) {
        //                     dataType: "json",
        //                         // Clear options
        //                         $("#selectRegion").find("option").remove();
        //                     // Loop through JSON response
        //                     $.each(responseJson, function (key, value) {
        //                         $('<option>').val(key).text(value).appendTo($("#selectRegion"));
        //                     });
        //                 });
        //             });
        //         });

        // async function getAllRooms() {
        //     $.ajax({
        //         type: 'GET',
        //         dataType: 'json',
        //         url: '/RoomsWithLightBulbs/ajax?command=ALL_ROOMS',
        //         data: {}
        //     }).done(function (data) {
        //
        //         $.each(data, function (key, item) {
        //             var $row = $('<tr><tr>'); // создаем tr
        //             $('.table tbody')
        //             append($row);
        //             console.log(item);
        //             // АF12 смотрим что в консоле прешло
        //         });
        //     });
        // }


        //OTSUDOVA
        $(document).ready(function () {

            $('#buttonDisplay').click(function () {
                $.ajax({
                    type: 'GET',
                    headers: {
                        Accept: "application/json; charset=utf-8",
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    url: '/RoomsWithLightBulbs/ajax?command=ALL_ROOMS',
                    success: function (result) {
                        var rooms = $.parseJSON(result);
                        var s = '';
                        for (var i = 0; i < rooms.length; i++) {
                            s += 'Id: ' + rooms[i].roomsName + '<br>';
                            s += 'Name: ' + rooms[i].country + '<br>';
                            s += 'Price: ' + rooms[i].lightStatus + '<br>';
                            s += '----------------------<br>';
                        }
                        $('#result').html(s);
                    }
                });
            });

        });

        $(document).ready(function () {
            $("#btnSubmit").click(function (e) {
                e.preventDefault();
                $.ajax({
                    type: 'GET',
                    url: '/RoomsWithLightBulbs/ajax?command=ALL_ROOMS',
                    success: async function (response) {
                        let json = await response.json();
                        let trHTML = '';
                        $.each(response, function (roomList, Object) {
                            trHTML += '<tr><td>' + Object.roomsName +
                                '</td><td>' + Object.country +
                                '</td><td>' + Object.lightStatus +
                                '</td><td>' + "Button" +
                                '</td></tr>';
                        });
                        $('#records_table').append(trHTML);
                        $('#test').html(trHTML);
                    },
                    error: function (e) {
                        $("#txtHint").html(e.responseText);
                    }
                });
            });
        });
        //DOSUDOVA

        // alert(response);
        // let trHTML = "<table class='table table-striped table-bordered table-hover' id='records_table'>" +
        //     "<tr class = 'odd gradeX' >" +
        //     "<th> Title </th>" +
        //     "<th> Country </th>" +
        //     "<th> Light is </th>" +
        //     "<th> Enter </th>" +
        //     "</tr>";
        // for (let i in json.roomList) {
        //     console.log(i);
        //     trHTML += '<tr><td>' + json.roomList[i].roomsName +
        //         '</td><td>' + json.roomList[i].country +
        //         '</td><td>' + json.roomList[i].lightStatus +
        //         '</td><td>' + "Button" +
        //         '</td></tr>';
        // }
        // trHTML += "</table>";
        // $('#records_table').append(trHTML);


        async function loadAllRooms() {
            let dataToGetCountries = new FormData();
            dataToGetCountries.append("command", "ALL_ROOMS");
            let response = await fetch("/RoomsWithLightBulbs/ajax", {
                method: 'POST',
                body: dataToGetCountries,
            });

            if (response.ok) {
                let json = await response.json();
                const $select = $("#records_table");
                const $test = $("#test");
                let trHTML = '';
                for (let i in json.roomList) {
                    $($test).Object(json.roomList[i]).text(json.roomList[i].roomsName).appendTo($test);
                    // trHTML += '<tr><td>' + json.roomList[i].roomsName +
                    //     '</td><td>' + json.roomList[i].country +
                    //     '</td><td>' + json.roomList[i].lightStatus +
                    //     '</td><td>' + "Button" +
                    //     '</td></tr>';
                }
            } else {
                alert("ERROR: " + response.status);
            }
        }
        {"0":{"roomsName":"room01","country":"BELARUS","lightStatus":true}}


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
            <button type="submit" id="btnSubmit" onclick="loadAllRooms()">Load all rooms</button>
        </div>
    </form>
    <table class='table table-striped table-bordered table-hover' id='records_table'>
        <tr class='odd gradeX'>
            <th>Title</th>
            <th>Country</th>
            <th>Light is</th>
            <th>Button</th>
        </tr>
        <div id="test" class="test"></div>
    </table>
</div>

<br>
<hr>
h3>Return Object List in Ajax</h3>
<form>
    <input type="button" value="Dislay" id="buttonDisplay">
    <br>
    <span id="result"></span>
</form>


<!-- <script src="resources/js/scripts.js"></script> -->
</body>
</html>
