package teapot;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CookieTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "941xk5NwTMBbAuvw33gx%2BzuX0XnBFF5KdX5fcooRb%2BLfUxcsz%2FT8KxDkPKJ6jwXAGINKh0vZRNecVJtoz33HTD6i2UYfYa7h7DIcKHtRXuXH9tRbjgk1fxPH8eX3QmBVLndEvFE0SIN68OoMdMy0PtcCkST3Ci4VB8ewQDpZvJM%3D";
        String msg =URLDecoder.decode(str, "UTF-8");
        System.out.println(msg);

    }

}
