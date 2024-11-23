package components;

public class Component {
    private double soundLength; // 몇초 => 전체 박자에 따라서 다르게 설정
    private double beat; // 박자 => 전체 박자가 1.0 이라 가정
    private double melody = 100.0; // => 음 높낮이, 기본값은 쉼표인 아무 멜로디 없는 걸 가정
    // 이건 나중에 실제 음 path로 바꾸기
    Component(double soundLength, double beat){
        this.soundLength = soundLength;
        this.beat = beat;
    }
    Component(double soundLength,double beat, double melody){
        // note의 경우 melody를 바꾸기
        this(soundLength,beat);
        this.melody = melody;
    }
//    void setMelody(double melody){
//        this.melody = melody;
//    }
//    double getMelody(){
//        return melody;
//    }
}
