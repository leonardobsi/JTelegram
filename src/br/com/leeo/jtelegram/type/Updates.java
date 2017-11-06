package br.com.leeo.jtelegram.type;

import java.util.List;

import br.com.leeo.jtelegram.Update;

public class Updates {

	private boolean ok;
	private List<Update> result;

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public List<Update> getResult() {
		return result;
	}

	public void setResult(List<Update> result) {
		this.result = result;
	}

}
