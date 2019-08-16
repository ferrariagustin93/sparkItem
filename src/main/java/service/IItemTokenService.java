package service;

import clase.ItemToken;
import exception.ApiException;

import java.util.Collection;

public interface IItemTokenService {

    public Collection<ItemToken> getItems();
    public Collection<ItemToken> getItemsUser(String id);
    public ItemToken getItem(String id);
    public String addItem(ItemToken itemToken) throws ApiException;
    public ItemToken updateItem(String id, ItemToken itemToken) throws ApiException;
    public ItemToken deleteItem(String id) throws ApiException;
    public boolean itemExists(String id) throws ApiException;

}
