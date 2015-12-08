<%--
  Created by IntelliJ IDEA.
  User: G551
  Date: 12/04/2015
  Time: 12:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>LOGIN PAGE</title>
        <style>
            .error {
                padding: 15px;
                margin-bottom: 20px;
                border: 1px solid transparent;
                border-radius: 4px;
                color: #a94442;
                background-color: #f2dede;
                border-color: #ebccd1;
            }

            .msg {
                padding: 15px;
                margin-bottom: 20px;
                border: 1px solid transparent;
                border-radius: 4px;
                color: #31708f;
                background-color: #d9edf7;
                border-color: #bce8f1;
            }

            #login-box {
                width: 350px;
                padding: 20px;
                margin: 100px auto;
                background: #fff;
                -webkit-border-radius: 2px;
                -moz-border-radius: 2px;
                border: 1px solid #000;
            }
        </style>
    </head>
    <body>
        <div id="login-box">

            <h2>Login with Email and Password</h2>

            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>

            <form name='loginForm' action="/login" method='POST'>

                <table>
                    <tr>
                        <td>Email:</td>
                        <td><input type='text' name='email' value=''></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type='password' name='password' /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input name="submit" type="submit" value="submit" /></td>
                    </tr>
                </table>

                <input type="hidden" name="" value="" />

            </form>

        </div>
    </body>
</html>

