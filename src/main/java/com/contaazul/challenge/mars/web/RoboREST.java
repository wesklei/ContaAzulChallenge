/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contaazul.challenge.mars.web;

import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 *
 * @author Wesklei Migliorini
 */
@Path("/mars")
@Produces(APPLICATION_JSON)
public class RoboREST {

    public static final Logger logger = Logger.getLogger(RoboREST.class.getCanonicalName());

    /**
     * Receive instructions to simulate mars robot move behavior
     *
     * @param instruction the command
     * @return The final position after instruction processed
     */
    @GET
    @Path("{instruction}")
    public String instruction(@PathParam("instruction") String instruction) {
        logger.info("Instruction received: " + instruction);
        return "(0,0,N)";
    }

}
