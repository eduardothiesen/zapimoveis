package br.com.thiesen.zapimoveis;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.thiesen.zapimoveis.data.Realty;

/**
 * Created by eduardothiesen on 06/04/17.
 */

public class RealtiesAdapter extends ArrayAdapter<Realty> {

    private Context mContext;

    public RealtiesAdapter(Context context, List<Realty> realties) {
        super(context, 0, realties);
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.realty_list_item, parent, false);
        }

        Realty currentRealty = getItem(position);

        ImageView realtyImageView = (ImageView) listItemView.findViewById(R.id.realty_image_view);

        TextView realtyType = (TextView) listItemView.findViewById(R.id.realty_type_text_view);
        TextView price = (TextView) listItemView.findViewById(R.id.price_text_view);
        TextView numberOfBedrooms = (TextView) listItemView.findViewById(R.id.number_of_bedrooms_text_view);
        TextView numberOfBaths = (TextView) listItemView.findViewById(R.id.number_of_baths_text_view);
        TextView numberOfParkingSpots = (TextView) listItemView.findViewById(R.id.number_of_parking_spots_text_view);
        TextView squareFootage = (TextView) listItemView.findViewById(R.id.square_footage_text_view);
        TextView address = (TextView) listItemView.findViewById(R.id.address_text_view);

        Picasso.with(mContext).load(currentRealty.getImageUrl()).placeholder(R.drawable.image_placeholder).error(R.drawable.image_placeholder).into(realtyImageView);

        realtyType.setText(currentRealty.getRealtyType());
        Locale brazilian = new Locale("pt", "BR");
        price.setText(NumberFormat.getCurrencyInstance(brazilian).format(currentRealty.getPrice()));
        numberOfBedrooms.setText(String.valueOf(currentRealty.getNumberOfBedrooms()));
        numberOfBaths.setText(String.valueOf(currentRealty.getNumberOfSuites()));
        numberOfParkingSpots.setText(String.valueOf(currentRealty.getParkingLotSpaces()));
        squareFootage.setText(String.valueOf(String.valueOf(currentRealty.getSquareFootage())));
        address.setText(currentRealty.getAddress().getFormattedAddres());

        return listItemView;
    }
}
