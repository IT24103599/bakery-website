package com.BakeryOrder.dao;

import com.BakeryOrder.model.InventoryItem;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class InventoryDao {
    private static final String FILE_PATH = "inventory_data.ser";
    private Queue<InventoryItem> inventoryQueue = new LinkedList<>();

    // Add item to both file and queue
    public void addItem(InventoryItem item) {
        List<InventoryItem> items = getAllItems();
        items.add(item);
        saveItemsToFile(items);
        inventoryQueue.add(item);
    }

    // Get all items from file
    public List<InventoryItem> getAllItems() {
        List<InventoryItem> items = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                items = (List<InventoryItem>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return items;
    }

    // Get item by ID
    public InventoryItem getItemById(int id) {
        List<InventoryItem> items = getAllItems();
        for (InventoryItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    // Update item
    public void updateItem(InventoryItem updatedItem) {
        List<InventoryItem> items = getAllItems();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == updatedItem.getId()) {
                items.set(i, updatedItem);
                break;
            }
        }
        saveItemsToFile(items);

        // Update queue if needed
        inventoryQueue = new LinkedList<>(items);
    }

    // Delete item
    public void deleteItem(int id) {
        List<InventoryItem> items = getAllItems();
        items.removeIf(item -> item.getId() == id);
        saveItemsToFile(items);

        // Update queue
        inventoryQueue = new LinkedList<>(items);
    }

    // Get low stock items
    public List<InventoryItem> getLowStockItems() {
        List<InventoryItem> items = getAllItems();
        List<InventoryItem> lowStockItems = new ArrayList<>();
        for (InventoryItem item : items) {
            if (item.getQuantity() < item.getThreshold()) {
                lowStockItems.add(item);
            }
        }
        return lowStockItems;
    }

    // Process next item in queue (for order processing)
    public InventoryItem processNextItem() {
        return inventoryQueue.poll();
    }

    // Get queue size
    public int getQueueSize() {
        return inventoryQueue.size();
    }

    // Sort items by quantity (using bubble sort)
    public List<InventoryItem> getItemsSortedByQuantity() {
        List<InventoryItem> items = getAllItems();
        bubbleSortByQuantity(items);
        return items;
    }

    private void bubbleSortByQuantity(List<InventoryItem> items) {
        int n = items.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (items.get(j).getQuantity() > items.get(j+1).getQuantity()) {
                    // Swap items
                    InventoryItem temp = items.get(j);
                    items.set(j, items.get(j+1));
                    items.set(j+1, temp);
                }
            }
        }
    }

    // Helper method to save items to file
    private void saveItemsToFile(List<InventoryItem> items) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}