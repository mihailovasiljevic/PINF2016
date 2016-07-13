package states;

import main.MainFrame;

public class Context {
	private State state;
	
	public Context(){
		this.state = new UpdateState();

	}
	public Context(Context context){
		this.state = context.getState();

	}
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
		if(state instanceof InsertState){
			MainFrame.getInstance().getStatusLabel().setText("Stanje unosa teksta.");
			
		}
		else if(state instanceof UpdateState){
			MainFrame.getInstance().getStatusLabel().setText("Stanje izmene/ pregleda.");
			
		}
		else
			MainFrame.getInstance().getStatusLabel().setText("Stanje pretrage.");
	}
	
}
