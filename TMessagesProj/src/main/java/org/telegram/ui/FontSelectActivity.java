package org.telegram.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Adapters.BaseFragmentAdapter;

import java.util.ArrayList;

public class FontSelectActivity extends BaseFragment
{
    private ArrayList<String> fonts = new ArrayList();
    private BaseFragmentAdapter listAdapter;
    private ListView listView;

    private void restartApp()
    {
        Context context = getParentActivity().getBaseContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            intent.addFlags(Intent.FILL_IN_COMPONENT); //0x8000
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 1, pendingIntent);
        System.exit(2);
    }

    public View createView(Context context)
    {
        actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        actionBar.setAllowOverlayTitle(true);
        actionBar.setTitle(LocaleController.getString("Fonts", R.string.Fonts));
        actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick()
        {
            public void onItemClick(int paramAnonymousInt)
            {
                if (paramAnonymousInt == -1)
                    finishFragment();
            }
        });
        fonts.add("تلگرام");
        fonts.add("ایران سانس نازک");
        fonts.add("ایران سانس خیلی نازک");
        fonts.add("ایران سانس معمولی");
        fonts.add("ایران سانس متوسط");
        fonts.add("ایران سانس ضخیم");
        fonts.add("افسانه");
        fonts.add("دست نویس");
        fonts.add("هما");
        fonts.add("مروارید");
        fonts.add("یکان");
        fonts.add("ترافیک");
        fonts.add("کودک");
        fonts.add("لوتوس");
        listAdapter = new ListAdapter(context);
        fragmentView = new FrameLayout(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setVisibility(View.INVISIBLE);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ((FrameLayout)fragmentView).addView(linearLayout);
        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams)linearLayout.getLayoutParams();
        layoutParams1.width = FrameLayout.LayoutParams.MATCH_PARENT;
        layoutParams1.height = FrameLayout.LayoutParams.MATCH_PARENT;
        layoutParams1.gravity = Gravity.TOP;
        linearLayout.setLayoutParams(layoutParams1);
        linearLayout.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                return true;
            }
        });
        listView = new ListView(context);
        listView.setEmptyView(linearLayout);
        listView.setVerticalScrollBarEnabled(false);
        listView.setDivider(null);
        listView.setDividerHeight(0);
        listView.setAdapter(listAdapter);
        ((FrameLayout)fragmentView).addView(listView);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)listView.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        listView.setLayoutParams(layoutParams);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String str = (fonts.get(position)).toString();
                SharedPreferences.Editor editor = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", Context.MODE_PRIVATE).edit();
                editor.putString("font_type", str);
                editor.commit();
                restartApp();
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
            {
            }

            public void onScrollStateChanged(AbsListView view, int scrollState)
            {
                if (scrollState == 1)
                    AndroidUtilities.hideKeyboard(getParentActivity().getCurrentFocus());
            }
        });
        return fragmentView;
    }

    public void onResume()
    {
        super.onResume();
        if (listAdapter != null)
            listAdapter.notifyDataSetChanged();

    }

    private class ListAdapter extends BaseFragmentAdapter
    {
        private Context mContext;

        public ListAdapter(Context context)
        {
            mContext = context;
        }

        public boolean areAllItemsEnabled()
        {
            return true;
        }

        public int getCount()
        {
            return fonts.size();
        }

        public Object getItem(int position)
        {
            return null;
        }

        public long getItemId(int position)
        {
            return position;
        }

        public int getItemViewType(int position)
        {
            return 0;
        }

        public View getView(int position, View view, ViewGroup viewGroup)
        {
            if (view == null)
                view = new FontSettingsCell(mContext, position);
            FontSettingsCell fontSettingsCell = (FontSettingsCell)view;
            String str = fonts.get(position);
            if (position != -1 + fonts.size());
            for (boolean bool = true; ; bool = false)
            {
                fontSettingsCell.setText(str, bool);
                return view;
            }
        }

        public int getViewTypeCount()
        {
            return 1;
        }

        public boolean hasStableIds()
        {
            return false;
        }

        public boolean isEmpty()
        {
            return false;
        }

        public boolean isEnabled(int paramInt)
        {
            return true;
        }
    }
}