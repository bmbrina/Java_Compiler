# Java_Compiler
Mini compiler in Java

## 1 Designing the scannerThe following information defines the vocabulary of the language to analyze: 

- INTEGER. At least one digit followed by zero or more digits.
- DOUBLE. At least one digit followed by zero or more digits, then a dot, and finally a sequence of at least one digit.
- VARIABLE. The symbol ’$’ followed by any combination of letters and digits, as long as they start with a letter.PARAMETER SEP. The symbol ’,’. This character is used to separate arguments of functions.
- PARENTHESIS. Either ’(’ or ’)’.- FUNCTION. A sequence of letters that matches any of the following reserved words: SUM, SUB, MUL OR DIV. Please note that any sequence of letters that does not match a reserved word must be rejected by the scanner. For simplicity, assume that all the functions in the language receive two parameters.- EOI. The character ’;’. This character is used to end the input. It is the equivalent to the EOF in other languages.Given the definition of the words recognized by the language, design and draw a finite automata state that recognizes the lexical elements of this language.## Designing the parserDesign a free-context grammar that accepts the following inputs:
- 3;
- SUM(2, 3);
- MUL(10.55, $myVar );
- MUL($b2,SUB( SUM (2.1,$a1), MUL(12, 13.809))); • DIV ($x, $y);For testing purposes, the following are inputs that will produce an error:- 3 → Fails because of the missing ’;’ to finish the input.- SUM(, 2); → Fails because of the missing parameter.- (3, 5); → Fails because of the missing function name.- MUL(($a, 5); → Fails because of the double opening parenthesis.- SUB($a1 MUL(90, 8)); → Fails because of the missing comma between the parameters of the function SUB.## ImplementationImplement a program that integrates the scanner and parser for the language described 1. Your implementation must comply with the following:
- The string your program will analyze will be introduced through the keyboard.- Your scanner must generate the list of tokens of the input string. The program must show the tokens on screen.- The scanner must be implemented by using the transition matrix strategy described in class.- If the scanner finds an error, it must stop and show the proper message on screen.- Your parser must be implemented by using the recursive descent method described inclass.- If the parser finds an error, it must stop and show the proper message on screen.- If no error is found during the analysis, the program must show the corresponding message.