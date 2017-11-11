package br.com.leeo.jtelegram;

import java.util.ArrayList;
import java.util.List;

import br.com.leeo.ReplyMarkup;

public class InlineKeyboardMarkup extends ReplyMarkup{

	private List<List<InlineKeyboardButton>> inline_keyboard= new ArrayList<List<InlineKeyboardButton>>();

	public List<List<InlineKeyboardButton>> getInline_keyboard() {
		return inline_keyboard;
	}

	public void setInline_keyboard(List<List<InlineKeyboardButton>> inline_keyboard) {
		this.inline_keyboard = inline_keyboard;
	}
}