/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import interpreter.debugger.ui.UI;
import java.io.*;
import interpreter.*;

/**
 *
 * @author avinashsahoo
 */

public class Debugger {

    Interpreter interpreter;
    DebuggerVirtualMachine virtM;
    UI inter;
    String file, file2;
    static boolean status = true;

    public Debugger(String codeFile, String sourceFile) {
        file = codeFile;
        file2 = sourceFile;
    }

    public void runDebug() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Interpreter interpreter = new Interpreter(file, true);
        virtM=new DebuggerVirtualMachine(interpreter.getProg(), file2);
        inter=new UI(virtM);
        inter.printCode();
        while(virtM.getRun()){
            inter.help();
        }
        System.out.println("Debugging is Done");
    }
}