package components;

public class Component {
    private double length;
    private String imagePath;
    private double melody = 100.0; // => none sound
    Component(double length, String imagePath){
        this.length = length;
        this.imagePath = imagePath;
    }
    void setMelody(double melody){
        this.melody = melody;
    }
    double getMelody(){
        return melody;
    }
}
