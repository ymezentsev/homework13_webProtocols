import com.google.gson.Gson;
import dto.Address;
import dto.Company;
import dto.Geo;
import dto.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Task1Service {
    private final String userUri;
    private final HttpClient client;

    public Task1Service(String userUri, HttpClient client) {
        this.userUri = userUri;
        this.client = client;
    }

    public void createNewUser() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(getDefaultUser());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(userUri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        getAndPrintResponse(request);
    }

    public void updateUser(int userIdForUpdate) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(getDefaultUser());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(userUri + "/" + userIdForUpdate))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        getAndPrintResponse(request);
    }

    public void deleteUser(int userIdForDelete) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(userUri + "/" + userIdForDelete))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        getAndPrintResponse(request);
    }

    public void getAllUsers() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(userUri))
                .GET()
                .build();

        getAndPrintResponse(request);
    }

    public void getUserById(int userId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(userUri + "/" + userId))
                .GET()
                .build();

        getAndPrintResponse(request);
    }

    public void getUserByUsername(String username) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(userUri + "?username=" + username))
                .GET()
                .build();

        getAndPrintResponse(request);
    }

    private void getAndPrintResponse(HttpRequest request) {
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(response);
        System.out.println(response.body());
    }

    private User getDefaultUser() {
        Company newCompany = Company.builder()
                .name("Romaguera-Crona")
                .catchPhrase("Multi-layered client-server neural-net")
                .bs("harness real-time e-market")
                .build();

        Geo newGeo = Geo.builder()
                .lat("-37.3159")
                .lng("81.1496")
                .build();

        Address newAddress = Address.builder()
                .street("Kulas Light")
                .suite("Apt. 556")
                .city("Gwenborough")
                .zipcode("92998-3874")
                .geo(newGeo)
                .build();

        return User.builder()
                .id(10)
                .name("Test_update")
                .username("Test")
                .email("test@test.com")
                .address(newAddress)
                .phone("1-770-736-8031 x56442")
                .website("hildegard.org")
                .company(newCompany)
                .build();
    }
}
