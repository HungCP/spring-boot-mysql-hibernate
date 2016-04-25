<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 04/22/2016
  Time: 5:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%
  String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/_header.jsp"></jsp:include>

<head>
  <meta charset="utf-8">
  <title>Attendance</title>

  <meta name="_csrf" content="${_csrf.token}"/>
  <!-- default header name is X-CSRF-TOKEN -->
  <meta name="_csrf_header" content="${_csrf.headerName}"/>

  <script src="http://jcrop-cdn.tapmodo.com/v0.9.12/js/jquery.Jcrop.min.js"></script>
  <link rel="stylesheet" href="http://jcrop-cdn.tapmodo.com/v0.9.12/css/jquery.Jcrop.css" type="text/css" />

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
  <nav role="navigation">
    <ul>
      <li><a href="/">Home</a></li>
    </ul>
  </nav>

  <div class="container">
    <table class="table table-bordered table-hover span12" border="1">
      <thead>
      <tr>
        <th>Hình ảnh</th>
        <th>Kết quả</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${imagesList}" var="image" varStatus="status">
        <tr>
          <td>
            <div class="form-group">
              <img src="data:image/jpeg;base64,${image}" id="cropbox" border="1" />
            </div>
            <div class="form-actions pull-right">
              <label><input type="text" size="4" id="x" name="l" /></label>
              <label><input type="text" size="4" id="y" name="t" /></label>
              <label><input type="text" size="4" id="w" name="w" /></label>
              <label><input type="text" size="4" id="h" name="h" /></label>
              <input type="button" value="Chọn" id="CropButton" onclick="crop();"/>
            </div>
          </td>
          <td>
            output<br>
            <div id="output" height="100"></div>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>

</body>

<jsp:include page="../fragments/_footer.jsp"></jsp:include>

</html>
