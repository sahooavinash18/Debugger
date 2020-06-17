/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

/**
 *
 * @author avinashsahoo
 */

import java.util.*;
import interpreter.*;

/**
 * Transfer goes to the function
 */

public class CallCode extends ByteCode {

    String label;
    int address;

    @Override
    public void init(Vector<String> args) {
        label = (String) args.firstElement();
    }

    public String getArgs() {
        return label;
    }

    public void setAddress(int i) {
        address = i;

    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushAddrs(vm.getPC());
        vm.setPC(address);
        String tmp = "BOP ";
        tmp = tmp.concat(label);
        vm.dump(tmp);
    }


    public int getAddress() {
        return address;
    }

}