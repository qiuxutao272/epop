package com.example.adbar;

import java.util.ArrayList;

import com.hrtvbic.adpop.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TimeListAdapter extends BaseAdapter {
	
	private ArrayList<String> locationData;

	private Context context = null;
	// ���ּ��ض���
	private LayoutInflater inflater = null;

	public TimeListAdapter(Context context,
			ArrayList<String> locationData) {
		super();
		this.context = context;
		this.locationData = locationData;
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		// ���������������ݵ�����
		return locationData.size();
	}

	public Object getItem(int position) {
		// �ò���
		return null;
	}

	public long getItemId(int position) {
		// �ò���
		return 0;
	}

	// �˷�����convertView����grid_item�ﶨ��������������һ��ImageView��TextView
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(
					R.layout.time_list_item, null);
		}
		TextView text = (TextView) convertView
				.findViewById(R.id.family_user_location_textview);
		text.setText(locationData.get(position));
		return convertView;// �����Ѿ��ı����convertView����ʡϵͳ��Դ
	}

}
