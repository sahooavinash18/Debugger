/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import java.util.*;

/**
 *
 * @author avinashsahoo
 */
// This class stores symbol table, function name, starting line number, ending line number and current line number

class Binder {

    private Object value;
    private String prevtop;   // prior symbol in same scope
    private Binder tail;      // prior binder for same symbol
    // restore this when closing scope

    Binder(Object v, String p, Binder t) {
        value = v;
        prevtop = p;
        tail = t;
    }

    Object getValue() {
        return value;
    }

    String getPrevtop() {
        return prevtop;
    }

    Binder getTail() {
        return tail;
    }
}

class Table {

    private java.util.HashMap<String, Binder> symbols = new java.util.HashMap<String, Binder>();
    private String top;    // reference to last symbol added to
    // current scope; this essentially is the
    // start of a linked list of symbols in scope
    private Binder marks;  // scope mark; essentially we have a stack of
    // marks - push for new scope; pop when closing
    // scope

    /*
     public static void main(String args[]) {
     Symbol s = Symbol.symbol("a", 1),
     s1 = Symbol.symbol("b", 2),
     s2 = Symbol.symbol("c", 3);
     Table t = new Table();
     t.beginScope();
     t.put(s,"top-level a");
     t.put(s1,"top-level b");
     t.beginScope();
     t.put(s2,"second-level c");
     t.put(s,"second-level a");
     t.endScope();
     t.put(s2,"top-level c");
     t.endScope();
     }
     */
    public Table() {
    }

    /**
     * Gets the object associated with the specified symbol in the Table.
     */
    public Object get(String key) {
        Binder e = symbols.get(key);
        return e.getValue();
    }

    /**
     * Puts the specified value into the Table, bound to the specified
     * Symbol.<br>
     * Maintain the list of symbols in the current scope (top);<br>
     * Add to list of symbols in prior scope with the same string identifier
     */
    public void put(String key, Object value) {
        symbols.put(key, new Binder(value, top, symbols.get(key)));
        top = key;
    }

    /**
     * Remembers the current state of the Table; push new mark on mark stack
     */
    public void beginScope() {
        marks = new Binder(null, top, marks);
        top = null;
    }

    public void pop(int i) {
        for (int counter = 0; counter < i; counter++) {
            Binder e = symbols.get(top);
            if (e.getTail() != null) {
                symbols.put(top, e.getTail());
            } else {
                symbols.remove(top);
            }
            top = e.getPrevtop();
        }
    }

    /**
     * Restores the table to what it was at the most recent beginScope that has
     * not already been ended.
     */
    public void endScope() {
        while (top != null) {
            Binder e = symbols.get(top);
            if (e.getTail() != null) {
                symbols.put(top, e.getTail());
            } else {
                symbols.remove(top);
            }
            top = e.getPrevtop();
        }
        top = marks.getPrevtop();
        marks = marks.getTail();
    }

    /**
     * @return a set of the Table's symbols.
     */
    public java.util.Set<String> keys() {
        return symbols.keySet();
    }
}

public class FunctionEnvironmentRecord {

    String prevtop = "";   // prior symbol in same scope
    Binder tail;      // prior binder for same symbol
    String name;
    Integer startLine, endLine;
    Integer currentLine = 0;
    Table table;

    public FunctionEnvironmentRecord() {
        table = new Table();
        name = new String();
        startLine = endLine = currentLine = 0;
    }

    void dump() {
        String start = "(";
        java.util.Set<String> setter = table.keys();
        Iterator iterator = setter.iterator();
        String command;
        String brackStart = "<";
        Integer value;
        while (iterator.hasNext()) {
            command = (String) iterator.next();
            value = (Integer) table.get(command);
            if (iterator.hasNext()) {
                brackStart = brackStart.concat(command + "/" + value.toString() + ",");
            } else {
                brackStart = brackStart.concat(command + "/" + value.toString());
            }
        }
        start = start.concat(brackStart + ">");
        if (name.isEmpty()) {
            command = "-,-,-,-";
            start = start.concat(command);
        } else if (currentLine == 0) {
            start = start.concat(name + "," + startLine.toString() + "," + endLine.toString() + ",-");
        } else {
            start = start.concat(name + "," + startLine.toString() + "," + endLine.toString() + "," + currentLine);
            start = start.concat(")");
            System.out.println(start);
        }

    }

    public static void main(String args[]) {
        FunctionEnvironmentRecord fctEnvRecord = new FunctionEnvironmentRecord();
        fctEnvRecord.beginScope();
        fctEnvRecord.dump();
        fctEnvRecord.FunctionNSE("g", 1, 20);
        fctEnvRecord.dump();
        fctEnvRecord.line(5);
        fctEnvRecord.dump();
        fctEnvRecord.enter("a", 4);
        fctEnvRecord.dump();
        fctEnvRecord.enter("b", 2);
        fctEnvRecord.dump();
        fctEnvRecord.enter("c", 7);
        fctEnvRecord.dump();
        fctEnvRecord.enter("a", 1);
        fctEnvRecord.dump();
        fctEnvRecord.pop(2);
        fctEnvRecord.dump();
        fctEnvRecord.pop(1);
        fctEnvRecord.dump();
    }

    void beginScope() {
        table.beginScope();
    }

    void endScope() {
        table.endScope();

    }

    void pop(int i) {
        table.pop(i);
    }

    public void line(int n) {
        currentLine = n;
    }

    public void FunctionNSE(String n, Integer s, Integer e) {
        name = n;
        startLine = s;
        endLine = e;
    }

    public Set<String> getKey() {
        return table.keys();
    }
    public String getName(){
        return name;
    }

    public Integer getStart() {
        return startLine;
    }

    public Integer getEnd() {
        return endLine;
    }

    public Integer getCurrent() {
        return currentLine;
    }

    public Integer getOffset(String n) {
        return (Integer) (table.get(n));
    }

    public void enter(String var, Integer value) {
        table.put(var, value);
    }
}