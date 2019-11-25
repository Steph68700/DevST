package com.simul.goodlucktoday;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.ads.*;


public class classement2 extends Activity {



	int score1=0;
	int score2=0;
	int score3=0;
	int score4 =0;
	int score5 =0;
	int score6 =0;
	int score7=0;
	TextView nbr=null;
	TextView pile=null;
	TextView carte=null;
	TextView bon=null;
	TextView tor=null;
	TextView roul=null;
	TextView total=null;
	TextView suiv=null;
	TextView today=null;
	int chance;
	int x=0;
	int n=0;
	int m=0;
	int score=0;

	int sc7=0;
	int sco6=0;
	int sco5=0;
	int sco4=0;
	int sco3=0;
	int sco2=0;
	int sco1=0;

	private InterstitialAd interstitialAd;

	SharedPreferences preferences7;
	SharedPreferences preferences6;
	SharedPreferences preferences5;
	SharedPreferences preferences4;
	SharedPreferences preferences3;
	SharedPreferences preferences2;
	SharedPreferences preferences1;
	SharedPreferences preferences;
	Button global=null;
	
	public void onBackPressed()
	{
		
	}


	private void loadInterstitialAd() {
		interstitialAd = new InterstitialAd(this, "1450909731871235_1519988424963365");
		interstitialAd.setAdListener((InterstitialAdListener) this);
		interstitialAd.loadAd();
	}


	public void onError(Ad ad, AdError error) {
		// Ad failed to load
		Log.d("ad", "error: " + error.toString());
	}


	public void onAdLoaded(Ad ad) {
		// Ad is loaded and ready to be displayed
		// You can now display the full screen add using this code:

		interstitialAd.show();

	}
	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        
	        setContentView(R.layout.classement2);

		 preferences7 = getSharedPreferences("s77", Context.MODE_PRIVATE);
		 preferences6 = getSharedPreferences("s66", Context.MODE_PRIVATE);
		 preferences5 = getSharedPreferences("s55", Context.MODE_PRIVATE);
		 preferences4 = getSharedPreferences("s44", Context.MODE_PRIVATE);
		 preferences3 = getSharedPreferences("s33", Context.MODE_PRIVATE);
		 preferences2 = getSharedPreferences("s22", Context.MODE_PRIVATE);
		 preferences1 = getSharedPreferences("s11", Context.MODE_PRIVATE);
		 preferences = getSharedPreferences("s0", Context.MODE_PRIVATE);


		 Bundle extras = getIntent().getExtras();
		 Bundle extras1 = getIntent().getExtras();
		 Bundle extras2 = getIntent().getExtras();
		 Bundle extras3 = getIntent().getExtras();
		 Bundle extras4 = getIntent().getExtras();
		 Bundle extras5 = getIntent().getExtras();


            if(extras!=null){

				score7 = preferences7.getInt("s77", score7);
				score7=score7+1;
				SharedPreferences.Editor edito7 = preferences7.edit();
				edito7.putInt("s77", score7);
				edito7.commit();

				sco6 = preferences6.getInt("s66", score6);
				score6= extras.getInt("66");
				score6=sco6+score6;
				SharedPreferences.Editor edito6 = preferences6.edit();
				edito6.putInt("s66", score6);
				edito6.commit();

				sco5 = preferences5.getInt("s55", score5);
				score5= extras1.getInt("55");
				score5=sco5+score5;
				SharedPreferences.Editor edito5 = preferences5.edit();
				edito5.putInt("s55", score5);
				edito5.commit();

				sco4 = preferences4.getInt("s44", score4);
				score4= extras2.getInt("444");
				score4=sco4+score4;
				SharedPreferences.Editor edito4 = preferences4.edit();
				edito4.putInt("s44", score4);
				edito4.commit();

				sco3 = preferences3.getInt("s33", score3);
				score3= extras3.getInt("3333");
				score3=sco3+score3;
				SharedPreferences.Editor edito3 = preferences3.edit();
				edito3.putInt("s33", score3);
				edito3.commit();

				sco2 = preferences2.getInt("s22", score2);
				score2= extras4.getInt("22222");
				score2=sco2+score2;
				SharedPreferences.Editor edito2 = preferences2.edit();
				edito2.putInt("s22", score2);
				edito2.commit();

				sco1 = preferences1.getInt("s11", score1);
				score1= extras5.getInt("111111");
				score1=sco1+score1;
				SharedPreferences.Editor edito1 = preferences1.edit();
				edito1.putInt("s11", score1);
				edito1.commit();

				score= extras5.getInt("66");
				SharedPreferences.Editor edito = preferences.edit();
				edito.putInt("s0", score);
				edito.commit();
           }

		  score7 = preferences7.getInt("s77", score7);

		  score6 = preferences6.getInt("s66", score6);
		  score5 = preferences5.getInt("s55", score5);
		  score4 = preferences4.getInt("s44", score4);
		  score3 = preferences3.getInt("s33", score3);
		  score2 = preferences2.getInt("s22", score2);
		   score1 = preferences1.getInt("s11", score1);

		 score = preferences.getInt("s0", score);

		 if(score7!=0) {
			 score6 = score6 / score7;
		 }
		 if(score7!=0) {
			 score5 = score5 / score7;
		 }
		 if(score7!=0) {
			 score4 = score4 / score7;
		 }
		 if(score7!=0) {
			 score3 = score3 / score7;
		 }
		 if(score7!=0) {
			 score2 = score2 / score7;
		 }
		 if(score7!=0) {
			 score1 = score1 / score7;
		 }

	    	global = (Button) findViewById(R.id.glob);
		 today = (TextView) findViewById(R.id.today);
	    	suiv = (Button) findViewById(R.id.suiv);
		 nbr = (TextView) findViewById(R.id.nbr);
		 pile = (TextView) findViewById(R.id.pile);
		 carte = (TextView) findViewById(R.id.carte);
		 bon = (TextView) findViewById(R.id.bon);
		 tor = (TextView) findViewById(R.id.tor);
		 roul= (TextView) findViewById(R.id.roul);
		 total = (TextView) findViewById(R.id.total);

		 today.setText("Votre chance Today : " + score+ " %");

			 nbr.setText("Nombre de test : " + score7);
			 pile.setText("Pile ou Face : " + score1+" %");
			 carte.setText("Carte Noir : " + score2+" %");
			 bon.setText("Le Bon Nombre : " + score3+" %");
			 tor.setText("Tout ou Rien : " + score4+" %");
			 roul.setText("La Roulette : " + score5+" %");
			 total.setText("Chance total : " + score6+" %");


	   	suiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {		
            Intent secondeActivite = new Intent(classement2.this, menu.class);
            startActivity(secondeActivite);
            classement2.this.finish();
           
                 }
          });
	   	
		global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {		
            Intent secondeActivite = new Intent(classement2.this, classement.class);
            startActivity(secondeActivite);
            classement2.this.finish();
           
                 }
          });
	 	
	 } 	 

}
