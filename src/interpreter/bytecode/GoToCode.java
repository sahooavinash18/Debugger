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

import java.util.Vector;
import interpreter.VirtualMachine;

/**
 * GOTO label
 */

public class GoToCode extends ByteCode {

    private String label;
    int address;

    public void setAddress(int i) {
        address = i;
    }

    @Override
    public void init(Vector<String> args) {
        label = (String) args.firstElement();
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setPC(address);
        String dumpString = "GOTO ";
        dumpString = dumpString.concat(label);
        vm.dump(dumpString);
        
    }

    public int getAddress() {
        return address;
    }

    @Override
    public String getArgs() {
        return label;
    }

}