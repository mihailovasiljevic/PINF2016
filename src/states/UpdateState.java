package states;

import form.Form;

public class UpdateState extends AState{

	@Override
	public void doAction(Context context, Form form) {
		int index = form.getTable().getSelectedRow();
		
	}

}
