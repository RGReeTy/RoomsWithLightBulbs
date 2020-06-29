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
                for (let i in json.countries) {
                    $("<option>").val(json.countries[i]).text(json.countries[i]).appendTo($select);
                }
            }
        }

        // let arr = response[0];
        //  for(let i in arr){
        //     $("<option>").val(i).text(i).appendTo($select);
        // }
        // let data = response.toString();
        // console.log(data);
        // console.log("-----------------------------");
        // console.log(response);


        //     $.each(response, function (key, value) {
        //     $("<option>").val(value.id).text(value.name).appendTo($select);
        // });


        // for (i in data.countries){
        //     $("<option>").val(countries.id).text(countries.name).appendTo($select);
        //     $('<option>').val(key).text(value).appendTo($("#selectRegion"));
        // }


        <%--async function fetchData() {--%>

        <%--    const response = await fetch('http://localhost:3000/users/');--%>
        <%--    const data = await response.json();--%>

        <%--    data.forEach(obj => {--%>
        <%--        Object.entries(obj).forEach(([key, value]) => {--%>
        <%--            console.log(`${key} ${value}`);--%>
        <%--        });--%>
        <%--        console.log('-------------------');--%>
        <%--    });--%>
        <%--};--%>


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

    </script>
</head>
<body>

<form class="" method="POST" action="Controller">
    <div>
        <input name="command" type="hidden" value="ADD_NEW_ROOM"/>
    </div>
    <div>
        <input type="text" name="name"
               placeholder="Enter room's 'Title' here"
               pattern="[A-Za-z0-9]{1,}" title="name"/>
    </div>
    <div>
        <input type="text" name="id_country"
               placeholder="Enter 'Country' here"
               pattern="[A-Za-z0-9]{1,}" title="Country"/>
    </div>
    <div>
        <input type="submit" id="button"
               value="Create new room"/>
    </div>
</form>

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


<!-- <script src="resources/js/scripts.js"></script> -->
</body>
</html>
