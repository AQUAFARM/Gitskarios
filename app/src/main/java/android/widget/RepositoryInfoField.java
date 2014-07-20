package android.widget;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.bean.RepositoryUiInfo;

import com.joanzapata.android.iconify.Iconify;

/**
 * Created by Bernat on 19/07/2014.
 */
public class RepositoryInfoField extends TextView {

    public RepositoryUiInfo repoInfo;

    public RepositoryInfoField(Context context) {
        super(context);
        init();
    }

    public RepositoryInfoField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RepositoryInfoField(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RepositoryInfoField(Context context, RepositoryUiInfo info) {
        super(context, null);
        this.repoInfo = info;
        init();
    }

    private void init() {
        int pL = (int) (24 * getResources().getDisplayMetrics().density);
        int pT = (int) (18 * getResources().getDisplayMetrics().density);
        setPadding(pL, pT, getPaddingRight(), getPaddingBottom());
        setCustomText();
    }

    private void setCustomText() {
        if (isInfoValid()) {
            String pluralText = getResources().getQuantityString(repoInfo.text, repoInfo.num, repoInfo.num);
            String customText = "{" + repoInfo.icon + "} " + pluralText;
            setText(Html.fromHtml(customText));
            Iconify.addIcons(this);
        }
    }

    private boolean isInfoValid() {
        return repoInfo != null && repoInfo.text > 0 && repoInfo.icon != null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = (int) (48 * getResources().getDisplayMetrics().density);
        setMeasuredDimension(widthMeasureSpec, height);
    }

    public void updateRepoInfo(RepositoryUiInfo info) {
        this.repoInfo = info;
        setCustomText();
    }

    public void notifyDataSetChanged() {
        setCustomText();
    }

}