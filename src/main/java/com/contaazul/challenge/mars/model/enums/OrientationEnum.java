/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contaazul.challenge.mars.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Wesklei Migliorini
 */
public enum OrientationEnum {

    NORTH("N", 0), SOUTH("S", 180), EAST("E", 90), WEST("W", 270);

    private final String orientationValue;
    private final int intValue;
    private static final Map<Integer, OrientationEnum> automataOrientationMap = new HashMap<>();

    static {
        for (OrientationEnum direction : OrientationEnum.values()) {
            automataOrientationMap.put(direction.getIntValue(), direction);
        }
    }

    OrientationEnum(final String position, final int value) {
        this.orientationValue = position;
        this.intValue = value;
    }

    public String getOrientationValue() {
        return this.orientationValue;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public static OrientationEnum getOrientationRotating(OrientationEnum orientationEnum, RotationEnum rotationEnum) {
        OrientationEnum newOrientationEnum;
        int value = orientationEnum.getIntValue() + rotationEnum.getIntValue();
        if (value < NORTH.getIntValue()) {
            newOrientationEnum = WEST;
        } else if (value > WEST.getIntValue()) {
            newOrientationEnum = NORTH;
        } else {
            newOrientationEnum = automataOrientationMap.get(value);
        }
        return newOrientationEnum;
    }
    

}
