package states;

import form.Form;

public interface State {
	public void doAction(Context context, Form form);
}
