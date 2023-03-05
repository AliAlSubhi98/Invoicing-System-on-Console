package src;

import java.awt.MenuItem;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, MenuItem> items;

    public Menu() {
        items = new HashMap<>();
    }

    public void addItem(MenuItem item) {
        items.put(item.getName(), item);
    }

    public void removeItem(String name) {
        items.remove(name);
    }

    public void updateItem(MenuItem item) {
        items.put(item.getName(), item);
    }

    public MenuItem getItem(String name) {
        return items.get(name);
    }

    public void show() {
        System.out.println("Menu:");
        for (MenuItem item : items.values()) {
            System.out.println(item);
        }
    }
}
