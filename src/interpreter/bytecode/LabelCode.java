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
 * used for GOTO, CALL and FALSEBRANCH
 */

public class LabelCode extends ByteCode {

    private String label;

    @Override
    public void init(Vector<String> args) {
        label = (String) args.firstElement();
    }

    @Override
    public void execute(VirtualMachine vm) {
        String dumpString = "LABEL ";
        dumpString = dumpString.concat(label);
       // vm.dump(dumpString);
    }

    public String getArgs() {
        return label;
    }
}