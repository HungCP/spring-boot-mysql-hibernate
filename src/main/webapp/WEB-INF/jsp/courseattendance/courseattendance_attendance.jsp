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

  <%--<link rel="stylesheet" href=<%=contextPath%>"/css/dataTables.bootstrap.css">
  <link rel="stylesheet" href=<%=contextPath%>"/css/dataTables.bootstrap.min.css">--%>
  <link rel="stylesheet" href=<%=contextPath%>"/css/jquery.dataTables.css">
  <link rel="stylesheet" href=<%=contextPath%>"/css/jquery.dataTables.min.css">

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
        ,minSize : [ 30, 30 ] // use for crop min size
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

      var userID = $('#selectUserID').val();
      var ImageName = $('#cropbox').attr('title');

      var cdata = {
        cropX: cropX,
        cropY: cropY,
        cropW: cropW,
        cropH: cropH,

        userID: userID,
        ImageName: ImageName
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
      <li><a href="/courses">Danh sách lớp học</a></li>
      <li><a href="/course/${model.course.id}/attendance">Thông tin điểm danh</a></li>
      <a href="/autoAttendance/${model.courseAttendance.id}" class="btn btn-default">Điểm danh tự động</a>
    </ul>
  </nav>

  <div class="container">
    <h1>Danh sách sinh viên tham gia buổi học</h1>
    <br>

    <table id="example" class="table table-bordered table-hover span12">
      <thead>
      <tr>
        <th>Mã sinh viên</th>
        <th>Họ và tên</th>
        <th>Ảnh</th>
        <th>X</th>
        <th>Y</th>
        <th>W</th>
        <th>H</th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${model.userImagesList}" var="userImage" varStatus="status">
        <tr>
          <td>${userImage.user.ma}</td>
          <td>${userImage.user.name}</td>
          <td>${userImage.image.newFilename}</td>
          <td>${userImage.xper}</td>
          <td>${userImage.yper}</td>
          <td>${userImage.width}</td>
          <td>${userImage.height}</td>
          <td>
            <spring:url value="/userImage/${userImage.id}/delete" var="deleteUrl" />
            <button class="btn btn-default btn-sm center-block" onclick="location.href='${deleteUrl}'">Xóa</button>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

    <br>
    <h1>Danh sách hình ảnh điểm danh</h1>
  </div>

  <div class="container-fluid">
    <div class="table-responsive">
      <table class="table table-bordered table-hover span12" border="1">
        <thead>
        <tr>
          <th class="col-sm-9">Hình ảnh</th>
          <th class="col-sm-3">Kết quả</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${model.imagesList}" var="image" varStatus="status">
          <tr>
            <td>
              <div class="form-group">
                <img id="cropbox" src="data:image/jpeg;base64,${image['key']}" title="${image['value']}" />
                <label class="text-info">${image['value']}</label>
              </div>
            </td>
            <td>
              output
              <br>
              <div class="form-group" id="output"></div>
              <div align="center">
                <label><input type="text" size="4" id="x" name="l" /></label>
                <label><input type="text" size="4" id="y" name="t" /></label>
                <label><input type="text" size="4" id="w" name="w" /></label>
                <label><input type="text" size="4" id="h" name="h" /></label>
              </div>
              <div class="form-group">
                <select class="form-control" id="selectUserID" >
                  <c:forEach var="option" items="${model.sinhVienList}">
                    <option value="<c:out value="${option.id}"/>">
                        ${option.name}
                    </option>
                  </c:forEach>
                </select>
              </div>
              <div class="form-group">
                <input type="button" value="Chọn" id="CropButton" onclick="crop();"/>
              </div>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>


  <%--<script src=<%=contextPath%>"/js/dataTables.bootstrap.js"></script>
  <script src=<%=contextPath%>"/js/dataTables.bootstrap.min.js"></script>--%>
  <script src=<%=contextPath%>"/js/jquery.dataTables.js"></script>
  <script src=<%=contextPath%>"/js/jquery.dataTables.min.js"></script>
  <script src=<%=contextPath%>"/js/jquery.js"></script>

  <script src=<%=contextPath%>"/js/jquery.Jcrop.min.js"></script>
  <script src=<%=contextPath%>"/js/jquery.color.js"></script>
  <script src=<%=contextPath%>"/js/jquery.Jcrop.js"></script>

</body>

<jsp:include page="../fragments/_footer.jsp"></jsp:include>

</html>
