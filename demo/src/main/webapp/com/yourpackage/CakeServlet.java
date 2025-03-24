package com.yourpackage;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class CakeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cake> cakes = new ArrayList<>();

        // Example cakes, replace with your database logic or other data source
        cakes.add(new Cake("Chocolate Cake", "A rich and moist chocolate cake topped with creamy chocolate frosting.", "$15.99", "cake-1.jpg"));
        cakes.add(new Cake("Vanilla Cake", "A light and fluffy vanilla cake with a smooth vanilla buttercream.", "$12.99", "cake-2.jpg"));
        cakes.add(new Cake("Red Velvet Cake", "A rich red velvet cake layered with cream cheese frosting.", "$18.99", "cake-3.jpg"));

        // Set the list of cakes as a request attribute
        request.setAttribute("cakes", cakes);

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
package com.yourpackage;

public class Cake {
    private String name;
    private String description;
    private String price;
    private String imageUrl;

    public Cake(String name, String description, String price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
