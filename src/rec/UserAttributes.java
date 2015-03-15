package rec;

import CustomClass.User;

public class UserAttributes
{
	public User user;
	public boolean flagWrongUser;
	public boolean flagWrongPassword;
	
	public UserAttributes (User user, boolean flagWrongUser, boolean flagWrongPassword)
	{
		this.user = user;
		this.flagWrongUser = flagWrongUser;
		this.flagWrongPassword = flagWrongPassword;
	}
	
	public User getUser () {
		return user;
	}
	
	public boolean getFlagWrongUser () {
		return flagWrongUser;
	}
	
	public boolean getFlagWrongPassword () {
		return flagWrongPassword;
	}
}
