package com.ad.Intromi;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ClipData.Item;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SavedCardsList extends Activity {

	    private static final String TAG = "IntroMi/SavedCardsList";

		private static final boolean D = true;

		private ListView listViewSavedCards;
	
	    private ArrayList<Profile> results;
	    private SavedCards mSaveCards; 
	  
	    private CardsListBaseAdapter listAdapter;
	    private Cards cards;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saved_cards_list);
		  results = new ArrayList<Profile>();
	    	listViewSavedCards = (ListView) findViewById(R.id.listViewSavedCards); 
	    	 mSaveCards = new SavedCards();
		listAdapter = new CardsListBaseAdapter(this,results);
		
		 cards = new Cards(getBaseContext());
		 mSaveCards = cards.loadCards();
		if (mSaveCards!=null)
		{
//		 System.out.println("tttt" +cards.mSaveCards.profileArrayList.get(3).getName());
		 
		 
		
	
			

		


    	ItemDetails item_details = new ItemDetails();
    	item_details.setName("name");
    	item_details.setItemDescription("ii");
    	item_details.setPrice("e");
    	item_details.setSite("u");
    	item_details.setImg("");
    	item_details.setProfessionlaHeadLine("he");
    	item_details.setMission("mission");
    	
    	
    	 for (int i =0  ;i<mSaveCards.profileArrayList.size();i++){
	          if (D) Log.v(TAG,"this is the cards i have" + mSaveCards.profileArrayList.get(i).getName()); 	  
	// 	 mSaveCards.profileArrayList.get(i).setName("dddddddd");
	// 	 mSaveCards.profileArrayList.get(i).set("wqewew");
	          mSaveCards.profileArrayList.get(i).setImg(mSaveCards.profileArrayList.get(i).getPicture()); 
    	results.add(mSaveCards.profileArrayList.get(i));
        
  	listViewSavedCards.setAdapter(listAdapter);
    	listAdapter.notifyDataSetChanged();
    	 }
		}
		
   	  listViewSavedCards.setOnItemClickListener(new OnItemClickListener() {

	        public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
	                long arg3) {
	      	Profile p = results.get(pos);
			Intent intent = new Intent(SavedCardsList.this, DetailActivity.class);
			intent.putExtra("url", p.getName());
			intent.putExtra("title", p.getEmail());
			intent.putExtra("desc", p.getMobilePhoneNum());
			intent.putExtra("head_line",p.getProfessionalHeadLine());
			intent.putExtra("site",p.getSite());
			intent.putExtra("mission",p.getMission());
			intent.putExtra("photo", getPhotoStringFromBitmap(p.getImg()));
			intent.putExtra("name",p.getName());
			
			
			
			startActivity(intent);
	           
/*
* 
*     	item_details.setName(name);
  	item_details.setItemDescription(phone);
  	item_details.setPrice(email);
  	item_details.setSite(site);
  	item_details.setImg(img);
  	results.add(item_details);
*/

	        }
	    });
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.saved_cards_list, menu);
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
    }
	
	  public String getPhotoStringFromBitmap(Bitmap bm){
			 
			 String ba1 = null;;
				if (bm!=null)
				{
				Bitmap bitmapOrg = bm;
		     ByteArrayOutputStream bao = new ByteArrayOutputStream();
		     //Resize the image
		  //   double width = bitmapOrg.getWidth();
		  //   double height = bitmapOrg.getHeight();
		  //   double ratio = 400/width;
		  //   int newheight = (int)(ratio*height);
		  //   bitmapOrg = Bitmap.createScaledBitmap(bitmapOrg, 400, newheight, true);
		  //   System.out.println("———-width" + width);
		  //   System.out.println("———-height" + height);
		     bitmapOrg.compress(Bitmap.CompressFormat.PNG, 95, bao);
		     byte[] ba = bao.toByteArray();
		     ba1 = Base64.encodeToString(ba, 0);
		       	return ba1;
				}
				else {
					return null;
				}
				
		 
		 }
	  
}

