package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {
	
	@Before
    static void setConnectedUser() {
        Security.setConnectedUser();
    }
	
    public static void index() {
        render();
    }
}