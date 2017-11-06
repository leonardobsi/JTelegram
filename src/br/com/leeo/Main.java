package br.com.leeo;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import br.com.leeo.jtelegram.CallbackQuery;
import br.com.leeo.jtelegram.LabeledPrice;
import br.com.leeo.jtelegram.Update;
import br.com.leeo.jtelegram.type.ReplyMarkup;
import br.com.leeo.jtelegram.type.Updates;

public class Main {

	private static final String BOT_TOKEN = "<TOKEN>";

	public static void main(String[] args) {

		Main main = new Main();

		long offset = 0;
		while (true) {

			Updates updates = main.getUpdates(offset);

			if (updates.getResult().size() > 0) {

				offset = updates.getResult().get(updates.getResult().size() - 1).getUpdate_id() + 1;

				for (Update update : updates.getResult()) {

					main.handle(update);
				}
			}
		}
	}

	public void handle(Update update) {

		// Where the party starts!
	}

	public void answerCallbackQuery(CallbackQuery callbackQuery, String text, boolean show_alert, String url,
			int cache_time) {

		try {

			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://api.telegram.org/bot"
					+ BOT_TOKEN + "/answerCallbackQuery?" + "callback_query_id=" + callbackQuery.getId() + "&text="
					+ text + "&show_alert=" + show_alert + "&url=" + url + "&cache_time=" + cache_time)
							.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setDoOutput(true);

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream());
			outputStreamWriter.flush();

			new JsonParser().parse(new InputStreamReader(httpsURLConnection.getInputStream()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Updates getUpdates(long offset) {

		try {

			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(
					"https://api.telegram.org/bot" + BOT_TOKEN + "/getUpdates?offset=" + offset).openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setDoOutput(true);

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream());
			outputStreamWriter.flush();

			JsonElement jsonElement = new JsonParser()
					.parse(new InputStreamReader(httpsURLConnection.getInputStream()));
			Updates updates = new Gson().fromJson(jsonElement, Updates.class);

			return updates;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param reply_markup ( Markdown | HTML)
	 * 
	 */
	public void sendMessage(String chat_id, String text, String parse_mode, boolean disable_web_page_preview,
			boolean disable_notification, int reply_to_message_id, ReplyMarkup reply_markup) {

		try {

			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://api.telegram.org/bot"
					+ BOT_TOKEN + "/sendMessage?reply_markup=" + new Gson().toJson(reply_markup) + "&parse_mode="
					+ parse_mode + "&chat_id=" + chat_id + "&text=" + URLEncoder.encode(text, "UTF-8"))
							.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setDoOutput(true);

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream());
			outputStreamWriter.flush();

			new JsonParser().parse(new InputStreamReader(httpsURLConnection.getInputStream()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendInvoice(String chat_id, String title, String description, String payload, String provider_token,
			String start_parameter, String currency, List<LabeledPrice> prices) {

		try {

			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://api.telegram.org/bot"
					+ BOT_TOKEN + "/sendInvoice" + "?chat_id=" + chat_id + "&title=" + title + "&description="
					+ description + "&payload=" + payload + "&provider_token=" + provider_token + "&start_parameter="
					+ start_parameter + "&currency=" + currency + "&prices=" + (new Gson().toJson(prices)))
							.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setDoOutput(true);

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream());
			outputStreamWriter.flush();

			new JsonParser().parse(new InputStreamReader(httpsURLConnection.getInputStream()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}