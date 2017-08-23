
import java.util.*;


public class Bracket {


    private HashSet openBraces;
    private HashSet closeBraces;

    public Bracket() {

        openBraces = new HashSet();

        closeBraces = new HashSet();

        openBraces.add("(");
        openBraces.add("{");
        openBraces.add("[");

        closeBraces.add(")");
        closeBraces.add("}");
        closeBraces.add("]");

    }

    private void insertSquareStack() {

    }

    public boolean isValidBracketInput(String inputBraces) {

        String currentBrace;    // variable that keeps track of each character from the input as we iterate
        String openBrace; // variable that keeps track of the open braces inside the stack
        String startingBrace; // variable that keeps track of the starting point, either (, [ or {
        Stack stack = new Stack(); // keep track of the number of open brackets == number of closing brackets
        Stack squareStack = new Stack(); // keep track of which bracket is inserted inside the square bracket

        int index = 0;

        // An empty string is not valid a expression
        if (inputBraces.equals("") || inputBraces.length() < 2) {
            return false;
        }

        currentBrace = Character.toString(inputBraces.charAt(index));

        //Each type of bracket needs to be first opened then closed
        if (openBraces.contains(currentBrace)) {

            if (currentBrace.equals("[") && (index + 1) < inputBraces.length()) {
                String nextBrace = Character.toString(inputBraces.charAt(index+1));

                if (!nextBrace.equals("]")) {
                    squareStack.push(nextBrace);
                }

            }

            stack.push(currentBrace);
            startingBrace = currentBrace;
            index++;

            while (index < inputBraces.length()) {

                currentBrace = Character.toString(inputBraces.charAt(index));

                if (openBraces.contains(currentBrace)) {

                    if (!stack.empty()) {
                        openBrace = (String) stack.peek();
                        // Inside parenthesis () only braces {} are allowed
                        if (openBrace.equals("(") && !currentBrace.equals("{")) {
                            return false;
                        }
                        // Inside braces {} only square brackets [] are allowed
                        else if (openBrace.equals("{") && !currentBrace.equals("[")) {
                            return false;
                        }
                        else if (openBrace.equals("[") && !squareStack.empty()) {

                            if (!(currentBrace.equals(squareStack.peek()))) {
                                return false;
                            }

                        }
                        else if (openBrace.equals("[") && squareStack.empty()) {
                            squareStack.push("[");
                            if (!(currentBrace.equals(squareStack.peek()))) {
                                return false;
                            }

                        }

                    }
                    // (){}
                    else if (stack.empty() && !startingBrace.equals(currentBrace)){
                        return false;
                    }


                    if (currentBrace.equals("[") && (index + 1) < inputBraces.length()) {

                        String nextBrace = Character.toString(inputBraces.charAt(index+1));

                        if (!nextBrace.equals("]")) {
                            squareStack.push(nextBrace);
                        }

                    }

                    stack.push(currentBrace);
                }

                else if (closeBraces.contains(currentBrace)) {

                    if (!stack.empty()){
                        openBrace = (String) stack.pop();
                        // You can only close the last bracket that was opened
                        // ()
                        if (openBrace.equals("(") && !currentBrace.equals(")")) {
                            return false;
                        }
                        // {}
                        else if (openBrace.equals("{") && !currentBrace.equals("}")) {
                            return false;
                        }

                        // []
                        else if (openBrace.equals("[") && !currentBrace.equals("]")) {
                            return false;
                        }
                        else if (openBrace.equals("[") && currentBrace.equals("]") && !squareStack.empty()) {
                            // pop the square stack
                            squareStack.pop();
                        }
                    }
                    // Each type of bracket needs to be  rst opened then closed
                    else {
                        return false;
                    }



                }
                // Any other characters than (){}[] will invalidate the string
                else {
                    return false;
                }

                index++;
            }


            if (!stack.empty()) {
                return false;
            }

        }
        else {
            return false;
        }



        return true;
    }

}
