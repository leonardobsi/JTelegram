package br.com.leeo.jtelegram;

public class LabeledPrice {

	private String label;
	private int amount;

	public LabeledPrice(String label, int amount) {
		this.label = label;
		this.amount = amount;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
