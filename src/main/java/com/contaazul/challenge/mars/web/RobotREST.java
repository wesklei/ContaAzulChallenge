package com.contaazul.challenge.mars.web;

import com.contaazul.challenge.mars.business.RobotOperator;
import com.contaazul.challenge.mars.model.Robot;
import java.util.logging.Logger;
import javax.inject.Inject;
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

    @Inject
    private RobotOperator robotOperator;

    /**
     * Receive instructions to simulate mars robot move behavior
     *
     * @param instructions the commands
     * @return The final position after instruction processed or error
     */
    @POST
    @Path("{instructions}")
    public Response instructions(@PathParam("instructions") String instructions) {
        logger.info("Instruction received: " + instructions);

        Robot robotOuput = robotOperator.operate(instructions);
        return Response.ok(robotOuput, MediaType.TEXT_PLAIN_TYPE).build();
    }

}
