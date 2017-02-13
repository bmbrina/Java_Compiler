import java.io.*;
import Compiler.Parser;
import Compiler.Scanner;
import Compiler.Token;
import java.util.List;

public class Run {

    public static void main(String[] args) {

        String string;
        Scanner scanner;
        Parser parser;
        scanner = new Scanner();
        parser = new Parser();
        List<Token> tokens;
        
        System.out.print("Type the expression: ");
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        try {
          string = userInput.readLine();
          System.out.println("Expression: " + string);
          if (!scanner.scan(string)) {
              System.exit(1);
          }        
          tokens = scanner.getTokens();
          for (Token token : tokens) {
              System.out.println(token);
          }
          parser.parse(tokens);
        }
        catch (IOException e) {System.err.println(e);}
    }
    
}
