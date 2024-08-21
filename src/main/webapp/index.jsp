<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario de Factura</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                margin: 2%;

            }
            h1 {
                color: #333;
            }
            form {
                background-color: #fff;
                padding: 40px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                margin-bottom: 20px;
            }
            label {
                display: block;
                margin-bottom: 8px;
                font-weight: bold;
            }
            input[type="text"], input[type="date"], input[type="time"], select {
                width: calc(100% - 22px);
                padding: 10px;
                margin-bottom: 10px;
                border-radius: 4px;
                border: 1px solid #ddd;
            }
            button {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 10px 15px;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }
            button:hover {
                background-color: #0056b3;
            }
            .remove-product {
                background-color: #dc3545;
                border: none;
                color: white;
                padding: 5px 10px;
                border-radius: 4px;
                cursor: pointer;
                font-size: 14px;
            }
            .remove-product:hover {
                background-color: #c82333;
            }
            .product-row {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
            }
            .product-row input {
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <h1>Crear Nueva Factura</h1>
        <form action="SVFactura" method="POST">
            <label for="ci">CI:</label>
            <input type="text" id="ci" name="ci" required />

            <label for="cliente">Cliente:</label>
            <input type="text" id="cliente" name="cliente" required />

            <label for="direccion">Dirección:</label>
            <input type="text" id="direccion" name="direccion" required />

            <label for="fecha">Fecha:</label>
            <input type="date" id="fecha" name="fecha" required />

            <label for="telefono">Teléfono:</label>
            <input type="text" id="telefono" name="telefono" required />

            <label for="estado">Estado:</label>
            <input type="text" id="estado" name="estado" required />

            <h2>Detalles del Producto</h2>
            <div id="product-container">
                <div class="product-row">
                    <input type="text" name="detalles[0].producto" placeholder="Producto" required />
                    <input type="number" name="detalles[0].cantidad" placeholder="Cantidad" min="1" required />
                    <input type="number" step="0.01" name="detalles[0].precio" placeholder="Precio" required />
                    <input type="number" step="0.01" name="detalles[0].subtotal" placeholder="Subtotal" required />
                    <input type="datetime-local" name="detalles[0].fechaRegistra" required />
                    <button type="button" class="remove-product" onclick="removeProduct(this)">X</button>
                </div>
            </div>
            <button type="button" onclick="addProduct()">Añadir Producto</button>
            <button type="submit">Enviar</button>
        </form>

        <h1>Mostrar Datos Cliente</h1>
        <form action="SVFactura" method="GET">
            <button type="submit">Mostrar Facturas</button>
        </form>

        <script>
            let productIndex = 1;

            function addProduct() {
                const container = document.getElementById('product-container');
                const newProductRow = document.createElement('div');
                newProductRow.classList.add('product-row');
                newProductRow.innerHTML = `
            <input type="text" name="detalles[${productIndex}].producto" placeholder="Producto" required />
            <input type="number" name="detalles[${productIndex}].cantidad" placeholder="Cantidad" min="1" required />
            <input type="number" step="0.01" name="detalles[${productIndex}].precio" placeholder="Precio" required />
            <input type="number" step="0.01" name="detalles[${productIndex}].subtotal" placeholder="Subtotal" required />
            <input type="datetime-local" name="detalles[${productIndex}].fechaRegistra" required />
            <button type="button" class="remove-product" onclick="removeProduct(this)">X</button>
        `;
                container.appendChild(newProductRow);
                productIndex++;
            }

            function removeProduct(button) {
                const row = button.parentNode;
                row.parentNode.removeChild(row);
            }
        </script>
    </body>
</html>
