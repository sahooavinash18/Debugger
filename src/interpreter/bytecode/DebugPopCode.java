/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.*;
import interpreter.bytecode.*;
import interpreter.debugger.*;

/**
 *
 * @author avinashsahoo
 */

public class DebugPopCode extends PopCode {

    @Override
    public void execute(VirtualMachine vm) {
        DebuggerVirtualMachine virtM = (DebuggerVirtualMachine) vm;
        super.execute(vm);
       // virtM.debugPop(getPop());
    }

}
