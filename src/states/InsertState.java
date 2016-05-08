package states;

import java.util.HashMap;

import form.Form;
import form.data.ConcreteDataGetter;
import form.data.IDataGetter;
import main.MainFrame;

public class InsertState implements State{

	@Override
	public void doAction(Context context, Form form) {
		MainFrame.getInstance().getContext().setState(this);
		
		IDataGetter data = new ConcreteDataGetter();
		HashMap<String, String> formattedData = data.getData(form.getDataPanel());
	}

}
