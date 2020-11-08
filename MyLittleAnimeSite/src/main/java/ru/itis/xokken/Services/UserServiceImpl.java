package ru.itis.xokken.Services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.xokken.Entity.User;
import ru.itis.xokken.Repositories.UserRepImp;
import ru.itis.xokken.Repositories.UserRepository;
import ru.itis.xokken.dto.UserDTO;


public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl(new UserRepImp(), new BCryptPasswordEncoder());
        UserDTO user = UserDTO.builder()
                .email("xokkeuct12832@gmail.com")
                .age(15)
                .password("123321")
                .name("Алексей Дьяконов")
                .build();
        System.out.println(userService.add(user));
        System.out.println(user.toString());
    }

    @Override
    public boolean add(UserDTO userDTO) {
        String hashPassword = passwordEncoder.encode(userDTO.getPassword());
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(hashPassword)
                .age(userDTO.getAge())
                .img("/hostImg/default.jpg")
                .build();
        if (userRepository.addUser(user)){
            return true;
        }
        return false;
    }

    public UserDTO checkUser(UserDTO userDTO){
        String hashPassword = passwordEncoder.encode(userDTO.getPassword());
        User user = userRepository.findUserByEmail(userDTO.getEmail());
        if (user == null) {
            return null;
        }
        UserDTO userDTO1 = returnUserDTO(user);
        if (passwordEncoder.matches(userDTO.getPassword(), userDTO1.getPassword())) {
            return userDTO1;
        }
        return null;
    }


    public void updateImg(UserDTO userDTO, String path) {
        User user = userRepository.findUserByEmail(userDTO.getEmail());
        userRepository.updateImg(user, path);
    }



    @Override
    public boolean update(UserDTO entity, String password) {
        return false;
    }


    private UserDTO returnUserDTO(User user){
        UserDTO userDTO = UserDTO.builder()
                .name(user.getName())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .img(user.getImg())
                .law(user.isLaw())
                .build();
        return userDTO;
    }
}
