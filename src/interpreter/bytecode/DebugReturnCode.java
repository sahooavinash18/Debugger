/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import interpreter.debugger.*;

/**
 *
 * @author avinashsahoo
 */

public class DebugReturnCode extends ReturnCode {

    @Override
    public void execute(VirtualMachine vm) {//CURRENTLY POPS STACK
        DebuggerVirtualMachine virtM = (DebuggerVirtualMachine) vm;
        super.execute(virtM);
        if (virtM.getTraceBool()) {
            String line = "";
            for (int i = 0; i < virtM.getFunctStackSize(); i++) {
                line += " ";
            }

            line += "exit " + virtM.getName() + ": " + topValue;
            virtM.setTrace(line);
        }
        virtM.end();

    }

}
