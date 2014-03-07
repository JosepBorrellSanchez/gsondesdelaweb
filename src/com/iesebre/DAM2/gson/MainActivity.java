package com.iesebre.DAM2.gson;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends Activity {
	private Person person = new Person(1,"hola","asd","asf");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
				//serialization (java to JSON)...
			//Gson gson = new Gson();
			//dades contains; Array of UserProfile
			// Type collectionType = new TypeToken<Person>(){}.getType();
			//String json = gson.toJson(person);
			//Log.i("text", json);
			//on this, parsing java to json
	                //profiles = gson.fromJson(json, UserProfile[].class);
			
			Button btnBoton1 = (Button)findViewById(R.id.button1);
			
			 
			btnBoton1.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View arg0)
			    {
			    	 String missatge = null;
					 Log.d(missatge,"Botón 1 pulsado!");
					 DownloadTask baixa = (DownloadTask) new DownloadTask().execute("http://192.168.202.108/json");
					 person = baixa.getPersona();
					 //Log.i("missatge", person.getPerson_givenName());
				}
			});
			
		}


		
		public class DownloadTask extends AsyncTask<String, Long, String> {
			Person persona;  
			protected String doInBackground(String... urls) {
			  
			    	String string = null;
			    	string =  HttpRequest.get(urls[0]).body();
			    	
			    	Gson parser = new Gson();
			    	persona = parser.fromJson(string, Person.class);
		
			      return string;
			   
			  }

			  public Person getPersona() {
				return persona;
			}
			  public String getPersonaName() {
				return persona.person_givenName;
			}
			  public int getPersonaId(){
				  return persona.person_id;
			  }
			  

			protected void onProgressUpdate(Long... progress) {
			    Log.d("MyApp", "Downloaded bytes: " + progress[0]);
			  }

			  protected void onPostExecute(String string) {
				  TextView nom = (TextView) findViewById(R.id.textView4);
				  TextView id = (TextView) findViewById(R.id.textView2);
			     // mytext.setText(string);
			      Log.d("MyApp", "Json de la web: " + persona.person_givenName);
			      nom.setText(persona.person_givenName);
			      id.setText(persona.person_id.toString());

			  }
			  
		}
		
		

		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
		
	}

}
