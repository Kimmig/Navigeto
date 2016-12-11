package com.navigeto.navigeto;

public class Config {
    //URLs to register.php and confirm.php file
    public static final String REGISTER_URL = "http://ec2-54-235-56-230.compute-1.amazonaws.com/register.php";
    public static final String CONFIRM_URL = "http://ec2-54-235-56-230.compute-1.amazonaws.com/confirm.php";
    public static final String LOGIN_URL = "http://ec2-54-235-56-230.compute-1.amazonaws.com/login.php";
    public static final String RESETPASS_URL = "http://ec2-54-235-56-230.compute-1.amazonaws.com/resetpassword.php";

    public static final String UPLOAD_URL = "http://ec2-54-235-56-230.compute-1.amazonaws.com/PicImages/uploadpicture.php";
    public static final String IMAGES_URL = "http://ec2-54-235-56-230.compute-1.amazonaws.com/PicImages/getImages.php";


        //Keys to send username, password, phone and otp
    public static final String KEY_USEREMAIL = "useremail";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_OTP = "otp";

    //JSON Tag from response from server
    public static final String TAG_RESPONSE= "ErrorMessage";
}
