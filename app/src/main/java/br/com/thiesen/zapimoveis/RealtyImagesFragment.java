package br.com.thiesen.zapimoveis;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class RealtyImagesFragment extends Fragment {

    public static final String ARG_PAGE = "page";

    private int mPageNumber;
    private String url;


    public RealtyImagesFragment() {
        // Required empty public constructor
    }

    public static RealtyImagesFragment create(int pageNumber, String url) {
        RealtyImagesFragment fragment = new RealtyImagesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        url = getArguments().getString("url");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_realty_images, container, false);
        ImageView image = (ImageView) rootView.findViewById(R.id.image_view);

        Picasso.with(getContext()).load(url).placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder).into(image);

        return rootView;
    }

}
