package controllers;

import java.sql.Date;

import models.Profile;
import models.Profile.ApplicationStatus;
import models.User;
import play.mvc.Util;
import play.mvc.With;

@With(Security.class)
public class Profiles extends CRUD{
	
	/**
	 * Renders the page for the user's profile
	 */
	public static void show() {
		Profile profile = Security.getCurrentUser().getProfile();
		String applicationStatus = ((profile != null && profile.getApplicationStatus() != null) 
				? profile.getApplicationStatus().toString() : null);
		render(profile, applicationStatus);
	}
	
	/**
	 * Endpoint for creating or updating profile object
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param address2
	 * @param city
	 * @param postalCode
	 * @param state
	 * @param emailCollege
	 * @param emailPersonal
	 * @param reasonForApplication
	 */
	public static void createProfile(
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
		User user = Security.getCurrentUser();
		boolean isSubmitted = request.params.get("Submit") != null;
		saveProfile(user, firstName, lastName, address,
			address2, city, postalCode, state,
			emailCollege, emailPersonal, reasonForApplication, isSubmitted);
		Application.index();
	}
	
	/**
	 * Create or update profile
	 * @param user
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param address2
	 * @param city
	 * @param postalCode
	 * @param state
	 * @param emailCollege
	 * @param emailPersonal
	 * @param reasonForApplication
	 * @param isSubmitted
	 */
	@Util
	public static void saveProfile(
			User user,
			String firstName,
			String lastName,
			String address,
			String address2,
			String city,
			String postalCode, 
			String state,
			String emailCollege,
			String emailPersonal,
			String reasonForApplication,
			boolean isSubmitted) { 
		// save user if modified
		if(!user.getFirstName().equals(firstName) ||
				!user.getLastName().equals(lastName)) {
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.save();
		}
		
		// get profile if exists
		Profile profile = null;
		if (user.getProfile() == null) {
			profile = new Profile();
			user.setProfile(profile);
			profile.setUser(user);
		} else {
			profile = user.getProfile();
		}
		
		// save profile
		//profile.setDateOfBirth(dateOfBirth);
		profile.setAddress(address);
		profile.setAddress2(address2);
		profile.setCity(city);
		profile.setPostalCode(postalCode);
		profile.setState(state);
		profile.setEmailCollege(emailCollege);
		profile.setEmailPersonal(emailPersonal);
		profile.setReasonForApplication(reasonForApplication);
		if (isSubmitted) {
			profile.setApplicationStatus(ApplicationStatus.Pending);
		} else if (profile.getApplicationStatus() == null) {
			profile.setApplicationStatus(ApplicationStatus.None);
		}
		profile.save();
	}

}
