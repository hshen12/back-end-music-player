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

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.util.Scanner;
import java.util.ArrayList;

/*
this is the MusicPlayerDriver class.

author: Hao Shen
*/

public class MusicPlayerDriver {
	

	public static void main(String[] args) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, JavaLayerException{



//check whether there's a absolute path provided or not
		if(args.length == 0) {
			System.out.println("usage: java MusicPlayerDriver <absolute_path>");
			System.exit(1);
		}
//created a MusicPlayer object
		MusicPlayer allFiles = new MusicPlayer();

//created an arraylist contain AbPath object
		ArrayList<AbPath> objectArrayList;
//calling a method in allFiles object
		allFiles.constructArrayList(args[0]);
//assign the arraylist 
		objectArrayList = allFiles.getObjectArrayList();

		int op = 0;

		Player player = null;
//a while loop to begin the program
		while(op != 5) {
//print out the menu
			System.out.println("MusicPlayer main menu: please enter a number for selection");

			System.out.println("1. list all songs sorted by song title");

			System.out.println("2. list all songs sorted by artist");

			System.out.println("3. play the song");

			System.out.println("4. stop playing");

			System.out.println("5. exit the program");

			System.out.println("====================");
//print out all songs
			System.out.println(allFiles.toString());

//creating Scanner object
			Scanner in = new Scanner(System.in);

			op = in.nextInt();
//check the input 
			if(op == 1) {

				allFiles.sortByName();
				System.out.println(allFiles.toString());
				
			} else if(op == 2) {

				allFiles.sortByArtist();
				System.out.println(allFiles.toString());
				
			} else if(op == 3) {
//ask for a song to play
				System.out.println("please enter a number for selecting the song you want to play:");
				System.out.println(allFiles.toString());

				int playSong = in.nextInt();

				if(playSong-1 <= objectArrayList.size() && playSong >= 1) { //check whether the input number is a valid song number

					if(player == null) {

						Player anotherPlayer = new Player(new FileInputStream(allFiles.getAbsolutePathForJTagger(playSong)));

						player = anotherPlayer;

						Thread t = new Thread() {
							public void run() {
								try {
									anotherPlayer.play();
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
						};

						t.start();
						continue;

					} else {  //if there's one song is playing. stop the current playing song and playing the chosen song.
						player.close();

						Player anotherPlayer = new Player(new FileInputStream(allFiles.getAbsolutePathForJTagger(playSong)));

						player = anotherPlayer;

						Thread t = new Thread() {
							public void run() {
								try {
									anotherPlayer.play();
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
						};

						t.start();
						continue;

					}

				} else {  //if the number entered out of balance, print out error message and start the loop again.
					System.out.println("please check the number you have entered.");
					continue;
				}


			} else if(op == 4) {
				if(player == null) {  //check if no song is playing, do nothing
					continue;
				}

				player.close();
				continue;

			} else if(op == 5) {

				System.exit(1);
			} else {

				System.out.println("wrong input, please check the number you have entered.");
				continue;
			}

		}







	}
}