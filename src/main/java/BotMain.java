import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class BotMain extends TelegramLongPollingBot {

    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new BotMain());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);

        try {
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/help" -> sendMsg(message, "Бот в разработке");
                default -> sendMsg(message, "Я живой! Я работаю!");
            }
        }
    }

    @Override
    public String getBotUsername() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/bot.config"));
            List<String> linesList = reader.lines().collect(Collectors.toList());
            reader.close();
            return linesList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getBotToken() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/bot.config"));
            List<String> linesList = reader.lines().collect(Collectors.toList());
            reader.close();
            return linesList.get(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
