package vista;

import java.io.Serializable;

import com.sun.xml.internal.ws.api.message.Attachment;

public class MailBean implements Serializable {
    // alot of properties
    private Attachment attachment;
    // setters and getters

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
}