package org.teapot.servlet.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {
    public void action(HttpServletRequest req, HttpServletResponse resp)  throws IOException ;
}
