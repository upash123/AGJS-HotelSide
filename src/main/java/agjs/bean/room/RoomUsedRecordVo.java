package agjs.bean.room;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class RoomUsedRecordVo {
	@EmbeddedId
	private RoomUsedRecordIdVo roomUsedRecordIdVo;
	private Integer roomStyleId;
	private String roomName;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date orderEndDate;
	private String userName;
	private Integer source;

	public RoomUsedRecordVo() {
		roomUsedRecordIdVo = new RoomUsedRecordIdVo();
	}
	
	public RoomUsedRecordVo(Integer roomId, String roomName, String userName, Date orderStartDate,Date orderEndDate) {
		this();
		setRoomId(roomId);
		this.roomName = roomName;
		this.userName = userName;
		setOrderStartDate(orderStartDate);
		this.orderEndDate = orderEndDate;
	}

	public Integer getRoomId() {
		return roomUsedRecordIdVo.getRoomId();
	}

	public void setRoomId(Integer roomId) {
		roomUsedRecordIdVo.setRoomId(roomId);
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getOrderStartDate() {
		return roomUsedRecordIdVo.getOrderStartDate();
	}

	public void setOrderStartDate(@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") Date orderStartDate) {
		roomUsedRecordIdVo.setOrderStartDate(orderStartDate);
	}

	public Integer getRoomStyleId() {
		return roomStyleId;
	}

	public void setRoomStyleId(Integer roomStyleId) {
		this.roomStyleId = roomStyleId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Date getOrderEndDate() {
		return orderEndDate;
	}

	public void setOrderEndDate(Date orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
}
