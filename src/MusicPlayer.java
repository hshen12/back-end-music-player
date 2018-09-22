
/*
this is a MusicPlayer class.
it contain most of the method.
@author Hao Shen
*/
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.util.ArrayList;
import java.util.Collections;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer {
	

//data member
	private ArrayList<AudioFile> readArrayList;
	private ArrayList<AbPath> objectArrayList;
//constructor
	public MusicPlayer() {
		this.readArrayList = new ArrayList<AudioFile>();
		this.objectArrayList = new ArrayList<AbPath>();
	}

//recurcively find the mp3 file in the given directory
	public void constructArrayList(String directory) {

		constructArrayList(new File(directory));

	}

//helper method 
	private void constructArrayList(File input) {

		if(input.isFile()) {
			if(input.getName().endsWith(".mp3")) {
				try {
					AudioFile f = AudioFileIO.read(input);
					AbPath oneObject = new AbPath(f.getTag().getFirst(FieldKey.TITLE), f.getTag().getFirst(FieldKey.ARTIST), input.getAbsolutePath());
					readArrayList.add(f);
					objectArrayList.add(oneObject);

				} catch(Exception e) {
					System.out.println(e.getMessage());
					System.exit(1);
				}

			}

		} else if(input.isDirectory()) {
			File[] children = input.listFiles();
			for(File f: children) {
				constructArrayList(f);
			}
		}

	}

//getter method, return the arraylist
	public ArrayList<AbPath> getObjectArrayList() {
		return this.objectArrayList;
	}

//sorting method 
	public void sortByName() {
		Collections.sort(objectArrayList, new ByNameComparator());
	}
//sorting method
	public void sortByArtist() {
		Collections.sort(objectArrayList, new ByArtistComparator());
	}

//getter method, return the absolute path in AbPath Object
	public String getAbsolutePathForJTagger(int index) {

		return objectArrayList.get(index-1).getAbPath();
	}

//toString method
	public String toString() {
		String result = "";
		int i = 1;
		for(AbPath a: objectArrayList) {
			result += i + ". Song: " + a.getName() + " Artist: " + a.getArtist() + "\n";
			i++;
		}

		return result;
	}


}