/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

/**
 *
 * @author avinashsahoo
 */

import interpreter.bytecode.*;
import java.util.*;

 
public class Program extends Object {
    
    static HashMap<String, Integer> address;
    private final ArrayList<Object> container;
    
    Program () {
        container = new ArrayList();
        address = new HashMap<String, Integer>();
    }
    
    public void push(ByteCode byteCode) {
        if(byteCode instanceof LabelCode) {
            LabelCode labelBranch = (LabelCode)byteCode;
            addLabel(labelBranch.getArgs(),container.size());
        }
        container.add(byteCode);
    }
    
    public void resolveAddress() {//resolves the addresses of these three bytecodes,
        for (Object content : container) {
            if (content instanceof GoToCode) {
                GoToCode changeBranch = (GoToCode) content;//makes it so the instance of the class equals the contents inside the container
                changeBranch.setAddress(address.get(changeBranch.getArgs()));//.the arguments inside the code, is taken by the hashmap, which sets the new address of the new instanced bytecode 
            } else if (content instanceof FalseBranchCode) {
                FalseBranchCode changeBranch = (FalseBranchCode) content;
                changeBranch.setAddress(address.get(changeBranch.getArgs()));
            } else if (content instanceof CallCode) {
                CallCode changeBranch = (CallCode) content;
                changeBranch.setAddress(address.get(changeBranch.getArgs()));
            }
        }
    }
    
    static public void addLabel(String key, int branch) {
        address.put(key, branch);
    }
    
    static public String getBranch(String branch) {
        return address.get(branch).toString();
    }
    
    public ByteCode getCode(int index) {
        return (ByteCode)container.get(index);
    }
    
    public int codeListSize() {
        return container.size();
    }

}