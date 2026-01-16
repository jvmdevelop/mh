using System.Net;
using System.Text.Json;
using MhFrontend.Models;

namespace MhFrontend.Services;

public partial class ApiService(ILogger<IApiService> logger) : IApiService
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
        LogBackendApiResponseStatuscode(result.StatusCode);  
        return await result.Content.ReadFromJsonAsync<AiMessage>();
    }

    [LoggerMessage(LogLevel.Information, "Backend API response: {StatusCode}")]
    partial void LogBackendApiResponseStatuscode(HttpStatusCode StatusCode);
}