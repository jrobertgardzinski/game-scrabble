package gamemechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class TileBag {	
	private List<Tile> letters;
	   
	@SuppressWarnings("unchecked")
	// Ignore casting to LinkedList warning
	public TileBag() { 
		letters = new LinkedList<Tile>();
	}
	
	public TileBag(LinkedList<Tile> letters) {
		this.letters = letters;
	}
	
	public List<Tile> getLetterList(){
		return this.letters;		
	}
	
	
	public List<Tile> replaceTiles(List<Tile> tilesToReplace){
		if (tilesToReplace.size() == 0)
		{
			throw new IllegalArgumentException("Size of argument tilesToReplace must be greater than 0!");
		}			
		this.letters.addAll(tilesToReplace);
		int quantity = tilesToReplace.size();
		return drawTiles(quantity);
	}
	
	public List<Tile> drawTiles(int howManyTiles){
		Collections.shuffle(this.letters);		
		List<Tile> tilesDrawn = new LinkedList<Tile>();		
		for(int i = 0; i < howManyTiles && this.letters.size() > 0; i++){
			tilesDrawn.add(this.letters.remove(0));
		}		
		return tilesDrawn;		
	}
	
}