/*
 * Simple HashTable implementation. Uses inner class hashobject to link key value pairs. 
 * HashFunction is simply mod division of key by size of array. 
 */
package algorithmsanddatastructures;

import java.util.LinkedList;

/**
 *
 * @author trevor
 */
public class HashTable {
    private LinkedList [] arr;
    private int size;
    
    public HashTable (int initSize) {
        size = initSize;
        arr = new LinkedList[size];
    }
    public void put(int key, Object value) {
        HashObject pair = new HashObject(key,value);
        if(arr[key%size]==null){
            arr[key%size] = new LinkedList<>();
        }
        arr[key%size].add(pair);
    }
    public Object get(int searchkey) {
        LinkedList<HashObject> templist = arr[searchkey%size];
        if(templist==null){
            return null;
        }
        for(HashObject temp:templist ) {
            if(temp.getKey()==searchkey) {
                return temp.getValue();
            }
        }
        return null;
    }
    /*
    !!!!!  Should use StringBuilder!
    */
    @Override
    public String toString() {
        String toPrint = "";
        for (int i = 0; i < size; i++) {
            LinkedList<HashObject> templist = arr[i];
            toPrint = toPrint + "[";
            if(templist!=null){   
                for(HashObject temp:templist ) {
                    toPrint = toPrint + "{"+temp.getValue() + "} ";
                }
            }
            toPrint = toPrint + "]\n";
        
        }
        return toPrint;
    }
}
class HashObject {
    private Object value;
    private int key; 
    
    HashObject (int initkey, Object initvalue) {
        key = initkey;
        value = initvalue;
    }
    public int getKey() {
        return key;
    }
    public Object getValue() {
        return value;
    }
   
}
    
    
