package mypackage;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import org.json.simple.parser.ParseException;

public class CarRentalList extends HttpServlet {

  int cont = 0;

  public void doGet(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    String nombre = req.getParameter("userid");
    out.println("<html>");

    JSONParser parser = new JSONParser();
    JSONObject obj = null;

    try {
    Object obj2 = parser.parse(new FileReader("./test.json"));
       obj = (JSONObject) obj2;
       JSONArray list = (JSONArray) obj.get("messages");
    Iterator<String> iterator = list.iterator();
    while(iterator.hasNext()){
      if (cont % 5 == 0) {
        out.println("<br><br>");
      }
            out.println("<br> <big>" + iterator.next() + "</big>");
      cont++;
        }
    out.println("</html>");
}
catch (ParseException e) {
            e.printStackTrace();
        }
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res)
                    throws ServletException, IOException {
    doGet(req, res);
  }
}
