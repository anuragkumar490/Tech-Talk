package model;

public class TalkRequest {

	private String reqId;
	private String date;
	private String topic;
	private String email;
	private String status;

	// constructor
	public TalkRequest(String reqId, String date, String topic, String email,
			String status) {
		super();
		this.reqId = reqId;
		this.date = date;
		this.topic = topic;
		this.email = email;
		this.status = status;
	}

	// getters and setters
	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TalkRequest [reqId=" + reqId + ", date=" + date + ", topic="
				+ topic + ", email=" + email + ", status=" + status + "]";
	}

}
