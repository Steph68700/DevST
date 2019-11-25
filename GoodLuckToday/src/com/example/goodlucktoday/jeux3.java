package com.example.goodlucktoday;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class jeux3 extends Activity {
	 
	TextView suiv=null;
	TextView chance=null;
	Button env= null;
	EditText nbr=null;
	Button a0=null;
	Button a1=null;
	Button a2=null;
	Button a3=null;
	Button a4=null;
	Button a5=null;
	Button a6=null;
	Button a7=null;
	Button a8=null;
	Button a9=null;
	int score1;
	int score2;
	
	public void onBackPressed()
	{
		
	}
	
	Random rand = new Random();
	int x = rand.nextInt(9 - 0 + 1) + 0;
	int max=10;
	int ch=100;
	int y=0;
	
	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.jeux3);
	        
	        Bundle extras = getIntent().getExtras();
            score1= extras.getInt("11");
           
            Bundle extras2 = getIntent().getExtras();
            score2= extras2.getInt("2");
           
            

	        env=(Button)findViewById(R.id.envoyer);
	        nbr=(EditText)findViewById(R.id.nbr2);
	        
	        suiv=(TextView)findViewById(R.id.suiv);
	        chance=(TextView)findViewById(R.id.chance);
	        
	        
	        a0=(Button)findViewById(R.id.a0);
	        a1=(Button)findViewById(R.id.a1);
	        a2=(Button)findViewById(R.id.a2);
	        a3=(Button)findViewById(R.id.a3);
	        a4=(Button)findViewById(R.id.a4);
	        a5=(Button)findViewById(R.id.a5);
	        a6=(Button)findViewById(R.id.a6);
	        a7=(Button)findViewById(R.id.a7);
	        a8=(Button)findViewById(R.id.a8);
	        a9=(Button)findViewById(R.id.a9);
	        
	        nbr.setClickable(false);
	        
	        chance.setText("Votre chance a ce jeux : "+ch+" %");
	     
	        
	        a0.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	y=0;
	          nbr.setText(String.valueOf(y));

	            	}
        });
	        
	    	a1.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {
		            	y=1;
		          nbr.setText(String.valueOf(y));
	
		            	}
	        });
	    	
	    	a2.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	y=2;
	          nbr.setText(String.valueOf(y));

	            	}
        });
	    	
	    	a3.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	y=3;
	          nbr.setText(String.valueOf(y));

	            	}
        });
	    	
	    	a4.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	y=4;
	          nbr.setText(String.valueOf(y));

	            	}
        });
	    	
	    	a5.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	y=5;
	          nbr.setText(String.valueOf(y));

	            	}
        });
	    	
	    	a6.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	y=6;
	          nbr.setText(String.valueOf(y));

	            	}
        });
	    	
	    	a7.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	y=7;
	          nbr.setText(String.valueOf(y));

	            	}
        });
	    	
	    	a8.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	y=8;
	          nbr.setText(String.valueOf(y));

	            	}
        });
	    	
	    	a9.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	y=9;
	          nbr.setText(String.valueOf(y));

	            	}
        });
	        
	        env.setOnClickListener(envListener);
}
	 private OnClickListener envListener = new OnClickListener() {

			
			@Override
			public void onClick(View v){
				
				 String aa = nbr.getText().toString();
				    if( aa.trim().equals("")){
	            		Toast.makeText(getApplicationContext(),
		                          "Veuillez entrer un nombre ", Toast.LENGTH_SHORT)
		                          .show();
	            	      return;
	            	}
				    float aaValue = Float.valueOf(aa);
				    nbr.getText().clear();
				    
				    if (aaValue!=x && aaValue==0){
				    	 a0.setBackgroundResource(R.drawable.anim_trefle);
				          AnimationDrawable frameAnimation = (AnimationDrawable) a0.getBackground(); 
			          	// Start the animation (looped playback by default).
			  	        frameAnimation.start();			  	       
			  	  	a0.postDelayed(new Runnable() {
	            	    @Override
	            	    public void run() {
	            	    	  a0.setVisibility(View.INVISIBLE );
	            	    	 }
	            	}, 1000);   
				    }
				    
				    if (aaValue!=x && aaValue==1){
				    	 a1.setBackgroundResource(R.drawable.anim_trefle_1);
				          AnimationDrawable frameAnimation = (AnimationDrawable) a1.getBackground(); 
			          	// Start the animation (looped playback by default).
			  	        frameAnimation.start();			  	       
			  	  	a1.postDelayed(new Runnable() {
	            	    @Override
	            	    public void run() {
	            	    	  a1.setVisibility(View.INVISIBLE );
	            	    	 }
	            	}, 1000);   
				    }
				    
				    if (aaValue!=x && aaValue==2){
				    	 a2.setBackgroundResource(R.drawable.anim_trefle_2);
				          AnimationDrawable frameAnimation = (AnimationDrawable) a2.getBackground(); 
			          	// Start the animation (looped playback by default).
			  	        frameAnimation.start();			  	       
			  	  	a2.postDelayed(new Runnable() {
	            	    @Override
	            	    public void run() {
	            	    	  a2.setVisibility(View.INVISIBLE );
	            	    	 }
	            	}, 1000);   
				    }
				    
				    if (aaValue!=x && aaValue==3){
				    	 a3.setBackgroundResource(R.drawable.anim_trefle_3);
				          AnimationDrawable frameAnimation = (AnimationDrawable) a3.getBackground(); 
			          	// Start the animation (looped playback by default).
			  	        frameAnimation.start();			  	       
			  	  	a3.postDelayed(new Runnable() {
	            	    @Override
	            	    public void run() {
	            	    	  a3.setVisibility(View.INVISIBLE );
	            	    	 }
	            	}, 1000);   
				    }
				    
				    if (aaValue!=x && aaValue==4){
				    	 a4.setBackgroundResource(R.drawable.anim_trefle_4);
				          AnimationDrawable frameAnimation = (AnimationDrawable) a4.getBackground(); 
			          	// Start the animation (looped playback by default).
			  	        frameAnimation.start();			  	       
			  	  	a4.postDelayed(new Runnable() {
	            	    @Override
	            	    public void run() {
	            	    	  a4.setVisibility(View.INVISIBLE );
	            	    	 }
	            	}, 1000);   
				    }
				    
				    if (aaValue!=x && aaValue==5){
				    	 a5.setBackgroundResource(R.drawable.anim_trefle_5);
				          AnimationDrawable frameAnimation = (AnimationDrawable) a5.getBackground(); 
			          	// Start the animation (looped playback by default).
			  	        frameAnimation.start();			  	       
			  	  	a5.postDelayed(new Runnable() {
	            	    @Override
	            	    public void run() {
	            	    	  a5.setVisibility(View.INVISIBLE );
	            	    	 }
	            	}, 1000);   
				    }
				    
				    if (aaValue!=x && aaValue==6){
				    	 a6.setBackgroundResource(R.drawable.anim_trefle_6);
				          AnimationDrawable frameAnimation = (AnimationDrawable) a6.getBackground(); 
			          	// Start the animation (looped playback by default).
			  	        frameAnimation.start();			  	       
			  	  	a6.postDelayed(new Runnable() {
	            	    @Override
	            	    public void run() {
	            	    	  a6.setVisibility(View.INVISIBLE );
	            	    	 }
	            	}, 1000);   
				    }
				    
				    if (aaValue!=x && aaValue==7){
				    	 a7.setBackgroundResource(R.drawable.anim_trefle_7);
				          AnimationDrawable frameAnimation = (AnimationDrawable) a7.getBackground(); 
			          	// Start the animation (looped playback by default).
			  	        frameAnimation.start();			  	       
			  	  	a7.postDelayed(new Runnable() {
	            	    @Override
	            	    public void run() {
	            	    	  a7.setVisibility(View.INVISIBLE );
	            	    	 }
	            	}, 1000);   
				    }
				    
				    if (aaValue!=x && aaValue==8){
				    	 a8.setBackgroundResource(R.drawable.anim_trefle_8);
				          AnimationDrawable frameAnimation = (AnimationDrawable) a8.getBackground(); 
			          	// Start the animation (looped playback by default).
			  	        frameAnimation.start();			  	       
			  	  	a8.postDelayed(new Runnable() {
	            	    @Override
	            	    public void run() {
	            	    	  a8.setVisibility(View.INVISIBLE );
	            	    	 }
	            	}, 1000);   
				    }
				    
				    if (aaValue!=x && aaValue==9){
				    	 a9.setBackgroundResource(R.drawable.anim_trefle_9);
				          AnimationDrawable frameAnimation = (AnimationDrawable) a9.getBackground(); 
			          	// Start the animation (looped playback by default).
			  	        frameAnimation.start();			  	       
			  	  	a9.postDelayed(new Runnable() {
	            	    @Override
	            	    public void run() {
	            	    	  a9.setVisibility(View.INVISIBLE );
	            	    	 }
	            	}, 1000);   
				    }

				    
				    if(max>aaValue && aaValue>x){
						ch=ch-11;
						chance.setText("Votre chance a ce jeux : "+ch+" %");
					}
					if(aaValue>max){
						Toast.makeText(getApplicationContext(),
		                          "Nombre trop grand ", Toast.LENGTH_SHORT)
		                          .show();
					}
					if(aaValue<x){
							ch=ch-10;
							chance.setText("Votre chance a ce jeux : "+ch+" %");
					}
					
					if(aaValue==x){
						
						a0.setClickable(false);
				    	a1.setClickable(false);
				    	 a2.setClickable(false);
			              a3.setClickable(false);
			              a4.setClickable(false);
			              a5.setClickable(false);
			              a6.setClickable(false);
			              a7.setClickable(false);
			              a8.setClickable(false);
			              a9.setClickable(false);
			              env.setClickable(false);
			              
			             
						
						 suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
						nbr.setText("Gagné");
						 suiv.setOnClickListener(new View.OnClickListener() {
					            @Override
					            public void onClick(View v) {
					            
					            Intent secondeActivite = new Intent(jeux3.this, jeux4.class);
					            final int play1 = getIntent().getIntExtra("11", score1);
					            secondeActivite.putExtra("111", play1);
					            final int play2 = getIntent().getIntExtra("2", score2);
					            secondeActivite.putExtra("22", play2);
					            secondeActivite.putExtra("3", ch);
					              startActivity(secondeActivite);
					              jeux3.this.finish();
					            	}
					            	   });
			         
					}
				
			}
			};
			}