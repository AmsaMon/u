package models;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.joda.time.DateTime;

import play.db.jpa.Model;
@Entity
@Table(name="profiles")
public class Profile extends Model {
	@OneToOne
    @JoinColumn(name = "user_id")
	private User user;
	// personal information
	private Date dateOfBirth;
	private String address;
	private String address2;
	private String city;
	private String state;
	private String postalCode;
	// TODO add country when it is no longer in USA
	//private String country;
	private String phone;
	private String emailCollege;
	private String emailPersonal;
	// TODO add profile picture?
	//private Picture profilePhoto;
	public enum ApplicationStatus {
		None, Pending, Holding, Accepted, Rejected; 
	}
	@Enumerated(EnumType.STRING)
	private ApplicationStatus applicationStatus;
	
	@Lob
	@Column(columnDefinition = "TEXT")
	private String reasonForApplication;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmailCollege() {
		return emailCollege;
	}
	public void setEmailCollege(String emailCollege) {
		this.emailCollege = emailCollege;
	}
	public String getEmailPersonal() {
		return emailPersonal;
	}
	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}
	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getReasonForApplication() {
		return reasonForApplication;
	}
	public void setReasonForApplication(String reasonForApplication) {
		this.reasonForApplication = reasonForApplication;
	}
}
