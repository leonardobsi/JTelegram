package br.com.leeo.jtelegram;

import br.com.leeo.jtelegram.type.ReplyMarkup;

public class ForceReply extends ReplyMarkup{

	private boolean force_reply= true;

	public boolean isForce_reply() {
		return force_reply;
	}

	public void setForce_reply(boolean force_reply) {
		this.force_reply = force_reply;
	}
	
}
