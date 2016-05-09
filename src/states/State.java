package states;

import java.util.ArrayList;

import form.Form;

public interface State {
	public void doAction(Context context, Form form);
	public void sync(Context context, Form form);
	public void setEditable(Form form, boolean isEditable);
	public void clearAll(Form form);
	public void fillAll(Form form, ArrayList<String> values);
	public void primaryKeyFieldEditable(Form form, boolean isEditable);	
}
