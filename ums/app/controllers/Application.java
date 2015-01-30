package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
@With(Security.class)
public class Application extends Controller {
	
	@Before
    static void setConnectedUser() {
        Security.setConnectedUser();
    }

    public static void index() {
        render();
    }
}