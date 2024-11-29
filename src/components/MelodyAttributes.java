package components;
// melody => 1) 세로선 방향 (위, 아래) 2) 가로선 위치 및 방향, 갯수
// beat => 1) 타원 색 채울지 안 채울지 2) 날개 0개 1개 2개 3) 점 유무
public class MelodyAttributes {
    private boolean isUpward; // true: 위 방향, false: 아래 방향
    // private boolean line; // true: 추가 가로선 존재, false: 추가 가로선 존재하지 않음
    private int[] lineInformation = new int[2]; // 0: line number,  1: line position;

    public MelodyAttributes(boolean isUpward,int[] lineInformation){
        this.isUpward = isUpward;
        this.lineInformation = lineInformation;
    }
//    public MelodyAttributes(){
//        this.isUpward = false;
//        this.lineInformation = new int []{0,0};
//    }
    public boolean getisUpward(){
        return isUpward;
    }
    public int[] getLineInformation(){
        return lineInformation;
    }
}
