package components;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

public class ShapeGroup {
    private final Shape rotatedEllipse;
    private final Line2D[] horizontalLines;
    private final Shape verticalLine;
    private final Shape tail;
    private final boolean isFilled;
    private final boolean isSpot;
    private final Point basicPoint;
    private final Shape hat;
    private final Shape curl;
    private final Shape hook;

    private final Shape attribute;
    // private final Shape spot;

    public ShapeGroup(){
        this.rotatedEllipse = null;
        this.horizontalLines = null;
        this.verticalLine = null;
        this.tail = null;
        this.isFilled = false;
        this.isSpot = false;
        this.basicPoint = null;

        this.hat =null;
        this.curl=null;
        this.hook =null;
        this.attribute = null;
        // this.spot =null;
    }


    // 멜로디 생성자
    public ShapeGroup(Shape rotatedEllipse, Line2D[] horizontalLines, Shape verticalLine, Shape tail,
                      boolean isFilled, boolean isSpot, Shape attribute,Point basicPoint) {
        this.rotatedEllipse = rotatedEllipse;
        this.horizontalLines = horizontalLines;
        this.verticalLine = verticalLine;
        this.tail = tail;
        this.isFilled = isFilled;
        this.isSpot = isSpot;
        this.basicPoint = basicPoint;

        this.hat =null;
        this.curl=null;
        this.hook =null;
        this.attribute = attribute;
        // this.spot =null;
    }

    public ShapeGroup(Shape hat,Shape curl,Shape hook,Point basicPoint) {
        this.rotatedEllipse = null;
        this.horizontalLines = null;
        this.verticalLine = null;
        this.tail = null;
        this.isFilled = false;
        this.isSpot = false;

        this.basicPoint = basicPoint;
        this.hat =hat;
        this.curl=curl;
        this.hook =hook;
        this.attribute = new GeneralPath();
        // this.spot =spot;
    }

    public Shape getRotatedEllipse() {
        return rotatedEllipse;
    }

    public Line2D[] getHorizontalLines() {
        return horizontalLines;
    }

    public Shape getVerticalLine() {
        return verticalLine;
    }

    public Shape getTail() {
        return tail;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public boolean isSpot() {
        return isSpot;
    }

    public Point getBasicPoint() {
        return basicPoint;
    }
    public Shape getHat() {
        return hat;
    }
    public Shape getCurl() {
        return curl;
    }
    public Shape getHook() {
        return hook;
    }
    public Shape getAttribute() {
        return attribute;
    }
}