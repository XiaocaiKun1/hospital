package his2.neusoft.neu24.his3.controller;

import his2.neusoft.neu24.his3.service.ChatModelService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.application.Platform;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;

public class ChatController {

    @FXML
    private ListView<Label> messageListView;

    @FXML
    private TextField userInput;

    private ChatModelService chatModelService = new ChatModelService();
    private List<String> conversationHistory = new ArrayList<>();
    String pre_word="请你扮演NEU东软云医院的智能助手，当用户提出问题时，你只回答与 NEU 东软云医院相关的挂号、收费、医生、发药等医疗服务及医院内部事务问题，对于其他与本医院无关的问题，你拒绝回答，只专注为患者提供本医院相关服务咨询。" +
            "同时回答时不要使用文本无法显示的特殊符号或表情，尽量简洁具体，用一句话概括";

    @FXML
    public void initialize() {
        // 初始化对话历史
        conversationHistory.add(pre_word);
//        updateMessageList();
    }

    @FXML
    private void sendMessage() {
        String message = userInput.getText().trim();

        if (message.isEmpty()) {
            return;
        }

        // 将用户消息添加到列表
        conversationHistory.add("用户: " + message);
        updateMessageList();

        // 清空输入框
        userInput.clear();

        // 调用大模型 API
        chatModelService.sendMessage(conversationHistory, this::handleModelResponse);
    }

    private void handleModelResponse(String modelResponse) {
        if (modelResponse != null && !modelResponse.isEmpty()) {
            conversationHistory.add("助手: " + modelResponse);
            updateMessageList();
        }
    }

    private void updateMessageList() {
        Platform.runLater(() -> {
            messageListView.getItems().clear();
            conversationHistory.forEach(msg -> {
                // 过滤掉初始化提示信息
                if (!msg.equals(pre_word)) {
                    Label label = new Label(msg);
                    label.setWrapText(true);
                    label.setStyle("-fx-font-size: 16px;");
                    messageListView.getItems().add(label);
                }
            });
        });
    }
}