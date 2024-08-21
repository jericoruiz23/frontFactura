package org.ibarra.crudfront.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.ibarra.crudfacturafront.model.Cabecera;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.ibarra.crudfacturafront.model.Detalle;

@WebServlet(name = "SVFactura", urlPatterns = {"/SVFactura"})
public class SVFactura extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Llamada al método para consumir la API POST
        String apiResponse = consumirApiPost("http://localhost:8080/cabecera/listar");

        // Procesar la respuesta de la API
        List<Cabecera> listaCabecera = parsearApiResponse(apiResponse);

        // Guardar la lista en la sesión
        HttpSession session = request.getSession();
        session.setAttribute("listaCabecera", listaCabecera);

        // Redirigir a la página JSP
        response.sendRedirect("mostrarFacturas.jsp");

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener datos de la cabecera
        String ci = request.getParameter("ci");
        String cliente = request.getParameter("cliente");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String estado = request.getParameter("estado");
        String fecha = request.getParameter("fecha");

        // Imprimir datos de la cabecera
        System.out.println("----------------------------------------");
        System.out.println("ci: " + ci);
        System.out.println("direccion: " + direccion);
        System.out.println("telefono: " + telefono);
        System.out.println("cliente: " + cliente);
        System.out.println("fecha: " + fecha);
        System.out.println("estado: " + estado);

        // Obtener y procesar los detalles
        List<Detalle> detalles = new ArrayList<>();
        int index = 0;

        while (true) {
            String producto = request.getParameter("detalles[" + index + "].producto");
            String cantidadStr = request.getParameter("detalles[" + index + "].cantidad");
            String precioStr = request.getParameter("detalles[" + index + "].precio");
            String subtotalStr = request.getParameter("detalles[" + index + "].subtotal");
            String fechaRegistraStr = request.getParameter("detalles[" + index + "].fechaRegistra");

            if (producto == null || cantidadStr == null || precioStr == null || subtotalStr == null || fechaRegistraStr == null) {
                break; // Salir del bucle si ya no hay más detalles
            }

            try {
                Integer cantidad = Integer.parseInt(cantidadStr);
                Double precio = Double.parseDouble(precioStr);
                Double subtotal = Double.parseDouble(subtotalStr);

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); // Formato para datetime-local
                Date fechaRegistra = formatter.parse(fechaRegistraStr);

                Detalle detalle = new Detalle(cantidad, subtotal, producto, precio, fechaRegistra);
                detalles.add(detalle);

                System.out.println(" ------- Detalle " + (index + 1) + " -------");
                System.out.println("Producto: " + producto);
                System.out.println("Cantidad: " + cantidad);
                System.out.println("Precio: " + precio);
                System.out.println("Subtotal: " + subtotal);
                System.out.println("Fecha de Registro: " + fechaRegistra);
                System.out.println("----------------------------------------");
            } catch (NumberFormatException | ParseException e) {
                e.printStackTrace(); // Manejar excepciones
            }

            index++;
        }

        // Aquí puedes continuar con la lógica para guardar la factura y los detalles
    }

    private String consumirApiPost(String apiUrl) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true); // Permitir salida de datos

            // No enviar datos en el cuerpo si no es necesario
            try (OutputStream os = connection.getOutputStream()) {
                os.write("".getBytes()); // Puedes poner datos aquí si es necesario
                os.flush();
            }

            // Leer la respuesta
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
                System.out.println("API consumida");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores específico o retorno de un mensaje de error
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        // Retornar la respuesta de la API
        return result.toString();
    }

    private List<Cabecera> parsearApiResponse(String apiResponse) {
        ObjectMapper mapper = new ObjectMapper();
        List<Cabecera> listaCabecera = new ArrayList<>();

        try {
            // Deserializar la respuesta JSON en una lista de objetos Cabecera
            listaCabecera = mapper.readValue(apiResponse, new TypeReference<List<Cabecera>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores específico
        }
        System.out.println("DATA PARSEADA");
        return listaCabecera;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
