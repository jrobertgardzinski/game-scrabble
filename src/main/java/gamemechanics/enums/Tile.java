package gamemechanics.enums;

// TODO rename to polish_alphabet or something
public enum Tile {
	BLANK(' ',0),
	A('A',1),
	A_special('Ą',5),
	B('B',3),
	C('C',2),
	C_special('Ć',2),
	D('D',2),
	E('E',1),
	E_special('Ę',1),
	F('F',5),
	G('G',3),
	H('H',3),
	I('I',1),
	J('J',3),
	K('K',2),
	L('L',2),
	L_special('Ł',2),
	M('M',2),
	N('N',1),
	N_special('Ń',1),
	O('O',1),
	O_special('Ó',1),
	P('P',2),
	R('R',1),
	S('S',1),
	S_special('Ś',1),
	T('T',2),
	U('U',3),
	W('W',1),
	Y('Y',2),
	Z('Z',1),
	Z_ziet('Ź',1),
	Z_zet_z_kropka('Ż',1);

	private char letter;
	private int points;
	
	Tile(char letter, int points) {
		this.letter = letter;
		this.points = points;
	}
	
	public char getLetter() {
		return this.letter;
	}
	
	public int getPoints() {
		return this.points;
	}
}
