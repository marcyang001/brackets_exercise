/**
 * Created by marcyang on 2017-08-22.
 *
 * This is the Bracket object. It tells whether a given string input is valid or invalid.
 *
 */

import java.util.*;


public class Bracket {


    private HashSet<String> openBraces;
    private HashSet<String> closeBraces;

    public Bracket() {

        openBraces = new HashSet<>();

        closeBraces = new HashSet<>();

        openBraces.add("(");
        openBraces.add("{");
        openBraces.add("[");

        closeBraces.add(")");
        closeBraces.add("}");
        closeBraces.add("]");

    }

    /*
    * Private helper function that inserts the type of the symbols into squareStack
    *
    * in order to keep track of what type of symbol is inserted inside a square bracket.
    *
    * */
    private void insertSquareStack(String currentBrace, String inputBraces, int index, Stack<String> squareStack) {

        if (currentBrace.equals("[") && (index + 1) < inputBraces.length()) {
            String nextBrace = Character.toString(inputBraces.charAt(index+1));

            if (!nextBrace.equals("]")) {
                squareStack.push(nextBrace);
            }

        }
    }

    public boolean isValidBracketInput(String inputBraces) {

        String currentBrace;    // variable that keeps track of each character from the input as we iterate
        String topOpenBrace; // variable that keeps track of the open braces on top of the stack
        String startingChar; // variable that keeps track of the starting point, either (, [ or {
        Stack<String> stack = new Stack<>(); // keep track of the number of open brackets == number of closing brackets
        Stack<String> squareStack = new Stack<>(); // keep track of which bracket is inserted inside the square bracket

        int index = 0;

        // An empty string is not valid a expression
        if (inputBraces.equals("") || inputBraces.length() < 2) {
            return false;
        }

        currentBrace = Character.toString(inputBraces.charAt(index));

        // Each type of bracket needs to be first opened then closed
        if (openBraces.contains(currentBrace)) {


            insertSquareStack(currentBrace, inputBraces, index, squareStack);

            // insert the first open brace
            stack.push(currentBrace);
            startingChar = currentBrace;
            index++;

            while (index < inputBraces.length()) {

                currentBrace = Character.toString(inputBraces.charAt(index));

                if (openBraces.contains(currentBrace)) {

                    if (!stack.empty()) {
                        topOpenBrace = stack.peek();
                        // Inside parenthesis () only braces {} are allowed
                        if (topOpenBrace.equals("(") && !currentBrace.equals("{")) {
                            return false;
                        }
                        // Inside braces {} only square brackets [] are allowed
                        else if (topOpenBrace.equals("{") && !currentBrace.equals("[")) {
                            return false;
                        }
                        else if (topOpenBrace.equals("[") && !squareStack.empty()) {
                            // if the most recent open bracket is [, and the squareStack is not empty
                            // check if the currentBrace is equal to the one on top of square Stack
                            // case handler for [(){}] --> invalid => return false
                            if (!(currentBrace.equals(squareStack.peek()))) {
                                return false;
                            }

                        }
                        else if (topOpenBrace.equals("[") && squareStack.empty()) {
                            // insert an open square bracket in to squareStack because,
                            // the program will remove it when encountering its corresponding closing square bracket
                            // case handler for [[][]]
                            squareStack.push("[");
                            if (!(currentBrace.equals(squareStack.peek()))) {
                                return false;
                            }

                        }

                    }
                    // (){}
                    else if (stack.empty() && !startingChar.equals(currentBrace)){
                        return false;
                    }

                    insertSquareStack(currentBrace, inputBraces, index, squareStack);

                    stack.push(currentBrace);
                }

                else if (closeBraces.contains(currentBrace)) {

                    if (!stack.empty()){
                        topOpenBrace = stack.pop();
                        // You can only close the last bracket that was opened
                        // valid: ()
                        // invalid: (]
                        if (topOpenBrace.equals("(") && !currentBrace.equals(")")) {
                            return false;
                        }
                        // valid: {}
                        // invalid: {]
                        else if (topOpenBrace.equals("{") && !currentBrace.equals("}")) {
                            return false;
                        }

                        // valid: []
                        // invalid: [}
                        else if (topOpenBrace.equals("[") && !currentBrace.equals("]")) {
                            return false;
                        }
                        // for every close square bracket, pop an element from the squareStack
                        else if (topOpenBrace.equals("[") && currentBrace.equals("]") && !squareStack.empty()) {
                            squareStack.pop();
                        }
                    }
                    // Each type of bracket needs to be opened first then closed
                    // invalid: {}]
                    else {
                        return false;
                    }

                }
                // Any characters other than (){}[] will invalidate the string
                else {
                    return false;
                }

                index++;
            }

            // handle : {, ({
            if (!stack.empty()) {
                return false;
            }

        }
        // return false if the input does not start with an open bracket
        else {
            return false;
        }



        return true;
    }

}
