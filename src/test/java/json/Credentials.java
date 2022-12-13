package json;

import org.apache.commons.lang3.RandomStringUtils;

public class Credentials {
    private String login;
    private String password;

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Credentials() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Credentials from(Courier courier) {
        return new Credentials(courier.getLogin(), courier.getPassword());
    }

    public static Credentials random() {
        return new Credentials(RandomStringUtils.randomAlphabetic(7), RandomStringUtils.randomAlphabetic(7));
    }
}
