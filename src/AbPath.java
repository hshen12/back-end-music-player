/*
this is a AbPath class
creating Abpath object.
authod Hao Shen
*/

public class AbPath {

//data member
	private String name;
	private String artist;
	private String abPath;

//constructor 
	public AbPath(String name, String artist, String abPath) {
		this.name = name;
		this.artist = artist;
		this.abPath = abPath;
	}

//getter method 
	public String getName() {
		return this.name;
	}
//getter method 
	public String getArtist() {
		return this.artist;
	}
//getter method 
	public String getAbPath() {
		return this.abPath;
	}






}