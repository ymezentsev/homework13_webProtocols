import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.Todos;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Task3Service {

    private final String userUri;
    private final HttpClient client;

    public Task3Service(String userUri, HttpClient client) {
        this.userUri = userUri;
        this.client = client;
    }

    public void getAllOpenTasks(int userId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(userUri + "/" + userId + "/todos"))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(response);

        Type typeToken = TypeToken.getParameterized(List.class, Todos.class).getType();

        Gson gson = new Gson();
        List<Todos> todosList = gson.fromJson(response.body(), typeToken);

        todosList.stream()
                .filter(it -> !it.isCompleted())
                .forEach(System.out::println);
    }
}
