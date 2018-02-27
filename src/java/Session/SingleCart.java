/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class SingleCart implements Cart{
    private List<Item> list;
    private String user;
    
    @Override
    public void initialize() {
        list = new ArrayList<Item>();
    }

    @Override
    public void addItem(Item item) {
        list.add(item);
    }

    @Override
    public void removeItem(Item item){
        int temp = 0;
        for(Item cart : list){
            if(cart.getId().equals(item.getId())){
                list.remove(temp);
                break;
            }
            temp++;
        }
    }

    @Override
    public List<Item> getContents() {
        return list;
    }

    @Override
    public void remove() {
        if(!list.isEmpty()){
            list = new ArrayList<Item>();
        }
    }
    
    @Override
    public String getUser(){
        return user;
    }
    
}
