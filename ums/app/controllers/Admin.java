package controllers;

import play.mvc.Controller;
import models.Profile;
import models.User;

public class Admin extends Controller{
	
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
		Profiles.saveProfile(user, firstName, lastName, address, address2, 
				city, postalCode, state, emailCollege, emailPersonal, 
				reasonForApplication, isSubmitted);
		Users.users();
	}
}
