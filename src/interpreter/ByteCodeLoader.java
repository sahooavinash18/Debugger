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
import java.io.*;
import java.util.*;
import interpreter.bytecode.*;


//The ByteCodeLoader class tokenizes the bytecodes from the file and put tokens into a Vector


public class ByteCodeLoader {

    private BufferedReader reader;//

    public ByteCodeLoader(String programFile) throws IOException {
        reader = new BufferedReader(new FileReader(programFile));//ByteCodeLoader reads the text/commands in the text file

    }

    public Program loadCodes() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        Program program = new Program();
        Vector<String> codeList = new Vector<String>();
        String newLine = reader.readLine();//makes the string newLine 

        while (newLine != null) {//while 
            StringTokenizer tokenizer = new StringTokenizer(newLine);//tokenizer is equal to the newLine's token
            codeList.clear();//clears codelist
            String hashKey = tokenizer.nextToken();
            String hashVal = CodeTable.get(hashKey);
            ByteCode bytecoder = (ByteCode) (Class.forName("interpreter.bytecode." + hashVal).newInstance());//creates instance of the bytecode
            System.out.println(hashVal);

            while (tokenizer.hasMoreTokens()) {

                codeList.add(tokenizer.nextToken());
            }
            bytecoder.init(codeList);
            program.push(bytecoder);

            newLine = reader.readLine();//reads the next line til emptied

        }

        program.resolveAddress();
        return program;
    }
}
