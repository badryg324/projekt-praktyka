package projekt.authentication;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class UserAuthentiactor {
    public  boolean isAdmin;
    public  boolean authenticate(String username, String password) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/user-list.txt"), StandardCharsets.UTF_8));
        String user;
        String userUsername;
        String userPassword;
        isAdmin = false;
        while ((user = bufferedReader.readLine()) !=null){
            userUsername = user.substring(0,user.indexOf(';'));
            user = user.substring(user.indexOf(';')+1);
            if(username.equals(userUsername)){
                userPassword = user.substring(0,user.indexOf(';'));
                if(password.equals(userPassword)){
                    user = user.substring(user.indexOf(';')+1);
                    if(user.equals("true")){
                        isAdmin=true;
                    }
                    bufferedReader.close();
                    return true;
                }
                bufferedReader.close();
                return false;
            }
        }
        bufferedReader.close();
        return false;
    }
}
