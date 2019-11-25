package com.simul.goodlucktoday;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BaseAdapter2 extends BaseAdapter {
	ArrayList<listItem> myList = new ArrayList<listItem>();
	Context context;
 
	// on passe le context afin d'obtenir un LayoutInflater pour utiliser notre
	// row_layout.xml
	// on passe les valeurs de notre a l'adapter
	public BaseAdapter2(Context context, ArrayList<listItem> myList) {
		this.myList = myList;
		this.context = context;
	}
 
	// retourne le nombre d'objet présent dans notre liste
	@Override
	public int getCount() {
		return myList.size();
	}
 
	// retourne un élément de notre liste en fonction de sa position
	@Override
	public listItem getItem(int position) {
		return myList.get(position);
	}
 
	// retourne l'id d'un élément de notre liste en fonction de sa position
	@Override
	public long getItemId(int position) {
		return myList.indexOf(getItem(position));
	}


// retourne la vue d'un élément de la liste
@Override
public View getView(int position, View convertView, ViewGroup parent) {
	MyViewHolder mViewHolder = null;

	// au premier appel ConvertView est null, on inflate notre layout
	if (convertView == null) {
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		convertView = mInflater.inflate(R.layout.listitem, parent, false);
		
		// nous placons dans notre MyViewHolder les vues de notre layout
		mViewHolder = new MyViewHolder();
		mViewHolder.textViewName = (TextView) convertView
				.findViewById(R.id.textViewName);
		mViewHolder.textViewAge = (TextView) convertView
				.findViewById(R.id.textViewScore);
		
		// nous attribuons comme tag notre MyViewHolder a convertView
		convertView.setTag(mViewHolder);
	} else {
		// convertView n'est pas null, nous récupérons notre objet MyViewHolder
		// et évitons ainsi de devoir retrouver les vues �achaque appel de getView
		mViewHolder = (MyViewHolder) convertView.getTag();
	}

	// nous récupèrons l'item de la liste demandé par getView
	listItem listItem = (listItem) getItem(position);

	// nous pouvons attribuer a nos vues les valeurs de l'élément de la liste
	mViewHolder.textViewName.setText(listItem.getName());
	mViewHolder.textViewAge.setText(String.valueOf(listItem.getScore())
			+ " % de chance");

	// nous retournos la vue de l'item demandé
	return convertView;
}

// MyViewHolder va nous permettre de ne pas devoir rechercher
// les vues � chaque appel de getView, nous gagnons ainsi en performance
private class MyViewHolder {
	TextView textViewName, textViewAge;
	
}

}