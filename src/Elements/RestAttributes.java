package Elements;

public class RestAttributes {
    private int[] hat = new int[2];
    private boolean curl;
    private int[] hook = new int[2];
    private boolean spot;
    public RestAttributes(int[] hat,boolean curl,int[] hook,boolean spot){
        this.hat = hat;
        this.curl = curl;
        this.hook = hook;
        this.spot = spot;
    }
    public int[] getHat(){
        return hat;
    }
    public boolean getCurl(){
        return curl;
    }
    public int[] getHook(){
        return hook;
    }
    public boolean getSpot(){
        return spot;
    }
}
