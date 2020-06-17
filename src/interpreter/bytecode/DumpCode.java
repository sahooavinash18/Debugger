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
 * VM should dump the runStack, checks and Turns on or off 
 */

public class DumpCode extends ByteCode {

    String label;

    @Override
    public void execute(VirtualMachine vm) {
        if (label.equals("ON")) {
            vm.dumpStateTrue();
        } else if (label.equals ("OFF")) {
            vm.dumpStateFalse();
        }
    }

    @Override
    public void init(Vector<String> args) {
        label = (String) args.firstElement();
    }

    @Override
    public String getArgs() {
        throw new UnsupportedOperationException("shouldn't be seeing this"); //To change body of generated methods, choose Tools | Templates.
    }
}