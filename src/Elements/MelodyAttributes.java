package Elements;

public class MelodyAttributes {
    private boolean isUpward;
    private int[] lineInformation = new int[2];

    public MelodyAttributes(boolean isUpward,int[] lineInformation){
        this.isUpward = isUpward;
        this.lineInformation = lineInformation;
    }
    public boolean getisUpward(){
        return isUpward;
    }
    public int[] getLineInformation(){
        return lineInformation;
    }
}
