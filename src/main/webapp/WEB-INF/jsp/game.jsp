<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
   <title>Игра</title>
   <style>
       .map-table body {
           font-family: Arial, sans-serif; /* Шрифт страницы */
           text-align: center; /* Центрирование текста на странице */
           background-color: #A9A9A9; /* Цвет фона страницы */
       }
       .map-table {
           margin: 20px auto; /* Центрирование таблицы */
           border-collapse: collapse; /* Объединение границ ячеек */
           width: auto; /* Автоширина таблицы (можно задать фиксированную ширину) */
       }
       .map-table td {
           width: 40px; /* Ширина ячейки */
           height: 40px; /* Высота ячейки */
           border: 3px solid #black; /* Граница ячейки */
           background-color: #ffffff; /* Цвет фона ячейки */
           text-align: center; /* Центрирование текста внутри ячейки */
           vertical-align: middle; /* Вертикальное центрирование текста внутри ячейки */
       .map-table caption {
           text-align: right;
           font-style: italic;
          }
       }
   </style>
   <style>
       .list-of-players body {
          font-family: Arial, sans-serif; /* Шрифт страницы */
          text-align: center; /* Центрирование текста на странице */
          background-color: #FFFFFF; /* Цвет фона страницы */
       }
       .list-of-players caption {
                  text-align: right;
                  font-style: italic;
       }
   </style>
   <style>
      body {
         font-family: Arial, sans-serif; /* Шрифт страницы */
         text-align: center; /* Центрирование текста на странице */
         background-color: #FFFFFF; /* Цвет фона страницы */
      }
   </style>
</head>
   <body>
   <caption>Список Игроков</caption>
   <table class="list-of-players">
       <head>
           <tr>
               <th>ID</th>
               <th>Имя</th>
               <th>Очки</th>
           </tr>
       </head>
       <body>
       <c:forEach var="player" items="${players}">
           <tr>
               <td>${player.id}</td>
               <td>${player.nickName}</td>
               <td>${player.countFood}</td>
           </tr>
       </c:forEach>
       </body>
   </table>

    <body>
        <caption>Карта</caption>
        <table class="map-table">
            <body>
                <c:forEach var="row" items="${map}">
                    <tr>
                        <c:forEach var="cell" items="${row}">
                             <td style="background-color: ${cell == 1 ? '#800000' : cell == 0 ? '#008000' : cell == 5 ? '#F4A460': '#C0C0C0'};">${""}</td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </body>
        </table>
    </body>
</body>
</html>