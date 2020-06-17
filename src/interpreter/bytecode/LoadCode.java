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

 //This class is used to load method in virtual machine

public class LoadCode extends ByteCode {

    int n;
    String id;

    @Override
    public void init(Vector<String> args) {
        n = Integer.parseInt((String) args.firstElement());
        id = (String) args.firstElement();
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.load(n);
        String tmp = "LOAD ";
        tmp = tmp.concat(((Integer) n).toString());
        if ((id.isEmpty()) == false) {

            tmp = tmp.concat(" " + id + "   int" + id + "   <load " + id + ">");
        }
        vm.dump(tmp);
    }

    @Override
    public String getArgs() {
        return id;
    }

}