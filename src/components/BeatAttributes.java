package components;
// melody => 1) 세로선 방향 (위, 아래) 2) 가로선 위치 및 방향, 갯수
// beat => 1) 타원 색 채울지 안 채울지 2) 날개 0개 1개 2개 3) 점 유무
public class BeatAttributes {
    private boolean isFilled; // 타원 색 채울지 안 채울지
    private int tailNum; // tail 갯수 (0개, 1개, 2개)
    private boolean spot; // 점 유뮤{
    public BeatAttributes(boolean isFilled,int tailNum,boolean spot){
        this.isFilled = isFilled;
        this.tailNum = tailNum;
        this.spot = spot;
    }
//    public BeatAttributes(){
//        this.isFilled = false;
//        this.tailNum = 0;
//        this.spot = false;
//    }
    public boolean getisFilled(){
        return isFilled;
    }
    public int getTailNum(){
        return tailNum;
    }
    public boolean getSpot(){
        return spot;
    }
}
