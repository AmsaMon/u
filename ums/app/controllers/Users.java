package controllers;

import java.util.List;

import controllers.CRUD.ObjectType;
import models.User;
import play.data.validation.Equals;
import play.data.validation.Required;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Before;
import play.mvc.With;

@With(Security.class)
public class Users extends CRUD{
	
	/*
	 * renders the signup page
	 */
	public static void signup() throws Exception{
		render();
	}
	
	/*
	 * creates new user
	 */
	public static void create(
			@Required String firstName,
			@Required String lastName,
			@Required String email,
			@Required String password,
			@Equals(value="password", message ="Passwords do not match")  
			String passwordConfirmation) throws Exception{
		// Check if the email already exists
		validation.isTrue("emailExistence", 
				User.find("byEmail", email).first() == null)
				.message("Email already exists");
		
		if(validation.hasErrors()) {
		    params.flash();  
		    validation.keep();
		    // redirect to sign-up page back
		    signup();
		} else {
			new User(firstName, lastName, email, password).save();
			// redirect to home page
			Application.index();
		}
	}
	
	/*
	 * renders the page to show User info
	 */
	public static void show(Long id) {
		User user = User.findById(id);
		render(user);
	}
	
	/*
	 * Edit the user
	 */
	public static void edit(
			Long userId,
			String firstName, 
			String lastName, 
			String email) {
		User user = User.findById(userId);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.save();
		users();
	}
	
	/*
	 * Renders the default list page
	 */
	public static void users() {
		list(0, null, null, null, null);
	}
	
	/*
	 * Copied from Crud.list()
	 */
	public static void list(int page, String search, String searchFields, String orderBy, String order) {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        if (page < 1) {
            page = 1;
        }
        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, (String) request.args.get("where"));
        Long count = type.count(search, searchFields, (String) request.args.get("where"));
        Long totalCount = type.count(null, null, (String) request.args.get("where"));
        try {
            render(type, objects, count, totalCount, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order);
        }
    }
	
	/*
	 * Renders the default login page
	 * Should be deprecated later
	 */
	public static void login() {
		render();
	}
	
	/*
	 * Authenticate the user
	 * if succeeded renders the index page
	 */
	public static void authenticate(String email, String password) {
		boolean authentic = Security.authenticate(email, password);
		validation.isTrue("loggedIn", authentic)
			.message("Email and Passwords do not match");
	    if (authentic) {
	    	Application.index();
	    } else {
	    	// has errors
		    params.flash();  
	    	validation.keep();
	    	Application.index();
	    }
	}
}
