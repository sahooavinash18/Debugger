/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.*;
import interpreter.bytecode.*;
import interpreter.debugger.*;
import java.util.*;

/**
 *
 * @author avinashsahoo
 */

public class FunctionCode extends ByteCode {

    int start, end;
    String function;

    @Override
    public void init(Vector<String> args) {
        function = args.firstElement();
        int i = function.indexOf("<");
        if (i != -1) {
            function = function.substring(0, i);
        }
        start = Integer.parseInt(args.get(1));
        end = Integer.parseInt(args.get(2));
    }

    @Override
    public void execute(VirtualMachine vm) {
        DebuggerVirtualMachine virtM = (DebuggerVirtualMachine) vm;
        virtM.setFunct(function, start, end);
        String line = "";

        if (virtM.getTraceBool()) {

            for (int i = 1; i < virtM.getFunctStackSize(); i++) {
                line += " ";
            }
            ByteCode code = virtM.getCode(virtM.getPC()+1);
            for (int i = 1;code instanceof FormalCode;i++) {
                line+= function + " ("+ virtM.peek() + ")";
                code=virtM.getCode(virtM.getPC()+i);
            }
                virtM.setTrace(line + ")");
            }
        
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String getArgs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}