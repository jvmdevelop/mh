using System.Text;
using System.Text.Json;
using MhFrontend.Models;

namespace MhFrontend.Services;

public class ApiService : IApiService
{
    private readonly HttpClient _client = new();
    private const string BaseUrl = "http://localhost:80";

    public async Task<AiMessage?> askBackend(UserMessage userMessage)
    {
        var options = new JsonSerializerOptions()
        {
            PropertyNamingPolicy = JsonNamingPolicy.CamelCase,
            PropertyNameCaseInsensitive = true
        };
        var result = await _client.PostAsJsonAsync($"{BaseUrl}/api/public/ask", userMessage, options);
        if (!result.IsSuccessStatusCode) return null;

        return await result.Content.ReadFromJsonAsync<AiMessage>();
    }
}