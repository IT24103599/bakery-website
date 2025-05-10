package com.BakeryOrder.service;

import com.BakeryOrder.dao.InventoryDao;
import com.BakeryOrder.model.InventoryItem;
import java.util.List;

public class InventoryService {
    private InventoryDao inventoryDao;

    public InventoryService() {
        this.inventoryDao = new InventoryDao();
    }

    public void addItem(InventoryItem item) {
        // Generate ID (simple implementation - in real app use better ID generation)
        List<InventoryItem> items = getAllItems();
        int newId = items.isEmpty() ? 1 : items.get(items.size() - 1).getId() + 1;
        item.setId(newId);

        inventoryDao.addItem(item);
    }

    public List<InventoryItem> getAllItems() {
        return inventoryDao.getAllItems();
    }

    public InventoryItem getItemById(int id) {
        return inventoryDao.getItemById(id);
    }

    public void updateItem(InventoryItem item) {
        inventoryDao.updateItem(item);
    }

    public void deleteItem(int id) {
        inventoryDao.deleteItem(id);
    }

    public List<InventoryItem> getLowStockItems() {
        return inventoryDao.getLowStockItems();
    }

    public int getTotalItemsCount() {
        return getAllItems().size();
    }

    public int getLowStockItemsCount() {
        return getLowStockItems().size();
    }

    public int getOutOfStockItemsCount() {
        int count = 0;
        for (InventoryItem item : getAllItems()) {
            if (item.getQuantity() <= 0) {
                count++;
            }
        }
        return count;
    }

    public InventoryItem processNextOrderItem() {
        return inventoryDao.processNextItem();
    }

    public List<InventoryItem> getItemsSortedByQuantity() {
        return inventoryDao.getItemsSortedByQuantity();
    }
}