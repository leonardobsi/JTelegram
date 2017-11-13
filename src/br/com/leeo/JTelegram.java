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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public abstract class JTelegram {

	public enum ParseMode {
		Markdown, HTML;
	}

	public enum Currency {
		BRL;
	}

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

	public void deleteMessage(CallbackQuery callbackQuery) {

		try {

			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(
					"https://api.telegram.org/bot<token>/deleteMessage?chat_id=p{chat_id}&message_id=p{message_id}".replace("<token>", BOT_TOKEN)
							.replace("p{chat_id}", callbackQuery.getMessage().getChat().getId())
							.replace("p{message_id}", callbackQuery.getMessage().getMessage_id())).openConnection();

			httpsURLConnection.setRequestMethod("GET");

			httpsURLConnection.getResponseCode();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editMessageReplyMarkup(CallbackQuery callbackQuery, ReplyMarkup reply_markup) {

		try {

			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(
					"https://api.telegram.org/bot<token>/editMessageReplyMarkup?chat_id=p{chat_id}&message_id=p{message_id}&reply_markup=p{reply_markup}"
							.replace("<token>", BOT_TOKEN).replace("p{chat_id}", callbackQuery.getMessage().getChat().getId())
							.replace("p{message_id}", callbackQuery.getMessage().getMessage_id())
							.replace("p{reply_markup}", URLEncoder.encode(new Gson().toJson(reply_markup), "UTF-8"))).openConnection();

			httpsURLConnection.setRequestMethod("GET");

			httpsURLConnection.getResponseCode();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editMessageText(CallbackQuery callbackQuery, String text, ParseMode parse_mode, ReplyMarkup reply_markup) {

		try {

			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(
					"https://api.telegram.org/bot<token>/editMessageText?chat_id=p{chat_id}&message_id=p{message_id}&text=p{text}&parse_mode=p{parse_mode}&reply_markup=p{reply_markup}"
							.replace("<token>", BOT_TOKEN).replace("p{chat_id}", callbackQuery.getMessage().getChat().getId())
							.replace("p{message_id}", callbackQuery.getMessage().getMessage_id())
							.replace("p{text}", URLEncoder.encode(text, "UTF-8")).replace("p{parse_mode}", parse_mode.toString())
							.replace("p{reply_markup}", URLEncoder.encode(new Gson().toJson(reply_markup), "UTF-8"))).openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.getResponseCode();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void answerCallbackQuery(CallbackQuery callbackQuery, String text, Boolean show_alert) {

		try {

			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(
					"https://api.telegram.org/bot<token>/answerCallbackQuery?callback_query_id=p{callback_query_id}&text=p{text}&show_alert=p{show_alert}"
							.replace("<token>", BOT_TOKEN).replace("p{callback_query_id}", callbackQuery.getId())
							.replace("p{show_alert}", show_alert.toString()).replace("p{text}", URLEncoder.encode(text, "UTF-8"))).openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.getResponseCode();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Updates getUpdates(long offset) {

		try {

			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://api.telegram.org/bot<token>/getUpdates?offset=p{offset}"
					.replace("<token>", BOT_TOKEN).replace("p{offset}", Long.toString(offset))).openConnection();

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
	public void sendMessage(String chat_id, String text, ParseMode parse_mode, ReplyMarkup reply_markup) throws Exception {

		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(
				"https://api.telegram.org/bot<token>/sendMessage?chat_id=p{chat_id}&parse_mode=p{parse_mode}&text=p{text}&reply_markup=p{reply_markup}"
						.replace("<token>", BOT_TOKEN).replace("p{chat_id}", chat_id).replace("p{parse_mode}", parse_mode.toString())
						.replace("p{text}", URLEncoder.encode(text, "UTF-8"))
						.replace("p{reply_markup}", URLEncoder.encode(new Gson().toJson(reply_markup), "UTF-8"))).openConnection();

		httpsURLConnection.setRequestMethod("GET");

		httpsURLConnection.getResponseCode();
	}

	public void answerPreCheckoutQuery(PreCheckoutQuery pre_checkout_query, Boolean ok) throws Exception {

		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(
				"https://api.telegram.org/bot<token>/answerPreCheckoutQuery?pre_checkout_query_id=p{pre_checkout_query_id}&ok=p{ok}"
						.replace("<token>", BOT_TOKEN).replace("p{pre_checkout_query_id}", pre_checkout_query.getId())
						.replace("p{ok}", ok.toString())).openConnection();

		httpsURLConnection.setRequestMethod("GET");
		httpsURLConnection.getResponseCode();
	}

	public void sendInvoice(String chat_id, String title, String description, String payload, String provider_token, String start_parameter,
			Currency currency, List<LabeledPrice> prices, URL photo_url) throws Exception {

		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(
				"https://api.telegram.org/bot<token>/sendInvoice?chat_id=p{chat_id}&title=p{title}&photo_url=p{photo_url}&description=p{description}&payload=p{payload}&provider_token=p{provider_token}&start_parameter=p{start_parameter}&currency=p{currency}&prices=p{prices}"
						.replace("<token>", BOT_TOKEN).replace("p{title}", title).replace("p{chat_id}", chat_id)
						.replace("p{photo_url}", URLEncoder.encode(photo_url.toString(), "UTF-8")).replace("p{description}", description)
						.replace("p{payload}", payload).replace("p{provider_token}", provider_token).replace("p{start_parameter}", start_parameter)
						.replace("p{currency}", currency.toString()).replace("p{prices}", new Gson().toJson(prices))).openConnection();

		httpsURLConnection.setRequestMethod("GET");
		httpsURLConnection.getResponseCode();
	}

	public void sendPhoto(String chat_id, URL photo, ReplyMarkup reply_markup) throws Exception {

		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(
				"https://api.telegram.org/bot<token>/sendPhoto?chat_id=p{chat_id}&disable_notification=true&photo=p{photo}&reply_markup=p{reply_markup}"
						.replace("<token>", BOT_TOKEN).replace("p{photo}", URLEncoder.encode(photo.toString(), "UTF-8"))
						.replace("p{reply_markup}", URLEncoder.encode(new Gson().toJson(reply_markup), "UTF-8"))).openConnection();

		httpsURLConnection.setRequestMethod("GET");

		httpsURLConnection.getResponseCode();
	}
}
