package components;

public class Note extends Component{
    private int x;
    private int y;
    Note(double length,String imagePath){
       super(length,imagePath);
    }
    void setPoint(int x,int y){
        this.x = x;
        this.y = y;
    }
}
