package com.BakeryOrder.controllers;

import com.BakeryOrder.model.InventoryItem;
import com.BakeryOrder.service.InventoryService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InventoryController", urlPatterns = {
        "/inventory",
        "/add-item",
        "/update-item",
        "/delete-item",
        "/process-order"
})
public class InventoryController extends HttpServlet {
    private InventoryService inventoryService = new InventoryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/add-item":
                    showAddForm(request, response);
                    break;
                case "/update-item":
                    showUpdateForm(request, response);
                    break;
                case "/delete-item":
                    deleteItem(request, response);
                    break;
                case "/process-order":
                    processOrderItem(request, response);
                    break;
                default:
                    listInventory(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/add-item":
                    addItem(request, response);
                    break;
                case "/update-item":
                    updateItem(request, response);
                    break;
                default:
                    listInventory(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listInventory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<InventoryItem> inventoryItems = inventoryService.getAllItems();
        request.setAttribute("inventoryItems", inventoryItems);
        request.setAttribute("totalItems", inventoryService.getTotalItemsCount());
        request.setAttribute("lowStockItems", inventoryService.getLowStockItemsCount());
        request.setAttribute("outOfStockItems", inventoryService.getOutOfStockItemsCount());

        request.getRequestDispatcher("/WEB-INF/views/inventory.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/add-item.jsp").forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        InventoryItem existingItem = inventoryService.getItemById(id);
        request.setAttribute("item", existingItem);
        request.getRequestDispatcher("/WEB-INF/views/update-item.jsp").forward(request, response);
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        double quantity = Double.parseDouble(request.getParameter("quantity"));
        String unit = request.getParameter("unit");
        double threshold = Double.parseDouble(request.getParameter("threshold"));
        String supplier = request.getParameter("supplier");

        InventoryItem newItem = new InventoryItem(0, name, category, quantity, unit, threshold, supplier);
        inventoryService.addItem(newItem);

        request.setAttribute("message", "Item added successfully!");
        request.setAttribute("messageType", "success");
        response.sendRedirect("inventory");
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        double quantity = Double.parseDouble(request.getParameter("quantity"));
        String unit = request.getParameter("unit");
        double threshold = Double.parseDouble(request.getParameter("threshold"));
        String supplier = request.getParameter("supplier");

        InventoryItem item = new InventoryItem(id, name, category, quantity, unit, threshold, supplier);
        inventoryService.updateItem(item);

        request.setAttribute("message", "Item updated successfully!");
        request.setAttribute("messageType", "success");
        response.sendRedirect("inventory");
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        inventoryService.deleteItem(id);

        request.setAttribute("message", "Item deleted successfully!");
        request.setAttribute("messageType", "success");
        response.sendRedirect("inventory");
    }

    private void processOrderItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InventoryItem processedItem = inventoryService.processNextOrderItem();
        if (processedItem != null) {
            request.setAttribute("message", "Processed item: " + processedItem.getName());
        } else {
            request.setAttribute("message", "No items in queue to process");
        }
        request.setAttribute("messageType", "success");
        response.sendRedirect("inventory");
    }
}
