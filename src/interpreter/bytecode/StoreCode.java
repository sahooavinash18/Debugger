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

 // This class gets value from top of the stack and stores value in offset n from the start of the current frame

public class StoreCode extends ByteCode {

    int n;
    String id = "";

    @Override
    public void init(Vector<String> args) {
        if (args.size() == 1) {
            n = Integer.parseInt((String) args.firstElement());
        } else {
            id = args.get(args.size() - 1);
            n = Integer.parseInt((String) args.firstElement());
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.store(n);
        String tmp = "STORE ";
        Integer i = n;
        tmp = tmp.concat(i.toString() + " " + id + "  " + id + " = " + n);
        vm.dump(tmp);
    }

    @Override
    public String getArgs() {
        return id;
    }

}