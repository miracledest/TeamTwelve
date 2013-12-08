
public class Venue {

	private String buildingBlock;
	private int level;
	private String roomName;
	private int size;
	
	public Venue(String buildingBlock, int level, String roomName, int size) {
		super();
		this.buildingBlock = buildingBlock;
		this.level = level;
		this.roomName = roomName;
		this.size = size;
	}

	public String getBuildingBlock() {
		return buildingBlock;
	}

	public void setBuildingBlock(String buildingBlock) {
		this.buildingBlock = buildingBlock;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}
