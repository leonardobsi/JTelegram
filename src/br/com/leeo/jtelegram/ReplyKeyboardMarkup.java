package br.com.leeo.jtelegram;

import java.util.ArrayList;
import java.util.List;

import br.com.leeo.jtelegram.type.ReplyMarkup;

public class ReplyKeyboardMarkup extends ReplyMarkup {

	private List<List<KeyboardButton>> keyboard = new ArrayList<List<KeyboardButton>>();
	private boolean resize_keyboard = true;
	private boolean one_time_keyboard = false;

	public List<List<KeyboardButton>> getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(List<List<KeyboardButton>> keyboard) {
		this.keyboard = keyboard;
	}

	public boolean isResize_keyboard() {
		return resize_keyboard;
	}

	public void setResize_keyboard(boolean resize_keyboard) {
		this.resize_keyboard = resize_keyboard;
	}

	public boolean isOne_time_keyboard() {
		return one_time_keyboard;
	}

	public void setOne_time_keyboard(boolean one_time_keyboard) {
		this.one_time_keyboard = one_time_keyboard;
	}

}