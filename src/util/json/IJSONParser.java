package util.json;

import java.util.ArrayList;

public interface IJSONParser {
	public ArrayList<JSONModel> parseJSON(String path, String rootName);   //returns table name
}
