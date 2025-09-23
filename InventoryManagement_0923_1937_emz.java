// 代码生成时间: 2025-09-23 19:37:29
package inventorymanagement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Managed Bean for InventoryManagement application.
 * Handles inventory operations.
 */
@ManagedBean
@SessionScoped
public class InventoryManagement implements Serializable {
    private Map<String, Integer> inventory = new HashMap<>();
    private String item;
    private Integer quantity;
    private String errorMessage = "";
    private String successMessage = "";

    /**
     * Default constructor.
     */
    public InventoryManagement() {
    }

    /**
     * Adds or updates an item in the inventory.
     * @param item The name of the item.
     * @param quantity The quantity of the item.
     */
    public void addItem() {
        try {
            if (item == null || quantity == null || quantity <= 0) {
                throw new IllegalArgumentException("Item or quantity cannot be null or less than or equal to zero.");
            }
            inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
            successMessage = "Item added/updated successfully.";
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    /**
     * Removes an item from the inventory.
     * @param item The name of the item to remove.
     * @param quantity The quantity of the item to remove.
     */
    public void removeItem() {
        try {
            if (item == null || quantity == null || quantity <= 0 || !inventory.containsKey(item)) {
                throw new IllegalArgumentException("Invalid item or quantity, or item does not exist in inventory.");
            }
            int currentQuantity = inventory.get(item);
            currentQuantity -= quantity;
            if (currentQuantity > 0) {
                inventory.put(item, currentQuantity);
            } else {
                inventory.remove(item);
            }
            successMessage = "Item removed successfully.";
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    // Getters and Setters
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    // Inventory-related methods
    public Map<String, Integer> getInventory() {
        return inventory;
    }
}
