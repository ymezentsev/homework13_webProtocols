import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.Post;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;

public class Task2Service {

    private final String userUri;
    private final HttpClient client;

    public Task2Service(String userUri, HttpClient client) {
        this.userUri = userUri;
        this.client = client;
    }

    public void getAllUsersCommentsToLastPost(int userId) {
        Post post = getLastPost(userId);
        getAllComments(post.getId(), userId);
    }

    private Post getLastPost(int userId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(userUri + "/" + userId + "/posts"))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

        Type typeToken = TypeToken.getParameterized(List.class, Post.class).getType();
        Gson gson = new Gson();
        List<Post> postList = gson.fromJson(response.body(), typeToken);

        return postList.stream()
                .max(Comparator.comparingInt(Post::getId)).orElseThrow();
    }

    private void getAllComments(int postId, int userId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/" + postId + "/comments"))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(response.body());


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter
                ("user-" + userId + "-post-" + postId + "-comments.json"))){
            bufferedWriter.write(response.body());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
