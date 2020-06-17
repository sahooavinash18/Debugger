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

 //This class gets top n levels from stack

public class PopCode extends ByteCode {

    int n;

    @Override
    public void init(Vector<String> args) {
        n = Integer.parseInt((String) args.firstElement());
    }

    @Override
    public void execute(VirtualMachine vm) {
        for (int i = 1; i < n; i++) {
            vm.pop();

        }
        String tmp = "POP ";
        tmp = tmp.concat(((Integer) n).toString());

        vm.dump(tmp);
    }

    public int getPop() {
        return n;
    }

    public String getArgs() {
        return Integer.toString(n);
    }
}