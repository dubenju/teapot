package org.teapot.util;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.teapot.servlet.model.ITeapotModel;

public class TeapotModelFactory {
    public static ITeapotModel getModel( HttpServletRequest req, HttpServletResponse res, ServletConfig config ) {
    	return null;
    }
}
