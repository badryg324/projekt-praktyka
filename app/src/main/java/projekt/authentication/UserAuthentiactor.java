package projekt.authentication;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class UserAuthentiactor {
    public  boolean authenticate(String username, String password) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/user-list.txt"), StandardCharsets.UTF_8));
        String user;
        String userUsername;
        String userPassword;
        while ((user = bufferedReader.readLine()) !=null){
            userUsername = user.substring(0,user.indexOf(';'));
            if(username.equals(userUsername)){
                userPassword = user.substring(user.indexOf(';')+1);
                if(password.equals(userPassword)){
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
