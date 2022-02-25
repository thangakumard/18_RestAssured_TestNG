import com.google.gson.Gson;
import entity.User;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Random;

public class SampleTest {

    private static HttpRequestUtility httpRequestUtility = null;

    @BeforeSuite
    public void setBaseUrl(){
        httpRequestUtility = new HttpRequestUtility("http://localhost:3000");

    }

    @Test
    public void getAPIRequest_test(){
        Response response = httpRequestUtility.sendGetRequest("users", ContentType.APPLICATION_JSON.toString(), null, null, null);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.getBody().prettyPrint().length() > 0);
    }

    @Test
    public void postAPIRequest_test(){
        Random rad = new Random();
        User user = new User();
        String id = String.valueOf(rad.nextInt(1000));
        user.id(id);
        user.firstName("firstName_" + id);
        user.lastName("lastName_" + id);
        user.email("email_" + id + "@email.com");
        user.age(rad.nextInt());
        user.companyId(id);

        Gson gson = new Gson();
        String requestBody = gson.toJson(user);

        Response response = httpRequestUtility.sendPostRequest("users" , ContentType.APPLICATION_JSON.toString(), null, null, requestBody, null);
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertTrue(response.getBody().prettyPrint().length() > 0);
    }

    @Test
    public void putAPIRequest_test(){
        Random rad = new Random();
        User user = new User();
        String id = String.valueOf(rad.nextInt(1000));
        user.id("1");
        user.firstName("firstName_" + id);
        user.lastName("lastName_" + id);
        user.email("email_" + id + "@email.com");
        user.age(rad.nextInt());
        user.companyId("1");

        Gson gson = new Gson();
        String requestBody = gson.toJson(user);

        Response response = httpRequestUtility.sendPutRequest("users/1" , null, requestBody);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.getBody().prettyPrint().length() > 0);
    }

    @Test
    public void deleteAPIRequest_test() {
        Random rad = new Random();
        User user = new User();
        String id = String.valueOf(rad.nextInt(1000));
        user.id(id);
        user.firstName("firstName_" + id);
        user.lastName("lastName_" + id);
        user.email("email_" + id + "@email.com");
        user.age(rad.nextInt());
        user.companyId(id);

        Gson gson = new Gson();
        String requestBody = gson.toJson(user);

        //Creating a user record
        Response response = httpRequestUtility.sendPostRequest("users" , ContentType.APPLICATION_JSON.toString(), null, null, requestBody, null);
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertTrue(response.getBody().prettyPrint().length() > 0);

        //deleting the new user record
        response = httpRequestUtility.sendDeleteRequest("users/"+id , null, null, null, null, null);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.getBody().prettyPrint().length() > 0);
    }
}
