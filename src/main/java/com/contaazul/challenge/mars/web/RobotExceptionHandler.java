/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contaazul.challenge.mars.web;

import com.contaazul.challenge.mars.exception.RobotBadRequestException;
import com.contaazul.challenge.mars.model.ErrorMessage;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Wesklei Migliorini
 */
@Provider
public class RobotExceptionHandler implements
        ExceptionMapper<RobotBadRequestException> {

    private static final String ERROR_CODE = "400";
    private static final String ERROR_BAD_REQUEST = "Bad Request";

    @Override
    public Response toResponse(final RobotBadRequestException exception) {
        return Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(ERROR_CODE, ERROR_BAD_REQUEST)).type(MediaType.TEXT_PLAIN).build();
    }
}
