package Elements;

public class BeatAttributes {
    private boolean isFilled;
    private int tailNum;
    private boolean spot;

    public BeatAttributes(boolean isFilled, int tailNum, boolean spot) {
        this.isFilled = isFilled;
        this.tailNum = tailNum;
        this.spot = spot;
    }

    public boolean getIsFilled() {
        return isFilled;
    }

    public int getTailNum() {
        return tailNum;
    }

    public boolean getSpot() {
        return spot;
    }
}
