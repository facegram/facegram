package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.google.android.gms.maps.model.GroundOverlayOptions;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.Components.LayoutHelper;

public class FontSettingsCell extends FrameLayout {
    private static Paint paint;
    private int font;
    private boolean needDivider;
    private TextView textView;
    private ImageView valueImageView;
    private TextView valueTextView;

    public FontSettingsCell(Context context, int _font) {
        super(context);
        int i;
        int i2;
        Typeface face;
        int i3 = 3;
        if (paint == null) {
            paint = new Paint();
            paint.setColor(0x000000);
            paint.setStrokeWidth(-1.0f);
        }
        font = _font;
        textView = new TextView(context);
        textView.setTextColor(getResources().getColor(R.color.hockeyapp_text_black));
        textView.setTextSize(1, 16.0f);
        textView.setLines(1);
        textView.setMaxLines(1);
        textView.setSingleLine(true);
        textView.setEllipsize(TruncateAt.END);
        if (LocaleController.isRTL) {
            i = 5;
        } else {
            i = 3;
        }
        textView.setGravity(i | 16);
        View view = textView;
        if (LocaleController.isRTL) {
            i2 = 5;
        } else {
            i2 = 3;
        }
        addView(view, LayoutHelper.createFrame(-1, GroundOverlayOptions.NO_DIMENSION, i2 | 48, 17.0f, 0.0f, 17.0f, 0.0f));
        switch (font) {
            case 0 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/rmedium.ttf");
                break;
            case 1 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/iransans_ultrlight.ttf");
                break;
            case 2 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/iransans_light.ttf");
                break;
            case 3 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/iransans.ttf");
                break;
            case 4 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/iransans_medium.ttf");
                break;
            case 5 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/iransans_bold.ttf");
                break;
            case 6 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/afsaneh.ttf");
                break;
            case 7 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/dastnevis.ttf");
                break;
            case 8 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/hama.ttf");
                break;
            case 9 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/morvarid.ttf");
                break;
            case 10 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/yekan.ttf");
                break;
            case 11 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/traffic.ttf");
                break;
            case 12 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/koodak.ttf");
                break;
            case 13 :
                face = Typeface.createFromAsset(context.getAssets(), "fonts/lotus.ttf");
                break;
            default:
                face = Typeface.createFromAsset(context.getAssets(), "fonts/iransans_light.ttf");
                break;
        }
        textView.setTypeface(face);
        valueTextView = new TextView(context);
        valueTextView.setTextColor(0x000000);
        valueTextView.setTextSize(1, 16.0f);
        valueTextView.setLines(1);
        valueTextView.setMaxLines(1);
        valueTextView.setSingleLine(true);
        valueTextView.setEllipsize(TruncateAt.END);
        valueTextView.setGravity((LocaleController.isRTL ? 3 : 5) | 16);
        view = valueTextView;
        if (LocaleController.isRTL) {
            i2 = 3;
        } else {
            i2 = 5;
        }
        addView(view, LayoutHelper.createFrame(-2, GroundOverlayOptions.NO_DIMENSION, i2 | 48, 17.0f, 0.0f, 17.0f, 0.0f));
        valueTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        valueImageView = new ImageView(context);
        valueImageView.setScaleType(ScaleType.CENTER);
        valueImageView.setVisibility(INVISIBLE);
        view = valueImageView;
        if (!LocaleController.isRTL) {
            i3 = 5;
        }
        addView(view, LayoutHelper.createFrame(-2, -2.0f, i3 | 16, 17.0f, 0.0f, 17.0f, 0.0f));
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), (needDivider ? 1 : 0) + AndroidUtilities.dp(48.0f));
        int availableWidth = ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - AndroidUtilities.dp(34.0f);
        int width = availableWidth / 2;
        if (valueImageView.getVisibility() == VISIBLE) {
            valueImageView.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY));
        }
        if (valueTextView.getVisibility() == VISIBLE) {
            valueTextView.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY));
            width = (availableWidth - valueTextView.getMeasuredWidth()) - AndroidUtilities.dp(8.0f);
        } else {
            width = availableWidth;
        }
        textView.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY));
    }

    public void setTextColor(int color) {
        textView.setTextColor(color);
    }

    public void setText(String text, boolean divider) {
        textView.setText(text);
        valueTextView.setVisibility(INVISIBLE);
        valueImageView.setVisibility(INVISIBLE);
        needDivider = divider;
        setWillNotDraw(!divider);
    }

    public void setTextAndValue(String text, String value, boolean divider) {
        boolean z = false;
        textView.setText(text);
        valueImageView.setVisibility(INVISIBLE);
        if (value != null) {
            valueTextView.setText(value);
            valueTextView.setVisibility(VISIBLE);
        } else {
            valueTextView.setVisibility(INVISIBLE);
        }
        needDivider = divider;
        if (!divider) {
            z = true;
        }
        setWillNotDraw(z);
        requestLayout();
    }

    public void setTextAndIcon(String text, int resId, boolean divider) {
        boolean z = false;
        textView.setText(text);
        valueTextView.setVisibility(INVISIBLE);
        if (resId != 0) {
            valueImageView.setVisibility(VISIBLE);
            valueImageView.setImageResource(resId);
        } else {
            valueImageView.setVisibility(INVISIBLE);
        }
        needDivider = divider;
        if (!divider) {
            z = true;
        }
        setWillNotDraw(z);
    }

    protected void onDraw(Canvas canvas) {
        if (needDivider) {
            canvas.drawLine((float) getPaddingLeft(), (float) (getHeight() - 1), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - 1), paint);
        }
    }
}
