package Grapics;

import java.awt.*;
import java.awt.geom.*;

public class ShapeUtils {

    // Attributes
    public static GeneralPath createAttribute(Point basicPoint, double modifyvalue) {
        GeneralPath attribute = new GeneralPath(GeneralPath.WIND_EVEN_ODD);

        if (modifyvalue == +0.5) {
            // sharp
            Point attributetPoint = new Point(basicPoint.x - 13, basicPoint.y +1);

            // outer
            attribute.moveTo(attributetPoint.x + 8, attributetPoint.y - 8);
            attribute.lineTo(attributetPoint.x + 4 , attributetPoint.y -7);
            attribute.lineTo(attributetPoint.x +4, attributetPoint.y -11);
            attribute.lineTo(attributetPoint.x + 2 , attributetPoint.y -11);
            attribute.lineTo(attributetPoint.x + 2, attributetPoint.y -6);
            attribute.lineTo(attributetPoint.x -2 , attributetPoint.y -4);
            attribute.lineTo(attributetPoint.x -2 , attributetPoint.y -8);
            attribute.lineTo(attributetPoint.x -4 , attributetPoint.y -8);
            attribute.lineTo(attributetPoint.x -4 , attributetPoint.y -3);
            attribute.lineTo(attributetPoint.x -8 , attributetPoint.y);
            attribute.lineTo(attributetPoint.x -8 , attributetPoint.y +3);
            attribute.lineTo(attributetPoint.x -4, attributetPoint.y);
            attribute.lineTo(attributetPoint.x -4 , attributetPoint.y +4);
            attribute.lineTo(attributetPoint.x -8 , attributetPoint.y +7);
            attribute.lineTo(attributetPoint.x -8 , attributetPoint.y +8);
            attribute.lineTo(attributetPoint.x -4 , attributetPoint.y +7);
            attribute.lineTo(attributetPoint.x -4 , attributetPoint.y +11);
            attribute.lineTo(attributetPoint.x -2 , attributetPoint.y +11);
            attribute.lineTo(attributetPoint.x -2 , attributetPoint.y +6);
            attribute.lineTo(attributetPoint.x +2 , attributetPoint.y +4);
            attribute.lineTo(attributetPoint.x +2 , attributetPoint.y +8);
            attribute.lineTo(attributetPoint.x +4 , attributetPoint.y +8);
            attribute.lineTo(attributetPoint.x +4 , attributetPoint.y +3);
            attribute.lineTo(attributetPoint.x +8 , attributetPoint.y);
            attribute.lineTo(attributetPoint.x +8 , attributetPoint.y -3);
            attribute.lineTo(attributetPoint.x +4 , attributetPoint.y);
            attribute.lineTo(attributetPoint.x +4 , attributetPoint.y-4);
            attribute.lineTo(attributetPoint.x +8 , attributetPoint.y -7);
            attribute.lineTo(attributetPoint.x +8 , attributetPoint.y -8);
            attribute.closePath();

            // inner
            attribute.moveTo(attributetPoint.x + 2, attributetPoint.y -3);
            attribute.lineTo(attributetPoint.x - 2, attributetPoint.y -1);
            attribute.lineTo(attributetPoint.x -2, attributetPoint.y +3);
            attribute.lineTo(attributetPoint.x + 2, attributetPoint.y +1);
            attribute.lineTo(attributetPoint.x + 2, attributetPoint.y -3);
            attribute.closePath();
            return attribute;

        }



        else if(modifyvalue == -0.5){
            // flat
            Point attributetPoint = new Point(basicPoint.x - 15, basicPoint.y - 10);

            // outer
            attribute.moveTo(attributetPoint.x-1, attributetPoint.y-6);
            attribute.lineTo(attributetPoint.x+1, attributetPoint.y-6);
            attribute.lineTo(attributetPoint.x+1, attributetPoint.y+6);
            attribute.curveTo(attributetPoint.x+1,attributetPoint.y+6,attributetPoint.x+3,attributetPoint.y+5,attributetPoint.x+4,attributetPoint.y+4);
            attribute.curveTo(attributetPoint.x+4,attributetPoint.y+4,attributetPoint.x+7,attributetPoint.y+4,attributetPoint.x+9,attributetPoint.y+7);
            attribute.curveTo(attributetPoint.x+8,attributetPoint.y+7,attributetPoint.x+6,attributetPoint.y+13,attributetPoint.x+4,attributetPoint.y+14);
            attribute.curveTo(attributetPoint.x+4,attributetPoint.y+14,attributetPoint.x+3,attributetPoint.y+15,attributetPoint.x+1,attributetPoint.y+16);
            attribute.lineTo(attributetPoint.x-1, attributetPoint.y+16);
            attribute.lineTo(attributetPoint.x-1, attributetPoint.y-3);
            attribute.closePath();

            // inner
            attribute.moveTo(attributetPoint.x+1, attributetPoint.y+8);
            attribute.curveTo(attributetPoint.x+1,attributetPoint.y+8,attributetPoint.x+4,attributetPoint.y+6,attributetPoint.x+6,attributetPoint.y+6);
            attribute.curveTo(attributetPoint.x+6,attributetPoint.y+6,attributetPoint.x+5,attributetPoint.y+7,attributetPoint.x+4,attributetPoint.y+11);
            attribute.lineTo(attributetPoint.x+1, attributetPoint.y+14);
            attribute.lineTo(attributetPoint.x+1, attributetPoint.y+8);
            attribute.closePath();

            return attribute;


        }
        else{
            return null;
        }
    }

    // Hat
    public static GeneralPath createHat( Point basicPoint, int[] hatInfomation, boolean spotInformation) {
        GeneralPath hat = new GeneralPath();
        Point hatPoint = new Point(basicPoint.x,basicPoint.y-44);

        if (hatInfomation[0] == 1) {
            if (hatInfomation[1] == 0) {
                hat.moveTo(hatPoint.x - 12, hatPoint.y+1);
                hat.lineTo(hatPoint.x - 12, hatPoint.y+2);
                hat.lineTo(hatPoint.x -7, hatPoint.y+2);
                hat.lineTo(hatPoint.x -6, hatPoint.y+7);
                hat.lineTo(hatPoint.x +6, hatPoint.y+7);
                hat.lineTo(hatPoint.x +7, hatPoint.y+2);
                hat.lineTo(hatPoint.x + 12, hatPoint.y+2);
                hat.lineTo(hatPoint.x + 12, hatPoint.y+1);

                hat.closePath();
            }
            else if (hatInfomation[1] == 1) {
                hat.moveTo(hatPoint.x - 6, hatPoint.y+2);
                hat.lineTo(hatPoint.x - 7, hatPoint.y+7);
                hat.lineTo(hatPoint.x - 12, hatPoint.y +7);
                hat.lineTo(hatPoint.x - 12, hatPoint.y +8);
                hat.lineTo(hatPoint.x + 12, hatPoint.y +8);
                hat.lineTo(hatPoint.x + 12, hatPoint.y +7);
                hat.lineTo(hatPoint.x + 7, hatPoint.y+7);
                hat.lineTo(hatPoint.x + 6, hatPoint.y+2);
                hat.closePath();

                if(spotInformation){
                    double spotRadius = 2;
                    double centerX = hatPoint.x + 13;
                    double centerY = hatPoint.y + 3;

                    hat.moveTo(centerX, centerY - spotRadius);
                    hat.curveTo(
                            centerX + spotRadius, centerY - spotRadius,
                            centerX + spotRadius, centerY + spotRadius,
                            centerX, centerY + spotRadius
                    );
                    hat.curveTo(
                            centerX - spotRadius, centerY + spotRadius,
                            centerX - spotRadius, centerY - spotRadius,
                            centerX, centerY - spotRadius
                    );
                    hat.closePath();
                }

            }
            return hat;
        }
        return null;
    }

    // Curl

    public static GeneralPath createCurl(Point basicPoint,boolean curlInformation,boolean spotInformation) {
        GeneralPath curl = new GeneralPath();
        if(curlInformation){
            Point hatPoint = new Point(basicPoint.x, basicPoint.y - 46 );

            curl.moveTo(hatPoint.x+3, hatPoint.y-1);
            curl.lineTo(hatPoint.x+7,hatPoint.y+5);
            curl.curveTo(hatPoint.x+7,hatPoint.y+5,hatPoint.x+8,hatPoint.y+6,hatPoint.x+7,hatPoint.y+7);
            curl.curveTo(hatPoint.x+7,hatPoint.y+7,hatPoint.x+6,hatPoint.y+8,hatPoint.x+5,hatPoint.y+10);
            curl.curveTo(hatPoint.x+5,hatPoint.y+10,hatPoint.x+6,hatPoint.y+12,hatPoint.x+8,hatPoint.y+15);
            curl.curveTo(hatPoint.x+8,hatPoint.y+15,hatPoint.x+5,hatPoint.y+15,hatPoint.x+4,hatPoint.y+16);
            curl.curveTo(hatPoint.x+4,hatPoint.y+16,hatPoint.x+4,hatPoint.y+18,hatPoint.x+6,hatPoint.y+22);
            curl.curveTo(hatPoint.x+6,hatPoint.y+22,hatPoint.x+2,hatPoint.y+19,hatPoint.x+1,hatPoint.y+15);
            curl.curveTo(hatPoint.x+1,hatPoint.y+15,hatPoint.x+3,hatPoint.y+13,hatPoint.x+6,hatPoint.y+14);
            curl.lineTo(hatPoint.x+1,hatPoint.y+8);

            curl.curveTo(hatPoint.x+1,hatPoint.y+8,hatPoint.x+4,hatPoint.y+6,hatPoint.x+5,hatPoint.y+4);
            curl.lineTo(hatPoint.x+3,hatPoint.y-1);

            curl.closePath();

            if(spotInformation){
                double spotRadius = 2;
                double centerX = hatPoint.x + 12;
                double centerY = hatPoint.y + 5;

                curl.moveTo(centerX, centerY - spotRadius);
                curl.curveTo(
                        centerX + spotRadius, centerY - spotRadius,
                        centerX + spotRadius, centerY + spotRadius,
                        centerX, centerY + spotRadius
                );
                curl.curveTo(
                        centerX - spotRadius, centerY + spotRadius,
                        centerX - spotRadius, centerY - spotRadius,
                        centerX, centerY - spotRadius
                );
                curl.closePath();
            }

            return curl;
        }
        return null;
    }

    // hook

    public static GeneralPath createHook(Point basicPoint, int[] hookInformation,boolean spotInformation) {
        GeneralPath hook = new GeneralPath();
        if(hookInformation[0]==1){
            Point hookPoint = new Point(basicPoint.x+2, basicPoint.y-48);
            hook.moveTo(hookPoint.x+11, hookPoint.y+3);
            hook.lineTo(hookPoint.x+13,hookPoint.y+3);
            hook.lineTo(hookPoint.x+7,hookPoint.y+25);
            hook.lineTo(hookPoint.x+5,hookPoint.y+25);
            hook.moveTo(hookPoint.x+11, hookPoint.y+3);
            hook.closePath();

            for (int i = 0; i < hookInformation[1]; i++) {
                Point hookPoint1 = new Point(hookPoint.x-i*2,hookPoint.y+i*7);
                hook.moveTo(hookPoint1.x+12, hookPoint1.y+12);
                hook.curveTo(hookPoint1.x+12, hookPoint1.y+12, hookPoint1.x + 9, hookPoint1.y + 7, hookPoint1.x + 6, hookPoint1.y + 8);
                hook.curveTo(hookPoint1.x+6, hookPoint1.y+8, hookPoint1.x + 7, hookPoint1.y + 5, hookPoint1.x + 6, hookPoint1.y + 4);
                hook.curveTo(hookPoint1.x+6, hookPoint1.y+4, hookPoint1.x + 4, hookPoint1.y + 3, hookPoint1.x + 2, hookPoint1.y + 5);
                hook.curveTo(hookPoint1.x+2, hookPoint1.y+5, hookPoint1.x +2, hookPoint1.y + 6, hookPoint1.x + 3, hookPoint1.y + 8);
                hook.curveTo(hookPoint1.x+3, hookPoint1.y+9, hookPoint1.x +6, hookPoint1.y + 10, hookPoint1.x + 9, hookPoint1.y + 9);
                hook.lineTo(hookPoint1.x+12,hookPoint1.y+12);
                hook.closePath();
            }
            if(spotInformation){
                double spotRadius = 2;
                double centerX = hookPoint.x + 15;
                double centerY = hookPoint.y + 7;

                hook.moveTo(centerX, centerY - spotRadius);
                hook.curveTo(
                        centerX + spotRadius, centerY - spotRadius,
                        centerX + spotRadius, centerY + spotRadius,
                        centerX, centerY + spotRadius
                );
                hook.curveTo(
                        centerX - spotRadius, centerY + spotRadius,
                        centerX - spotRadius, centerY - spotRadius,
                        centerX, centerY - spotRadius
                );
                hook.closePath();
            }
            return hook;
        }
        return null;
    }


    // circle - 1

    public static Shape applyRotation(Ellipse2D ellipse, Point basicPoint) {
        AffineTransform rotateTransform = AffineTransform.getRotateInstance(
                Math.toRadians(330), basicPoint.x, basicPoint.y);
        return rotateTransform.createTransformedShape(ellipse);
    }

    // circle - 2
    public static void handleEllipseAndSpot(Graphics2D g2, Shape rotatedEllipse, boolean isFilled, boolean spot, Point basicPoint) {
        if (isFilled) {
            g2.fill(rotatedEllipse);
        } else {
            g2.draw(rotatedEllipse);
        }

        if (spot) {
            g2.fillOval(basicPoint.x + 10, basicPoint.y - 2, 3, 3);
        }
    }

    // vertical

    public static Line2D createVerticalLine(Point basicPoint, boolean isUpward,double beatValue) {
        if(beatValue!=1.0){
            if (isUpward) {
                return new Line2D.Double(basicPoint.x +5, basicPoint.y-1, basicPoint.x + 6, basicPoint.y - 30);
            } else {
                return new Line2D.Double(basicPoint.x - 6, basicPoint.y+2, basicPoint.x - 6, basicPoint.y + 31);
            }
        }
        return null;
    }

    // horizontal
    public static Line2D[] createHorizontalLines(Point basicPoint, int[] lineInformation) {
        int numLines = lineInformation[0];
        int offset = lineInformation[1];
        Line2D[] horizontalLines = new Line2D[numLines];
        for (int i = 0; i < numLines; i++) {
            horizontalLines[i] = new Line2D.Double(
                    basicPoint.x -8,
                    basicPoint.y + offset * 4,
                    basicPoint.x + 8,
                    basicPoint.y + offset * 4);
            offset -= 2;
        }
        return horizontalLines;
    }

    // tail
    public static GeneralPath createTail(Point basicPoint, int tailNum, boolean isUpward) {
        GeneralPath tail = new GeneralPath();
        if(isUpward){
            for (int i = 0; i < tailNum; i++) {
                Point flagPoint = new Point(basicPoint.x + 6, basicPoint.y - 30 + i * 9);

                tail.moveTo(flagPoint.x, flagPoint.y);
                tail.curveTo(flagPoint.x, flagPoint.y, flagPoint.x + 2, flagPoint.y + 4, flagPoint.x + 6, flagPoint.y + 6);
                tail.curveTo(flagPoint.x + 6, flagPoint.y + 6, flagPoint.x + 12, flagPoint.y + 12, flagPoint.x + 6, flagPoint.y + 20);
                tail.curveTo(flagPoint.x + 6, flagPoint.y + 20, flagPoint.x + 9, flagPoint.y + 12, flagPoint.x + 4, flagPoint.y + 10);
                tail.curveTo(flagPoint.x + 4, flagPoint.y + 10, flagPoint.x + 2, flagPoint.y + 9, flagPoint.x, flagPoint.y + 8);
            }
        }

        else{
            for (int i = 0; i < tailNum; i++) {
                Point flagPoint = new Point(basicPoint.x - 6, basicPoint.y + 31 - i * 9);

                tail.moveTo(flagPoint.x, flagPoint.y);
                tail.curveTo(flagPoint.x, flagPoint.y, flagPoint.x + 2, flagPoint.y - 4, flagPoint.x + 6, flagPoint.y - 6);
                tail.curveTo(flagPoint.x + 6, flagPoint.y - 6, flagPoint.x + 12, flagPoint.y - 12, flagPoint.x + 6, flagPoint.y - 20);
                tail.curveTo(flagPoint.x + 6, flagPoint.y - 20, flagPoint.x + 9, flagPoint.y - 12, flagPoint.x + 4, flagPoint.y - 10);
                tail.curveTo(flagPoint.x + 4, flagPoint.y - 10, flagPoint.x + 2, flagPoint.y - 9, flagPoint.x, flagPoint.y - 8);
            }
        }

        return tail;
    }


}
