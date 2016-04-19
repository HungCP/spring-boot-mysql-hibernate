<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 04/19/2016
  Time: 8:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/_header.jsp"></jsp:include>

<head>
  <meta charset="utf-8">
  <title>Upload image</title>
  <!-- Bootstrap styles -->
  <link rel="stylesheet" href=<%=contextPath%>"/css/bootstrap.min.css">
  <!-- Generic page styles -->
  <link rel="stylesheet" href=<%=contextPath%>"/css/style.css">
  <!-- blueimp Gallery styles -->
  <link rel="stylesheet" href=<%=contextPath%>"/css/blueimp-gallery.min.css">
  <!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
  <link rel="stylesheet" href=<%=contextPath%>"/css/jquery.fileupload.css">
  <link rel="stylesheet" href=<%=contextPath%>"/css/jquery.fileupload-ui.css">
  <!-- CSS adjustments for browsers with JavaScript disabled -->
  <noscript>
    <link rel="stylesheet" href=<%=contextPath%>"/css/jquery.fileupload-noscript.css">
  </noscript>
  <noscript>
    <link rel="stylesheet" href=<%=contextPath%>"/css/jquery.fileupload-ui-noscript.css">
  </noscript>
</head>

<body>
  <nav role="navigation">
    <ul>
      <li><a href="/">Home</a></li>
    </ul>
  </nav>

  <div class="container">
    <h1>Upload ảnh buổi học</h1>
    <br/>

    <div class="form-group">
        <label class="col-sm-2 control-label ">Tiêu đề </label>
        <label class="col-sm-10 control-label text-primary">${form.name}</label>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label ">Lớp học </label>
        <label class="col-sm-10 control-label text-primary">${form.course.name}</label>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label ">Phòng học </label>
        <label class="col-sm-10 control-label text-primary">${form.classroom.ma}</label>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label">Ành</label>
        <div class="col-sm-10">
            <div class="row fileupload-buttonbar">
                <div class="col-lg-7">
                    <!-- The fileinput-button span is used to style the file input field as button -->
                      <span class="btn btn-success fileinput-button">
                          <i class="glyphicon glyphicon-plus"></i>
                          <span>Add files...</span>
                          <input type="file" name="files[]" multiple>
                      </span>
                    <button type="submit" class="btn btn-primary start">
                        <i class="glyphicon glyphicon-upload"></i>
                        <span>Start upload</span>
                    </button>
                    <%--<button type="reset" class="btn btn-warning cancel">
                        <i class="glyphicon glyphicon-ban-circle"></i>
                        <span>Cancel upload</span>
                    </button>
                    <button type="button" class="btn btn-danger delete">
                        <i class="glyphicon glyphicon-trash"></i>
                        <span>Delete</span>
                    </button>--%>
                    <%--<input type="checkbox" class="toggle">--%>
                    <!-- The global file processing state -->
                    <span class="fileupload-process"></span>
                </div>
                <!-- The global progress state -->
                <div class="col-lg-5 fileupload-progress fade">
                    <!-- The global progress bar -->
                    <div class="progress progress-striped active" role="progressbar" aria-valuemin="0"
                         aria-valuemax="100">
                        <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                    </div>
                    <!-- The extended global progress state -->
                    <div class="progress-extended">&nbsp;</div>
                </div>
            </div>
            <!-- The table listing the files available for upload/download -->
            <table role="presentation" class="table table-striped">
                <tbody class="files"></tbody>
            </table>
        </div>
    </div>
  </div>

  </body>

  <jsp:include page="../fragments/_footer.jsp"></jsp:include>
  <!-- The template to display files available for upload -->
  <script id="template-upload" type="text/x-tmpl">
  {% for (var i=0, file; file=o.files[i]; i++) { %}
      <tr class="template-upload fade">
          <td>
              <span class="preview"></span>
          </td>
          <td>
              <p class="name">{%=file.name%}</p>
              <strong class="error text-danger"></strong>
          </td>
          <td>
              <p class="size">Processing...</p>
              <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
          </td>
          <td>
              <%--{% if (!i && !o.options.autoUpload) { %}
                  <button class="btn btn-primary start">
                      <i class="glyphicon glyphicon-upload"></i>
                      <span>Start</span>
                  </button>
              {% } %}--%>
              {% if (!i) { %}
                  <button class="btn btn-warning cancel">
                      <i class="glyphicon glyphicon-ban-circle"></i>
                      <span>Cancel</span>
                  </button>
              {% } %}
          </td>
      </tr>
  {% } %}




  </script>
  <!-- The template to display files available for download -->
  <script id="template-download" type="text/x-tmpl">
  {% for (var i=0, file; file=o.files[i]; i++) { %}
      <tr class="template-download fade">
          <td>
              <span class="preview">
                  {% if (file.thumbnailUrl) { %}
                      <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                  {% } %}
              </span>
          </td>
          <td>
              <p class="name">
                  {% if (file.url) { %}
                      <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                  {% } else { %}
                      <span>{%=file.name%}</span>
                  {% } %}
              </p>
              {% if (file.error) { %}
                  <div><span class="label label-danger">Error</span> {%=file.error%}</div>
              {% } %}
          </td>
          <td>
              <span class="size">{%=o.formatFileSize(file.size)%}</span>
          </td>
          <td>
              {% if (file.deleteUrl) { %}
                  <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                      <i class="glyphicon glyphicon-trash"></i>
                      <span>Delete</span>
                  </button>
                  <input type="checkbox" name="delete" value="1" class="toggle">
              {% } else { %}
                  <button class="btn btn-warning cancel">
                      <i class="glyphicon glyphicon-ban-circle"></i>
                      <span>Cancel</span>
                  </button>
              {% } %}
          </td>
      </tr>
  {% } %}




  </script>
  <script src=<%=contextPath%>"/js/jquery.min.js"></script>
  <!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
  <script src=<%=contextPath%>"/js/vendor/jquery.ui.widget.js"></script>
  <!-- The Templates plugin is included to render the upload/download listings -->
  <script src=<%=contextPath%>"/js/tmpl.min.js"></script>
  <!-- The Load Image plugin is included for the preview images and image resizing functionality -->
  <script src=<%=contextPath%>"/js/load-image.all.min.js"></script>
  <!-- The Canvas to Blob plugin is included for image resizing functionality -->
  <script src=<%=contextPath%>"/js/canvas-to-blob.min.js"></script>
  <!-- Bootstrap JS is not required, but included for the responsive demo navigation -->
  <script src=<%=contextPath%>"/js/bootstrap.min.js"></script>
  <!-- blueimp Gallery script -->
  <script src=<%=contextPath%>"/js/jquery.blueimp-gallery.min.js"></script>
  <!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
  <script src=<%=contextPath%>"/js/jquery.iframe-transport.js"></script>
  <!-- The basic File Upload plugin -->
  <script src=<%=contextPath%>"/js/jquery.fileupload.js"></script>
  <!-- The File Upload processing plugin -->
  <script src=<%=contextPath%>"/js/jquery.fileupload-process.js"></script>
  <!-- The File Upload image preview & resize plugin -->
  <script src=<%=contextPath%>"/js/jquery.fileupload-image.js"></script>
  <!-- The File Upload audio preview plugin -->
  <script src=<%=contextPath%>"/js/jquery.fileupload-audio.js"></script>
  <!-- The File Upload video preview plugin -->
  <script src=<%=contextPath%>"/js/jquery.fileupload-video.js"></script>
  <!-- The File Upload validation plugin -->
  <script src=<%=contextPath%>"/js/jquery.fileupload-validate.js"></script>
  <!-- The File Upload user interface plugin -->
  <script src=<%=contextPath%>"/js/jquery.fileupload-ui.js"></script>
  <!-- The main application script -->
  <script src=<%=contextPath%>"/js/main.js"></script>
  <!-- The XDomainRequest Transport is included for cross-domain file deletion for IE 8 and IE 9 -->
  <!--[if (gte IE 8)&(lt IE 10)]>
  <script src=<%=contextPath%>"/js/cors/jquery.xdr-transport.js" ></script>
<![endif]-->
</html>
