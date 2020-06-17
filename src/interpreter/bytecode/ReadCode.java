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

import interpreter.VirtualMachine;
import java.util.*;
import java.util.Vector;
import java.io.*;

 // This class reads and puts integer on top of stack

public class ReadCode extends ByteCode {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void init(Vector<String> args) {
    }

    @Override
    public void execute(VirtualMachine vm) {
        System.out.print("input number ");
        int input = scanner.nextInt();
        vm.Push(input);
        String tmp = "READ ";
        vm.dump(tmp);
    }

    @Override
    public String getArgs() {
        return "";
    }

}