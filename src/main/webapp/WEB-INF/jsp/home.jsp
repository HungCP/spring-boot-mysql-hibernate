<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:sec="http://www.springframework.org/security/tags">
<html lang="en">

<jsp:include page="fragments/_header.jsp"></jsp:include>

<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <title>Home page</title>

    <link rel="stylesheet" href=<%=contextPath%>"/css/jquery.Jcrop.css">
    <link rel="stylesheet" href=<%=contextPath%>"/css/jquery.Jcrop.min.css">

    <script language="Javascript">
        function showCoords(c) {
            var imageheight = document.getElementById('cropbox').naturalHeight;
            var imagewidth = document.getElementById('cropbox').naturalWidth;
            var xper = (c.x * 100 / $('#cropbox').width());
            var yper = (c.y * 100 / $('#cropbox').height());
            var wPer = (c.w * 100 / $('#cropbox').width());
            var hper = (c.h * 100 / $('#cropbox').height());

            var actX = (xper * imagewidth / 100);
            var actY = (yper * imageheight / 100);
            var actW = (wPer * imagewidth / 100);
            var actH = (hper * imageheight / 100);
            $('#x').val(parseInt(actX));
            $('#y').val(parseInt(actY));
            $('#w').val(parseInt(actW));
            $('#h').val(parseInt(actH));
        };
        $(document).ready(function() {
            var w = 100;
            var h = 100;
            var W = $('#cropbox').width();
            var H = $('#cropbox').height();
            var x = W / 2 - w / 2;
            var y = H / 2 - h / 2;
            var x1 = x + w;
            var y1 = y + h;

            $('#cropbox').Jcrop({
                onChange : showCoords,
                onSelect : showCoords,

                setSelect : [ x, y, x1, y1 ]
                ,minSize : [ 100, 100 ] // use for crop min size
                ,aspectRatio : 1 / 1    // crop ration
            });

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $.ajaxSetup({
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                }
            });
        });

        function crop() {
            var cropX = $('#x').val();
            var cropY = $('#y').val();
            var cropW = $('#w').val();
            var cropH = $('#h').val();

            var cdata = {
                cropX: cropX,
                cropY: cropY,
                cropW: cropW,
                cropH: cropH
            }

            $.ajax({
                url: "crop",
                contentType: "application/json",
                data: JSON.stringify(cdata),
                method: "POST",
                async: false,
                success: function (data) {
                    console.log("-----------");
                    $("#output").append('<img src="'+data+'"/>');
                },
                error: function (xhr, status, error) {
                    console.log("error----------->" + xhr);
                }
            });
        };
    </script>

</head>
<body>
    <table border="1">
        <tr>
            <td>
                <input type="button" value="Crop" id="CropButton" onclick="crop();"/>
                <img src="image/baby.jpg" id="cropbox" height="500" border="1" />
            </td>
            <td>
                output<br>
                <div id="output" height="100"></div>
            </td>
        </tr>
    </table>
    <br>
    <label><input type="text" size="4" id="x" name="l" /></label>
    <label><input type="text" size="4" id="y" name="t" /></label>
    <label><input type="text" size="4" id="w" name="w" /></label>
    <label><input type="text" size="4" id="h" name="h" /></label>

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
            </c:if>

            <sec:authorize access="hasAuthority('ADMIN')">
                <li><a href="users">Danh sách người dùng</a></li>
                <li><a href="/classrooms">Danh sách phòng học</a></li>
                <li><a href="/courses">Danh sách lớp học</a></li>
                <li><a href="/user/${currentUser.id}/update">View myself</a></li>
            </sec:authorize>

            <sec:authorize access="hasAnyAuthority('GIAO_VIEN','USER')">
                <li><a href="/user/${currentUser.id}/view">View myself</a></li>
                <li><a href="/courses/my-courses">Danh sách lớp học của tôi</a></li>
            </sec:authorize>
        </ul>
    </nav>

    <script src=<%=contextPath%>"/js/jquery.Jcrop.min.js"></script>
    <script src=<%=contextPath%>"/js/jquery.color.js"></script>
    <script src=<%=contextPath%>"/js/jquery.Jcrop.js"></script>

</body>

<jsp:include page="fragments/_footer.jsp"></jsp:include>

</html>