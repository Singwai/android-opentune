package co.opentune.android.Utility;

/**
 * Created by Singwai on 5/5/15.
 * This does all the client side checking for sign up, sign in and possible update user info, if exist
 */
public class InputValidator {

    //6 or more characters.
    private static final String PASSWORD_REGEX = ".{6,}";

    //todo add validName regex.
    public static boolean isValidName (String name){
        //todo please check what is consider a valid name.
        return true;
    }

    public static boolean isValidEmail (String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public static boolean isValidPassword (String password){
        return password.matches(PASSWORD_REGEX);
    }

}
