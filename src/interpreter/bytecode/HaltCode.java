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
 * To execute HALT
 */

public class HaltCode extends ByteCode {

    @Override
    public void init(Vector<String> args) {
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.stopRun();
    }

    @Override
    public String getArgs() {
return "";    }



}
