package br.com.leeo.jtelegram;

import java.util.ArrayList;
import java.util.List;

import br.com.leeo.jtelegram.type.ReplyMarkup;

public class ReplyKeyboardMarkup extends ReplyMarkup{

	private List<List<KeyboardButton>> keyboard = new ArrayList<List<KeyboardButton>>();
	private boolean resize_keyboard;
	
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

}