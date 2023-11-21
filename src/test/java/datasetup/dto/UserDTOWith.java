package datasetup.dto;

public class UserDTOWith {

    private String email = "No email";
    private String password = "No password";

    public String getEmail() {

        return email;
    }

    public String getPassword() {

        return password;
    }

    public UserDTOWith withEmail(String email) {

        if (email != null && !email.isEmpty())
            this.email = email;
        return this;
    }

    public UserDTOWith withPassword(String password) {

        if (password != null && !password.isEmpty())
            this.password = password;
        return this;
    }
}
