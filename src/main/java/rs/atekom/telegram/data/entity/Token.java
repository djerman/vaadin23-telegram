package rs.atekom.telegram.data.entity;

public class Token {

	private Long id;
	private String first_name;
	private String last_name;
	private String username;
	private String photo_url;
	private String auth_date;
	private String hash;
	private String bot;
	
	public Token() {
		// TODO Auto-generated constructor stub
	}
	
	public Token(Long id, String first_name, String last_name, String username, String photo_url, String auth_date, String hash) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.photo_url = photo_url;
		this.auth_date = auth_date;
		this.hash = hash;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public String getAuth_date() {
		return auth_date;
	}

	public void setAuth_date(String auth_date) {
		this.auth_date = auth_date;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getBot() {
		return bot;
	}

	public void setBot(String bot) {
		this.bot = bot;
	}
	
}
