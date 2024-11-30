package components;

import pages.Scorepage;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.HashMap;

import static Grapics.ShapeUtils.*;
import static pages.Scorepage.*;


public class Element {
    private double soundLength; // 몇초 => 전체 박자에 따라서 다르게 설정
    private double beat; // 박자 => 전체 박자가 1.0 이라 가정
    private double melodyForPlay; // => 음 높낮이, 기본값은 쉼표인 아무 멜로디 없는 걸 가정
    private Point StartPosition;
    private Point LastPosition;
    private Point basicPosition;
    private ShapeGroup shapeGroup;
    private double melodyForDraw;
    private double modifyValue;
    private String soundPath;


    public Element(){
        this.beat=1.0;
        this.soundLength =0.0;
        this.shapeGroup=null;
        this.StartPosition = new Point();
        this.LastPosition = new Point();
        this.basicPosition = new Point();
        this.soundPath = "";
        this.melodyForPlay =16;
    }
    public Element(double beat,double melodyForPlay,Point startPosition,double[] melodyForDraw){
        this.beat = beat;
        this.melodyForPlay = melodyForPlay;
        this.melodyForDraw = melodyForDraw[0];
        this.modifyValue = melodyForDraw[1];
        // this.shapeGroup = shapeGroup;
        // soundLength 계산하기
        double tempo = Scorepage.tempo;
        this.soundLength = ((60)/tempo) *4 *beat;
        this.StartPosition = startPosition;
        setPositions();
        System.out.println("After set position: its startElement.getStartPosition().x = "+getStartPosition().x+ " its startElement.getStartPosition().y = "+getStartPosition().y);
        System.out.println("After set position: its lastElement.getLastPosition().x = "+getLastPosition().x+ " its lastElement.getLastPosition().y = "+getLastPosition().y);
        System.out.println("After set position: its basicElement.getBasicPosition().x = "+getBasicPosition().x+ " its basicElement.getBasicPosition().y = "+getBasicPosition().y);
        // System.out.println(melodyForPlay);
        if(melodyForPlay==16.0){
            setShapeGroupForRest();
        }
        else{
            // 멜로디면
            setShapeGroupForMelody();
        }
        // set Tempo
        setSoundForPlay();
    }
    public ShapeGroup getShapeGroup(){
        return shapeGroup;
    }
    public double getSoundLength(){
        return soundLength;
    }
    public double getBeat(){
        return beat;
    }
    public double getMelodyForPlay(){
        return melodyForPlay;
    }
    public Point getStartPosition(){
        return StartPosition;
    }
    public Point getLastPosition(){
        return LastPosition;
    }
    public Point getBasicPosition() {
        return basicPosition;
    }
    public void setPositions(){
        this.LastPosition = new Point((int)(this.StartPosition.x +(10+11*(16*beat-1))),this.StartPosition.y);
        if(this.melodyForPlay==16.0){
            this.basicPosition = new Point((int)((this.StartPosition.x+this.LastPosition.x)*0.5)-7,this.StartPosition.y);

        }
        else{
            if(this.beat<=0.25){
                this.basicPosition = new Point((int)((this.LastPosition.x- this.StartPosition.x)*0.5+(this.StartPosition.x)),(int) (this.StartPosition.y - 4 * insertMelodyForDraw[0]));
            }
            else{
                this.basicPosition = new Point((int)((this.LastPosition.x- this.StartPosition.x)*0.25+(this.StartPosition.x)),(int) (this.StartPosition.y - 4 * insertMelodyForDraw[0]));

            }
        }
    }
    public String getSoundPath(){
        return soundPath;
    }

    public void setSoundForPlay(){
        this.soundPath= originalMelodyHashmap.get(melodyForPlay);
    }

    public void setShapeGroupForMelody(){
        MelodyAttributes melodyAttributes = melodySettings.get(melodyForDraw);
        BeatAttributes beatAttributes = beatSettings.get(beat);

        boolean isUpward = melodyAttributes.getisUpward();
        int[] lineInformation = melodyAttributes.getLineInformation();
        boolean isFilled = beatAttributes.getisFilled();
        int tailNum = beatAttributes.getTailNum();
        boolean spot = beatAttributes.getSpot();

        if(this.modifyValue==0.5||modifyValue==-0.5){
            Point temp = new Point(this.basicPosition);
            this.basicPosition = new Point(temp.x+5,temp.y);
        }
        Ellipse2D ellipse = new Ellipse2D.Double(this.basicPosition.x - 6.5, this.basicPosition.y - ((Scorepage.smallGap / 2) - 0.5), 13, Scorepage.smallGap - 1);
        Shape rotatedEllipse = applyRotation(ellipse, this.basicPosition);

        // 세로선 생성
        Line2D verticalLine = createVerticalLine(this.basicPosition, isUpward,this.beat);

        // 가로선 생성
        Line2D[] horizontalLines = createHorizontalLines(this.basicPosition, lineInformation);

        // 꼬리 생성
        GeneralPath tail = createTail(this.basicPosition, tailNum,isUpward);

        GeneralPath attribute = createAttribute(this.basicPosition,modifyValue);

        this.shapeGroup = new ShapeGroup(rotatedEllipse, horizontalLines, verticalLine, tail, isFilled, spot,attribute, this.basicPosition);
    }
    public void setShapeGroupForRest(){
        RestAttributes restAttributes = restSetiing.get(beat);
        int[] hatInfomation = restAttributes.getHat();
        boolean curlInformation = restAttributes.getCurl();
        int[] hookInformation = restAttributes.getHook();
        boolean spotInformation = restAttributes.getSpot();

        // this.basicPosition = new Point((int)((this.StartPosition.x+this.LastPosition.x)*0.5),this.StartPosition.y);
        GeneralPath hat = createHat(this.basicPosition,hatInfomation,spotInformation);
        GeneralPath curl = createCurl(this.basicPosition,curlInformation,spotInformation);
        GeneralPath hook = createHook(this.basicPosition,hookInformation,spotInformation);

        this.shapeGroup = new ShapeGroup(hat, curl, hook, this.basicPosition);
    }

}
