package com.simul.goodlucktoday;


import org.json.JSONArray;
import com.facebook.ads.*;

import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class classement extends Activity {

	ArrayList<listItem> myList= new ArrayList<listItem>();
	BaseAdapter2 adapter;
	ListView list;
	int score1=0;
	int score2=0;
	int score3=0;
	int score4 =0;
	int score5 =0;
	int score6 =0;
	TextView ch =null;
	Button suiv=null;
	Button clas2=null;
	int chance;
	int x=0;
	TextView global=null;
	int n=0;
	SharedPreferences preferences6;
	SharedPreferences preferences5;
	SharedPreferences preferences4;
	SharedPreferences preferences3;
	SharedPreferences preferences2;
	SharedPreferences preferences1;
	int glob=0;



	Random rand = new Random();
	int nb = rand.nextInt(10 - 0 + 1) + 0;
	
	public void onBackPressed()
	{
		
	}



	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        

		 preferences6 = getSharedPreferences("s6", Context.MODE_PRIVATE);
		 preferences5 = getSharedPreferences("s5", Context.MODE_PRIVATE);
		 preferences4 = getSharedPreferences("s4", Context.MODE_PRIVATE);
		 preferences3 = getSharedPreferences("s3", Context.MODE_PRIVATE);
		 preferences2 = getSharedPreferences("s2", Context.MODE_PRIVATE);
		 preferences1 = getSharedPreferences("s1", Context.MODE_PRIVATE);
	        
	        setContentView(R.layout.classement);

		 list = (ListView) findViewById(R.id.list);
		 ch = (TextView) findViewById(R.id.chance);
		 suiv = (Button) findViewById(R.id.suiv);
		 clas2 = (Button) findViewById(R.id.clas2);
	        
            Bundle extras = getIntent().getExtras();
		 Bundle extras1 = getIntent().getExtras();
		 Bundle extras2 = getIntent().getExtras();
		 Bundle extras3 = getIntent().getExtras();
		 Bundle extras4 = getIntent().getExtras();
		 Bundle extras5 = getIntent().getExtras();


            if(extras!=null){

				glob=1;

            	score6= extras.getInt("6");
				SharedPreferences.Editor edito6 = preferences6.edit();
				edito6.putInt("s6", score6);
				edito6.commit();

				score5= extras1.getInt("5");
				SharedPreferences.Editor edito5 = preferences5.edit();
				edito5.putInt("s5", score5);
				edito5.commit();

				score4= extras2.getInt("44");
				SharedPreferences.Editor edito4 = preferences4.edit();
				edito4.putInt("s4", score6);
				edito4.commit();

				score3= extras3.getInt("333");
				SharedPreferences.Editor edito3 = preferences3.edit();
				edito3.putInt("s3", score3);
				edito3.commit();

				score2= extras4.getInt("2222");
				SharedPreferences.Editor edito2 = preferences2.edit();
				edito2.putInt("s2", score2);
				edito2.commit();

				score1= extras5.getInt("11111");
				SharedPreferences.Editor edito1 = preferences1.edit();
				edito1.putInt("s1", score1);
				edito1.commit();

	
           }
            else{

				clas2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

						Intent secondeActivite = new Intent(classement.this, classement2.class);
						startActivity(secondeActivite);
						classement.this.finish();

					}
				});
            }



		 final ProgressDialog dialog = ProgressDialog.show(classement.this, "", "Please wait");

		 Timer timer = new Timer();
		 TimerTask task = new TimerTask() {

			 @Override
			 public void run() {
				dialog.dismiss();
			 }
		 };

		 timer.schedule(task, 5000);



		 score6 = preferences6.getInt("s6", score6);
		 score5 = preferences5.getInt("s5", score5);
		 score4 = preferences4.getInt("s4", score4);
		 score3 = preferences3.getInt("s3", score3);
		 score2 = preferences2.getInt("s2", score2);
		 score1 = preferences1.getInt("s1", score1);

	    	ch.setText("Votre Chance :" + score6 + " %");

		 Bundle params = new Bundle();
		 params.putString("fields", "score");

		 GraphRequest request = new GraphRequest(
	   		    AccessToken.getCurrentAccessToken(),
	   		    "/1450909731871235/scores",
	   		    null,
	   		    HttpMethod.GET,
	   		    new GraphRequest.Callback() {
	   		        public void onCompleted(GraphResponse response) {

	   		        	FacebookRequestError error = response.getError();
						Log.d("BBB", "response: " + response.toString());
	   		        	
	   		        	if(error==null){
	   				
	   		         if(response!=null) {

                         try {

                             JSONObject graphObject = response.getJSONObject();
                            JSONArray data = graphObject.getJSONArray("data");

                          
                                 int length=data.length();
                            
                                 for(int i =0;i<length;i++){

                                     dialog.dismiss();
                             JSONObject oneUser = data.optJSONObject(i);
                                 JSONObject userObj = oneUser.optJSONObject("user");
                               
                                 String name = userObj.getString("name");
                                 int score = oneUser.getInt("score");

									 myList.add(new listItem(name , score));

    
                                
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
	   		    });request.setParameters(params); request.executeAsync();


         suiv.setOnClickListener(new View.OnClickListener() {
			 @Override
			 public void onClick(View v) {

				 if (glob == 1) {
					 Intent secondeActivite = new Intent(classement.this, classement2.class);
					 final int play1 = getIntent().getIntExtra("11111", score1);
					 secondeActivite.putExtra("111111", play1);
					 final int play2 = getIntent().getIntExtra("2222", score2);
					 secondeActivite.putExtra("22222", play2);
					 final int play3 = getIntent().getIntExtra("333", score3);
					 secondeActivite.putExtra("3333", play3);
					 final int play4 = getIntent().getIntExtra("44", score4);
					 secondeActivite.putExtra("444", play4);
					 final int play5 = getIntent().getIntExtra("5", score5);
					 secondeActivite.putExtra("55", play5);
					 final int play6 = getIntent().getIntExtra("6", score6);
					 secondeActivite.putExtra("66", play6);
					 startActivity(secondeActivite);
					 classement.this.finish();
				 }

				 if (glob == 0) {
					 Intent secondeActivite = new Intent(classement.this, menu.class);
					 startActivity(secondeActivite);
					 classement.this.finish();
				 }
			 }
		 });

	 } 	 

}