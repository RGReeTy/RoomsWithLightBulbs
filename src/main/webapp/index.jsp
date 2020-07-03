<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Rooms with Light Bulbs</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <style>
        .poup {
            width: 200px;
            height: 100px;
            border: 1px solid black;
            margin: 0 auto;
            margin-top: 250px;
            align-content: center;
        }
    </style>
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

<div style="text-align: center; align-content: center">
    <br>
    <h1><c:choose>
        <c:when test="${not empty message}">
            ${message}
        </c:when>
    </c:choose>
    </h1>
</div>
<br>
<hr>

<div class="poup" style="display:none;">
    <div id="text">
    <div id="roomsName"></div>
        Light is ON = <a id="switch" href="#"><span></span></a>
    </div>
    <br>
    <button type="submit" class="close">Close</button>
</div>
<br>

<div class="dataTable_wrapper" align="center">
    <form>
        <div class="btn-group">
            <button type="submit" id="btnSubmit">Load all rooms
            </button>
        </div>
    </form>
    <table id="show2" class="edit">
    </table>
</div>


<script src="resources/js/scripts.js"></script>
</body>
</html>
