package com.jdroid.android.tabs;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jdroid.android.R;
import com.jdroid.android.view.BadgeView;

/**
 * Custom view for TabWidgets.
 */
public class CustomTabView extends LinearLayout {
	
	private Handler handler;
	private TabAction tab;
	
	public CustomTabView(Context context) {
		super(context);
	}
	
	/**
	 * @param context - Context.
	 * @param tab the {@link TabAction}
	 * @param textSize - Text size.
	 * @param drawableTextColor - Text color.
	 * @param drawableBackgroundColor - Background color.
	 */
	public CustomTabView(Context context, final TabAction tab, int textSize, int drawableTextColor,
			int drawableBackgroundColor) {
		super(context);
		
		this.tab = tab;
		handler = new Handler() {
			
			@Override
			public void handleMessage(Message m) {
				setNotifications(TabBadgeNotificationManager.getBadges(tab));
			}
		};
		
		LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.custom_tab_view, this);
		
		setOrientation(LinearLayout.VERTICAL);
		setGravity(Gravity.CENTER);
		
		ImageView imageView = (ImageView)findViewById(R.id.tabImage);
		imageView.setImageResource(tab.getIconResource());
		
		TextView textView = (TextView)findViewById(R.id.tabText);
		textView.setText(tab.getNameResource());
		textView.setTextSize(textSize);
		XmlResourceParser parser = getResources().getXml(drawableTextColor);
		ColorStateList colors = null;
		try {
			colors = ColorStateList.createFromXml(getResources(), parser);
			textView.setTextColor(colors);
		} catch (XmlPullParserException e) {
			textView.setTextColor(Color.WHITE);
		} catch (IOException e) {
			textView.setTextColor(Color.WHITE);
		}
		
		Drawable bkgColor = getResources().getDrawable(drawableBackgroundColor);
		setBackgroundDrawable(bkgColor);
		
		TabBadgeNotificationManager.registerTabHandler(tab, handler);
		setNotifications(TabBadgeNotificationManager.getBadges(tab));
	}
	
	/**
	 * @see android.view.View#onDetachedFromWindow()
	 */
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		TabBadgeNotificationManager.unRegisterTabHandler(tab);
	}
	
	/**
	 * Sets a notification number in the badge.
	 * 
	 * @param notifications
	 */
	public void setNotifications(Integer notifications) {
		BadgeView badgeView = (BadgeView)findViewById(R.id.tabBadge);
		badgeView.setNotifications(notifications);
	}
}