package br.com.leeo.jtelegram;

import br.com.leeo.ReplyMarkup;

public class ReplyKeyboardRemove extends ReplyMarkup{

	private boolean remove_keyboard= true;

	public boolean isRemove_keyboard() {
		return remove_keyboard;
	}

	public void setRemove_keyboard(boolean remove_keyboard) {
		this.remove_keyboard = remove_keyboard;
	}
	
	
}
