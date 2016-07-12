package util.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JButton;

public class JSONModel {
	private ArrayList<String> toolbarItems;
	private ArrayList<String> formItems;
	private ArrayList<String> newFormItems;
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
	public ArrayList<String> getNewFormItems() {
		return newFormItems;
	}

	public void setNewFormItems(ArrayList<String> newFormItems) {
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

		if (check(item, toolbarItems))
			return -1;

		toolbarItems.add(item);
		return 0;
	}

	public int addFormItem(String item) {

		if (check(item, formItems))
			return -1;

		formItems.add(item);
		return 0;
	}

	public int addButton(String button) {
		if (check(button, newFormItems))
			return -1;

		newFormItems.add(button);
		return 0;
	}

	@SuppressWarnings("unchecked")
	private boolean check(String item, ArrayList<String> collection) {


		ArrayList<String> strings = collection;
		for (String s : strings) {
			if (s.equalsIgnoreCase(item))
				return true;
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
		}  else if (collectionName.equalsIgnoreCase("newFormItems")) {
			map.put(tableName, newFormItems);
			return map;
		} else {
			return null;
		}
	}

}
