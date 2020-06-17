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

import java.util.*;
import interpreter.VirtualMachine;

// This is ByteCode abstract class which initializes ByteCode object

public abstract class ByteCode {

    public abstract void init(Vector<String> args);
    public abstract void execute(VirtualMachine vm);
    public abstract String getArgs();

}
