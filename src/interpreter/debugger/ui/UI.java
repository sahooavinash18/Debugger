/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.ui;

import interpreter.debugger.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author avinashsahoo
 */


public class UI {

    int line;
    private BufferedReader in;
    Scanner scanner;
    private ArrayList<Integer> lineBreak;
    private Vector<SourceLine> breakContainer;
    String userInput;
    static DebuggerVirtualMachine virtM;

    public UI(DebuggerVirtualMachine n) {
        line = 1;
        this.virtM = n;
        scanner = new Scanner(System.in);
        // breakContainer = new Vector<SourceLine>();
        breakContainer = virtM.breakpointContainer;
        lineBreak = new ArrayList();
    }

    public void help() {
        System.out.println("Type ? for help");

        System.out.println("> ");
        userInput = scanner.next();
        switch (userInput) {                //case that checks user input

            case "?":

                System.out.println("Command\t\tUse\t\t\tEx.");
                System.out.println("s\t\tsets breakpoints\ts 3");
                System.out.println("cl\t\tclears\t\t\tc 3");
                System.out.println("pc\t\tprints code\t\tpc");
                System.out.println("df\t\tdisplays funct\t\tdf");
                System.out.println("ct\t\tcontinues\t\tct");
                System.out.println("q\t\texits\t\t\tq");
                System.out.println("dv\t\tdisplay variable\tdv");
                System.out.println("o\t\tsteps out\t\to");
                System.out.println("i\t\tsteps in\t\ti");
                System.out.println("tr\t\ttraces\t\t\ttr");
                System.out.println("ps\t\tprints callstack\tp");
                System.out.println("sb\t\tshows breakpoints\tsb");
                System.out.println("so\t\tsteps over\t\tso");

                break;
            case "s":
                setBreak();
                break;
            case "pc":
                printCode();
                break;
            case "cl":
                clearBreak();
                break;
            case "o":
                stepOut();
                break;
            case "df":
                displayFunct();
                break;
            case "ct":
                cont();
                break;
            case "q":
                quit();
                break;
            case "dv":
                displayVar();
                break;
            case "i":
                stepIn();
                break;
            case "tr":
                trace();
                break;
            case "ps":
                printStack();
                break;
            case "sb":
                showBreak();
                break;
            case "so":
                stepOver();
                break;

            default:
                System.out.println("Incorrect syntax or unknown command");
                break;
        }
    }

    public void printCode() {//prints whole code
        int sourceSize = breakContainer.size();
        String source;
        for (int i = 0; i < sourceSize; i++) {
            SourceLine sLine = breakContainer.get(i);
            if (sLine.isBreakptSet) {
                source = "*";
            } else {
                source = "";

            }
            source = source.concat(" " + sLine.getSourceLine());
            System.out.println((i + 1) + ": " + source);
        }
    }

    public void trace() {
        virtM.trace();
    }

    public void stepOver() {
        virtM.stepOver();
    }

    public void printStack() {
        System.out.println(virtM.getStack());
    }

    public void setBreak() {
        ArrayList<Integer> point = new ArrayList();
        boolean breakStatus = true;
        userInput = scanner.next();
        StringTokenizer tokenizer = new StringTokenizer(userInput);
        while (tokenizer.hasMoreTokens()) {
            int buffer = Integer.parseInt(tokenizer.nextToken());
            point.add(buffer);
            lineBreak.add(buffer);

        }
        if (virtM.setBreak(point)) {
            System.out.println("Breakpoints set at lines: ");
            for (int lines : point) {
                System.out.print(point + " ");
            }
        } else {
            System.out.println("Error could not set breakpoints");
        }

    }

    public void showBreak() {

        if (breakContainer.isEmpty()) {
            System.out.println("No break points set");
            return;
        } else {
            System.out.println("Breakpoints set at: ");
        }
        System.out.println(lineBreak.size());
        String buffer;
        Iterator<Integer> iterator = lineBreak.iterator();
        while (iterator.hasNext()) {
            buffer = (Integer.toString(iterator.next()));
            System.out.print(buffer + " ");
        }
        System.out.println("");

    }

    public void clearBreak() {
        ArrayList<Integer> point = new ArrayList();
        lineBreak = new ArrayList();
        boolean breakStatus = true;
        userInput = scanner.next();
        StringTokenizer tokenizer = new StringTokenizer(userInput);
        while (tokenizer.hasMoreTokens()) {
            point.add(Integer.parseInt(tokenizer.nextToken()));
        }
        virtM.clearBreak(point);
        System.out.println("Breakpoints removed at lines: " + point);

    }

    public void cont() {//continue is a used word
        if (virtM.getTraceBool()) {
            virtM.executeProgram();
            virtM.displayTrace();
        } else {
            virtM.executeProgram();
        }
        printCode();
    }

    public void stepOut() {
        virtM.stepOut();
        cont();
    }

    public void stepIn() {
        virtM.stepIn();
        cont();
    }

    public void displayFunct() {
        int size = virtM.breakpointContainer.size();
        String source;
        ArrayList<Integer> st, end;
        st = virtM.getStart();
        end = virtM.getEnd();

        if (st != null) {
            line = virtM.getCurrent();
            for (int buffer = st.get(0); buffer < end.get(0); buffer++) {
                if (breakContainer.get(buffer).isBreakptSet) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
                System.out.print(String.format("%2d", (breakContainer.indexOf(breakContainer.get(buffer)) + 1)) + ": ");
                if (line == buffer + 1) {
                    System.out.println(breakContainer.get(buffer).getSourceLine() + "<----");
                } else {
                    System.out.println(breakContainer.get(buffer).getSourceLine());

                }
            }
        } else {
            line = virtM.getCurrent();
            int buffer = 0;
            System.out.println();
            for (SourceLine src : breakContainer) {
                if (src.breakptGet()) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }

                System.out.print(String.format("%2d", (breakContainer.indexOf(src) + 1)) + ": ");

                if (line == buffer + 1) {
                    System.out.println(src.getSourceLine() + "<------");
                } else {
                    System.out.println(src.getSourceLine());
                }

                buffer++;
            }
        }
    }

    public void quit() {
        System.out.println("EXECUTION STOPPED");
        virtM.setRun(false);
    }

    public void displayVar() {
        virtM.displayVar();
    }
}