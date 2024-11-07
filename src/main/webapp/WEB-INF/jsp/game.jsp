<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
   <title>Игра</title>

</head>
   <body>
       <table border="1px" width="100%" height="100%">
           <tr align = "center">
               <td width="20%" valign = "top">
                   <table width="100%">
                       <head>
                          <tr>
                              <th>ID</th>
                              <th>NickName</th>
                              <th>Score</th>
                          </tr>
                       </head>
                           <c:forEach var="player" items="${players}">
                              <tr align = "center">
                                  <td>${player.id}</td>
                                  <td align = "left">${player.nickName}</td>
                                  <td>${player.countFood}</td>
                              </tr>
                           </c:forEach>
                  </table>
               </td>
               <td>
                   <table border="1px" width="100%" height="100%">
                       <c:forEach var="row" items="${map}">
                           <tr>
                               <c:forEach var="cell" items="${row}">
                                   <td style="background-color: ${cell == 1 ? '#800000' : cell == 0 ? '#008000' : cell == 5 ? '#F4A460': '#C0C0C0'};"></td>
                               </c:forEach>
                           </tr>
                       </c:forEach>
                   </table>
               </td>
           </tr>
       </table>
   </body>
</html>