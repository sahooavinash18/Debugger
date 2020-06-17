/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import interpreter.ByteCodeLoader.*;
import java.util.*;
import interpreter.*;
import java.io.*;
import interpreter.bytecode.*;

/**
 *
 * @author avinashsahoo
 */


public class DebuggerVirtualMachine extends VirtualMachine {

    public Stack<FunctionEnvironmentRecord> environment;
    FunctionEnvironmentRecord funcRecord;
    ArrayList<String> source;
    static ArrayList lineBreak, breakPoint;
    private BufferedReader in;
    Scanner scanner;
    Stack<Integer> functSt, functEnd;
    String userInput;
    ArrayList<String> printTrace;
    public boolean stepout, stepin, stepover;
    boolean trace = false;
//    boolean pause;
    public Vector<SourceLine> breakpointContainer;

    public DebuggerVirtualMachine(Program program, String sourceFile) throws FileNotFoundException, IOException {
        super(program);
        printTrace = new ArrayList();

        stepout = false;
        stepin = false;
        functSt = new Stack();
        functEnd = new Stack();
        funcRecord = new FunctionEnvironmentRecord();
        environment = new Stack();
        // environment.push(funcRecord);
        scanner = new Scanner(System.in);
        //    pause = false;
        isRunning = true;
        source = new ArrayList();
        breakPoint = new ArrayList();
        breakpointContainer = new Vector();
        in = new BufferedReader(new FileReader(sourceFile));
        String lineHolder = in.readLine();
        while (lineHolder != null) {
            System.out.println(lineHolder);
            source.add(lineHolder);
            lineHolder = in.readLine();
            breakpointContainer.add(new SourceLine(source.get(source.size() - 1), false));
        }
    }

    public boolean setBreak(ArrayList<Integer> breakArray) {
        try {
            //    point = Integer.parseInt(tokenizer.nextToken());
            for (Integer compare : breakArray) {
                String buffer = breakpointContainer.get(compare - 1).getSourceLine();
                if (!(buffer.contains("{") || buffer.contains("int") || buffer.contains("boolean") || buffer.contains("while") || buffer.contains("return") || buffer.contains("=") || buffer.contains("if"))) {
                    return false;
                }
            }
            for (Integer compare : breakArray) {
                breakpointContainer.get(compare - 1).breakptSet(true);

            }

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public void clearBreak(ArrayList<Integer> breakArray) {
        try {
            //    point = Integer.parseInt(tokenizer.nextToken());

            for (Integer compare : breakArray) {
                breakpointContainer.get(compare - 1).breakptSet(false);

            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("This shouldn't have happened clearBreak failed");
        }
    }

    public int sourceSize() {
        return source.size();
    }

    public void runTrue() {
        isRunning = true;
    }

    public void stepOut() {
        stepout = true;

    }

    public void stepIn() {
        stepin = true;
    }

    @Override

    public void executeProgram() {
        int envsize = environment.size();

        while (isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            pc++;

            if (code instanceof LineCode) {
                LineCode linecode = (LineCode) code;

                if (linecode.getLine() > 0 && breakpointContainer.get(linecode.getLine() - 1).breakptGet()) {
                    //if there's a breakpoint stop
                    stepout = false;
                    stepin = false;
                    stepover = false;
                    code = program.getCode(pc);

                    if (code instanceof FunctionCode) {//if its a function code increase counter and execute code
                        code.execute(this);
                        pc++;
                        code = program.getCode(pc);

                        while (code instanceof FormalCode) {//while the code is a formal code, execute formal code
                            code.execute(this);
                            pc++;
                            code = program.getCode(pc);
                        }
                    }
                    break;
                }
            }

            if (stepout&&environment.size() == envsize - 1) {//if stepout check is true, set it to false and break
                    stepout = false;
                    break;
                
            }

            if (stepin&&environment.size() == envsize + 1) {//same for step in but it'll break if there's an instance of a line or new entry in stack as said in reader

                    if (code instanceof FunctionCode) {
                        ByteCode nextcode = program.getCode(pc);
                        if (nextcode instanceof LineCode
                                || nextcode instanceof ReadCode
                                || nextcode instanceof WriteCode) {
                            stepin = false;
                            break;
                        }
                    }
                 else if (code instanceof LineCode && ((LineCode) code).getLine() > 0) {
                    stepin = false;
                    break;
                }
            }
            if (stepover) {//checks stepover is true, breaks if the current entry is popped or line changes while the stack size stays the same
                if (envsize == environment.size()) {
                    if ((code instanceof LineCode) && ((LineCode) code).getLine() > 0) {
                        stepover = false;
                        break;
                    }
                }
            }
        }
    }

    public void debugPop(int pop) {//pops top of functionRecord
        funcRecord.pop(pop);
    }

    public ByteCode getCode(int pc) {
        return program.getCode(pc);
    }

    public void end() {//pops top of functionenvironmentrecord stack
        FunctionEnvironmentRecord n = environment.pop();
        //n.endScope();
    }

    public void stackPeek() {//checks the top of the enviromental stack
        FunctionEnvironmentRecord n = environment.peek();
    }

    public void setFunct(String s, int x, int y) {//sets the name and start and end of the function
        funcRecord.FunctionNSE(s, x, y);
    }

    public int getFunctStackSize() {//returns function stack size
        return environment.size();
    }

    public ArrayList<Integer> getEnd() {//gets end of function
        if (functEnd.isEmpty()) {
            return null;
        }
        ArrayList<Integer> end = new ArrayList();
        end.add(functEnd.peek());
        return end;
    }


    public void funcRecEnter(String n, Integer i) {//enters new function record
        funcRecord.enter(n, i);
    }

    /* public void setPause(boolean status) {
     pause = status;
     }*/
    public void currentLine(int n) {
        funcRecord.line(n);
    }

    public ArrayList<Integer> getStart() {
        if (functSt.isEmpty()) {
            return null;
        }
        ArrayList<Integer> start = new ArrayList();
        start.add(functSt.peek());
        return start;
    }

    public String getStack() {
        String callst = "";
        ListIterator iterator = environment.listIterator(environment.size());
        while (iterator.hasPrevious()) {
            FunctionEnvironmentRecord buffer = (FunctionEnvironmentRecord) iterator.previous();
            callst += buffer.getName() + ":" + buffer.getCurrent() + "\n";
        }
        return callst;
    }

    public String getName() {
        return funcRecord.getName();
    }

    public void trace() {
        if (trace) {
            trace = false;
            System.out.println("Trace turned off");
        } else {
            trace = true;
            System.out.println("Trace turned on");
        }
    }

    public boolean getTraceBool() {//returns trace boolean
        return trace;
    }


    public void setTrace(String n) {//adds to the trace arraylist
        printTrace.add(n);
    }

    public void displayTrace() {//prints trace until counter reaches trace's end

        for (String counter:printTrace) {
            System.out.println(counter);
        }
    }

    public void pushfuncRecordIntoStack() {
        environment.push(funcRecord);
        funcRecord = new FunctionEnvironmentRecord();
        funcRecord.beginScope();
    }

    public Integer getCurrent() {//gets current int of the function
        return funcRecord.getCurrent();
    }

    public void displayVar() {//display variable
        String displayedvar;
        Integer val, offset;
        Set<String> table = funcRecord.getKey();
        Iterator iterator = table.iterator();
        try {

            while (iterator.hasNext()) {
                displayedvar = (String) (iterator.next());
                offset = funcRecord.getOffset(displayedvar);
                val = runStack.getValue(offset);
                System.out.println(displayedvar + "=" + val);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("no variable to input yet");
        }
    }

    public void stepOver() {
        stepover = true;
    }
}