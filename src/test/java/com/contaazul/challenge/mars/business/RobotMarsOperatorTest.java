package com.contaazul.challenge.mars.business;

import com.contaazul.challenge.mars.exception.RobotBadRequestException;
import com.contaazul.challenge.mars.model.Robot;
import com.contaazul.challenge.mars.model.enums.OrientationEnum;
import com.contaazul.challenge.mars.model.enums.RotationEnum;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Wesklei Migliorini
 */
public class RobotMarsOperatorTest {

    private RobotMarsOperator robotMarsOperator = new RobotMarsOperator(new Robot());

    @Test
    public void operateShouldNotReturnError() {
        robotMarsOperator.operate("MMMM");
        robotMarsOperator = new RobotMarsOperator(new Robot());
        robotMarsOperator.operate("MMRMMRMM");
        robotMarsOperator = new RobotMarsOperator(new Robot());
        robotMarsOperator.operate("MML");
    }

    @Test(expected = RobotBadRequestException.class)
    public void operateInvalidInstructionShouldReturnError() {
        robotMarsOperator.operate("AA123MLR");
    }

    @Test(expected = RobotBadRequestException.class)
    public void operateInvalidMoveShouldReturnError() {
        robotMarsOperator.operate("MMMMMMMMMMMMMMMMMMMMMMMM");
    }

    @Test
    public void isValidInstructionsShouldNotReturnError() {
        assertEquals(robotMarsOperator.isValidInstructions("MMMM"), true);
        assertEquals(robotMarsOperator.isValidInstructions("MMRMMRMM"), true);
        assertEquals(robotMarsOperator.isValidInstructions("MML"), true);
        assertEquals(robotMarsOperator.isValidInstructions("AAA"), false);
        assertEquals(robotMarsOperator.isValidInstructions("MMMMMMMMMMMMMMMMMMMMMMMM"), false);
    }

    @Test
    public void isMoveInstructionShouldNotReturnError() {
        assertEquals(robotMarsOperator.isMoveInstruction('M'), true);
        assertEquals(robotMarsOperator.isMoveInstruction('L'), false);
    }

    @Test
    public void isRotateInstructionShouldNotReturnError() {
        assertEquals(robotMarsOperator.isRotateInstruction('R'), true);
        assertEquals(robotMarsOperator.isRotateInstruction('L'), true);
        assertEquals(robotMarsOperator.isRotateInstruction('M'), false);
    }

    @Test
    public void isValidMoveShouldNotReturnError() {
        assertEquals(robotMarsOperator.isValidMove(0, 0), true);
        assertEquals(robotMarsOperator.isValidMove(-1, -1), false);

    }

    @Test
    public void operateRotateToNorthShouldNotReturnError() {
        OrientationEnum orientationEnum = OrientationEnum.getOrientationRotating(OrientationEnum.EAST, RotationEnum.LEFT);
        assertEquals(orientationEnum, OrientationEnum.NORTH);
        orientationEnum = OrientationEnum.getOrientationRotating(OrientationEnum.WEST, RotationEnum.RIGHT);
        assertEquals(orientationEnum, OrientationEnum.NORTH);
    }

    @Test
    public void operateRotateToSouthShouldNotReturnError() {
        OrientationEnum orientationEnum = OrientationEnum.getOrientationRotating(OrientationEnum.WEST, RotationEnum.LEFT);
        assertEquals(orientationEnum, OrientationEnum.SOUTH);
        orientationEnum = OrientationEnum.getOrientationRotating(OrientationEnum.EAST, RotationEnum.RIGHT);
        assertEquals(orientationEnum, OrientationEnum.SOUTH);
    }

    @Test
    public void operateRotateToWestShouldNotReturnError() {
        OrientationEnum orientationEnum = OrientationEnum.getOrientationRotating(OrientationEnum.NORTH, RotationEnum.LEFT);
        assertEquals(orientationEnum, OrientationEnum.WEST);
        orientationEnum = OrientationEnum.getOrientationRotating(OrientationEnum.SOUTH, RotationEnum.RIGHT);
        assertEquals(orientationEnum, OrientationEnum.WEST);
    }

    @Test
    public void operateRotateToEastShouldNotReturnError() {
        OrientationEnum orientationEnum = OrientationEnum.getOrientationRotating(OrientationEnum.SOUTH, RotationEnum.LEFT);
        assertEquals(orientationEnum, OrientationEnum.EAST);
        orientationEnum = OrientationEnum.getOrientationRotating(OrientationEnum.NORTH, RotationEnum.RIGHT);
        assertEquals(orientationEnum, OrientationEnum.EAST);
    }

}
