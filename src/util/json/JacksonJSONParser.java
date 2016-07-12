package util.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJSONParser implements IJSONParser {

	private ArrayList<JSONModel> jsonModels;

	public JacksonJSONParser() {
		this.jsonModels = new ArrayList<>();

	}

	public ArrayList<JSONModel> getJsonModels() {
		return jsonModels;
	}

	public void setJsonModels(ArrayList<JSONModel> jsonModels) {
		this.jsonModels = jsonModels;
	}

	@Override
	public ArrayList<JSONModel> parseJSON(String path, String rootName) {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		try {
			root = mapper.readTree(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonNode rootNode = root.path(rootName);

		// JsonNode featuresNames = sourceNode.path("featuresNames");

		Iterator<Entry<String, JsonNode>> nodeIterator = rootNode.fields();

		while (nodeIterator.hasNext()) {
			JSONModel model = new JSONModel();

			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodeIterator.next();
			String tableName = entry.getKey();
			model.setTableName(tableName);

			JsonNode fields = entry.getValue();

			Iterator<Entry<String, JsonNode>> entryIterator = fields.fields();
			System.out.println("key --> " + tableName + " value-->" + fields);
			while (entryIterator.hasNext()) {
				Map.Entry<String, JsonNode> concrete = (Map.Entry<String, JsonNode>) entryIterator.next();
				String concreteName = concrete.getKey();
				JsonNode concreteArray = concrete.getValue();
				if (concreteArray.isArray()) {
					if (concreteName.equalsIgnoreCase("TOOLBAR")) {
						System.out.println("\tkey --> " + concreteName + " value-->" + concreteArray);

						for (final JsonNode objNode : concreteArray) {
							model.addToolbarItem(objNode.toString().substring(1, objNode.toString().length() - 1));
							System.out.println("\t\tIZ PETLJE: "
									+ objNode.toString().substring(1, objNode.toString().length() - 1));
						}

					} else if (concreteName.equalsIgnoreCase("FORM")) {
						System.out.println("\tkey --> " + concreteName + " value-->" + concreteArray);

						for (final JsonNode objNode : concreteArray) {
							model.addFormItem(objNode.toString().substring(1, objNode.toString().length() - 1));
							System.out.println("\t\tIZ PETLJE: "
									+ objNode.toString().substring(1, objNode.toString().length() - 1));
						}
					} else if (concreteName.equalsIgnoreCase("NEW")) {
						System.out.println("\tkey --> " + concreteName + " value-->" + concreteArray);

						for (final JsonNode objNode : concreteArray) {
							model.addButton(objNode.toString().substring(1, objNode.toString().length() - 1));
							System.out.println("\t\tIZ PETLJE: "
									+ objNode.toString().substring(1, objNode.toString().length() - 1));
						}
					}else if (concreteName.equalsIgnoreCase("DISABLED_FIELDS")) {
						System.out.println("\tkey --> " + concreteName + " value-->" + concreteArray);

						for (final JsonNode objNode : concreteArray) {
							model.addDisabledField(objNode.toString().substring(1, objNode.toString().length() - 1));
							System.out.println("\t\tIZ PETLJE: "
									+ objNode.toString().substring(1, objNode.toString().length() - 1));
						}
					}
				}

			}
			jsonModels.add(model);
		}

		// retList =
		// (ArrayList<String>)sourceNode.findValuesAsText("featuresNames");
		return jsonModels;
	}

	public void printParsed() {
		for (JSONModel model : jsonModels) {
			System.out.println(model.getTableName());
			if (model.getFormItems().size() > 0) {
				System.out.println("\t FORM ITEMS FOR EXCLUSION:\n");
				for (String s : model.getFormItems()) {
					System.out.println("\t"+s);
				}
				System.out.println("\n");
			}
			if (model.getToolbarItems().size() > 0) {
				System.out.println("\t TOOLBAR ITEMS FOR EXCLUSION:\n");
				for (String s : model.getToolbarItems()) {
					System.out.println("\t"+s);
				}
				System.out.println("\n");
			}
			if (model.getNewFormItems().size() > 0) {
				System.out.println("\t BUTTON ITEMS FOR INCLUSION:\n");
				for (String s : model.getNewFormItems()) {
					System.out.println("\t"+s);
				}
			}
		}
	}

	public static void main(String args[]) {
		JacksonJSONParser pars = new JacksonJSONParser();
		pars.parseJSON("config.json", "TABLES");

		System.out.println("========== ISPARSIRANO =========");
		pars.printParsed();
	}

}
