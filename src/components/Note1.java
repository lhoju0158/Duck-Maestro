package components;

public class Note1 extends Note {
    // private double melody;
    Note1(){
        super(1.0,  "../../images/notes/note1.png");
    }
    Note1(double melody){
        this();
        super.setMelody(melody);
    }
}
