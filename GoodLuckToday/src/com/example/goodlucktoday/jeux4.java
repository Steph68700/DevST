package com.example.goodlucktoday;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class jeux4 extends Activity{
	
	
	Button env =null;
	Button nain = null;
	Button sol=null;
	RelativeLayout fond=null;
	
	TextView cent=null;
	TextView zero=null;
	TextView chance=null;
	TextView suiv=null;
	int score1;
	int score2;
	int score3;
	
	public void onBackPressed()
	{
		
	}
	
	int ch=0;	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeux4);
        
        Bundle extras = getIntent().getExtras();
        score1= extras.getInt("111");
        
        Bundle extras2 = getIntent().getExtras();
        score2= extras2.getInt("22");
        
        Bundle extras3 = getIntent().getExtras();
        score3= extras3.getInt("3");
        
        suiv=(TextView)findViewById(R.id.suiv);
        chance=(TextView)findViewById(R.id.chance);
        cent=(TextView)findViewById(R.id.cent);
        zero=(TextView)findViewById(R.id.zero);
        env=(Button)findViewById(R.id.env);
        nain=(Button)findViewById(R.id.nain);
        sol=(Button)findViewById(R.id.sol);
        fond=(RelativeLayout)findViewById(R.id.fond);
      
        
        
        nain.setVisibility(View.INVISIBLE);
        sol.setVisibility(View.INVISIBLE);
        
        cent.setText("");
	  	    zero.setText("");
	  	    chance.setText("Votre chance a ce jeux : ?%");
        
        env.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	fond.setBackgroundDrawable(getResources().getDrawable(R.drawable.fond4));
            	 suiv.setBackgroundDrawable(getResources().getDrawable(R.drawable.suiv));
            	
            	Random rand = new Random();
            	int x = rand.nextInt(1 - 0 + 1) + 0;
            	
            	int y = 0;
            	
            	
            	env.setVisibility(View.INVISIBLE);
 
            	 if(x==y){
                 	ch=100;
                 	nain.setVisibility(View.VISIBLE);
                 	sol.setVisibility(View.VISIBLE);
                 	
                 	 sol.setBackgroundResource(R.drawable.anim_soleil);
   		          AnimationDrawable frameAnimation1 = (AnimationDrawable) sol.getBackground(); 
   	          	// Start the animation (looped playback by default).
   	  	        frameAnimation1.start();	
   	  	        
                 	chance.setText("Votre chance a ce jeux : 100%");
                 	zero.setText("");
                 	cent.setText("100%");
                 		  	       
                 }
                 if(x!=y){
                 	ch=0;
                 	chance.setText("Votre chance a ce jeux : 0%");
                 	nain.setBackgroundDrawable(getResources().getDrawable(R.drawable.nain5));
                 	sol.setBackgroundDrawable(getResources().getDrawable(R.drawable.eclaire2));
                 	nain.setVisibility(View.VISIBLE);
                 	sol.setVisibility(View.VISIBLE);
                 	
                	 sol.setBackgroundResource(R.drawable.anim_eclaire);
  		          AnimationDrawable frameAnimation1 = (AnimationDrawable) sol.getBackground(); 
  	          	// Start the animation (looped playback by default).
  	  	        frameAnimation1.start();
  	  	        
  	  	        cent.setText("");
  	  	    zero.setText("0%");
  	  	        
  	
                 }
                
                 
                 suiv.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
   
                     Intent secondeActivite = new Intent(jeux4.this, jeux5.class);
                     final int play1 = getIntent().getIntExtra("111", score1);
                     secondeActivite.putExtra("1111", play1);
                     final int play2 = getIntent().getIntExtra("22", score2);
                     secondeActivite.putExtra("222", play2);
                     final int play3 = getIntent().getIntExtra("3", score3);
                     secondeActivite.putExtra("33", play3);
                     secondeActivite.putExtra("4", ch);
                       startActivity(secondeActivite);
                       jeux4.this.finish();
                     	}
                     	   });
                 
            	
            }
 	   });
        
       
        
	}
}
