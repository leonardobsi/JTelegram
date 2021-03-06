package br.com.leeo.jtelegram;

import java.util.List;

public class Message {

	private String message_id;
	private String text;
	private Chat chat;
	private List<MessageEntity> entities;
	private Message reply_to_message;
	private User from;
	private Invoice invoice;
	private SuccessfulPayment successful_payment;
	private Location location;
	private Contact contact;
	private List<PhotoSize> photo;

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public List<MessageEntity> getEntities() {
		return entities;
	}

	public void setEntities(List<MessageEntity> entities) {
		this.entities = entities;
	}

	public Message getReply_to_message() {
		return reply_to_message;
	}

	public void setReply_to_message(Message reply_to_message) {
		this.reply_to_message = reply_to_message;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public SuccessfulPayment getSuccessful_payment() {
		return successful_payment;
	}

	public void setSuccessful_payment(SuccessfulPayment successful_payment) {
		this.successful_payment = successful_payment;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<PhotoSize> getPhoto() {
		return photo;
	}

	public void setPhoto(List<PhotoSize> photo) {
		this.photo = photo;
	}

}
