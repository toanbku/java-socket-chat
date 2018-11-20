package data;

import java.io.Serializable;

import tags.Tags;

@SuppressWarnings("serial")
public class DataFile implements Serializable{

	@SuppressWarnings("unused")
	private String openTags = Tags.FILE_DATA_OPEN_TAG;
	@SuppressWarnings("unused")
	private String closeTags = Tags.FILE_DATA_CLOSE_TAG;
	public byte[] data;
	
	public DataFile() {
		data = new byte[Tags.MAX_MSG_SIZE];
	}
	
	public DataFile(int size) {
		data = new byte[size];
	}
}
