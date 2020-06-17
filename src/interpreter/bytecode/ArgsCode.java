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

 //This class create a new frame from runStack 

public class ArgsCode extends ByteCode {

    int n;

    public void init(Vector<String> args) {
        n = Integer.parseInt((String) args.firstElement());
    }

    public void execute(VirtualMachine vm) {
        vm.newFrameAt(n);
        String tmp = "ARGS ";
        tmp = tmp.concat(((Integer) n).toString());
        vm.dump(tmp);
    }

    public String getArgs() {
        return Integer.toString(n);
    }
}