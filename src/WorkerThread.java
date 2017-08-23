/**
 * Created by marcyang on 2017-08-22.
 *
 * This is a thread that launches the Bracket object and does isValidBracketInput computation
 *
 */


public class WorkerThread implements Runnable {


    private String input;
    private int nthInput;

    public WorkerThread(int nthInput, String inputBrace) {

        this.input = inputBrace;
        this.nthInput = nthInput;
    }

    @Override
    public void run() {
        Bracket b = new Bracket();
        System.out.println (nthInput + ". " + b.isValidBracketInput(this.input));

    }
}
