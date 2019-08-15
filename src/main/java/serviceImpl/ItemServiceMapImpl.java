package serviceImpl;


import clase.Item;
import exception.ApiException;
import service.IItemService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ItemServiceMapImpl implements IItemService {

    private Map<String,Item> itemMap;


    public ItemServiceMapImpl()
    {
        itemMap = new HashMap<String,Item>();
    }


    public Collection<Item> getItems() {
        return this.itemMap.values();
    }

    public Item getItem(String id) {
        return this.itemMap.get(id);
    }

    public String addItem(String id,Item item) throws ApiException {
        this.itemMap.put(id,item);
        return id;
    }

    public Item updateItem(String id, Item item) throws ApiException {
        this.itemMap.replace(id,item);
        return this.itemMap.get(id);
    }

    public Item deleteItem(String id) throws ApiException {
        return this.itemMap.remove(id);
    }

    public boolean itemExists(String id) throws ApiException {
        return this.itemMap.containsKey(id);
    }
}
