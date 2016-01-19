/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contaazul.challenge.mars.business;

import com.contaazul.challenge.mars.exception.RobotBadRequestException;
import com.contaazul.challenge.mars.model.Robot;
import com.contaazul.challenge.mars.model.enums.OrientationEnum;
import com.contaazul.challenge.mars.model.enums.RotationEnum;
import java.util.logging.Logger;

/**
 *
 * @author Wesklei Migliorini
 */
public class RobotOperator {

    private static final Logger logger = Logger.getLogger(RobotOperator.class.getCanonicalName());

    private static final char MOVE_INSTRUCTION = 'M';
    private static final int TERRAIN_MIN = 0;
    private static final int TERRAIN_MAX = 4;

    private final Robot robot;

    public RobotOperator(Robot robot) {
        this.robot = robot;
    }

    public void operate(String instructions) {

        if (!isValidInstructions(instructions)) {
            throw new RobotBadRequestException();
        }

        for (char instruction : instructions.toCharArray()) {

            if (isMoveInstruction(instruction)) {
                operateMove();

            } else if (isRotateInstruction(instruction)) {
                RotationEnum rotationEnum = RotationEnum.getByValue(instruction);
                operateRotateTo(rotationEnum);
            }
        }

    }

    private boolean isValidInstructions(String instructions) {

        boolean isValid = false;

        String moveLimits = new String(new char[TERRAIN_MAX + 1]).replace("\0", String.valueOf(MOVE_INSTRUCTION));

        if (!instructions.contains(moveLimits) && instructions.matches("^[LRM]*")) {
            isValid = true;
        }

        return isValid;

    }

    private boolean isMoveInstruction(char instruction) {
        return MOVE_INSTRUCTION == instruction;

    }

    private boolean isRotateInstruction(char instruction) {
        return RotationEnum.getByValue(instruction) != null;
    }

    private void operateMove() {

        int newXCord = robot.getxCoord();
        int newYCord = robot.getyCoord();

        OrientationEnum orientationEnum = robot.getOrientationEnum();
        switch (orientationEnum) {
            case NORTH:
                newYCord++;
                break;
            case SOUTH:
                newYCord--;
                break;

            case EAST:
                newXCord++;
                break;
            case WEST:
                newXCord--;
                break;
        }

        if (isValidMove(newXCord, newYCord)) {
            robot.setxCoord(newXCord);
            robot.setyCoord(newYCord);

            logger.info("Operate Move: Robot in (" + robot.getxCoord() + "," + robot.getyCoord() + "," + robot.getOrientationEnum().getOrientationValue() + ")");
        } else {
            logger.info("Operate Move: ERROR Robot in (" + newXCord + "," + newYCord + "," + robot.getOrientationEnum().getOrientationValue() + ")");
            throw new RobotBadRequestException();
        }
    }

    private boolean isValidMove(int newXCord, int newYCord) {
        if (newXCord <= TERRAIN_MAX && newXCord >= TERRAIN_MIN
                && newYCord >= TERRAIN_MIN && newYCord <= TERRAIN_MAX) {
            return true;
        }

        return false;
    }

    private void operateRotateTo(RotationEnum toRotationEnum) {
        OrientationEnum orientationEnum = robot.getOrientationEnum();

        OrientationEnum newOrientationEnum = OrientationEnum.getOrientationRotating(orientationEnum, toRotationEnum);
        robot.setOrientationEnum(newOrientationEnum);

        logger.info("Operate Rotate To: Robot in (" + robot.getxCoord() + "," + robot.getyCoord() + "," + robot.getOrientationEnum().getOrientationValue() + ")");
    }

}
