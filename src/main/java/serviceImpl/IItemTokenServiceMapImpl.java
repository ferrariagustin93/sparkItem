package serviceImpl;

import clase.Item;
import clase.ItemToken;
import exception.ApiException;
import service.IItemService;
import service.IItemTokenService;

import java.util.*;

public class IItemTokenServiceMapImpl implements IItemTokenService {

    private Map<String,ItemToken> itemTokenMap;
    private static int id;

    public IItemTokenServiceMapImpl() {
        itemTokenMap = new HashMap<String,ItemToken>();
        id+=1;
    }

    @Override
    public Collection<ItemToken> getItems() {
        return  this.itemTokenMap.values();
    }

    @Override
    public Collection<ItemToken> getItemsUser(String id) {
        Collection<ItemToken> listUserItem = new ArrayList<>();
        for(ItemToken item : this.itemTokenMap.values()) {
            if(item.getUserId().equals(id)) {
                ((ArrayList<ItemToken>) listUserItem).add(item);
            }
        }
        return listUserItem;
    }

    @Override
    public ItemToken getItem(String id) {
        return this.itemTokenMap.get(id);
    }


    public String addItem(ItemToken itemToken) throws ApiException {
        itemToken.setId(String.valueOf(IItemTokenServiceMapImpl.id));
        this.itemTokenMap.put(String.valueOf(id),itemToken);
        IItemTokenServiceMapImpl.id=IItemTokenServiceMapImpl.id+1;
        return String.valueOf((IItemTokenServiceMapImpl.id-1));
    }

    @Override
    public ItemToken updateItem(String id, ItemToken itemToken) throws ApiException {
        this.itemTokenMap.replace(id,itemToken);
        return this.itemTokenMap.get(id);
    }

    @Override
    public ItemToken deleteItem(String id) throws ApiException {
        return this.itemTokenMap.remove(id);
    }

    @Override
    public boolean itemExists(String id) throws ApiException {
        return this.itemTokenMap.containsKey(id);
    }


}
