package model;

public class TechTalk {
	private int id;
	private String date;
	private String title;
	private String description;
	private String presenter;

	// constructor
	public TechTalk(String date, String title, String description,
			String presenter) {
		this.date = date;
		this.title = title;
		this.description = description;
		this.presenter = presenter;
	}

	public TechTalk(int id, String date, String title, String description,
			String presenter) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.description = description;
		this.presenter = presenter;
	}

	// getters and setters
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPresenter() {
		return presenter;
	}

	public void setPresenter(String presenter) {
		this.presenter = presenter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TechTalk [date=" + date + ", title=" + title + ", description="
				+ description + ", presenter=" + presenter + "]";
	}

}
