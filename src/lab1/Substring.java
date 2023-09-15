package lab1;

public class Substring {
    private int position;
    private int range;

    public Substring(int position, int range){
        this.position = position;
        this.range = range;
    }

    public int getPosition() {
        return position;
    }

    public int getRange() {
        return range;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Position: " + position + "\nRange: " + range;
    }
}
