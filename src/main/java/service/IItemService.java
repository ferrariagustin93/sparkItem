package service;

import exception.ApiException;
import clase.Item;

import java.util.Collection;

public interface IItemService {

    public Collection<Item> getItems();
    public Item getItem(String id);
    public String addItem(String id,Item item) throws ApiException;
    public Item updateItem(String id, Item item) throws ApiException;
    public Item deleteItem(String id) throws ApiException;
    public boolean itemExists(String id) throws ApiException;
}
