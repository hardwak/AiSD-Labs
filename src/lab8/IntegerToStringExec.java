package lab8;

public class IntegerToStringExec implements IExecutor<Integer, String> {
    StringBuffer line = new StringBuffer();

    public void execute(Integer elem) {
        line.append(elem + "; ");
    }

    public String getResult() {
        line.delete(line.length() - 2, line.length());
        return line.toString();
    }

}

