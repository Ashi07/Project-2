package com.niit.Dao;

import com.niit.model.ProfilePicture;

public interface ProfileDao {
	
	public void uploadProfilePicture(ProfilePicture profilePicture);
	public ProfilePicture getProfilePicture(String email);

}
