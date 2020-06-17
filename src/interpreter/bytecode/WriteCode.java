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

 //This class write value on top of stack in output

public class WriteCode extends ByteCode {

    int n;

    @Override
    public void init(Vector<String> args) {
    }

    @Override
    public void execute(VirtualMachine vm) {
        n = vm.peek();
        System.out.println(n);
        String tmp = "WRITE ";
        vm.dump(tmp);
    }

    public String getArgs() {
        return Integer.toString(n);
    }

}