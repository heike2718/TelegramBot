import Telegram.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        initializeTelegramBot();
    }

    private static void initializeTelegramBot() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/bot.config"));
            List<String> linesList = reader.lines().collect(Collectors.toList());
            reader.close();

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot(linesList.get(0), linesList.get(1)));

        } catch (TelegramApiException | IOException e) {
            e.printStackTrace();
        }
    }
}
