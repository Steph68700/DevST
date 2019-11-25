package com.example.goodlucktoday;


import org.json.JSONArray;


import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;

import android.app.Activity;
import android.app.LauncherActivity.ListItem;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class classement extends Activity {

	ArrayList<listItem> myList= new ArrayList<listItem>();
	BaseAdapter2 adapter;
	ListView list;
	int score1=0;
	int score2=0;
	int score3=0;
	int score4 =0;
	int score5 =0;
	TextView ch =null;
	Button suiv=null;
	int chance;
	int x=0;
	SharedPreferences preferences;
	TextView global=null;
	int n=0;
	
	Random rand = new Random();
	int nb = rand.nextInt(10 - 0 + 1) + 0;
	
	public void onBackPressed()
	{
		
	}
	
	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        AdBuddiz.setPublisherKey("TEST_PUBLISHER_KEY");
	        AdBuddiz.setTestModeActive();
	        AdBuddiz.cacheAds(this); // this = current Activity
	        
	        
	        setContentView(R.layout.classement);
	        
	        
            Bundle extras = getIntent().getExtras();
            if(extras!=null){
            	AdBuddiz.showAd(this);
           
            	score1= extras.getInt("6");
	
           }
            else{
           if(nb==3){
        	   AdBuddiz.showAd(this);
           }
            }

           
	    	list = (ListView) findViewById(R.id.list);
	    	ch = (TextView) findViewById(R.id.chance);
	    	suiv = (Button) findViewById(R.id.suiv);
	    	
	    	
	 	
	        final ProgressDialog dialog = ProgressDialog.show(classement.this, "", "Please wait");
	      
	    	ch.setText("Votre Chance :"+score1+" %");
	    	
	    

	   	new GraphRequest(
	   		    AccessToken.getCurrentAccessToken(),
	   		    "1450909731871235/scores",
	   		    null,
	   		    HttpMethod.GET,
	   		    new GraphRequest.Callback() {
	   		        public void onCompleted(GraphResponse response) {
	   		        	
	   		        	FacebookRequestError error = response.getError();
	   		        
	   		        	
	   		        	if(error==null){
	   				
	   		         if(response!=null) {
	 	            		
                         try {
                             
                             JSONObject graphObject = response.getJSONObject();
                            JSONArray data = graphObject.getJSONArray("data");
                            
                          
                                 int length=data.length();
                            
                                 for(int i =0;i<length;i++){
                                 
                                 
                                 JSONObject oneUser = data.optJSONObject(i);
                                 JSONObject userObj = oneUser.optJSONObject("user");
                               
                                 String name = userObj.getString("name");
                                 int score = oneUser.getInt("score");
                                 
                                 myList.add(new listItem(name , score));
                                 dialog.dismiss();
    
                                
                             }
                          
                             
                             BaseAdapter2 adapter = new BaseAdapter2(classement.this, myList);
                             list.setAdapter(adapter);
      					

                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                         
	   		         }
	   		        	}
	   		         else if (error!=null){ Toast.makeText(getApplicationContext(),
	                          "Connect to Internet or Facebook ", Toast.LENGTH_SHORT)
	                          .show();
	   		      dialog.dismiss();} 
	   		         }
	   		    }).executeAsync();
	   	

		 	
	   	suiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {	
            	
            Intent secondeActivite = new Intent(classement.this, menu.class);   
            startActivity(secondeActivite);
            classement.this.finish();
           
                 }
          });
	 	
	 } 	 

}