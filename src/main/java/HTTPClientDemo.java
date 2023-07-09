import java.net.http.HttpClient;

public class HTTPClientDemo {

    public static final String USER_URI = "https://jsonplaceholder.typicode.com/users";
    public static final HttpClient CLIENT = HttpClient.newBuilder().build();

    public static void main(String[] args) {
//        Task1Service task1Service = new Task1Service(USER_URI, CLIENT);
//        task1Service.createNewUser();
//        task1Service.updateUser(1);
//        task1Service.deleteUser(1);
//        task1Service.getAllUsers();
//        task1Service.getUserById(1);
//        task1Service.getUserByUsername("Samantha");


//        Task2Service task2Service = new Task2Service(USER_URI, CLIENT);
//        task2Service.getAllUsersCommentsToLastPost(4);


        Task3Service task3Service = new Task3Service(USER_URI, CLIENT);
        task3Service.getAllOpenTasks(1);

    }
}
