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


 //This class takes current function and pops the current frame out

public class ReturnCode extends ByteCode {

    String label = "";
    int topValue;

    @Override
    public void init(Vector<String> args) {
        if (!args.isEmpty()) {
            label = args.firstElement();
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.popFrame();
        vm.setPC(vm.popAddress());
        topValue = vm.peek();
        String tmp = "RETURN ";
        if ((label.equals("NULL"))) {
            vm.dump(tmp);
        } else {
            tmp = tmp.concat(label);
            vm.dump(tmp);
        }
    }

    @Override
    public String getArgs() {

        return label;
    }

}