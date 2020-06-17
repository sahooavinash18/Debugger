/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

/**
 *
 * @author avinashsahoo
 */

public class SourceLine {

    private  String sourceLine;
    public Boolean isBreakptSet;

    public SourceLine(String i, Boolean isBreakptSet) {
        sourceLine = i;//contains source program linei
        this.isBreakptSet = isBreakptSet;//is a breakpoint set for this line?
    }

    public void breakptSet(boolean isBreakptSet) {
        this.isBreakptSet = isBreakptSet;
    }

    public boolean breakptGet() {
        return isBreakptSet;
    }

    public String getSourceLine() {
        return sourceLine;
    }
}