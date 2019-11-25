package com.example.goodlucktoday;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class jeux2 extends Activity{
	
	TextView chance=null;
	TextView suiv=null;
	Button a1=null;
	Button a2=null;
	Button a3=null;
	Button a4=null;
	Button a5=null;
	Button a6=null;
	Button a7=null;
	Button a8=null;
	Button a9=null;
	int ch=0;
	ImageView trefle=null;
	int score1=0;
	
	public void onBackPressed()
	{
		
	}
	
	
	Random rand = new Random();
	int y = rand.nextInt(8 - 0 + 1) + 0;
	
	int x=0;
	int z=0;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.jeux2);
	        
	        Bundle extras = getIntent().getExtras();
            score1= extras.getInt("1");
           
	        
	        a1=(Button)findViewById(R.id.a1);
	        a2=(Button)findViewById(R.id.a2);
	        a3=(Button)findViewById(R.id.a3);
	        a4=(Button)findViewById(R.id.a4);
	        a5=(Button)findViewById(R.id.a5);
	        a6=(Button)findViewById(R.id.a6);
	        a7=(Button)findViewById(R.id.a7);
	        a8=(Button)findViewById(R.id.a8);
	        a9=(Button)findViewById(R.id.a9);
	        
	        suiv=(TextView)findViewById(R.id.suiv);
	        
	        chance=(TextView)findViewById(R.id.chance);
	        
	        trefle=(ImageView)findViewById(R.id.trefle);
	        
	        chance.setText("Votre chance a ce jeux : "+ch+" %");
	       
	        a1.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	          		        
	            	
	            	x=0; 
	            	if(x!=y){
	            		z=z+1;
	            		ch=ch+12;
	            		chance.setText("Votre chance a ce jeux : "+ch+" %");
	            	
	            		a1.setBackgroundResource(R.drawable.carte_blanche);
	            		AnimationDrawable frameAnimation = (AnimationDrawable) a1.getBackground(); 
		            	// Start the animation (looped playback by default).
		    	        frameAnimation.start();
	            		
		    	        if(z==2){
		  	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille1));
		  	        }
		  	        
		  	        if(z==4){
		  	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille2));
		  	        }
		  	        
		  	        if(z==6){
		  	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille3));
		  	        }
		  	        
		  	        if(z==8){
		  	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille4));
		  	        }
	            	}
	            	
	           	 else if (x==y) {
	           		 
	           		a1.setBackgroundResource(R.drawable.carte_noir);
	           		AnimationDrawable frameAnimation = (AnimationDrawable) a1.getBackground(); 
	            	// Start the animation (looped playback by default).
	    	        frameAnimation.start();
	           		 
	    	        suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
	   	        
		              a2.setClickable(false);
		              a3.setClickable(false);
		              a4.setClickable(false);
		              a5.setClickable(false);
		              a6.setClickable(false);
		              a7.setClickable(false);
		              a8.setClickable(false);
		              a9.setClickable(false);
		              
	     	        	suiv.setOnClickListener(new View.OnClickListener() {
	     		            @Override
	     		            public void onClick(View v) {
	     		            	
	     		            	  Intent secondeActivite = new Intent(jeux2.this, jeux3.class);
			     		           final int play1 = getIntent().getIntExtra("1",score1);
			     		            secondeActivite.putExtra("11",play1);
			     		           secondeActivite.putExtra("2", ch);
			     		         startActivity(secondeActivite);
			     		        jeux2.this.finish();
	     	
	     		            	}
	     	        });
	     	        	}
	            }
	            });
	        
	        a2.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	      
	            	x=1; 
	            	if(x!=y){
	            		z=z+1;
	            		
	            		a2.setBackgroundResource(R.drawable.carte_blanche);
	            		AnimationDrawable frameAnimation = (AnimationDrawable) a2.getBackground(); 
		            	// Start the animation (looped playback by default).
		    	        frameAnimation.start();
	            		
	            		ch=ch+12;
	            		chance.setText("Votre chance a ce jeux : "+ch+" %");
	            		
	            		 if(z==2){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille1));
	       	        }
	       	        
	       	        if(z==4){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille2));
	       	        }
	       	        
	       	        if(z==6){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille3));
	       	        }
	       	        
	       	        if(z==8){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille4));
	       	        }
	            	
	            	}
	           	 else if (x==y) {
	           		 
	           		a2.setBackgroundResource(R.drawable.carte_noir);
	           		AnimationDrawable frameAnimation = (AnimationDrawable) a2.getBackground(); 
	            	// Start the animation (looped playback by default).
	    	        frameAnimation.start();
	           		 
	    	        suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
	    	        
	   	              a1.setClickable(false);
		              a2.setClickable(false);
		              a3.setClickable(false);
		              a4.setClickable(false);
		              a5.setClickable(false);
		              a6.setClickable(false);
		              a7.setClickable(false);
		              a8.setClickable(false);
		              a9.setClickable(false);
	     	        	suiv.setOnClickListener(new View.OnClickListener() {
	     		            @Override
	     		            public void onClick(View v) {
	     		            	
	     		            	  Intent secondeActivite = new Intent(jeux2.this, jeux3.class);
			     		           final int play1 = getIntent().getIntExtra("1",score1);
			     		            secondeActivite.putExtra("11",play1);
			     		           secondeActivite.putExtra("2", ch);
			     		         startActivity(secondeActivite);
			     		        jeux2.this.finish();
	     	
	     		            	}
	     	        });
	     	        	}
	            }
	            });
	        
	        a3.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	
	            	x=2; 
	            	if(x!=y){
	            		z=z+1;
	            		
	            		a3.setBackgroundResource(R.drawable.carte_blanche);
	            		AnimationDrawable frameAnimation = (AnimationDrawable) a3.getBackground(); 
		            	// Start the animation (looped playback by default).
		    	        frameAnimation.start();
	            		
	            		ch=ch+12;
	            		chance.setText("Votre chance a ce jeux : "+ch+" %");
	            		
	            		 if(z==2){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille1));
	       	        }
	       	        
	       	        if(z==4){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille2));
	       	        }
	       	        
	       	        if(z==6){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille3));
	       	        }
	       	        
	       	        if(z==8){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille4));
	       	        }
	            	}
	           	 else if (x==y) {
	           		 
	           		a3.setBackgroundResource(R.drawable.carte_noir);
	           		AnimationDrawable frameAnimation = (AnimationDrawable) a3.getBackground(); 
	            	// Start the animation (looped playback by default).
	    	        frameAnimation.start();
	           		 
	    	        suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
	    	        
	   	              a1.setClickable(false);
		              a2.setClickable(false);
		              a3.setClickable(false);
		              a4.setClickable(false);
		              a5.setClickable(false);
		              a6.setClickable(false);
		              a7.setClickable(false);
		              a8.setClickable(false);
		              a9.setClickable(false);
	     	        	suiv.setOnClickListener(new View.OnClickListener() {
	     		            @Override
	     		            public void onClick(View v) {
	     		            	
	     		            	  Intent secondeActivite = new Intent(jeux2.this, jeux3.class);
			     		           final int play1 = getIntent().getIntExtra("1",score1);
			     		            secondeActivite.putExtra("11",play1);
			     		           secondeActivite.putExtra("2", ch);
			     		         startActivity(secondeActivite);
			     		        jeux2.this.finish();
	     	
	     		            	}
	     	        });
	     	        	}
	            }
	            });
	        
	        a4.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	
	          	
	            	x=3; 
	            	if(x!=y){
	            		z=z+1;
	            		
	            		a4.setBackgroundResource(R.drawable.carte_blanche);
	            		AnimationDrawable frameAnimation = (AnimationDrawable) a4.getBackground(); 
		            	// Start the animation (looped playback by default).
		    	        frameAnimation.start();
	            		
	            		ch=ch+12;
	            		chance.setText("Votre chance a ce jeux : "+ch+" %");
	            		
	            		 if(z==2){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille1));
	       	        }
	       	        
	       	        if(z==4){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille2));
	       	        }
	       	        
	       	        if(z==6){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille3));
	       	        }
	       	        
	       	        if(z==8){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille4));
	       	        }
	            	
	            	}
	           	 else if (x==y) {
	           		 
	           		a4.setBackgroundResource(R.drawable.carte_noir);
	           		AnimationDrawable frameAnimation = (AnimationDrawable) a4.getBackground(); 
	            	// Start the animation (looped playback by default).
	    	        frameAnimation.start();
	           		 
	    	        suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
	    	        
	   	              a1.setClickable(false);
		              a2.setClickable(false);
		              a3.setClickable(false);
		              a4.setClickable(false);
		              a5.setClickable(false);
		              a6.setClickable(false);
		              a7.setClickable(false);
		              a8.setClickable(false);
		              a9.setClickable(false);
	     	        	suiv.setOnClickListener(new View.OnClickListener() {
	     		            @Override
	     		            public void onClick(View v) {
	     		            	
	     		            	  Intent secondeActivite = new Intent(jeux2.this, jeux3.class);
			     		           final int play1 = getIntent().getIntExtra("1",score1);
			     		            secondeActivite.putExtra("11",play1);
			     		           secondeActivite.putExtra("2", ch);
			     		         startActivity(secondeActivite);
			     		        jeux2.this.finish();
	     	
	     		            	}
	     	        });
	     	        	}
	            }
	            });
	        
	        a5.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	x=4; 
	            	if(x!=y){
	            		z=z+1;
	            		
	            		a5.setBackgroundResource(R.drawable.carte_blanche);
	            		AnimationDrawable frameAnimation = (AnimationDrawable) a5.getBackground(); 
		            	// Start the animation (looped playback by default).
		    	        frameAnimation.start();
	            		
	            		ch=ch+13;
	            		chance.setText("Votre chance a ce jeux : "+ch+" %");
	            		
	            		 if(z==2){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille1));
	       	        }
	       	        
	       	        if(z==4){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille2));
	       	        }
	       	        
	       	        if(z==6){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille3));
	       	        }
	       	        
	       	        if(z==8){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille4));
	       	        }

	            	}
	           	 else if (x==y) {
	           		 
	           		a5.setBackgroundResource(R.drawable.carte_noir);
	           		AnimationDrawable frameAnimation = (AnimationDrawable) a5.getBackground(); 
	            	// Start the animation (looped playback by default).
	    	        frameAnimation.start();
	           		 
	    	        suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
	    	        
	   	              a1.setClickable(false);
		              a2.setClickable(false);
		              a3.setClickable(false);
		              a4.setClickable(false);
		              a5.setClickable(false);
		              a6.setClickable(false);
		              a7.setClickable(false);
		              a8.setClickable(false);
		              a9.setClickable(false);
	     	        	suiv.setOnClickListener(new View.OnClickListener() {
	     		            @Override
	     		            public void onClick(View v) {
	     		            	
	     		            	  Intent secondeActivite = new Intent(jeux2.this, jeux3.class);
			     		           final int play1 = getIntent().getIntExtra("1",score1);
			     		            secondeActivite.putExtra("11",play1);
			     		           secondeActivite.putExtra("2", ch);
			     		         startActivity(secondeActivite);
			     		        jeux2.this.finish();
	     	
	     		            	}
	     	        });
	     	        	}
	            }
	            });
	        
	        a6.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	x=5; 
	            	if(x!=y){
	            		z=z+1;
	            		
	            		a6.setBackgroundResource(R.drawable.carte_blanche);
	            		AnimationDrawable frameAnimation = (AnimationDrawable) a6.getBackground(); 
		            	// Start the animation (looped playback by default).
		    	        frameAnimation.start();
	            		
	            		ch=ch+13;
	            		chance.setText("Votre chance a ce jeux : "+ch+" %");
	            		
	            		 if(z==2){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille1));
	       	        }
	       	        
	       	        if(z==4){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille2));
	       	        }
	       	        
	       	        if(z==6){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille3));
	       	        }
	       	        
	       	        if(z==8){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille4));
	       	        }
	            	
	            	}
	           	 else if (x==y) {
	           		 
	           		a6.setBackgroundResource(R.drawable.carte_noir);
	           		AnimationDrawable frameAnimation = (AnimationDrawable) a6.getBackground(); 
	            	// Start the animation (looped playback by default).
	    	        frameAnimation.start();
	           		 
	    	        suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
	    	        
	   	              a1.setClickable(false);
		              a2.setClickable(false);
		              a3.setClickable(false);
		              a4.setClickable(false);
		              a5.setClickable(false);
		              a6.setClickable(false);
		              a7.setClickable(false);
		              a8.setClickable(false);
		              a9.setClickable(false);
	     	        	suiv.setOnClickListener(new View.OnClickListener() {
	     		            @Override
	     		            public void onClick(View v) {
	     		            	
	     		            	  Intent secondeActivite = new Intent(jeux2.this, jeux3.class);
			     		           final int play1 = getIntent().getIntExtra("1",score1);
			     		            secondeActivite.putExtra("11",play1);
			     		           secondeActivite.putExtra("2", ch);
			     		         startActivity(secondeActivite);
			     		        jeux2.this.finish();
	     	
	     		            	}
	     	        });
	     	        	}
	            }
	            });
	        
	        a7.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	x=6; 
	            	if(x!=y){
	            		z=z+1;
	            		
	            		a7.setBackgroundResource(R.drawable.carte_blanche);
	            		AnimationDrawable frameAnimation = (AnimationDrawable) a7.getBackground(); 
		            	// Start the animation (looped playback by default).
		    	        frameAnimation.start();
	            		
	            		ch=ch+12;
	            		chance.setText("Votre chance a ce jeux : "+ch+" %");
	            		
	            		 if(z==2){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille1));
	       	        }
	       	        
	       	        if(z==4){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille2));
	       	        }
	       	        
	       	        if(z==6){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille3));
	       	        }
	       	        
	       	        if(z==8){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille4));
	       	        }
	            	
	            	}
	           	 else if (x==y) {
	           		 
	           		a7.setBackgroundResource(R.drawable.carte_noir);
	           		AnimationDrawable frameAnimation = (AnimationDrawable) a7.getBackground(); 
	            	// Start the animation (looped playback by default).
	    	        frameAnimation.start();
	           		 
	    	        suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
	    	        
	   	              a1.setClickable(false);
		              a2.setClickable(false);
		              a3.setClickable(false);
		              a4.setClickable(false);
		              a5.setClickable(false);
		              a6.setClickable(false);
		              a7.setClickable(false);
		              a8.setClickable(false);
		              a9.setClickable(false);
	     	        	suiv.setOnClickListener(new View.OnClickListener() {
	     		            @Override
	     		            public void onClick(View v) {
	     		            	
	     		            	  Intent secondeActivite = new Intent(jeux2.this, jeux3.class);
			     		           final int play1 = getIntent().getIntExtra("1",score1);
			     		            secondeActivite.putExtra("11",play1);
			     		           secondeActivite.putExtra("2", ch);
			     		         startActivity(secondeActivite);
			     		        jeux2.this.finish();
	     	
	     		            	}
	     	        });
	     	        	}
	            }
	            });
	        
	        a8.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	x=7; 
	            	if(x!=y){
	            		z=z+1;
	            		
	            		a8.setBackgroundResource(R.drawable.carte_blanche);
	            		AnimationDrawable frameAnimation = (AnimationDrawable) a8.getBackground(); 
		            	// Start the animation (looped playback by default).
		    	        frameAnimation.start();
	            		
	            		ch=ch+12;
	            		chance.setText("Votre chance a ce jeux : "+ch+" %");
	            		
	            		 if(z==2){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille1));
	       	        }
	       	        
	       	        if(z==4){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille2));
	       	        }
	       	        
	       	        if(z==6){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille3));
	       	        }
	       	        
	       	        if(z==8){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille4));
	       	        }
	            	
	            	}
	           	 else if (x==y) {
	           		 
	           		a8.setBackgroundResource(R.drawable.carte_noir);
	           		AnimationDrawable frameAnimation = (AnimationDrawable) a8.getBackground(); 
	            	// Start the animation (looped playback by default).
	    	        frameAnimation.start();
	           		 
	    	        suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
	    	        
	   	              a1.setClickable(false);
		              a2.setClickable(false);
		              a3.setClickable(false);
		              a4.setClickable(false);
		              a5.setClickable(false);
		              a6.setClickable(false);
		              a7.setClickable(false);
		              a8.setClickable(false);
		              a9.setClickable(false);
	     	        	suiv.setOnClickListener(new View.OnClickListener() {
	     		            @Override
	     		            public void onClick(View v) {
	     		            	
	     		            	  Intent secondeActivite = new Intent(jeux2.this, jeux3.class);
			     		           final int play1 = getIntent().getIntExtra("1",score1);
			     		            secondeActivite.putExtra("11",play1);
			     		           secondeActivite.putExtra("2", ch);
			     		         startActivity(secondeActivite);
			     		        jeux2.this.finish();
	     	
	     		            	}
	     	        });
	     	        	}
	            }
	            });

	        
	        a9.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	x=8; 
	            	if(x!=y){
	            		z=z+1;
	            		
	            		a9.setBackgroundResource(R.drawable.carte_blanche);
	            		AnimationDrawable frameAnimation = (AnimationDrawable) a9.getBackground(); 
		            	// Start the animation (looped playback by default).
		    	        frameAnimation.start();
	            		
	            		ch=ch+13;
	            		chance.setText("Votre chance a ce jeux : "+ch+" %");
	            		
	            		 if(z==2){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille1));
	       	        }
	       	        
	       	        if(z==4){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille2));
	       	        }
	       	        
	       	        if(z==6){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille3));
	       	        }
	       	        
	       	        if(z==8){
	       	        	  trefle.setBackgroundDrawable(getResources().getDrawable(R.drawable.feuille4));
	       	        }
	            	
	            	}
	            	 else if (x==y) {
	            		 
	            	
	                      
	            		 a9.setBackgroundResource(R.drawable.carte_noir);
	 	           		AnimationDrawable frameAnimation = (AnimationDrawable) a9.getBackground(); 
	 	            	// Start the animation (looped playback by default).
	 	    	        frameAnimation.start();
	            		 
	 	    	       suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
	 	    	        
		   	              a1.setClickable(false);
	 		              a2.setClickable(false);
	 		              a3.setClickable(false);
	 		              a4.setClickable(false);
	 		              a5.setClickable(false);
	 		              a6.setClickable(false);
	 		              a7.setClickable(false);
	 		              a8.setClickable(false);
	 		              a9.setClickable(false);

		     	        	suiv.setOnClickListener(new View.OnClickListener() {
		     		            @Override
		     		            public void onClick(View v) {
		     		            	
		     		            Intent secondeActivite = new Intent(jeux2.this, jeux3.class);
		     		           final int play1 = getIntent().getIntExtra("1",score1);
		     		            secondeActivite.putExtra("11",play1);
		     		           secondeActivite.putExtra("2", ch);
		     		         startActivity(secondeActivite);
		     		        jeux2.this.finish();
		     	
		     		            	}
		     	        });
		     	        	}
	            }
	            });
	
	        }
	       
	        
	        }
	        



