import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import Telegram.Bot;
import brainngm.telegram_bot.config.TelegramBotConfig;

public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(final String[] args) {

		// initializeTelegramBot();

		try {

			TelegramBotConfig config = readConfig();

			LOGGER.info("starting application with configuration {}", config.toString());
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(new Bot(config.getName(), config.getToken()));

		} catch (Exception e) {

			System.exit(1);
		}
	}

	private static void initializeTelegramBot() {

		// try with resources is better, so you do not need a finally-bloc to close the Reader.
		try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/bot.config"))) {

			List<String> linesList = reader.lines().collect(Collectors.toList());
			reader.close();

			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(new Bot(linesList.get(0), linesList.get(1)));

		} catch (TelegramApiException | IOException e) {

			e.printStackTrace();
		}
	}

	private static TelegramBotConfig readConfig() {

		try (InputStream in = Main.class.getResourceAsStream("/Config.json")) {

			TelegramBotConfig config = new ObjectMapper().readValue(in, TelegramBotConfig.class);

			return config;

		} catch (Exception e) {

			LOGGER.error("could not deserialize configuration: ", e.getMessage(), e);
			throw new RuntimeException("could not deserialize configuration", e);
		}

	}
}
