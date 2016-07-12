package util.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JButton;

public class JSONModel {
	private ArrayList<String> toolbarItems;
	private ArrayList<String> formItems;
	private ArrayList<JButton> newFormItems;
	private String tableName;

	public JSONModel(ArrayList<String> toolbarItems, ArrayList<String> formItems, String tableName) {

		this.toolbarItems = toolbarItems;
		this.formItems = formItems;
		this.tableName = tableName;
	}

	public JSONModel(String tableName) {
		this.toolbarItems = new ArrayList<>();
		this.formItems = new ArrayList<>();
		this.newFormItems = new ArrayList<>();
		this.tableName = tableName;
	}
	public JSONModel() {
		this.toolbarItems = new ArrayList<>();
		this.formItems = new ArrayList<>();
		this.newFormItems = new ArrayList<>();
		this.tableName = "";
	}
	public ArrayList<JButton> getNewFormItems() {
		return newFormItems;
	}

	public void setNewFormItems(ArrayList<JButton> newFormItems) {
		this.newFormItems = newFormItems;
	}

	public ArrayList<String> getToolbarItems() {
		return toolbarItems;
	}

	public void setToolbarItems(ArrayList<String> toolbarItems) {
		this.toolbarItems = toolbarItems;
	}

	public ArrayList<String> getFormItems() {
		return formItems;
	}

	public void setFormItems(ArrayList<String> formItems) {
		this.formItems = formItems;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int addToolbarItem(String item) {

		if (check(item, toolbarItems, "String"))
			return -1;

		toolbarItems.add(item);
		return 0;
	}

	public int addFormItem(String item) {

		if (check(item, formItems, "String"))
			return -1;

		formItems.add(item);
		return 0;
	}

	public int addButton(JButton button) {
		if (check(button, newFormItems, "JButton"))
			return -1;

		newFormItems.add(button);
		return 0;
	}

	@SuppressWarnings("unchecked")
	private boolean check(Object item, ArrayList<?> collection, String itemType) {

		if (itemType.equalsIgnoreCase("JButton")) {
			ArrayList<JButton> buttons = (ArrayList<JButton>) collection;
			for (JButton s : buttons) {
				if (s.getName().equalsIgnoreCase(((JButton) item).getName()))
					return true;
			}
			return false;
		} else if (itemType.equalsIgnoreCase("String")) {
			ArrayList<String> strings = (ArrayList<String>) collection;
			for (String s : strings) {
				if (s.equalsIgnoreCase((String) item))
					return true;
			}
			return false;
		}

		return false;

	}

	private LinkedHashMap<String, ArrayList<String>> getMap(String collectionName) {

		LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<>();

		if (collectionName.equalsIgnoreCase("toolbarItems")) {
			map.put(tableName, toolbarItems);
			return map;
		} else if (collectionName.equalsIgnoreCase("formItems")) {
			map.put(tableName, formItems);
			return map;
		} else {
			return null;
		}
	}

	private LinkedHashMap<String, ArrayList<JButton>> getButtonMap(String collectionName) {

		LinkedHashMap<String, ArrayList<JButton>> map = new LinkedHashMap<>();

		if (collectionName.equalsIgnoreCase("newFormItems")) {
			map.put(tableName, newFormItems);
			return map;
		} else {
			return null;
		}
	}
}
