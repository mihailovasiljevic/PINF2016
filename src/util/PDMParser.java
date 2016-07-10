package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Properties;

import rs.mgifos.mosquito.IMetaLoader;
import rs.mgifos.mosquito.LoadingException;
import rs.mgifos.mosquito.impl.pdm.PDMetaLoader;
import rs.mgifos.mosquito.model.MetaColumn;
import rs.mgifos.mosquito.model.MetaModel;
import rs.mgifos.mosquito.model.MetaTable;

public class PDMParser implements IParser{
	private IMetaLoader metaLoader;
	private Properties properties;
	private MetaModel model;
	private final String CLABLES = "src/database/cLables.properties";
	private final String TLABLES = "src/database/tLables.properties";
	public PDMParser() {
		this.metaLoader = new PDMetaLoader();
		this.properties = new Properties();
	}
	
	@Override
	public void createLabelsProperties(String path) {
		// TODO Auto-generated method stub
		properties.put(PDMetaLoader.FILENAME, path); // stavi se putanja dopdm-a.
		StringBuilder builder = new StringBuilder("");
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(CLABLES)));
			this.model = metaLoader.getMetaModel(properties);
			for (MetaTable table : model) { //direktno se iterira kroz model
				System.out.println();
				for(MetaColumn column : table){
					builder.append(table.getCode()+"."+column.getCode()+" = " + column.getName()+"\n");
					
				}
			}
			System.out.println(builder.toString());
			writer.write(builder.toString());
		} catch (UnsupportedEncodingException | FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (LoadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	          if ( writer != null ) {
		        	try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	    }
		

	}

	@Override
	public void createTablesProperties(String path) {
		// TODO Auto-generated method stub
		properties.put(PDMetaLoader.FILENAME, path); // stavi se putanja dopdm-a.
		StringBuilder builder = new StringBuilder("");
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(TLABLES)));
			this.model = metaLoader.getMetaModel(properties);
			for (MetaTable table : model) { //direktno se iterira kroz model
				System.out.println();
				builder.append(table.getCode()+" = " + table.getName()+"\n");
			}
			System.out.println(builder.toString());
			writer.write(builder.toString());
		} catch (UnsupportedEncodingException | FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (LoadingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	          if ( writer != null ) {
		        	try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	    }		
	}
	
	public static void main(String[] args){
		PDMParser pdmParser = new PDMParser();
		pdmParser.createLabelsProperties("MODEL/MagacinskoPoslovanje/MagacinskoPoslovanje.pdm");
		pdmParser.createTablesProperties("MODEL/MagacinskoPoslovanje/MagacinskoPoslovanje.pdm");
	}

}
