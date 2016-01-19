package com.contaazul.challenge.mars.business;

import com.contaazul.challenge.mars.model.Robot;

/**
 *
 * @author Wesklei Migliorini
 */
public interface RobotOperator {

    public Robot operate(String instructions);  

    public boolean isValidInstructions(String instructions);

    public boolean isMoveInstruction(char instruction);

    public boolean isRotateInstruction(char instruction);

    public boolean isValidMove(int newXCord, int newYCord);
    
}
