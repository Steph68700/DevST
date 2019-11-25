package com.example.goodlucktoday;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.applinks.AppLinkData;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;


import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.format.Time;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.TextView;
import android.widget.Toast;


public class menu extends Activity {

	public Button com = null;
	public Button param = null;
	public Button classement = null;
	public LoginButton loginButton;
	private CallbackManager callbackManager;
	public Button friend=null;
	String accessToken="";
	SharedPreferences preferences3;
	SharedPreferences preferences4;
	String fb_id="";
	TextView txt=null;
	TextView temps=null;
	TextView temps2=null;
	int compteur=2;
	TextView comp=null;
	int jour;
	int mois;
	Calendar c = Calendar.getInstance(); 

	

	public void onBackPressed()
	{
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(menu.this);
		alertDialog.setTitle("Exit" );
		alertDialog.setCancelable(true);
		alertDialog.setMessage("Quitter l'application ? " );          
     	alertDialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
		
		}
		});
		alertDialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				 moveTaskToBack(true);
				
			}
			});
		alertDialog.setIcon(R.drawable.exit);
		alertDialog.show();
	}

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      
        
        
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
       
        setContentView(R.layout.menu);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        friend =(Button)findViewById(R.id.friend);
        classement =(Button)findViewById(R.id.classement);
        param =(Button)findViewById(R.id.param);
        txt=(TextView)findViewById(R.id.txt);
        comp=(TextView)findViewById(R.id.comp);
        temps=(TextView)findViewById(R.id.time);
        temps2=(TextView)findViewById(R.id.time2);
        loginButton.setReadPermissions(Arrays.asList("user_friends","user_games_activity"));       
       
      preferences4 = getSharedPreferences("da", Context.MODE_PRIVATE);
        
        preferences3 = getSharedPreferences("bam", Context.MODE_PRIVATE);
		SharedPreferences.Editor edito2 = preferences3.edit();
		compteur = preferences3.getInt("bam", compteur);

        comp.setText("compteur: "+compteur +"fois" );

        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	 String appLinkUrl, previewImageUrl;

                 appLinkUrl = "https://fb.me/1455384018090473";
                 previewImageUrl = "https://www.mydomain.com/my_invite_image.jpg";
                 

                 if (AppInviteDialog.canShow()) {
                     AppInviteContent content = new AppInviteContent.Builder()
                                 .setApplinkUrl(appLinkUrl)
                                 .setPreviewImageUrl(previewImageUrl)
                                 .build();
                     AppInviteDialog.show(menu.this, content);
                 }
            }
           }); 
        
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

         
         	
         
            }
         
            @Override
            public void onCancel() {
            	 
            }
         
            @Override
            public void onError(FacebookException e) {
            	
            }
        });
        
    
        
        com=(Button)findViewById(R.id.com);
        
        com.setBackgroundColor(Color.TRANSPARENT);
        
        if(compteur ==2){
        
        	jour = c.get(Calendar.DAY_OF_YEAR);

        	 SharedPreferences.Editor editor = preferences4.edit();
             editor.putInt("da", jour);
             editor.commit();
        	
        }
        jour = preferences4.getInt("da", jour);
        mois = c.get(Calendar.DAY_OF_YEAR);
        
        temps.setText(""+jour);   
        temps2.setText(""+mois);   

      	if(jour!=mois){
		
		edito2.putInt("bam", 2);
   		 compteur= preferences3.getInt("bam", 2); 
   			edito2.commit();              			
    	comp.setText("compteur: "+compteur +" fois"); 
        
  	}
  
	  
        if(compteur>=1){
        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	 Intent secondeActivite = new Intent(menu.this, jeux1.class);
                 startActivity(secondeActivite);
                 menu.this.finish();
            	
            	if(compteur==2){
            		compteur=1;
            	  	preferences3 = getSharedPreferences("bam", Context.MODE_PRIVATE);
        			SharedPreferences.Editor edito2 = preferences3.edit();
        			compteur = preferences3.getInt("bam", 1);
        			
        			edito2.putInt("bam",1);
       	   		 compteur= preferences3.getInt("bam",1); 
       	   			edito2.commit();              			
                	
               
            		
            	}
            	if(compteur==1){
            		
            compteur=0;
            	preferences3 = getSharedPreferences("bam", Context.MODE_PRIVATE);
    			SharedPreferences.Editor edito2 = preferences3.edit();
    			compteur = preferences3.getInt("bam", 0);
    			
    			edito2.putInt("bam",0);
   	   		 compteur= preferences3.getInt("bam",0); 
   	   			edito2.commit();              			
            	
              	             
            	}  
            	
                 }
          });
        }
        else if(compteur<=0){
        	 com.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
        	Toast.makeText(getApplicationContext(), "Vous pourez rententer votre chance demain!",
        			   Toast.LENGTH_LONG).show();
             	}      
             
      });
    }

        classement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent secondeActivite = new Intent(menu.this, classement.class);
            startActivity(secondeActivite);
            menu.this.finish();
              	             
                            
                 }
          });
        param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent secondeActivite = new Intent(menu.this, param.class);
            startActivity(secondeActivite);
            menu.this.finish();
              	             
                            
                 }
          });


    }
   
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
   
    }
