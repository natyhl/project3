package Project3;

public abstract class Game {
	
//	explains rules
	public abstract String explainRules();
	
//	ask user for input
	public abstract String setup();
	
//	verify input
	public abstract boolean goodPlayerInput(String guess);
	
//	update game and check status
	public abstract String checkWinOrLose();
	
//	can we play again
	public abstract boolean canPlayAgain();

}
