package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Processor of HTTP request.
 */
public class Processor extends Thread{
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public void process() throws IOException {
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        PrintWriter output = new PrintWriter(socket.getOutputStream());
        System.out.println(request.getRequestLine());


        if(request.getRequestLine().equals("GET /create/itemid HTTP/1.1")){
            // We are returning a simple web page now.
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, this is creation page</p></body>");
            output.println("</html>");
            output.flush();
        }
        else if(request.getRequestLine().equals("GET /delete/itemid HTTP/1.1")){
            // We are returning a simple web page now.
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, this is deletion page</p></body>");
            output.println("</html>");
            output.flush();
        }

        else if(request.getRequestLine().equals("GET /exec/params HTTP/1.1")){
            // We are returning a simple web page now.
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><h2>Hello, counting unique words in a text file: </h2>");
            File f = new File("C:\\Users\\user\\Desktop\\socket\\src\\com\\company\\file01.txt");
            ArrayList arr=new ArrayList();
            HashMap<String, Integer> listOfWords = new HashMap<String, Integer>();
            Scanner in = new Scanner(f);
            int i=0;
            while(in.hasNext())
            {
                String s=in.next();
                //System.out.println(s);
                arr.add(s);
            }
            Iterator itr=arr.iterator();
            while(itr.hasNext()) {
                i++;
                listOfWords.put((String) itr.next(), i);
                output.println(listOfWords);    //for Printing the words
            }

            Set<Object> uniqueValues = new HashSet<Object>(listOfWords.values());

            output.println("The number of unique words: " + uniqueValues.size());
//            output.println("<body><h2>Hello, Fibonacci sequence till 1.000.000 are: </h2>");
//            int firstNumber = 0;
//            int secondNumber = 1;
//            for(int i = 0; i < 10000000; i++){
//                // output.print(String.valueOf(firstNumber) + ", ");
//                int nextElement = firstNumber + secondNumber;
//                firstNumber = secondNumber;
//                secondNumber = nextElement;
//            }
            output.println("</body>");
            output.println("</html>");
            output.flush();
        }

        else {
            // We are returning a simple web page now.
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, world!</p></body>");
            output.println("</html>");
            output.flush();

        }
        socket.close();
    }
}
