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

/**
 * Records and processes the stack of active frames
 * When we call a function we'll push a new frame on the stack
 * when we return from a function we'll pop the top frame
 */

public class RunTimeStack {

    private final Stack<Integer> framePointers;
    private final Vector<Integer> runStack;

    public RunTimeStack() {
        framePointers = new Stack<Integer>();
        runStack = new Vector<Integer>();
        framePointers.push(0);
    }

    public void dump() {//dump the RunTimeStack info for debugging
        Iterator pointer = framePointers.iterator();
        int counter = (Integer) pointer.next();
        if (pointer.hasNext()) {
            counter = (Integer) pointer.next();
        }
        System.out.print("[");//start of value printing
        if (!runStack.isEmpty()) {
            System.out.print(runStack.get(0));
        }
        for (int i = 1; i < runStack.size(); i++) {
            if (i == counter) {
                System.out.print("] [" + runStack.get(i));
                if (pointer.hasNext()) {
                    counter = (Integer) pointer.next();
                }
            } else {
                System.out.print("," + runStack.get(i));
            }
        }
        System.out.println("]");
    }

    public int peek() {//returns the top oitem on teh runtime stack
        return runStack.lastElement();
    }

    public int pop() {//pop the top item from the runtime stack
        int pop = runStack.lastElement();
        runStack.remove(runStack.size() - 1);
        return pop;
    }

    public int push(int i) {//push this item on the runtime stack
        runStack.add(i);
        return i;
    }

    public void newFrameAt(int offset) {//start new frame
        framePointers.push(runStack.size() - offset);
    }

    public int peekFrame() {//returns top fram
        return framePointers.peek();
    }

    public void popFrame() {//pops the top, saves it and returns the value
        int returnValue = runStack.lastElement();
        while (runStack.size() != framePointers.peek()) {
            runStack.remove(runStack.size() - 1);
        }
        framePointers.pop();
        runStack.add(returnValue);
    }

    public int store(int offset) {//used to store into variabels
        int storeValue = runStack.get(runStack.size() - 1);
        runStack.remove(runStack.size() - 1);
        runStack.set(framePointers.peek() + offset, storeValue);
        return storeValue;
    }

    public int load(int offset) {//loads variables onto the stack
        int loadValue = runStack.get(framePointers.peek() + offset);
        runStack.add(loadValue);
        return loadValue;
    }

    public Integer push(Integer i) {//loads literals on the stack
        runStack.add(i);
        return i;
    }

    public int getRunStackSize() {//returns stack size
        return runStack.size();
    }
    public int getValue(int n) {//returns the value of stack
        return runStack.get(n);
    }


}