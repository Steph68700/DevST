package com.example.goodlucktoday;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.LauncherActivity.ListItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BaseAdapter2 extends BaseAdapter {
	ArrayList<listItem> myList = new ArrayList<listItem>();
	Context context;
 
	// on passe le context afin d'obtenir un LayoutInflater pour utiliser notre
	// row_layout.xml
	// on passe les valeurs de notre � l'adapter
	public BaseAdapter2(Context context, ArrayList<listItem> myList) {
		this.myList = myList;
		this.context = context;
	}
 
	// retourne le nombre d'objet pr�sent dans notre liste
	@Override
	public int getCount() {
		return myList.size();
	}
 
	// retourne un �l�ment de notre liste en fonction de sa position
	@Override
	public listItem getItem(int position) {
		return myList.get(position);
	}
 
	// retourne l'id d'un �l�ment de notre liste en fonction de sa position
	@Override
	public long getItemId(int position) {
		return myList.indexOf(getItem(position));
	}


// retourne la vue d'un �l�ment de la liste
@Override
public View getView(int position, View convertView, ViewGroup parent) {
	MyViewHolder mViewHolder = null;

	// au premier appel ConvertView est null, on inflate notre layout
	if (convertView == null) {
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		convertView = mInflater.inflate(R.layout.listitem, parent, false);
		
		// nous pla�ons dans notre MyViewHolder les vues de notre layout
		mViewHolder = new MyViewHolder();
		mViewHolder.textViewName = (TextView) convertView
				.findViewById(R.id.textViewName);
		mViewHolder.textViewAge = (TextView) convertView
				.findViewById(R.id.textViewScore);
		
		// nous attribuons comme tag notre MyViewHolder � convertView
		convertView.setTag(mViewHolder);
	} else {
		// convertView n'est pas null, nous r�cup�rons notre objet MyViewHolder
		// et �vitons ainsi de devoir retrouver les vues � chaque appel de getView
		mViewHolder = (MyViewHolder) convertView.getTag();
	}

	// nous r�cup�rons l'item de la liste demand� par getView
	listItem listItem = (listItem) getItem(position);

	// nous pouvons attribuer � nos vues les valeurs de l'�l�ment de la liste
	mViewHolder.textViewName.setText(listItem.getName());
	mViewHolder.textViewAge.setText(String.valueOf(listItem.getScore())
			+ " % de chance");

	// nous retournos la vue de l'item demand�
	return convertView;
}

// MyViewHolder va nous permettre de ne pas devoir rechercher
// les vues � chaque appel de getView, nous gagnons ainsi en performance
private class MyViewHolder {
	TextView textViewName, textViewAge;
	
}

}