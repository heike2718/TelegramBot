// =====================================================
// Project: TelegramBot
// (c) Heike Winkelvoß
// =====================================================
package brainngm.telegram_bot.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TelegramBotConfig
 */
public class TelegramBotConfig {

	@JsonProperty
	private String name;

	@JsonProperty
	private String token;

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getToken() {

		return token;
	}

	public void setToken(final String token) {

		this.token = token;
	}

	@Override
	public String toString() {

		return "TelegramBotConfig [name=" + name + ", token=xxxxx]";
	}

}
