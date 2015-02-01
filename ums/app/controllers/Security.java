package controllers;

import play.mvc.Before;
import models.User;
public class Security extends Secure.Security {
	
	@Before
    public static void setConnectedUser() {
		User user = null;
        if(Security.isConnected()) {
            user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("isConnected", true);
            renderArgs.put("user", user);
        }
    }
	
	public static boolean authenticate(String email, String password) {
		User user = User.connect(email, password);
		if(user != null) {
			session.put("username", user.getEmail());
		}
	    return user != null;
	}
	
	static void onDisconnected(){
	    try {
	    	Application.index();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	static void onAuthenticated() {
	    Application.index();
	}
	
	static boolean check(String profile) {
	    if("admin".equals(profile)) {
	        return User.find("byEmail", connected()).<User>first().isAdmin();
	    }
	    return false;
	}
	
	public static User getCurrentUser() {
		if (!Security.isConnected()) {
			return null;
		}
		return (User)renderArgs.get("user");
	}
}
