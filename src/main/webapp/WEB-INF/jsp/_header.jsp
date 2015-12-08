<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 12/04/2015
  Time: 12:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div style="background: #E0E0E0; height: 55px; padding: 5px;">

    <div style="float: left">
        <h1>My Site</h1>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">
        Hello <b>${loginedUser.name}</b>
        <br/>
        Search <input name="search">

    </div>

</div>