<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:sec="http://www.springframework.org/security/tags">
<html lang="en">
<head>
    <script src="js/jquery-2.2.0.js" ></script>
    <script src="js/bootstrap.js" ></script>
    <link href="css/bootstrap.css" rel="stylesheet" />

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://jcrop-cdn.tapmodo.com/v0.9.12/js/jquery.Jcrop.min.js"></script>
    <link rel="stylesheet" href="http://jcrop-cdn.tapmodo.com/v0.9.12/css/jquery.Jcrop.css" type="text/css" />

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" charset="utf-8">
    <title>Home page</title>

    <script language="Javascript">
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        function showCoords(c) {
            var imageheight = document.getElementById('cropbox').naturalHeight;
            var imagewidth = document.getElementById('cropbox').naturalWidth;
            var xper = (c.x * 100 / jQuery('#cropbox').width());
            var yper = (c.y * 100 / jQuery('#cropbox').height());
            var wPer = (c.w * 100 / jQuery('#cropbox').width());
            var hper = (c.h * 100 / jQuery('#cropbox').height());

            var actX = (xper * imagewidth / 100);
            var actY = (yper * imageheight / 100);
            var actW = (wPer * imagewidth / 100);
            var actH = (hper * imageheight / 100);
            jQuery('#x').val(parseInt(actX));
            jQuery('#y').val(parseInt(actY));
            jQuery('#w').val(parseInt(actW));
            jQuery('#h').val(parseInt(actH));
        };
        jQuery(document).ready(function() {
            var w = 100;
            var h = 100;
            var W = jQuery('#cropbox').width();
            var H = jQuery('#cropbox').height();
            var x = W / 2 - w / 2;
            var y = H / 2 - h / 2;
            var x1 = x + w;
            var y1 = y + h;

            jQuery('#cropbox').Jcrop({
                onChange : showCoords,
                onSelect : showCoords,

                setSelect : [ x, y, x1, y1 ]
                ,minSize : [ 100, 100 ] // use for crop min size
                ,aspectRatio : 1 / 1    // crop ration
            });

            jQuery('#CropButton').click(function() {
                console.log("aaaaaaaaaaaaa      e");
                var cropX = jQuery('#x').val();
                var cropY = jQuery('#y').val();
                var cropW = jQuery('#w').val();
                var cropH = jQuery('#h').val();

                var request_Data = {
                    cropX : cropX,
                    cropY : cropY,
                    cropW : cropW,
                    cropH : cropH
                }

                $.ajax({
                    type: "POST",
                    url: "crop",
                    data: {
                        cropX : cropX,
                        cropY : cropY,
                        cropW : cropW,
                        cropH : cropH
                    },
                    success: function(data){
                        console.log("aaaaaaaaaaaaa");
                        $("#output").html(data);
                    },
                    error: function(e){
                        console.log(e);
                    }
                });

            });
        });
    </script>

</head>
<body>

    <center>
        <table border="1">
            <tr>
                <td><input type="button" value="Crop" id="CropButton" />
                    <img src="image/baby.jpg" id="cropbox" height="500" border="1" />
                </td>
                <td>
                    output<br>
                    <div id="output" height="100"></div>
                </td>
            </tr>
        </table>
        <br> <label><input type="text" size="4" id="x" name="l" /></label>
        <label><input type="text" size="4" id="y" name="t" /></label> <label><input
            type="text" size="4" id="w" name="w" /></label> <label><input
            type="text" size="4" id="h" name="h" /></label>
    </center>

    <nav role="navigation">
        <ul>
            <c:if test="${empty currentUser}">
                <li><a href="/login">Log in</a></li>
            </c:if>
            <c:if test="${not empty currentUser}">
                <li>
                    <form action="/logout" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit">Log out</button>
                    </form>
                </li>
                <li><a href="/user/${currentUser.id}">View myself</a></li>
            </c:if>

            <sec:authorize access="hasAuthority('ADMIN')">
                <li><a href="/users">Danh sách người dùng</a></li>
                <li><a href="/classrooms">Danh sách phòng học</a></li>
                <li><a href="/courses">Danh sách lớp học</a></li>
            </sec:authorize>

            <sec:authorize access="hasAnyAuthority('GIAO_VIEN','USER')">
                <li><a href="/courses/my-courses">Danh sách lớp học</a></li>
            </sec:authorize>
        </ul>
    </nav>
</body>
</html>