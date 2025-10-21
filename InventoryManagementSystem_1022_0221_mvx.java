// 代码生成时间: 2025-10-22 02:21:04
package com.inventorysystem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.validator.ValidatorException;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Set;

/**
 * InventoryBean is the ManagedBean responsible for the inventory management.
 * It handles CRUD operations and validation.
 */
@ManagedBean(name = "inventoryBean")
@ViewScoped
public class InventoryBean implements Serializable {

    private List<Item> items;
    private Item selectedItem;
    private String newItemName;
    private float newItemQuantity;
    private float newItemPrice;

    public InventoryBean() {
        items = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        // Initialize the list with sample data
        items.add(new Item("Book", 100, 9.99f));
        items.add(new Item("Pen", 200, 1.50f));
        items.add(new Item("Notebook", 150, 2.75f));
    }

    // Getters and Setters
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getNewItemName() {
        return newItemName;
    }

    public void setNewItemName(String newItemName) {
        this.newItemName = newItemName;
    }

    public float getNewItemQuantity() {
        return newItemQuantity;
    }

    public void setNewItemQuantity(float newItemQuantity) {
        this.newItemQuantity = newItemQuantity;
    }

    public float getNewItemPrice() {
        return newItemPrice;
    }

    public void setNewItemPrice(float newItemPrice) {
        this.newItemPrice = newItemPrice;
    }

    // CRUD operations
    public void addItem() {
        try {
            if (isValidItem()) {
                Item newItem = new Item(newItemName, newItemQuantity, newItemPrice);
                items.add(newItem);
                FacesMessage message = new FacesMessage("Item added successfully!", newItemName);
                FacesContext.getCurrentInstance().addMessage(null, message);
                resetNewItemFields();
            }
        } catch (ValidatorException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    private boolean isValidItem() throws ValidatorException {
        if (newItemName == null || newItemName.isEmpty()) {
            throw new ValidatorException(new FacesMessage("Item name is required."));
        }
        if (newItemQuantity <= 0) {
            throw new ValidatorException(new FacesMessage("Item quantity must be greater than 0."));
        }
        if (newItemPrice <= 0) {
            throw new ValidatorException(new FacesMessage("Item price must be greater than 0."));
        }
        return true;
    }

    public void deleteItem() {
        if (selectedItem != null) {
            items.remove(selectedItem);
            FacesMessage message = new FacesMessage("Item removed successfully!", selectedItem.getName());
            FacesContext.getCurrentInstance().addMessage(null, message);
            resetSelectedItem();
        }
    }

    private void resetNewItemFields() {
        newItemName = "";
        newItemQuantity = 0;
        newItemPrice = 0;
    }

    private void resetSelectedItem() {
        selectedItem = null;
    }

    // Inner class representing an item
    public static class Item {
        private String name;
        private float quantity;
        private float price;

        public Item(String name, float quantity, float price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getQuantity() {
            return quantity;
        }

        public void setQuantity(float quantity) {
            this.quantity = quantity;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }
    }
}
