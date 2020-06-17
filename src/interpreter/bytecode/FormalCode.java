/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.*;
import interpreter.debugger.*;
import java.util.*;

/**
 *
 * @author avinashsahoo
 */

public class FormalCode extends ByteCode {

    String args;
    int n;

    @Override
    public void init(Vector<String> args) {

        this.args = args.firstElement();
        n = Integer.parseInt((String) args.lastElement());
    }

    @Override
    public void execute(VirtualMachine vm) {
        DebuggerVirtualMachine virtM = (DebuggerVirtualMachine) vm;
        virtM.funcRecEnter(args,n);
        
    }

    @Override
    public String getArgs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}