package controllers;

import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import models.Profile;
import models.User;
@With(Secure.class)
@Check("admin")
public class Admin extends Controller{
	
	@Before
    static void setConnectedUser() {
        Security.setConnectedUser();
    }
	
	public static void showProfile(Long userId) {
		User user = User.findById(userId);
		Profile profile = user.getProfile();
		render(user, profile);
	}
	
	public static void createProfile(
			Long userId,
			String firstName,
			String lastName,
			String address,
			String address2,
			String city,
			String postalCode, 
			String state,
			String emailCollege,
			String emailPersonal,
			String reasonForApplication) {
		User user = User.findById(userId);
		boolean isSubmitted = request.params.get("Submit") != null;
		Profiles.saveOrSubmit(user, firstName, lastName, address, address2, 
				city, postalCode, state, emailCollege, emailPersonal, 
				reasonForApplication, isSubmitted);
		Users.users();
	}
}
