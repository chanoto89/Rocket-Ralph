package com.csgames.game.android;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.swarmconnect.Swarm;
import com.thundershock.game.RocketRalph;

public class AndroidLauncher extends AndroidApplication {
	
	AdView adView;
	AdRequest request;
	RelativeLayout layout;
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = true;
		//initialize(new HotAirGame(), config);
		View gameView = initializeForView(new RocketRalph(), config);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		

		layout = new RelativeLayout(this);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	    
	    layout.addView(gameView);
	    
	    
	    // At last set content view to the Relative Layout that has both gameView and adView.
	    //setContentView(layout);
	    loadAd();
	    
	    //Add this method call for the leaderboards
	    Swarm.setActive(this);
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    if(adView !=null){
	        adView.resume();
	    }
	    
	    Swarm.setActive(this);
	    //Swarm.setAllowGuests(true);
	    Swarm.init(this, 14065, "f1f78861071bca635dd99d09ac254397");
	}
	@Override
	protected void onPause() {
	    super.onPause();
	    if(adView!=null){
	        adView.pause();
	    }
	    
	    Swarm.setInactive(this);
	}
	 @Override
	protected void onDestroy() {
	    super.onDestroy();
	    if(adView!=null){
	        adView.destroy();
	    }
	}
	 
	 
	 
	 public void loadAd(){
		 
	 	adView = new AdView(this);// create new adview instance
	    adView.setAdSize(AdSize.SMART_BANNER);//com.google.android.gms.ads.AdSize.BANNER);// set the adsize
	    adView.setAdUnitId("ca-app-pub-3405228217512122/1502572693");// your ad unit id    ca-app-pub-6916351754834612/3101802628
	    adView.setBackgroundColor(Color.BLACK);
	    //Create layout params for ad view
	    RelativeLayout.LayoutParams adParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	    adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.ALIGN_PARENT_RIGHT);//set position
	    
	    //Add both game view and ad view to the relative layout-
	    layout.addView(adView, adParams);
	    //Now just request ad by creating new instance of AdRequest
	    request=new AdRequest.Builder().build();//.addTestDevice(AdRequest.DEVICE_ID_EMULATOR) to add test device
	    adView.loadAd(request);

		setContentView(layout);
	 }
}
