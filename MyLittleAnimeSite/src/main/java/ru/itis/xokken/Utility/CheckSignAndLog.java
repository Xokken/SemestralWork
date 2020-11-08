package ru.itis.xokken.Utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.xokken.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckSignAndLog {
    private String emailSign;
    private String passwordSignOne;
    private String passwordSignTwo;
    private String name;
    private int age = -1;
    private String emailLog;
    private String passwordLog;
    private Pattern patternOne = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
    private Pattern patternTwo = Pattern.compile("^[a-zA-Z0-9]{6,}");

    public CheckSignAndLog(HttpServletRequest request) {
        this.emailSign = request.getParameter("inputES");
        this.passwordSignOne = request.getParameter("inputPSOne");
        this.passwordSignTwo = request.getParameter("inputPST");
        this.name = request.getParameter("inputNS");
        if ((request.getParameter("inputA")) != null) {
            this.age = Integer.parseInt(request.getParameter("inputA"));
        }
        this.emailLog = request.getParameter("inputEmailLog");
        this.passwordLog = request.getParameter("inputPL");
    }

    public boolean checkCorrect(){
        Matcher matcherOne;
        Matcher matcherTwo;
        if (passwordLog == null) {
            matcherOne = patternOne.matcher(emailSign);
            matcherTwo = patternTwo.matcher(passwordSignOne);
            if ((passwordSignOne.equals(passwordSignTwo)) && (matcherOne.find() && matcherTwo.find())) {
                return true;
            }
            else {
                return false;
            }
        }
        else{
            matcherOne = patternOne.matcher(emailLog);
            matcherTwo = patternTwo.matcher(passwordLog);
            if (matcherOne.find() && matcherTwo.find()){
                return true;
            }
            else {
                return false;
            }
        }
    }

    public UserDTO returnUserDTO(){
        UserDTO userDTO;
        if ((emailLog != null)){
            userDTO = UserDTO.builder()
                    .email(emailLog)
                    .password(passwordLog)
                    .build();
        }
        else {
            userDTO = UserDTO.builder()
                    .name(name)
                    .password(passwordSignOne)
                    .email(emailSign)
                    .age(age)
                    .img("/hostImg/default.jpg")
                    .build();
        }
        return userDTO;
    }
}
