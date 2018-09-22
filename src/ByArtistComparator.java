/*
this is a ByArtistComparator class
it sort arraylist by its artist
author Hao Shen
*/

import java.util.Comparator;

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

public class ByArtistComparator implements Comparator<AbPath> {
	
	@Override 
	public int compare(AbPath a1, AbPath a2) {
		return a1.getArtist().compareTo(a2.getArtist());
	}
}