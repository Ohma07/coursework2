package utils;

public class Validator {

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
}
