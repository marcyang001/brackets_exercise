/**
 * Created by marcyang on 2017-08-22.
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
