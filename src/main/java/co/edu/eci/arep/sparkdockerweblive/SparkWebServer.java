/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eci.arep.sparkdockerweblive;

import co.edu.eci.arep.sparkdockerweblive.persistencia.Mongo;
import java.util.Map;
import java.util.Set;
import static spark.Spark.*;

/**
 *
 * @author Andrés Quintero
 */
public class SparkWebServer {
    
    /**
     * Permite visualizar a aplicación
     * @param args args
     */

    public static void main(String... args) {
        //staticFiles.location("/public");
        port(getPort());
        get("/", (req, res) -> {
            Mongo cm = new Mongo();
            //res.type("text/html");
            String outputLine = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF - 8\">\n"
                    + "<title>Mensajes</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<h2>Ingrese un nuevo mensaje</h2>\n"
                    + "<form method=\"post\" action=\"/api/submit\">\n"
                    + "Mensaje:<input type = \"text\" name=\"mensaje\"/>\n"
                    + "<input type = \"submit\" value=\"Enviar mensaje\"/>\n"
                    + "</form>\n"
                    + "<h3>Mensajes Antiguos</h3>\n"
                    + cm.selectAll() + "\n"
                    + "</body>\n"
                    + "</html>\n";
            return outputLine;
        });

        post("/api/submit", (req, res) -> {

            //get all the form attributes.
            Mongo cm = new Mongo();
            Map<String, String[]> parameterMap = req.raw().getParameterMap();
            Set<String> parameterSet = parameterMap.keySet();
            parameterSet.stream().forEach(each -> {
                String join = String.join(",", parameterMap.get(each));
                cm.añadirMensaje(join);
                System.out.println("El mensaje es: " + join);
            });
            String outputLine = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "<meta charset=\"UTF - 8\">\n"
                    + "<title>Mensajes</title>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<h2>Ingrese un nuevo mensaje</h2>\n"
                    + "<form method=\"post\" action=\"/api/submit\">\n"
                    + "Mensaje:<input type = \"text\" name=\"mensaje\"/>\n"
                    + "<input type = \"submit\" value=\"Enviar mensaje\"/>\n"
                    + "</form>\n"
                    + "<h3>Mensajes Antiguos</h3>\n"
                    + cm.selectAll() + "\n"
                    + "</body>\n"
                    + "</html>\n";
            return outputLine;
        });
    }
    
    /**
     * establce un puerto local
     * @return puerto 
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
