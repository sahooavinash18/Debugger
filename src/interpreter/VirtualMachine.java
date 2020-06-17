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

import interpreter.bytecode.*;
import java.util.*;


public class VirtualMachine {

    protected RunTimeStack runStack;
    public int pc;
    protected Stack<Integer> returnAddrs;
    public boolean isRunning;
    protected Program program;
    public boolean dump;

    public VirtualMachine(Program program) {
        this.program = program;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
    }

    public void executeProgram() {
        pc = 0;
        // runStack = new RunTimeStack();
        //  returnAddrs = new Stack();
        isRunning = true;
        while (isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            if (dump) {
                runStack.dump();
            }
            // check that the operation is correct
            pc++;
        }
    }
//most methods below are runtimeSSTack methods

    public void setPC(int i) {//sets program counter
        pc = i;

    }

    public void dump(String label) {//dumps labels if dump is on
        if (dump) {
            System.out.println(label);
        }

    }

    public int getPC() {//returns program counter
        return pc;
    }

    public void dumpStateTrue() {//deterimines if dump is on 
        dump = true;
    }

    public void dumpStateFalse() {//deterimines if dump is  off
        dump = false;
    }

    public int peek() {
        return runStack.peek();
    }

    public int pop() {
        return runStack.pop();
    }

    public int Push(int i) {
        return runStack.push(i);
    }

    public void pushAddrs(int addrs) {
        returnAddrs.push(addrs);
    }

    public void newFrameAt(int i) {
        runStack.newFrameAt(i);
    }

    public void popFrame() {
        runStack.popFrame();
    }

    public int getStackSize() {
        return runStack.getRunStackSize();
    }

    public int store(int i) {
        return runStack.store(i);
    }

    public int load(int i) {
        return runStack.load(i);
    }

    public Integer push(Integer i) {
        return runStack.push(i);
    }

    public Integer popAddress() {
        return returnAddrs.pop();
    }

    public void setRun(boolean status) {
        isRunning = status;
    }

    public Boolean getRun() {
        return isRunning;
    }

    public void stopRun() {
        isRunning = false;
    }

}