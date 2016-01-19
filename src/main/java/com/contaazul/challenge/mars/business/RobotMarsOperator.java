package com.contaazul.challenge.mars.business;

import com.contaazul.challenge.mars.exception.RobotBadRequestException;
import com.contaazul.challenge.mars.model.Robot;
import com.contaazul.challenge.mars.model.enums.OrientationEnum;
import com.contaazul.challenge.mars.model.enums.RotationEnum;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Wesklei Migliorini
 */
@Named
public class RobotMarsOperator implements RobotOperator {

    private static final Logger logger = Logger.getLogger(RobotMarsOperator.class.getCanonicalName());

    private static final char MOVE_INSTRUCTION = 'M';
    private static final int TERRAIN_MIN = 0;
    private static final int TERRAIN_MAX = 4;

    private final Robot robot;

    @Inject
    public RobotMarsOperator(Robot robot) {
        this.robot = robot;
    }

    @Override
    public Robot operate(String instructions) {

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

        return robot;
    }
    
    @Override
    public boolean isValidInstructions(String instructions) {

        boolean isValid = false;

        String moveLimits = new String(new char[TERRAIN_MAX + 1]).replace("\0", String.valueOf(MOVE_INSTRUCTION));

        if (!instructions.contains(moveLimits) && instructions.matches("^[LRM]*")) {
            isValid = true;
        }

        return isValid;

    }
    
    @Override
    public boolean isMoveInstruction(char instruction) {
        return MOVE_INSTRUCTION == instruction;

    }

    @Override
    public boolean isRotateInstruction(char instruction) {
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

    @Override
    public boolean isValidMove(int newXCord, int newYCord) {
        return newXCord <= TERRAIN_MAX && newXCord >= TERRAIN_MIN
                && newYCord >= TERRAIN_MIN && newYCord <= TERRAIN_MAX;
    }

    private void operateRotateTo(RotationEnum toRotationEnum) {
        OrientationEnum orientationEnum = robot.getOrientationEnum();

        OrientationEnum newOrientationEnum = OrientationEnum.getOrientationRotating(orientationEnum, toRotationEnum);
        robot.setOrientationEnum(newOrientationEnum);

        logger.info("Operate Rotate To: Robot in (" + robot.getxCoord() + "," + robot.getyCoord() + "," + robot.getOrientationEnum().getOrientationValue() + ")");
    }

}
