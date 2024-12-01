package Grapics;

import Elements.*;
import Elements.Element;
import Pages.Scorepage;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Grapics.ShapeUtils.*;

public class ScoreForeground extends JPanel {
    private static ArrayList<ShapeGroup> shapes = new ArrayList<>();
    public ScoreForeground() {
        setOpaque(false);
    }

    public void updateShapes() {
        shapes.clear();
        for (List<Element> measure : Scorepage.elements) {
            for (Element element : measure) {
                ShapeGroup shapeGroup = element.getShapeGroup();
                shapes.add(shapeGroup);
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (ShapeGroup group : shapes) {
            // melody
            if (group.getRotatedEllipse() != null) {
                handleEllipseAndSpot(g2, group.getRotatedEllipse(), group.isFilled(), group.isSpot(), group.getBasicPoint());
            }
            if (group.getHorizontalLines() != null) {
                Line2D[] horizontalLines = group.getHorizontalLines();
                for (Line2D line : horizontalLines) {
                    g2.draw(line);
                }
            }
            if (group.getVerticalLine() != null) {
                g2.draw(group.getVerticalLine());
            }

            if (group.getTail() != null) {
                g2.fill(group.getTail());
            }
            // rest
            if(group.getHat()!=null){
                g2.fill(group.getHat());
            }
            if(group.getCurl()!=null){
                g2.fill(group.getCurl());
            }
            if(group.getHook()!=null){
                g2.fill(group.getHook());
            }
            if(group.getAttribute()!=null){
                g2.fill(group.getAttribute());
            }
        }
    }
}




