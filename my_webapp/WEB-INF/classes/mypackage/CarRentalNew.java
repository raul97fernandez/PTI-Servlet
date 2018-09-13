package mypackage;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import org.json.simple.parser.ParseException;

public class CarRentalNew extends HttpServlet {

  int cont = 0;

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    String model_vehicle = req.getParameter("model_vehicle");
    String model_vehicle_string = "Empty";
    switch(model_vehicle) {
      case "54": 
        model_vehicle_string = "Econòmic";
        break;
      case "71": 
        model_vehicle_string = "Semi-Luxe";
        break;
      case "82": 
        model_vehicle_string = "Luxe";
        break;
      case "139": 
        model_vehicle_string = "Limusina";
        break;
    }
    String sub_model_vehicle = req.getParameter("sub_model_vehicle");
    String num_vehicles = req.getParameter("num_vehicles");
    String dies_lloguer = req.getParameter("dies_lloguer");
    String descompte = req.getParameter("descompte");
    out.println("<html><big>Car Model: " + model_vehicle_string + "</big><br> "
               + "<pre>Engine: " + sub_model_vehicle + "</pre>"
               + "<pre>Number of units: " + num_vehicles + "</pre>"
               + "<pre>Number of days: " + dies_lloguer + "</pre>"
               + "<pre>Descompte(%): " + descompte + "</pre>"

              + "<html>");

    JSONParser parser = new JSONParser();
    JSONObject obj = null;

    try {
       Object obj2 = parser.parse(new FileReader("./test.json"));
       obj = (JSONObject) obj2;
       JSONArray list = (JSONArray) obj.get("messages");
       list.add(model_vehicle_string);
       list.add(sub_model_vehicle);
       list.add(num_vehicles);
       list.add(dies_lloguer);
       list.add(descompte);

       obj.put("messages", list);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (ParseException e) {
            e.printStackTrace();
        }
    if (obj == null) {
          obj.put("name", "Exemple1");

       JSONArray list = new JSONArray();
       list.add(model_vehicle_string);
       list.add(sub_model_vehicle);
       list.add(num_vehicles);
       list.add(dies_lloguer);
       list.add(descompte);

      obj.put("messages", list);
    }

    try (FileWriter file = new FileWriter("./test.json")) {

        file.write(obj.toJSONString());
        file.flush();

    } catch (IOException e) {
        e.printStackTrace();
    }

    System.out.print(obj);
  }



  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
  }
}
