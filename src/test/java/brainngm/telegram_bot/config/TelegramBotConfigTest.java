// =====================================================
// Project: TelegramBot
// (c) Heike Winkelvoß
// =====================================================
package brainngm.telegram_bot.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TelegramBotConfigTest
 */
public class TelegramBotConfigTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(TelegramBotConfig.class);

	@Test
	void should_deserializeConfig() {

		// Arrange + Act
		try (InputStream in = getClass().getResourceAsStream("/ConfigTest.json")) {

			TelegramBotConfig config = new ObjectMapper().readValue(in, TelegramBotConfig.class);

			// Assert
			assertEquals("Железяка Бот", config.getName());
			assertEquals("secret", config.getToken());

		} catch (Exception e) {

			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	void should_serialize() throws JsonProcessingException {

		// This is helpful in order to generate the contents of a json-config-file
		// Arrange

		TelegramBotConfig config = new TelegramBotConfig();
		config.setName("Железяка Бот");
		config.setToken("secret");

		// Act
		String json = new ObjectMapper().writeValueAsString(config);

		LOGGER.info(json);

		assertEquals("{\"name\":\"Железяка Бот\",\"token\":\"secret\"}", json);

	}

}
