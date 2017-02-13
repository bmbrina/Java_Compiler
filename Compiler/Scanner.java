package Compiler;

import Compiler.Token.Type;
import java.util.ArrayList;
import java.util.List;

public class Scanner {

    private final int ERROR = 9999;
    private final int[][] transitionMatrix;
    private List<Token> tokens;

    public Scanner() {

        transitionMatrix = new int[][]{
            {0, 1, ERROR, 4, 6, 7, ERROR, 8, 109, 10},
            {101, 1, 2, ERROR, 101, ERROR, ERROR, ERROR, 101, 101},
            {ERROR, 3, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR},
            {ERROR, 3, ERROR, ERROR, 103, ERROR, ERROR, ERROR, 103, 103},
            {ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, 5, ERROR, ERROR, ERROR},
            {105, 5, ERROR, ERROR, 105, ERROR, 5, ERROR, 105, 105},
            {106, 106, ERROR, 106, ERROR, ERROR, ERROR, 106, ERROR, ERROR},
            {107, 107, ERROR, 107, ERROR, ERROR, ERROR, 107, ERROR, ERROR},
            {108, ERROR, ERROR, ERROR, ERROR, 108, ERROR, ERROR, ERROR, ERROR},
            {ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR, ERROR},
            {ERROR, ERROR, ERROR, ERROR, 110, ERROR, ERROR, ERROR, 110, 110},
        };
    }

    public boolean scan(String string) {
        char c = ' ';
        String s = " ";
        boolean is_function = false;
        int state, index;
        StringBuilder value;  

        state = 0;
        index = 0;
        tokens = new ArrayList();
        string = string + " ";
        while (index < string.length()) {
            value = new StringBuilder();
            do {
                int filtered_input;
                is_function = false;
                if (isFunction(string, index) && (state != 4 || state != 5)) {
                  s = string.substring(index, index + 3);
                  index += 3;
                  filtered_input = 7;
                  is_function = true;
                } else {
                  c = string.charAt(index);
                  index++;
                  filtered_input = filter(c);
                }
                state = transitionMatrix[state][filtered_input];
                if (state > 0 && state < 100) {
                  if(is_function){
                    value.append(s);
                  } else {
                    value.append(c);
                  }  
                }
            } while (index < string.length() && state < 100);
            /*
             * The token is recognized by the system.
             */
            switch (state) {
                case 0:
                    return true;
                case 9999:
                    value.append(c);
                    System.out.println("LEXICAL ERROR: the string \'" + value.toString() + "\' is not a valid element in the language.");
                    return false;
                case 101:
                    tokens.add(new Token(Type.INTEGER, value.toString()));
                    break;
                case 103:
                    tokens.add(new Token(Type.DOUBLE, value.toString()));
                    break;
                case 105:
                    tokens.add(new Token(Type.VARIABLE, value.toString()));
                    break;
                case 106:
                    tokens.add(new Token(Type.PARAMETER_SEP, value.toString()));
                    break;
                case 107:
                    tokens.add(new Token(Type.PARENTHESIS, value.toString()));
                    break;
                case 108:
                    tokens.add(new Token(Type.FUNCTION, value.toString()));
                    break;
                case 109:
                    tokens.add(new Token(Type.EOI, ";"));
                    if(index + 1 >= string.length()) {
                      return true;
                    } else {
                      System.out.println("LEXICAL ERROR: there shouldn't be elements after end of input.");
                      return false;
                    }
                case 110:
                    tokens.add(new Token(Type.PARENTHESIS, value.toString()));
                    break;
            }

            if (is_function && state != 108){
              index-=3;
            } else {
              index--;
            }

            state = 0;
        }
        return true;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    private boolean isFunction(String s, int index) {
      if(index + 3 > s.length()) {
        return false;
      }

      String temp = s.substring(index, index + 3);
      switch(temp) {
        case "SUM":
        case "SUB":
        case "MUL":
        case "DIV":
          return true;
        default:
          return false;
      }
    }
    
    private int filter(char c) {
        switch (c) {
            case ' ':
            case '\t':
                return 0;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return 1;
            case '.':
                return 2;
            case '$':
                return 3;
            case ',':
                return 4;
            case '(':
                return 5;
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
                return 6;
            case ';':
                return 8;
            case ')':
                return 9;
            default:
                return 10;
        }
    }

}
