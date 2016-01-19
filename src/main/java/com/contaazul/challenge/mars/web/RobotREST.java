/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contaazul.challenge.mars.web;

import com.contaazul.challenge.mars.business.RobotOperator;
import com.contaazul.challenge.mars.exception.RobotBadRequestException;
import com.contaazul.challenge.mars.model.Robot;
import java.util.logging.Logger;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 *
 * @author Wesklei Migliorini
 */
@Path("/mars")
@Produces(APPLICATION_JSON)
public class RobotREST {

    public static final Logger logger = Logger.getLogger(RobotREST.class.getCanonicalName());

    /**
     * Receive instructions to simulate mars robot move behavior
     *
     * @param instruction the command
     * @return The final position after instruction processed
     */
    @POST
    @Path("{instruction}")
    public Response instruction(@PathParam("instruction") String instruction) {
        logger.info("Instruction received: " + instruction);
        
        Robot robot = new Robot();
        RobotOperator robotOperator = new RobotOperator(robot);
        robotOperator.operate(instruction);
        
        return Response.ok(robot,MediaType.TEXT_PLAIN_TYPE).build();        
    }

}
