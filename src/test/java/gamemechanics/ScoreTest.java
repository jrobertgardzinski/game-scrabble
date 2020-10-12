package gamemechanics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import gamemechanics.enums.Premium;

class ScoreTest {
	@Nested
	class TestingWithPredefinedWordsCombinations{
		@Test
		void placingFirstWordTest() {			
			List<Word> words = new ArrayList<Word>();
			
			List<Field> fieldList1 = new ArrayList<Field>();				// 6
			fieldList1.add(new Field.Builder(8,8).setTile('T').setPremium(Premium.DOUBLE_WORD).build());
			fieldList1.add(new Field.Builder(8,9).setTile('A').build());
			
			List<Field> fieldList2 = new ArrayList<Field>();				// 10
			fieldList2.add(new Field.Builder(7,9).setTile('P').setPremium(Premium.DOUBLE_LETTER).build());
			fieldList2.add(new Field.Builder(8,9).setTile('A').build());
			fieldList2.add(new Field.Builder(9,9).setTile('N').setPremium(Premium.DOUBLE_LETTER).build());
			fieldList2.add(new Field.Builder(10,9).setTile('N').build());
			fieldList2.add(new Field.Builder(11,9).setTile('Y').build());
			
			Word word1 = new Word(fieldList1); // 6 pts
			Word word2 = new Word(fieldList2); // 13 pts
			
			List<Word> combinedWords = new ArrayList<Word>();
			
			combinedWords.add(word2);
			combinedWords.add(word1);
			
			Score score = new Score();
			score.calculate(combinedWords);
			
			assertEquals(16, score.getTotal());
		}
	}
}
