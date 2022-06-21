package vn.thphong.manager.utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    // định dạng userName : chỉ đc bắt đầu bằng chữ hoặc số, tối thiểu 3 đến 20 ký tự( chỉ chứa số, chữ số, dấu chấm và dấu gạch ngang dưới)
    public static final String USERNAME_PATTERN = "^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    // đinh dạng password: Phải bắt đầu bằng chữ in hoa(chỉ chứa chữ và số, tối thiểu 8-24 ký tự
    public static final String PASSWORD_PATTERN = "^([A-Z][a-z0-9]{8,24})";

    public static final String FULLNAME_REGEX = "^([A-Z]+[a-z]*[ ]?)+$";
    public static final String PHONE_REGEX = "^[0][1-9][0-9]{8}$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._]+@[a-z]+\\.[a-z]{2,3}$";
    public static final String ADDRESS_REGEX = "^\\d+( ?\\w+ ?[\\,\\-\\/]*)+$";

    public static boolean isPasswordValid(String password) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    public static boolean isUsernameValid(String userName) {
        return Pattern.compile(USERNAME_PATTERN).matcher(userName).matches();
    }

    public static boolean isFullNameValid(String fullName) {
        return Pattern.compile(FULLNAME_REGEX).matcher(fullName).matches();
    }

    public static boolean isPhoneValid(String number) {
        return Pattern.compile(PHONE_REGEX).matcher(number).matches();
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public static boolean isAddressValid(String address) {
        return Pattern.compile(ADDRESS_REGEX).matcher(address).matches();
    }
}

