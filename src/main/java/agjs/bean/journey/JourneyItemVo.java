package agjs.bean.journey;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JourneyItemVo {

	private Integer journeyItemId;

	private Integer sohId;

	private String journeyName;

	private Integer adults;

	private Integer children;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private java.util.Date journeyDate;

	private String dateString;

	public JourneyItemVo() {
	}

	@Override
	public String toString() {
		return "JourneyItemVo [journeyItemId=" + journeyItemId + ", sohId=" + sohId + ", journeyName=" + journeyName
				+ ", adults=" + adults + ", children=" + children + ", journeyDate=" + journeyDate + ", dateString="
				+ dateString + "]";
	}

	public Integer getJourneyItemId() {
		return journeyItemId;
	}

	public void setJourneyItemId(Integer journeyItemId) {
		this.journeyItemId = journeyItemId;
	}

	public Integer getSohId() {
		return sohId;
	}

	public void setSohId(Integer sohId) {
		this.sohId = sohId;
	}

	public String getJourneyName() {
		return journeyName;
	}

	public void setJourneyName(String journeyName) {
		this.journeyName = journeyName;
	}

	public Integer getAdults() {
		return adults;
	}

	public void setAdults(Integer adults) {
		this.adults = adults;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

	public java.util.Date getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(java.util.Date journeyDate) {
		this.journeyDate = journeyDate;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

}
