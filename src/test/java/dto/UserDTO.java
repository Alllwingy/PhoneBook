package dto;

public class UserDTO {

    private String email = "No email";
    private String password = "No password";

    public UserDTO(String email, String password) {

        setEmail(email);
        setPassword(password);
    }

    public String email() {

        return email;
    }

    public void setEmail(String email) {

        if (email != null && !email.isEmpty())
            this.email = email;
    }

    public String password() {

        return password;
    }

    public void setPassword(String password) {

        if (password != null && !password.isEmpty())
            this.password = password;
    }
}
