package com.example.goodlucktoday;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class jeux1 extends Activity {
	
	int x=0;
	int compteur=0;
	int compt=0;
	int decompteur=5;
	int ch=50;
		
	TextView chance=null;
	TextView reste=null;
	TextView pour=null;
	TextView reponse=null;
	TextView choix=null;
	TextView suiv=null;
	Button pile=null;
	Button face=null;
	
	public void onBackPressed()
	{
		
	}
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.jeux1);
	        
	        pile=(Button)findViewById(R.id.pile1);
	        face=(Button)findViewById(R.id.face1);
	        suiv=(TextView)findViewById(R.id.suiv);
	        
	        chance=(TextView)findViewById(R.id.chance);
	        reste=(TextView)findViewById(R.id.reste);
	        pour=(TextView)findViewById(R.id.pour);
	        reponse=(TextView)findViewById(R.id.reponse);
	        choix=(TextView)findViewById(R.id.choix);
	        suiv=(TextView)findViewById(R.id.suiv);
	        
	        chance.setText("Votre chance a ce jeux : "+ch+" %");
	        
	       
	        pile.setOnClickListener(new View.OnClickListener() {
	            @SuppressWarnings({ "deprecation" })
				@Override
	            public void onClick(View v) {
	            	x=0; 
	            	if(compteur<5){
	            	compteur=compteur+1;
	            	decompteur=decompteur-1;
	            	
	            	
	            	choix.setText("Votre choix: pile");
	            	reste.setText("Vous avez "+decompteur+" lancé");
	            	
	            	Random rand = new Random();
	            	int y = rand.nextInt(1 - 0 + 1) + 0;
	            	
	            	
	            	if(y==0){
	                  	reponse.setBackgroundDrawable(getResources().getDrawable(R.drawable.pile_face));
	        	    	Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_pile_face);
		            	reponse.startAnimation(animation);
		            
		            	reponse.postDelayed(new Runnable() {
		            	    @Override
		            	    public void run() {
		            	    	reponse.setBackgroundDrawable(getResources().getDrawable(R.drawable.pile));	
		            	    }
		            	}, 1000);
	            		
	            	}
	            	else if(y==1){
	            	  	reponse.setBackgroundDrawable(getResources().getDrawable(R.drawable.pile_face));
	        	    	Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_pile_face);
		            	reponse.startAnimation(animation);
		           
		            	reponse.postDelayed(new Runnable() {
		            	    @Override
		            	    public void run() {
		            	    	reponse.setBackgroundDrawable(getResources().getDrawable(R.drawable.face));	
		            	    }
		            	}, 1000);
	            		
		            	}
	            	
	            	if (x==y){
	            		compt=compt+1;
	            		pour.setText(""+compt+"/"+compteur);
	            		ch=ch+10;
	            		chance.setText("Votre chance a ce jeux : "+ch+" %");
	            	}
	            	else if (x!=y){
	            		compt=compt+0;
	            		pour.setText(""+compt+"/"+compteur);
	            		ch=ch-10;
	            		chance.setText("Votre chance a ce jeux : "+ch+" %");
	            	}
	            }
	            	if(compteur>=5){
			        	choix.setText("Vous avez déja lancé la pièce 5 fois");
			        	suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
		            }
	            }
	          });
	        
	        face.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	            	
	            	if(compteur<5){ 
	            		x=1;
	            	compteur=compteur+1;
	            	choix.setText("Votre choix: face");
	            	decompteur=decompteur-1;
	            	reste.setText("Vous avez "+decompteur+" lancé");
	            	
	            	Random rand = new Random();
	            	int y = rand.nextInt(1 - 0 + 1) + 0;
	            	
	            	if(y==0){
	            	   	reponse.setBackgroundDrawable(getResources().getDrawable(R.drawable.pile_face));
	        	    	Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_pile_face);
		            	reponse.startAnimation(animation);
		            
		            	reponse.postDelayed(new Runnable() {
		            	    @Override
		            	    public void run() {
		            	    	reponse.setBackgroundDrawable(getResources().getDrawable(R.drawable.pile));	
		            	    }
		            	}, 1000);
		            	}
	            	
		            else if(y==1){
		            	reponse.setBackgroundDrawable(getResources().getDrawable(R.drawable.pile_face));
	        	    	Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_pile_face);
		            	reponse.startAnimation(animation);
		           
		            	reponse.postDelayed(new Runnable() {
		            	    @Override
		            	    public void run() {
		            	    	reponse.setBackgroundDrawable(getResources().getDrawable(R.drawable.face));	
		            	    }
		            	}, 1000);
			            }
	            	
	            	if (x==y){
	            		compt=compt+1;
	            		pour.setText(""+compt+"/"+compteur);
	            		ch=ch+10;
	            		chance.setText("Votre chance a ce jeux : "+ch+" %");
	            	}
	            	else if (x!=y){
	            		compt=compt+0;
	            		pour.setText(""+compt+"/"+compteur);
	            		ch=ch-10;
	            		chance.setText("Votre chance a ce jeux : "+ch+" %");
	            	}
	            	}
	            
	            if(compteur>=5){
		        	choix.setText("Vous avez déja lancé la pièce 5 fois");
		        	suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
			            
	            }
	            
	            
		        }
	          });
	         
	       
	        
	        suiv.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	             	 
	            	if(compteur>=5){
	            		
	            Intent secondeActivite = new Intent(jeux1.this, jeux2.class);
	            secondeActivite.putExtra("1", ch);
	            startActivity(secondeActivite);
	            jeux1.this.finish();
	            	}
	            	else if (compteur <=5){
	            		choix.setText("Vos n'avez pas terminé vos lancés");
	            	}
	              	             
	                 }
	          });
	        
	        
	            }
	        
	 }
