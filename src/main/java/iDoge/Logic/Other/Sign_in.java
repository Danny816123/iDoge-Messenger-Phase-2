package iDoge.Logic.Other;

import com.fasterxml.jackson.databind.ObjectMapper;
import iDoge.Models.User;
import iDoge.Models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static iDoge.Logic.Other.Main_Menu.loadMainMenu;
import static iDoge.Models.Profile.loadProfile;

public class Sign_in {
    private static Logger log = LogManager.getLogger(Sign_in.class.getName());

    public static void userSaver(String username) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User(username);
        objectMapper.writeValue(new File("Database/Users/" + user.getUsername() + ".json"), user);
        log.info("User saved");
    }

    public static User userLoader(String username) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("User loaded");
        log.info("File opened");
        return objectMapper.readValue(new File("Database/Users/" + username + ".json"), User.class);
    }

    public static User signIn() throws IOException {
        Scanner beholder = new Scanner(System.in);
        a : while (true){
            System.out.println(ConsoleColors.PURPLE_BOLD + "WELCOME TO iDOGE!!!");
            System.out.println(ConsoleColors.YELLOW_BOLD + "Do you have an account? (yes/no)");
            log.info("WELCOME TO iDOGE!!!");
            log.info("Do you have an account? (yes/no)");
            if (beholder.nextLine().equals("yes")){
                while (true){
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your username:");
                    log.info("Enter your username:");
                    String username = beholder.nextLine();
                    try{
                        User user = userLoader(username);
                        if (user.isDeleted()){
                            System.out.println(ConsoleColors.RED_BOLD + username + " User doesn't exist... :(((");
                            log.error(username +" User doesn't exist... :(((");
                            continue;
                        }
                    }catch (IOException e){
                        System.out.println(ConsoleColors.RED_BOLD + username + " User doesn't exist... :(((");
                        log.error(username + " User doesn't exist... :(((");
                        continue;
                    }
                    User user = userLoader(username);
                    Profile profile = loadProfile(username);
                    while (true) {
                        System.out.println(ConsoleColors.YELLOW_BOLD + username + " Enter your password:");
                        String password = beholder.nextLine();
                        if (password.equals(profile.getPassword())){
                            System.out.println(ConsoleColors.PURPLE_BOLD + username + " Signed in successfully!!!");
                            log.info(username + " Enter your password:");
                            return user;
                        }else {
                            System.out.println(ConsoleColors.RED_BOLD + username + " Password is incorrect... :(((");
                            log.error(username + " Password is incorrect... :(((");
                        }
                    }
                }
            } else{
                while (true){
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your name");
                    log.info("Enter your name");
                    String name = beholder.nextLine();
                    while (true){
                        System.out.println(ConsoleColors.YELLOW_BOLD + "Enter your desired username:");
                        log.info("Enter your desired username:");
                        String username = beholder.nextLine();
                        boolean deleted = false;
                        try{
                            User user = userLoader(username);
                            if (!user.isDeleted()) {
                                System.out.println(ConsoleColors.RED_BOLD + username + " Username is already taken... :(((");
                                log.error(username + " Username is already taken... :(((");
                                continue;
                            }
                            deleted = true;
                            System.out.println(ConsoleColors.YELLOW_BOLD + username + "Username has been used before...");
                            log.info(username + " Username has been used before...");
                        }catch (IOException e){
                            deleted = false;
                        }
                        System.out.println(ConsoleColors.YELLOW_BOLD + username + " Enter your password:");
                        log.info(username + " Enter your password:");
                        String password = beholder.nextLine();
                        f : while (true){
                            System.out.println(ConsoleColors.YELLOW_BOLD + username + " Enter your email:");
                            log.info(username + " Enter your email:");
                            String email = beholder.nextLine();
                            try{
                                File dir = new File("Database/Users/");
                                File[] directoryListing = dir.listFiles();
                                if (directoryListing != null) {
                                    for (File child : directoryListing) {
                                        ObjectMapper objectMapper = new ObjectMapper();
                                        User user2 = objectMapper.readValue(child, User.class);
                                        log.info("File opened");
                                        if (loadProfile(user2.getUsername()).getEmail().equals(email)){
                                            System.out.println(ConsoleColors.RED_BOLD + email + " There's an account with this email... :(((");
                                            log.error(email + " There's an account with this email... :(((");
                                            log.info("File closed");
                                            continue f;
                                        }
                                        log.info("File closed");
                                    }
                                }
                            }catch (Exception e){
                                System.out.println(ConsoleColors.RED_BOLD + "An error occurred... :(((");
                                log.error("An error occurred... :(((");
                                continue;
                            }
                            try{
                                if (!deleted) {
                                    userSaver(username);
                                    Profile profile = new Profile(userLoader(username), name, password, email);
                                    profile.saveProfile();
                                } else {
                                    Profile profile = loadProfile(username);
                                    profile.getUser().setDeleted(false);
                                    profile.setOnline(true);
                                    profile.getUser().saveUser();
                                    profile.setName(name);
                                    profile.setPassword(password);
                                    profile.setEmail(email);
                                    profile.setID(profile.getUser().getUsername()+email+name);
                                    profile.setLastseen(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                                    profile.saveProfile();
                                }
                            }catch (IOException e){
                                System.out.println(ConsoleColors.RED_BOLD + "An error occurred... :(((");
                                log.error("An error occurred... :(((");
                                continue;
                            }
                            System.out.println(ConsoleColors.PURPLE_BOLD + username + " Account created successfully!!!");
                            log.info(username + " Account created successfully!!!");
                            continue a;
                        }
                    }
                }
            }
        }
    }

    public static void start() throws IOException {
        User myUser = signIn();
        Profile myProfile = loadProfile(myUser.getUsername());
        loadMainMenu(myProfile);
    }
}
