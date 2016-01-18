/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contaazul.challenge.mars.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Startup config file. Set root Path to /rest
 *
 * @author Wesklei Migliorini
 */
@ApplicationPath("/rest")
public class StartupApplication extends Application {
}
