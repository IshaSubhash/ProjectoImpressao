package ac.isutc.project3.Impressoes.models;

public class LoginResponse {
	String message;
	Boolean status;
	Integer id;
	public LoginResponse(String message, Boolean status, int id) {
		
		this.message = message;
		this.status = status;
		this.id = id;
	}
	
	public LoginResponse() {
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LoginResponse [message=" + message + ", status=" + status + ", id=" + id + "]";
	}
	
	
}
