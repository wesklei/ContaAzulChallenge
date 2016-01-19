/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contaazul.challenge.mars.model;

import com.contaazul.challenge.mars.model.enums.OrientationEnum;

/**
 *
 * @author Wesklei Migliorini
 */
public class Robot {

    private static final long serialVersionUID = -340875071855645838L;

    private int xCoord;
    private int yCoord;
    private OrientationEnum orientationEnum;

    public Robot() {
        xCoord = 0;
        yCoord = 0;
        orientationEnum = OrientationEnum.NORTH;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public OrientationEnum getOrientationEnum() {
        return orientationEnum;
    }

    public void setOrientationEnum(OrientationEnum orientationEnum) {
        this.orientationEnum = orientationEnum;
    }

    @Override
    public String toString() {
        return "(" + xCoord + "," + yCoord +"," + orientationEnum.getOrientationValue() + ")";
    }
    
    

}
