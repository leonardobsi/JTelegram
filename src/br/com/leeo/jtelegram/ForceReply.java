package br.com.leeo.jtelegram;

import br.com.leeo.ReplyMarkup;

public class ForceReply extends ReplyMarkup{

	private boolean force_reply= false;

	public boolean isForce_reply() {
		return force_reply;
	}

	public void setForce_reply(boolean force_reply) {
		this.force_reply = force_reply;
	}
	
}
