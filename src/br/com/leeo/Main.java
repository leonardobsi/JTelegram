package br.com.leeo;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import br.com.leeo.jtelegram.Update;
import br.com.leeo.jtelegram.type.ReplyMarkup;
import br.com.leeo.jtelegram.type.Updates;

public class Main {

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

		/*
		 * InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
		 * 
		 * InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
		 * inlineKeyboardButton.setText("Cor preta");
		 * inlineKeyboardButton.setCallback_data("/black");
		 * 
		 * InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
		 * inlineKeyboardButton2.setText("Cor branca");
		 * inlineKeyboardButton2.setCallback_data("/white");
		 * 
		 * List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
		 * inlineKeyboardButtons.add(inlineKeyboardButton);
		 * inlineKeyboardButtons.add(inlineKeyboardButton2);
		 * inlineKeyboardMarkup.getInline_keyboard().add(inlineKeyboardButtons);
		 * 
		 * System.out.println(new Gson().toJson(inlineKeyboardMarkup)); //
		 * -------------- ReplyKeyboardMarkup replyKeyboardMarkup = new
		 * ReplyKeyboardMarkup(); replyKeyboardMarkup.setResize_keyboard(true);
		 * 
		 * KeyboardButton keyboardButton = new KeyboardButton();
		 * keyboardButton.setText("leonardo");
		 * 
		 * KeyboardButton keyboardButton2 = new KeyboardButton();
		 * keyboardButton2.setText("soares");
		 * 
		 * KeyboardButton keyboardButton3 = new KeyboardButton();
		 * keyboardButton3.setText("souza");
		 * 
		 * List<KeyboardButton> keyboardButtons = new ArrayList<>();
		 * keyboardButtons.add(keyboardButton); keyboardButtons.add(keyboardButton2);
		 * keyboardButtons.add(keyboardButton3);
		 * 
		 * replyKeyboardMarkup.getKeyboard().add(keyboardButtons);
		 * 
		 * System.out.println(new Gson().toJson(replyKeyboardMarkup));
		 * 
		 * // --------------
		 * 
		 * System.out.println(new Gson().toJson(new ForceReply()));
		 * 
		 * new Main().sendMessage("479550025", "Ho ho ho", "Markdown", false, false, 0,
		 * replyKeyboardMarkup);
		 * 
		 * // new Main().sendMessage("479550025", "Ha ha ho", "Markdown", false, false,
		 * 0, // new ReplyKeyboardRemove());
		 */
	}

	public void handle(Update update) {
		
		
	}

	public Updates getUpdates(long offset) {

		try {

			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(
					"https://api.telegram.org/bot<token>/getUpdates?offset="
							+ offset).openConnection();

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

	public void sendMessage(String chat_id, String text, String parse_mode, boolean disable_web_page_preview,
			boolean disable_notification, int reply_to_message_id, ReplyMarkup reply_markup) {

		try {

			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(
					"https://api.telegram.org/bot<token>/sendMessage?reply_markup="
							+ new Gson().toJson(reply_markup) + "&parse_mode=" + parse_mode + "&chat_id=" + chat_id
							+ "&text=" + URLEncoder.encode(text, "UTF-8")).openConnection();

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