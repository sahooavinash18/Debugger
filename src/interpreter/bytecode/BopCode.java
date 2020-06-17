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
import interpreter.*;

/**
 * This class performs a binary operation on top two arguments on the runStack and result is pushed back into runStack
 */

public class BopCode extends ByteCode {

    String operator;

    @Override

    public void init(Vector<String> args) {
        operator = (String) args.firstElement();
    }

    public void execute(VirtualMachine vm) {
        int valueOne = vm.pop();
        int valueTwo = vm.pop();
        int sum;
        switch (operator) {
            case "+":
                sum = valueTwo + valueOne;
                break;
            case "-":
                sum = valueTwo - valueOne;
                break;
            case "*":
                sum = valueTwo * valueOne;
                break;
            case "/":
                sum = valueTwo / valueOne;
                break;
            case "==":
                if (valueTwo == valueOne) {
                    sum = 1;
                } else {
                    sum = 0;

                }
                break;
            case "!=":
                if (valueTwo != valueOne) {
                    sum = 1;
                } else {
                    sum = 0;
                }
                break;
            case ">=":
                if (valueTwo >= valueOne) {
                    sum = 1;
                } else {
                    sum = 0;
                }
                break;
            case "<=":
                if (valueTwo <= valueOne) {
                    sum = 1;
                } else {
                    sum = 0;
                }
                break;
            case ">":
                if (valueTwo > valueOne) {
                    sum = 1;
                } else {
                    sum = 0;
                }
                break;
            case "<":
                if (valueTwo < valueOne) {
                    sum = 1;
                } else {
                    sum = 0;
                }
                break;
            case "|":
                if (valueTwo == 1 || valueOne == 1) {
                    sum = 1;
                } else {
                    sum = 0;
                }
                break;
            case "&":
                if (valueTwo == 1 && valueOne == 1) {
                    sum = 1;
                } else {
                    sum = 0;
                }
                break;
            default:
                sum = 0;
                break;
        }
        vm.push(sum);
        String tmp = "BOP ";
        tmp = tmp.concat(operator);
        vm.dump(tmp);
    }

    @Override
    public String getArgs() {
        return operator;
    }

}