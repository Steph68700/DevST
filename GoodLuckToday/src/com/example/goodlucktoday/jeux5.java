package com.example.goodlucktoday;

import java.util.Random;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class jeux5 extends Activity{
	
	int ch=0;
	TextView score=null;
	TextView chance=null;
	TextView nbr=null;
	TextView suiv=null;
	TextView compte=null;
	Button env=null;
	int score1;
	int score2;
	int score3;
	int score4;
	
	int compt=11;
	int y=0;
	int x=0;
	
	public void onBackPressed()
	{
		
	}
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeux5);
        
        Bundle extras = getIntent().getExtras();
        score1= extras.getInt("1111");
        
        Bundle extras2 = getIntent().getExtras();
        score2= extras2.getInt("222");
        
        Bundle extras3 = getIntent().getExtras();
        score3= extras3.getInt("33");
        
        Bundle extras4 = getIntent().getExtras();
        score4= extras4.getInt("4");
	
 nbr=(TextView)findViewById(R.id.nbr);
        
        suiv=(TextView)findViewById(R.id.suiv);
        chance=(TextView)findViewById(R.id.chance);
        score=(TextView)findViewById(R.id.score);
        compte=(TextView)findViewById(R.id.compt);
        env=(Button)findViewById(R.id.env);
        
        compte.setText("Compteur : 11");
        chance.setText("Votre chance a ce jeux : "+y);
        
        env.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(compt>0){
            
            		Random rand = new Random();
            		 x = rand.nextInt(9 - 0 + 1) + 0;
            		
            		compt=compt-1;
            
     	     env.setClickable(false);
        	
        	if(x==0){
        		nbr.setBackgroundResource(R.drawable.anim_roue0);
        		 final AnimationDrawable frameAnimation = (AnimationDrawable) nbr.getBackground(); 
 	          	// Start the animation (looped playback by default).
 	  	        frameAnimation.start();
 	  	        
 	  	  	nbr.postDelayed(new Runnable() {
        	    @Override
        	    public void run() {
        	    	  frameAnimation.stop();
        	    	  env.setClickable(true);
        	    	  
        	    	  y=y+x;
        	    	  if(compt==0){
        	    	  ch=(score1+score2+score3+score4+y)/5;
        	    	  
        	    		 Bundle params = new Bundle();
                   	   	 params.putInt("score", ch);
                   	   	 
                   	 	/* make the API call */
                   		 	new GraphRequest(
                   		 	    AccessToken.getCurrentAccessToken(),
                   		 	    "/me/scores",
                   		 	    params,
                   		 	    HttpMethod.POST,
                   		 	    new GraphRequest.Callback() {
                   		 	        public void onCompleted(GraphResponse response) {
                   		 	            /* handle the result */
                   		 	        	 if(response!=null) {
                   		 	        	
                   		 	        	Log.d("AAAA", "response: " + response.toString());
                   		 	          
                   		 	        	 }
                   		 	          
                   		 	           
                   		 	        }
                   		 	    }
                   		 	).executeAsync();
        	    	  }
              		score.setText("Votre score : "+y);
              		chance.setText("Votre chance a ce jeux : "+y);
        	    	 }
        	}, 700);   
		    
        	}
        	if(x==1){
        		nbr.setBackgroundResource(R.drawable.anim_roue1);
        		 final AnimationDrawable frameAnimation = (AnimationDrawable) nbr.getBackground(); 
 	          	// Start the animation (looped playback by default).
 	  	        frameAnimation.start();
 	  	        
 	  	  	nbr.postDelayed(new Runnable() {
        	    @Override
        	    public void run() {
        	    	  frameAnimation.stop();
        	    	  env.setClickable(true);
        	    	  
        	    	  y=y+x;
        	    	  if(compt==0){
            	    	  ch=(score1+score2+score3+score4+y)/5;
            	    	  
            	    		 Bundle params = new Bundle();
                       	   	 params.putInt("score", ch);
                       	   	 
                       	 	/* make the API call */
                       		 	new GraphRequest(
                       		 	    AccessToken.getCurrentAccessToken(),
                       		 	    "/me/scores",
                       		 	    params,
                       		 	    HttpMethod.POST,
                       		 	    new GraphRequest.Callback() {
                       		 	        public void onCompleted(GraphResponse response) {
                       		 	            /* handle the result */
                       		 	        	 if(response!=null) {
                       		 	        	
                       		 	        	Log.d("AAAA", "response: " + response.toString());
                       		 	          
                       		 	        	 }
                       		 	          
                       		 	           
                       		 	        }
                       		 	    }
                       		 	).executeAsync();
            	    	  }
              		score.setText("Votre score : "+y);
              		chance.setText("Votre chance a ce jeux : "+y);
        	    	 }
        	}, 700);   
		    
        	}
        	if(x==2){
        		nbr.setBackgroundResource(R.drawable.anim_roue2);
        		 final AnimationDrawable frameAnimation = (AnimationDrawable) nbr.getBackground(); 
 	          	// Start the animation (looped playback by default).
 	  	        frameAnimation.start();
 	  	        
 	  	  	nbr.postDelayed(new Runnable() {
        	    @Override
        	    public void run() {
        	    	  frameAnimation.stop();
        	    	  env.setClickable(true);
        	    	  
        	    	  y=y+x;
        	    	  if(compt==0){
            	    	  ch=(score1+score2+score3+score4+y)/5;
            	    	  
            	    		 Bundle params = new Bundle();
                       	   	 params.putInt("score", ch);
                       	   	 
                       	 	/* make the API call */
                       		 	new GraphRequest(
                       		 	    AccessToken.getCurrentAccessToken(),
                       		 	    "/me/scores",
                       		 	    params,
                       		 	    HttpMethod.POST,
                       		 	    new GraphRequest.Callback() {
                       		 	        public void onCompleted(GraphResponse response) {
                       		 	            /* handle the result */
                       		 	        	 if(response!=null) {
                       		 	        	
                       		 	        	Log.d("AAAA", "response: " + response.toString());
                       		 	          
                       		 	        	 }
                       		 	          
                       		 	           
                       		 	        }
                       		 	    }
                       		 	).executeAsync();
            	    	  }
              		score.setText("Votre score : "+y);
              		chance.setText("Votre chance a ce jeux : "+y);
        	    	 }
        	}, 700);   
		    
        	}
        	if(x==3){
        		nbr.setBackgroundResource(R.drawable.anim_roue3);
        		 final AnimationDrawable frameAnimation = (AnimationDrawable) nbr.getBackground(); 
 	          	// Start the animation (looped playback by default).
 	  	        frameAnimation.start();
 	  	        
 	  	  	nbr.postDelayed(new Runnable() {
        	    @Override
        	    public void run() {
        	    	  frameAnimation.stop();
        	    	  env.setClickable(true);
        	    	  
        	    	  y=y+x;
        	    	  if(compt==0){
            	    	  ch=(score1+score2+score3+score4+y)/5;
            	    	  
            	    		 Bundle params = new Bundle();
                       	   	 params.putInt("score", ch);
                       	   	 
                       	 	/* make the API call */
                       		 	new GraphRequest(
                       		 	    AccessToken.getCurrentAccessToken(),
                       		 	    "/me/scores",
                       		 	    params,
                       		 	    HttpMethod.POST,
                       		 	    new GraphRequest.Callback() {
                       		 	        public void onCompleted(GraphResponse response) {
                       		 	            /* handle the result */
                       		 	        	 if(response!=null) {
                       		 	        	
                       		 	        	Log.d("AAAA", "response: " + response.toString());
                       		 	          
                       		 	        	 }
                       		 	          
                       		 	           
                       		 	        }
                       		 	    }
                       		 	).executeAsync();
            	    	  }
              		score.setText("Votre score : "+y);
              		chance.setText("Votre chance a ce jeux : "+y);
        	    	 }
        	}, 700);   
		    
        	}
        	if(x==4){
        		nbr.setBackgroundResource(R.drawable.anim_roue4);
        		 final AnimationDrawable frameAnimation = (AnimationDrawable) nbr.getBackground(); 
 	          	// Start the animation (looped playback by default).
 	  	        frameAnimation.start();
 	  	        
 	  	  	nbr.postDelayed(new Runnable() {
        	    @Override
        	    public void run() {
        	    	  frameAnimation.stop();
        	    	  env.setClickable(true);
        	    	  
        	    	  y=y+x;
        	    	  if(compt==0){
            	    	  ch=(score1+score2+score3+score4+y)/5;
            	    	  
            	    		 Bundle params = new Bundle();
                       	   	 params.putInt("score", ch);
                       	   	 
                       	 	/* make the API call */
                       		 	new GraphRequest(
                       		 	    AccessToken.getCurrentAccessToken(),
                       		 	    "/me/scores",
                       		 	    params,
                       		 	    HttpMethod.POST,
                       		 	    new GraphRequest.Callback() {
                       		 	        public void onCompleted(GraphResponse response) {
                       		 	            /* handle the result */
                       		 	        	 if(response!=null) {
                       		 	        	
                       		 	        	Log.d("AAAA", "response: " + response.toString());
                       		 	          
                       		 	        	 }
                       		 	          
                       		 	           
                       		 	        }
                       		 	    }
                       		 	).executeAsync();
            	    	  }
              		score.setText("Votre score : "+y);
              		chance.setText("Votre chance a ce jeux : "+y);
        	    	 }
        	}, 700);   
		    
        	}
        	if(x==5){
        		nbr.setBackgroundResource(R.drawable.anim_roue5);
        		 final AnimationDrawable frameAnimation = (AnimationDrawable) nbr.getBackground(); 
 	          	// Start the animation (looped playback by default).
 	  	        frameAnimation.start();
 	  	        
 	  	  	nbr.postDelayed(new Runnable() {
        	    @Override
        	    public void run() {
        	    	  frameAnimation.stop();
        	    	  env.setClickable(true);
        	    	  
        	    	  y=y+x;
        	    	  if(compt==0){
            	    	  ch=(score1+score2+score3+score4+y)/5;
            	    	  
            	    		 Bundle params = new Bundle();
                       	   	 params.putInt("score", ch);
                       	   	 
                       	 	/* make the API call */
                       		 	new GraphRequest(
                       		 	    AccessToken.getCurrentAccessToken(),
                       		 	    "/me/scores",
                       		 	    params,
                       		 	    HttpMethod.POST,
                       		 	    new GraphRequest.Callback() {
                       		 	        public void onCompleted(GraphResponse response) {
                       		 	            /* handle the result */
                       		 	        	 if(response!=null) {
                       		 	        	
                       		 	        	Log.d("AAAA", "response: " + response.toString());
                       		 	          
                       		 	        	 }
                       		 	          
                       		 	           
                       		 	        }
                       		 	    }
                       		 	).executeAsync();
            	    	  }
              		score.setText("Votre score : "+y);
              		chance.setText("Votre chance a ce jeux : "+y);
        	    	 }
        	}, 700);   
		    
        	}
        	if(x==6){
        		nbr.setBackgroundResource(R.drawable.anim_roue6);
        		 final AnimationDrawable frameAnimation = (AnimationDrawable) nbr.getBackground(); 
 	          	// Start the animation (looped playback by default).
 	  	        frameAnimation.start();
 	  	        
 	  	  	nbr.postDelayed(new Runnable() {
        	    @Override
        	    public void run() {
        	    	  frameAnimation.stop();
        	    	  env.setClickable(true);
        	    	  
        	    	  y=y+x;
        	    	  if(compt==0){
            	    	  ch=(score1+score2+score3+score4+y)/5;
            	    	  
            	    		 Bundle params = new Bundle();
                       	   	 params.putInt("score", ch);
                       	   	 
                       	 	/* make the API call */
                       		 	new GraphRequest(
                       		 	    AccessToken.getCurrentAccessToken(),
                       		 	    "/me/scores",
                       		 	    params,
                       		 	    HttpMethod.POST,
                       		 	    new GraphRequest.Callback() {
                       		 	        public void onCompleted(GraphResponse response) {
                       		 	            /* handle the result */
                       		 	        	 if(response!=null) {
                       		 	        	
                       		 	        	Log.d("AAAA", "response: " + response.toString());
                       		 	          
                       		 	        	 }
                       		 	          
                       		 	           
                       		 	        }
                       		 	    }
                       		 	).executeAsync();
            	    	  }
              		score.setText("Votre score : "+y);
              		chance.setText("Votre chance a ce jeux : "+y);
        	    	 }
        	}, 700);   
		    
        	}
        	if(x==7){
        		nbr.setBackgroundResource(R.drawable.anim_roue7);
        		 final AnimationDrawable frameAnimation = (AnimationDrawable) nbr.getBackground(); 
 	          	// Start the animation (looped playback by default).
 	  	        frameAnimation.start();
 	  	        
 	  	  	nbr.postDelayed(new Runnable() {
        	    @Override
        	    public void run() {
        	    	  frameAnimation.stop();
        	    	  env.setClickable(true);
        	    	  
        	    	  y=y+x;
        	    	  if(compt==0){
            	    	  ch=(score1+score2+score3+score4+y)/5;
            	    	  
            	    		 Bundle params = new Bundle();
                       	   	 params.putInt("score", ch);
                       	   	 
                       	 	/* make the API call */
                       		 	new GraphRequest(
                       		 	    AccessToken.getCurrentAccessToken(),
                       		 	    "/me/scores",
                       		 	    params,
                       		 	    HttpMethod.POST,
                       		 	    new GraphRequest.Callback() {
                       		 	        public void onCompleted(GraphResponse response) {
                       		 	            /* handle the result */
                       		 	        	 if(response!=null) {
                       		 	        	
                       		 	        	Log.d("AAAA", "response: " + response.toString());
                       		 	          
                       		 	        	 }
                       		 	          
                       		 	           
                       		 	        }
                       		 	    }
                       		 	).executeAsync();
            	    	  }
              		score.setText("Votre score : "+y);
              		chance.setText("Votre chance a ce jeux : "+y);
        	    	 }
        	}, 700);   
		    
        	}
        	if(x==8){
        		nbr.setBackgroundResource(R.drawable.anim_roue8);
        		 final AnimationDrawable frameAnimation = (AnimationDrawable) nbr.getBackground(); 
 	          	// Start the animation (looped playback by default).
 	  	        frameAnimation.start();
 	  	        
 	  	  	nbr.postDelayed(new Runnable() {
        	    @Override
        	    public void run() {
        	    	  frameAnimation.stop();
        	    	  env.setClickable(true);
        	    	  
        	    	  y=y+x;
        	    	  if(compt==0){
            	    	  ch=(score1+score2+score3+score4+y)/5;
            	    	  
            	    		 Bundle params = new Bundle();
                       	   	 params.putInt("score", ch);
                       	   	 
                       	 	/* make the API call */
                       		 	new GraphRequest(
                       		 	    AccessToken.getCurrentAccessToken(),
                       		 	    "/me/scores",
                       		 	    params,
                       		 	    HttpMethod.POST,
                       		 	    new GraphRequest.Callback() {
                       		 	        public void onCompleted(GraphResponse response) {
                       		 	            /* handle the result */
                       		 	        	 if(response!=null) {
                       		 	        	
                       		 	        	Log.d("AAAA", "response: " + response.toString());
                       		 	          
                       		 	        	 }
                       		 	          
                       		 	           
                       		 	        }
                       		 	    }
                       		 	).executeAsync();
            	    	  }
              		score.setText("Votre score : "+y);
              		chance.setText("Votre chance a ce jeux : "+y);
        	    	 }
        	}, 700);   
		    
        	}
        	if(x==9){
        		nbr.setBackgroundResource(R.drawable.anim_roue9);
        		 final AnimationDrawable frameAnimation = (AnimationDrawable) nbr.getBackground(); 
 	          	// Start the animation (looped playback by default).
 	  	        frameAnimation.start();
 	  	        
 	  	  	nbr.postDelayed(new Runnable() {
        	    @Override
        	    public void run() {
        	    	  frameAnimation.stop();
        	    	  env.setClickable(true);
        	    	  
        	    	  y=y+x;
        	    	  if(compt==0){
            	    	  ch=(score1+score2+score3+score4+y)/5;
            	    	  
            	    		 Bundle params = new Bundle();
                       	   	 params.putInt("score", ch);
                       	   	 
                       	 	/* make the API call */
                       		 	new GraphRequest(
                       		 	    AccessToken.getCurrentAccessToken(),
                       		 	    "/me/scores",
                       		 	    params,
                       		 	    HttpMethod.POST,
                       		 	    new GraphRequest.Callback() {
                       		 	        public void onCompleted(GraphResponse response) {
                       		 	            /* handle the result */
                       		 	        	 if(response!=null) {
                       		 	        	
                       		 	        	Log.d("AAAA", "response: " + response.toString());
                       		 	          
                       		 	        	 }
                       		 	          
                       		 	           
                       		 	        }
                       		 	    }
                       		 	).executeAsync();
            	    	  }
        	        	
              		score.setText("Votre score : "+y);
              		chance.setText("Votre chance a ce jeux : "+y);
        	    	 }
        	}, 700);   
		    
        	}
 	        
        		compte.setText("Compteur : "+compt);
        		
        		if(compt==0){
        		 suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
        		 
        		 ch=(score1+score2+score3+score4+y)/5;
        		 
        		 Bundle params = new Bundle();
           	   	 params.putInt("scores", ch);
           	   	 
           	 	/* make the API call */
           		 	new GraphRequest(
           		 	    AccessToken.getCurrentAccessToken(),
           		 	    "/me/scores",
           		 	    params,
           		 	    HttpMethod.POST,
           		 	    new GraphRequest.Callback() {
           		 	        public void onCompleted(GraphResponse response) {
           		 	            /* handle the result */
           		 	        	 if(response!=null) {
           		 	        	
           		 	        	Log.d("AAAA", "response: " + response.toString());
           		 	          
           		 	        	 }
           		 	          
           		 	           
           		 	        }
           		 	    }
           		 	).executeAsync();
                	
        		 
        		}
        	
            	}
            	
            }
            	
            	   });
        
        
        
        	suiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(compt<=0){
            Intent secondeActivite = new Intent(jeux5.this, classement.class);
            final int play1 = getIntent().getIntExtra("1111", score1);
            secondeActivite.putExtra("11111", play1);
            final int play2 = getIntent().getIntExtra("222", score2);
            secondeActivite.putExtra("2222", play2);
            final int play3 = getIntent().getIntExtra("33", score3);
            secondeActivite.putExtra("333", play3);
            final int play4 = getIntent().getIntExtra("4", score4);
            secondeActivite.putExtra("44", play4);
            secondeActivite.putExtra("5", y);
            secondeActivite.putExtra("6", ch);
              startActivity(secondeActivite);
              jeux5.this.finish();
            	}
            	}
            	   });
              	
           
        
        	}

	}
