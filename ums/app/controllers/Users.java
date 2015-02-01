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
@With(Secure.class)
public class Users extends CRUD{
	
	@Before
    static void setConnectedUser() {
        Security.setConnectedUser();
    }
	
	/*
	 * renders the page to show User info
	 */
	public static void show(Long id) {
		User other = User.findById(id);
		render(other);
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
	@Check("admin")
	public static void users() {
		list(0, null, null, null, null);
	}
	
	/*
	 * Copied from Crud.list()
	 */
	@Check("admin")
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
}
