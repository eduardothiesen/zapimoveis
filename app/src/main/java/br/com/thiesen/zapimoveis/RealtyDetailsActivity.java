package br.com.thiesen.zapimoveis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import br.com.thiesen.zapimoveis.data.Realty;

import static br.com.thiesen.zapimoveis.R.id.price_text_view;

public class RealtyDetailsActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    private Realty realty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realty_details);

        realty = (Realty) getIntent().getExtras().getSerializable("realty");

        final TextView realtyTypeTextView = (TextView) findViewById(R.id.realty_type_text_view);
        TextView priceTextView = (TextView) findViewById(price_text_view);
        TextView numberOfBedroomsTextView = (TextView) findViewById(R.id.number_of_bedrooms_text_view);
        TextView numberOfSuitesTextView = (TextView) findViewById(R.id.number_of_baths_text_view);
        TextView numberOfParkingSpotsTextView = (TextView) findViewById(R.id.number_of_parking_spots_text_view);
        TextView squareFootageTextView = (TextView) findViewById(R.id.square_footage_text_view);
        TextView addressTextView = (TextView) findViewById(R.id.address_text_view);
        TextView salerInfoTextView = (TextView) findViewById(R.id.saler_info_text_view);
        TextView descriptionTextView = (TextView) findViewById(R.id.description_text_view);
        TextView characteristicsTextView = (TextView) findViewById(R.id.characteristics_text_view);
        TextView commonAreaTextView = (TextView) findViewById(R.id.common_area_text_view);
        TextView commonAreaPriceTextView = (TextView) findViewById(R.id.common_area_price_text_view);
        TextView updatedDateTextView = (TextView) findViewById(R.id.update_date_text_view);

        ImageButton sendMessageButton = (ImageButton) findViewById(R.id.send_message_button);

        realtyTypeTextView.setText(realty.getRealtyType());
        Locale brazilian = new Locale("pt", "BR");
        priceTextView.setText(NumberFormat.getCurrencyInstance(brazilian).format(realty.getPrice()));
        numberOfBedroomsTextView.setText(String.valueOf(realty.getNumberOfBedrooms()));
        numberOfSuitesTextView.setText(String.valueOf(realty.getNumberOfSuites()));
        numberOfParkingSpotsTextView.setText(String.valueOf(realty.getParkingLotSpaces()));
        squareFootageTextView.setText(String.valueOf(realty.getSquareFootage()));
        addressTextView.setText(realty.getAddress().getFormattedAddres());
        salerInfoTextView.setText("Código: " + realty.getClient().getCode() + "\n" + "Nome: " + realty.getClient().getName());
        descriptionTextView.setText(realty.getDescription());

        String characteristics = "";

        for (int i = 0; i < realty.getCharacteristics().size(); i++) {
            characteristics = characteristics + realty.getCharacteristics().get(i) + "\n";
        }

        characteristicsTextView.setText(characteristics);

        String commonArea = "";

        for (int i = 0; i < realty.getCommonAreaCharacteristics().size(); i++) {
            commonArea = commonArea + realty.getCommonAreaCharacteristics().get(i) + "\n";
        }

        commonAreaTextView.setText(commonArea);
        commonAreaPriceTextView.setText(NumberFormat.getCurrencyInstance(brazilian).format(realty.getCondominiumPrice()));

        if (realty.getUpdateDate() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
            updatedDateTextView.setText("Data de atualização: " + simpleDateFormat.format(realty.getUpdateDate()));
        }

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mPagerAdapter = new RealtyImagesPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RealtyDetailsActivity.this, SendMessageActivity.class);
                intent.putExtra("id", realty.getRealtyCode());

                startActivity(intent);
            }
        });
    }

    private class RealtyImagesPagerAdapter extends FragmentStatePagerAdapter {
        public RealtyImagesPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return RealtyImagesFragment.create(position, realty.getPictures().get(position));
        }

        @Override
        public int getCount() {
            return realty.getPictures().size();
        }
    }
}
