package Telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Bot extends TelegramLongPollingBot {

	@Getter
	private final String botUsername;

	@Getter
	private final String botToken;

	// Метод для отправки сообщений
	public void sendMessage(final Message message, final String text) {

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
	public void onUpdateReceived(final Update update) {

		Message message = update.getMessage();

		if (message != null && message.hasText()) {

			switch (message.getText()) {

			case "/help" -> sendMessage(message, "Бот в разработке");
			default -> sendMessage(message, "Я живой! Я работаю!");
			}
		}
	}

	@Override
	public String getBotUsername() {

		return this.botUsername;
	}

	@Override
	public String getBotToken() {

		return this.botToken;
	}
}
