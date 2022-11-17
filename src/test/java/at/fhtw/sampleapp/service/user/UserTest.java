package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void testUserServiceGetCompleteList() throws Exception {
        URL url = new URL("http://localhost:10001/user");
        URLConnection urlConnection = url.openConnection();

        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            //Weather[] weatherList = new ObjectMapper().readValue(bufferedReader.readLine(), Weather[].class);
            //assertEquals(3, weatherList.length);
            List<User> userList = new ObjectMapper().readValue(bufferedReader.readLine(), new TypeReference<List<User>>(){});
            assertEquals(3, userList.size());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        bufferedReader.close();
    }
    @Test
    void testUserServiceGetById() throws Exception {
        URL url = new URL("http://localhost:10001/user/1");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            User user = new ObjectMapper().readValue(bufferedReader.readLine(), User.class);
            assertEquals(1, user.getUser_id());
            assertEquals("kienboec", user.getUser_name());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        bufferedReader.close();
    }

    @Test
    void testUserServiceRegister() throws Exception {
        URL url = new URL("http://localhost:10001/");
    }
}
