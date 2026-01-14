using MhFrontend.Models;

namespace MhFrontend.Services;

public interface IApiService
{
    Task<AiMessage?> askBackend(UserMessage userMessage);
}