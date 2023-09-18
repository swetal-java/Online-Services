package model;

public class ServicemanServices {
	private int s_id, s_price, serviceman_id;
	private String s_name, s_category, s_duration;

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public int getS_price() {
		return s_price;
	}

	public void setS_price(int s_price) {
		this.s_price = s_price;
	}

	public int getServiceman_id() {
		return serviceman_id;
	}

	public void setServiceman_id(int serviceman_id) {
		this.serviceman_id = serviceman_id;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_category() {
		return s_category;
	}

	public void setS_category(String s_category) {
		this.s_category = s_category;
	}

	public String getS_duration() {
		return s_duration;
	}

	public void setS_duration(String s_duration) {
		this.s_duration = s_duration;
	}

	@Override
	public String toString() {
		return "ServicemanServices [s_id=" + s_id + ", s_price=" + s_price + ", serviceman_id=" + serviceman_id
				+ ", s_name=" + s_name + ", s_category=" + s_category + ", s_duration=" + s_duration + "]";
	}
}
