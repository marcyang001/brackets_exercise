import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

//        Part0
//        RunSimpleTests();
//
//        Part 1
//        PartIOfTheProgram(args);
//
//        Part 2
//        partIIOfTheProgram(args);


    }

    public static void RunSimpleTests() {
        Bracket b = new Bracket();

        String[] goodTestCases = {"()", "[]", "{}", "({})", "({}{})","{[]}", "{[][][]}" ,"[()]", "[{}]", "[[]]", "[[[]]]", "[()()]", "{[][()()]}", "()()", "[][]", "[[()][{}]]"};

        String[] badTestCases = { "(()", "{}}", "({)}", "[d]", "", "([])", "({}())" ,"(())", "{{}}", "{()}","{[]{}[]}", "[([])]", ")", "())", "[}}", "}", "[[]{}()[][]]","[[][{}]{}[][]]", "[(){}]", "(){}"};


        System.out.println("------ Valid Test Cases ------");
        System.out.println();

        for (int i = 0; i < goodTestCases.length; i++) {
            if (!b.isValidBracketInput(goodTestCases[i])) {
                System.out.println("ERROR: Error in Testcase #" + (i+1));
            }
        }

        System.out.println("INFO: Passed Valid Test Cases");
        System.out.println();


        System.out.println("------ Invalid Test Cases ------");
        System.out.println();
        for (int i = 0; i < badTestCases.length; i++) {
            if (b.isValidBracketInput(badTestCases[i])) {
                System.out.println("ERROR: Error in Testcase #" + (i+1));
            }

        }

        System.out.println("INFO: Passed Invalid Test Cases");
    }

    public static void PartIOfTheProgram(String[] args) {



        String strLine;

        //Read File Line By Line
        try {
            int lineNum = 1;
            FileInputStream fstream = new FileInputStream(args[0]);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            Bracket b = new Bracket();
            while ((strLine = br.readLine()) != null)   {

                System.out.println(lineNum + ". " + b.isValidBracketInput(strLine));

                lineNum++;
            }

            //Close the input stream
            br.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void partIIOfTheProgram(String[] args) {
        // max number of thread = number of cores
        int max_thread = Runtime.getRuntime().availableProcessors();


        ExecutorService executor = Executors.newFixedThreadPool(max_thread); //creating a pool of 4 threads

        String strLine;

        //Read File Line By Line
        try {
            int lineNum = 0;
            FileInputStream fstream = new FileInputStream(args[0]);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            while ((strLine = br.readLine()) != null)   {

                Runnable worker = new WorkerThread(lineNum, strLine);
                executor.execute(worker);
                lineNum++;
            }

            //Close the input stream
            br.close();

            executor.shutdown();
            while (!executor.isTerminated()) {   }

            System.out.println("Finished all threads");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
