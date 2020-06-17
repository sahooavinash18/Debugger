/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

/**
 *
 * @author avinashsahoo
 */

import java.util.*;

 // This class Loads ByteCode and name of ByteCode Class into a bytecode HashMap

public class CodeTable extends Object {

    private static HashMap<String, String> table = new HashMap<String, String>();

    public static String get(String code) {
        return table.get(code);
    }

    public static void init() {
        table.put("HALT", "HaltCode");
        table.put("POP", "PopCode");
        table.put("FALSEBRANCH", "FalseBranchCode");
        table.put("GOTO", "GoToCode");
        table.put("STORE", "StoreCode");
        table.put("LOAD", "LoadCode");
        table.put("LIT", "LitCode");
        table.put("ARGS", "ArgsCode");
        table.put("CALL", "CallCode");
        table.put("RETURN", "ReturnCode");
        table.put("BOP", "BopCode");
        table.put("READ", "ReadCode");
        table.put("WRITE", "WriteCode");
        table.put("LABEL", "LabelCode");
        table.put("DUMP", "DumpCode");

    }

    public static void initDebug() {
        table.put("CALL", "DebugCallCode");
        table.put("POP", "DebugPopCode");
        table.put("RETURN", "DebugReturnCode");
        table.put("LIT", "DebugLitCode");
        table.put("LINE", "LineCode");
        table.put("FUNCTION", "FunctionCode");
        table.put("FORMAL", "FormalCode");
        table.put("HALT", "HaltCode");
        table.put("FALSEBRANCH", "FalseBranchCode");
        table.put("GOTO", "GoToCode");
        table.put("STORE", "StoreCode");
        table.put("LOAD", "LoadCode");
        table.put("ARGS", "ArgsCode");
        table.put("BOP", "BopCode");
        table.put("READ", "ReadCode");
        table.put("WRITE", "WriteCode");
        table.put("LABEL", "LabelCode");
        table.put("DUMP", "DumpCode");
    }

}