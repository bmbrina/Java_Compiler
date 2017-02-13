package Compiler;

import Compiler.Token.Type;
import java.util.List;

public class Parser {

    public Parser() {
        
    }
    
    public void parse(List<Token> tokens) {        
        parseF(tokens);        
        System.out.println("The input is a well formed expresssion.");           
    }
    
    private void parseF(List<Token> tokens) {
        if (tokens.size() == 1) {
          match(tokens, new Token(Type.EOI, ";"));
        }

        switch (tokens.get(0).getType()) {
            case INTEGER: 
                match(tokens, new Token(Type.INTEGER, ""));
                break;
            case DOUBLE:
                match(tokens, new Token(Type.DOUBLE, ""));
                break;
            case VARIABLE:
                match(tokens, new Token(Type.VARIABLE, ""));
                break;
            case FUNCTION:
                match(tokens, new Token(Type.FUNCTION, ""));
                match(tokens, new Token(Type.PARENTHESIS, "("));
                parseF(tokens);
                match(tokens, new Token(Type.PARAMETER_SEP, ","));
                parseF(tokens);
                match(tokens, new Token(Type.PARENTHESIS, ")"));
                break;
            case EOI:
                match(tokens, new Token(Type.EOI, ";"));
                break;
            default:
                System.out.println("ERROR: Unexpected: \'" + tokens.get(0) + "\'");
                System.out.println("The system will halt.");
                System.exit(1);
        }
    }        
    
    private boolean match(List<Token> tokens, Token expectedToken) {        
        if (!tokens.isEmpty() && tokens.get(0).equalsToken(expectedToken)) {
            tokens.remove(0);
            return true;
        } else {
            if (tokens.isEmpty()) {
                System.out.println("ERROR: Expecting \'" + expectedToken.getValue() + "\'; found nothing.");
            } else {
                System.out.println("ERROR: Expecting \'" + expectedToken.getValue() + "\'; found \'" + tokens.get(0).getValue() + "\'");
            }
            System.out.println("The system will halt.");
            System.exit(1);
        }
        return false;
    }
    
}
