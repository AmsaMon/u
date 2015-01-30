package models;

import java.util.Date;

public class AcademicStatus {
	private Profile profile;
	private School school;
	private Date expectedGraduationDate;
	private DegreeType degreeType;
	private String major;
	private String secondaryMajor;
	private String minor;
	
	public enum DegreeType {
		Secondary, TwoYear, Bachelor, Master, Doctoral;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Date getExpectedGraduationDate() {
		return expectedGraduationDate;
	}

	public void setExpectedGraduationDate(Date expectedGraduationDate) {
		this.expectedGraduationDate = expectedGraduationDate;
	}

	public DegreeType getDegreeType() {
		return degreeType;
	}

	public void setDegreeType(DegreeType degreeType) {
		this.degreeType = degreeType;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSecondaryMajor() {
		return secondaryMajor;
	}

	public void setSecondaryMajor(String secondaryMajor) {
		this.secondaryMajor = secondaryMajor;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

}
