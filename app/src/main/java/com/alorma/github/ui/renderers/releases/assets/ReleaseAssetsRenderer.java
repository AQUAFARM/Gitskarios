package com.alorma.github.ui.renderers.releases.assets;

import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alorma.github.R;
import com.alorma.github.sdk.bean.dto.response.ReleaseAsset;
import com.pedrogomez.renderers.Renderer;

public class ReleaseAssetsRenderer extends Renderer<ReleaseAsset> {

  @BindView(R.id.releaseAssetName) TextView releaseAssetName;
  @BindView(R.id.releaseAssetSize) TextView releaseAssetSize;
  private OnReleaseAssetClicked onReleaseAssetClicked;

  @Override
  protected void setUpView(View view) {

  }

  @Override
  protected void hookListeners(View view) {
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (onReleaseAssetClicked != null) {
          onReleaseAssetClicked.onReleaseAssetCLicked(getContent());
        }
      }
    });
  }

  @Override
  protected View inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
    View inflatedView = layoutInflater.inflate(R.layout.row_repo_release_asset, viewGroup, false);
    ButterKnife.bind(this, inflatedView);
    return inflatedView;
  }

  @Override
  public void render() {
    ReleaseAsset releaseAsset = getContent();
    releaseAssetName.setText(releaseAsset.name);
    if (releaseAsset.size > 0) {
      String size = Formatter.formatShortFileSize(releaseAssetSize.getContext(), releaseAsset.size);
      releaseAssetSize.setText(size);
      releaseAssetSize.setVisibility(View.VISIBLE);
    } else {
      releaseAssetSize.setVisibility(View.GONE);
    }
  }

  public void setOnReleaseAssetClicked(OnReleaseAssetClicked onReleaseAssetClicked) {
    this.onReleaseAssetClicked = onReleaseAssetClicked;
  }

  public interface OnReleaseAssetClicked {
    void onReleaseAssetCLicked(ReleaseAsset asset);
  }
}
