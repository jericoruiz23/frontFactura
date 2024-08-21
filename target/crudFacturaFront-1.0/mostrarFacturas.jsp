<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar Facturas</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                color: #333;
                margin: 10px;
                padding: -10px; /* Ajusta el padding general */
            }
            h1 {
                text-align: center;
                color: #000000;
                margin-bottom: 10px; /* Reduce el margen inferior del título */
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 15px; /* Reduce el margen inferior de la tabla */
                background-color: #fff;
            }
            th, td {
                padding: 8px; /* Reduce el padding de las celdas */
                border: 1px solid #ddd;
                text-align: left;
            }
            th {
                background-color: #5D6D7E;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            .detalle-table {
                width: 100%;
                margin-top: 5px; /* Reduce el margen superior de la tabla de detalles */
            }
            .detalle-table th {
                background-color: #3498DB;
            }
            .back-button {
                display: inline-block;
                margin: 20px auto;
                padding: 10px 20px;
                background-color: #3498DB;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                text-align: center;
                text-decoration: none;
                transition: background-color 0.3s, box-shadow 0.3s;
                box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            }
            .back-button:hover {
                background-color: #2980B9;
                box-shadow: 0px 5px 7px rgba(0, 0, 0, 0.15);
            }
            .back-button:active {
                background-color: #1F618D;
                box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.1);
            }
            .delete-button {
                background-color: #E74C3C;
                color: white;
                border: none;
                border-radius: 4px; /* Ajusta el radio del borde */
                cursor: pointer;
                font-size: 14px; /* Ajusta el tamaño de la fuente */
                padding: 6px 10px; /* Ajusta el padding del botón */
                transition: background-color 0.3s, box-shadow 0.3s;
                box-shadow: 0px 3px 5px rgba(0, 0, 0, 0.1); /* Reduce el tamaño de la sombra */
            }
            .delete-button:hover {
                background-color: #C0392B;
                box-shadow: 0px 5px 7px rgba(0, 0, 0, 0.15);
            }
            .delete-button:active {
                background-color: #A93226;
                box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.1);
            }
            .delete-cell {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Datos de Facturas</h1>

        <!-- Mostrar la lista de cabeceras -->
        <table>
            <thead>
                <tr>
                    <th>CI</th>
                    <th>Cliente</th>
                    <th>Dirección</th>
                    <th>Teléfono</th>
                    <th>Estado</th>
                    <th>Fecha</th>
                    <th>Total</th>
                    <th>Detalles</th>
                    <th>Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <!-- Iterar sobre la listaCabecera y mostrar los datos -->
                <c:forEach var="cabecera" items="${listaCabecera}">
                    <tr>
                        <td>${cabecera.ci}</td>
                        <td>${cabecera.cliente}</td>
                        <td>${cabecera.direccion}</td>
                        <td>${cabecera.telefono}</td>
                        <td>${cabecera.estado}</td>
                        <td>${cabecera.fecha}</td>
                        <td>${cabecera.total}</td>
                        <td>
                            <!-- Mostrar detalles de la factura -->
                            <table class="detalle-table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Cantidad</th>
                                        <th>Subtotal</th>
                                        <th>Producto</th>
                                        <th>Precio</th>
                                        <th>Fecha Registro</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="detalle" items="${cabecera.detalles}">
                                        <tr>
                                            <td>${detalle.id}</td>
                                            <td>${detalle.cantidad}</td>
                                            <td>${detalle.subtotal}</td>
                                            <td>${detalle.producto}</td>
                                            <td>${detalle.precio}</td>
                                            <td>${detalle.fechaRegistra}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </td>
                        <td class="delete-cell">
                            <form action="eliminarFactura" method="post">
                                <input type="hidden" name="id" value="${cabecera.id}">
                                <button type="submit" class="delete-button">X</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Botón para volver atrás -->
        <a class="back-button" href="javascript:history.back()">Atrás</a>

    </body>
</html>
