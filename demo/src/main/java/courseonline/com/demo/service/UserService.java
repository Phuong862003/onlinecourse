package courseonline.com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import courseonline.com.demo.enity.Login;
import courseonline.com.demo.enity.ScrapedData;
import courseonline.com.demo.enity.User;
import courseonline.com.demo.enity.UserDto;
import courseonline.com.demo.respository.LoginRespostory;
import courseonline.com.demo.respository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRespostory loginRespostory;


    
    public void register(UserDto userDto) throws Exception{
        User existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser != null) {
            throw new Exception("Tài khoản email đã tồn tại!");
        }

        Login login = loginRespostory.findByUsername(userDto.getUsername());
        if (login != null) {
            throw new Exception("Username đã tồn tại!");
        }

        Login newLogin = new Login();
        newLogin.setUsername(userDto.getUsername());
        newLogin.setPassword(userDto.getPassword());
        newLogin.setRole("customer");
        loginRespostory.save(newLogin);

        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setEmail(userDto.getEmail());
        newUser.setLogin(newLogin);
        userRepository.save(newUser);
    }

    public User getUserByLoginId(int loginId) {
        return userRepository.findByLoginId(loginId);
    }

    public User getUserById(int id){
        return userRepository.findById(id).get();
    }

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void delete(int id){
        userRepository.deleteById(id);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public static boolean isNotBlank(String str) {
        return str != null && str.trim().length() > 0;
    }

    public List<User> search(String keyword) {
        if (keyword != null && !keyword.isBlank()) {
            List<User> users = userRepository.findByNameContaining(keyword);
            for (User user : users) {
                String imageUrl = user.getImage();
                if (isNotBlank(imageUrl)) {
                    user.setImage(processImageUrl(imageUrl));
                } else {
                    imageUrl = "https://znews-photo.zingcdn.me/w660/Uploaded/natmts/2023_02_03/z4080571344877_dcc05eb033d9e910039ad77df6eb1b05.jpg";
                }
                
                user.setImage(imageUrl);
            }
            return users;
        } else {
            List<User> users = userRepository.findAll();
            for (User user : users) {
                String imageUrl = user.getImage();
                if (isNotBlank(imageUrl)) {
                    user.setImage(processImageUrl(imageUrl));
                } else {
                    imageUrl = "https://znews-photo.zingcdn.me/w660/Uploaded/natmts/2023_02_03/z4080571344877_dcc05eb033d9e910039ad77df6eb1b05.jpg";
                }
                user.setImage(imageUrl);
            }
            return users;
        }

    }

    private String processImageUrl(String imageUrl) {
        return null;
    }

    public User getById(int id){
        return userRepository.findById(id).get();
    }


    public List <User> getAll(){
       List<User> users= userRepository.findAll();
       for (User user : users) {
                String imageUrl = user.getImage();
                if (isNotBlank(imageUrl)) {
                    user.setImage(processImageUrl(imageUrl));
                } else {
                    imageUrl = "https://znews-photo.zingcdn.me/w660/Uploaded/natmts/2023_02_03/z4080571344877_dcc05eb033d9e910039ad77df6eb1b05.jpg";
                }
                user.setImage(imageUrl);
            }
            return users;
    }

}
