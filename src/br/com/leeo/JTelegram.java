package br.com.leeo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import br.com.leeo.jtelegram.CallbackQuery;
import br.com.leeo.jtelegram.LabeledPrice;
import br.com.leeo.jtelegram.PreCheckoutQuery;
import br.com.leeo.jtelegram.Update;
import br.com.leeo.jtelegram.type.ReplyMarkup;
import br.com.leeo.jtelegram.type.Updates;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public abstract class JTelegram {

	private long offset = 0;
	private final String BOT_TOKEN;

	public JTelegram(String pBotToken) {

		BOT_TOKEN = pBotToken;
	}

	public void loadUpdates() {

		try {
			Updates updates = getUpdates(offset);

			if (updates.getResult().size() > 0) {

				offset = updates.getResult().get(updates.getResult().size() - 1).getUpdate_id() + 1;

				for (Update update : updates.getResult()) {

					handle(update);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public abstract void handle(Update update);

	public void answerCallbackQuery(CallbackQuery callbackQuery, String text, Boolean show_alert, String url, Integer cache_time) {

		try {

			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://api.telegram.org/bot" + BOT_TOKEN + "/answerCallbackQuery?"
					+ "callback_query_id=" + callbackQuery.getId() + "&text=" + URLEncoder.encode(text, "UTF-8") + "&show_alert="
					+ show_alert.booleanValue() + "&url=" + url + "&cache_time=" + cache_time.intValue()).openConnection();

			httpsURLConnection.setRequestMethod("GET");

			httpsURLConnection.getResponseCode();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Updates getUpdates(long offset) {

		try {

			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://api.telegram.org/bot" + BOT_TOKEN + "/getUpdates?offset="
					+ offset).openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setDoOutput(true);

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream());
			outputStreamWriter.flush();

			JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(httpsURLConnection.getInputStream()));

			Updates updates = new Gson().fromJson(jsonElement, Updates.class);

			return updates;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param reply_markup
	 *            ( Markdown | HTML)
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 * 
	 */
	public void sendMessage(String chat_id, String text, String parse_mode, ReplyMarkup reply_markup) throws Exception {

		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://api.telegram.org/bot" + BOT_TOKEN
				+ "/sendMessage?reply_markup=" + URLEncoder.encode(new Gson().toJson(reply_markup), "UTF-8") + "&parse_mode=" + parse_mode
				+ "&chat_id=" + chat_id + "&text=" + URLEncoder.encode(text, "UTF-8")).openConnection();

		httpsURLConnection.setRequestMethod("GET");

		httpsURLConnection.getResponseCode();
	}

	public void setChatDescription(String chat_id, String description) throws Exception {

		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://api.telegram.org/bot" + BOT_TOKEN
				+ "/setChatDescription?chat_id=" + chat_id + "&description=" + URLEncoder.encode(description, "UTF-8")).openConnection();

		httpsURLConnection.setRequestMethod("GET");

		httpsURLConnection.getResponseCode();
	}

	public void answerPreCheckoutQuery(PreCheckoutQuery pre_checkout_query, Boolean ok, String error_message) throws Exception {

		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://api.telegram.org/bot" + BOT_TOKEN
				+ "/answerPreCheckoutQuery?pre_checkout_query_id=" + pre_checkout_query.getId() + "&ok=" + ok.booleanValue() + "&error_message="
				+ URLEncoder.encode(error_message, "UTF-8")).openConnection();

		httpsURLConnection.setRequestMethod("GET");

		httpsURLConnection.getResponseCode();
	}

	public void sendInvoice(String chat_id, String title, String description, String payload, String provider_token, String start_parameter,
			String currency, List<LabeledPrice> prices, URL photo_url) throws Exception {

		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://api.telegram.org/bot" + BOT_TOKEN + "/sendInvoice"
				+ "?chat_id=" + chat_id + "&title=" + title + "&photo_url=" + URLEncoder.encode(photo_url.toString(), "UTF-8") + "&description="
				+ description + "&payload=" + payload + "&provider_token=" + provider_token + "&start_parameter=" + start_parameter + "&currency="
				+ currency + "&prices=" + (new Gson().toJson(prices))).openConnection();

		httpsURLConnection.setRequestMethod("GET");

		httpsURLConnection.getResponseCode();
	}

	public void sendPhoto(String chat_id, URL photo, ReplyMarkup reply_markup) throws Exception {

		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://api.telegram.org/bot" + BOT_TOKEN + "/sendPhoto?chat_id="
				+ chat_id + "&disable_notification=true&photo=" + URLEncoder.encode(photo.toString(), "UTF-8") + "&reply_markup="
				+ URLEncoder.encode(new Gson().toJson(reply_markup), "UTF-8")).openConnection();

		httpsURLConnection.setRequestMethod("GET");

		httpsURLConnection.getResponseCode();
	}
}