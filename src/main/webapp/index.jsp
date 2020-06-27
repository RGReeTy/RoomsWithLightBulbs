<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<body>

<form class="" method="POST" action="Controller">
    <div>
        <input name="action" type="hidden" value="ADD_NEW_ROOM"/>
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


<form class="" method="POST" action="Controller">
    <div>
        <input name="action" type="hidden" value="ADD_NEW_ROOM"/>
    </div>
    <div>
        <label for="countries"><c:out value="${country}"/></label>

        <select required class="form-control" name="testId" id="countries">
            <option value="0" selected><c:out value="${view_all}"/></option>
        </select>
    </div>

    <div>
        <input name="action" type="hidden" value="ADD_NEW_ROOM"/>
        <input class="button" type="submit" name="ADD_NEW_ROOM"
               value="ADD_NEW_ROOM">
    </div>
</form>

<script src="resources/js/scripts.js"></script>
</body>
</html>
