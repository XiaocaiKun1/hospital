package his2.neusoft.neu24.his3.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ChatModelService {

    private static final String API_URL = "https://api.siliconflow.cn/v1/chat/completions";
    private static final String API_KEY = "sk-phjenofgrguxeunjafdjustkcflurnpxclzrszjhfaazcmph"; // 替换为实际的 API 密钥

    public void sendMessage(List<String> conversationHistory, ResponseHandler responseHandler) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            // 构建消息列表
            StringBuilder messagesBuilder = new StringBuilder("[");
            for (String msg : conversationHistory) {
                String role = msg.startsWith("用户") ? "user" : "assistant";
                messagesBuilder.append("{\"role\":\"").append(role).append("\",\"content\":\"").append(msg.substring(msg.indexOf(":") + 2)).append("\"},");
            }
            messagesBuilder.deleteCharAt(messagesBuilder.length() - 1).append("]");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(
                            "{\"model\":\"deepseek-ai/DeepSeek-R1-0528-Qwen3-8B\",\"stream\":false,\"max_tokens\":512,\"messages\":" + messagesBuilder.toString() + "}"))
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(responseBody -> {
                        // 提取模型回答
                        if (responseBody != null && !responseBody.isEmpty()) {
                            // 简单解析模型的回答
                            int start = responseBody.indexOf("\"content\":\"") + 11;
                            int end = responseBody.indexOf("\"", start);
                            String answer = responseBody.substring(start, end).replace("\\n", "");
                            responseHandler.handleResponse(answer);
                        }
                    })
                    .exceptionally(ex -> {
                        responseHandler.handleResponse("模型调用失败：" + ex.getMessage());
                        return null;
                    });

        } catch (Exception e) {
            responseHandler.handleResponse("模型调用失败：" + e.getMessage());
        }
    }

    @FunctionalInterface
    public interface ResponseHandler {
        void handleResponse(String response);
    }
}