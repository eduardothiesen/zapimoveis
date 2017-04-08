package br.com.thiesen.zapimoveis;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.com.thiesen.zapimoveis.data.Realty;
import br.com.thiesen.zapimoveis.network.ErrorParser;
import br.com.thiesen.zapimoveis.network.NetworkController;
import br.com.thiesen.zapimoveis.network.Response;
import br.com.thiesen.zapimoveis.network.ResponseParser;

public class RealtiesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Response> {

    private static final String LOG_TAG = RealtiesActivity.class.getName();

    private static final int REALTIES_LOADER_ID = 1;
    private static final int REALTY_DETAILS_LOADER_ID = 2;

    private RealtiesAdapter mAdapter;

    private ProgressBar mLoadingIndicator;
    private LinearLayout mEmptyStateView;
    private TextView mEmptyStateMessage;

    private ProgressDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realties);

        mAdapter = new RealtiesAdapter(this, new ArrayList<Realty>());

        mLoadingIndicator = (ProgressBar) findViewById(R.id.progress_indicator);
        mEmptyStateView = (LinearLayout) findViewById(R.id.empty_state_view);
        mEmptyStateMessage = (TextView) mEmptyStateView.findViewById(R.id.empty_state_message);
        mEmptyStateMessage.setText(R.string.no_realties_found_message);

        ListView realtiesListView = (ListView) findViewById(R.id.realties_list_view);
        realtiesListView.setAdapter(mAdapter);
        realtiesListView.setEmptyView(mEmptyStateView);
        mEmptyStateView.setVisibility(View.GONE);

        retrieveRealties();

        ImageButton tryAgainButton = (ImageButton) findViewById(R.id.try_again_button);
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveRealties();
            }
        });

        realtiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ConnectivityManager cm =
                        (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting()) {

                    mLoadingDialog.show();

                    LoaderManager loaderManager = getSupportLoaderManager();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", mAdapter.getItem(position).getRealtyCode());
                    loaderManager.initLoader(REALTY_DETAILS_LOADER_ID, bundle, RealtiesActivity.this);
                } else {
                    Toast.makeText(RealtiesActivity.this, R.string.no_internet_connection_2_message, Toast.LENGTH_LONG).show();
                }
            }
        });

        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setMessage(getString(R.string.please_wait_message));
        mLoadingDialog.setCancelable(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter_bedrooms:

                mAdapter.sort(new Comparator<Realty>() {
                    @Override
                    public int compare(Realty o1, Realty o2) {
                        return Double.compare(o1.getNumberOfBedrooms(), o2.getNumberOfBedrooms());
                    }
                });

                return true;
            case R.id.action_filter_parking_spots:
                mAdapter.sort(new Comparator<Realty>() {
                    @Override
                    public int compare(Realty o1, Realty o2) {
                        return Double.compare(o1.getParkingLotSpaces(), o2.getParkingLotSpaces());
                    }
                });
                return true;
            case R.id.action_filter_price:
                mAdapter.sort(new Comparator<Realty>() {
                    @Override
                    public int compare(Realty o1, Realty o2) {
                        return Double.compare(o1.getPrice(), o2.getPrice());
                    }
                });
                return true;

            case R.id.action_filter_square_footage:
                mAdapter.sort(new Comparator<Realty>() {
                    @Override
                    public int compare(Realty o1, Realty o2) {
                        return Double.compare(o1.getSquareFootage(), o2.getSquareFootage());
                    }
                });
                return true;

            case R.id.action_filter_suites:
                mAdapter.sort(new Comparator<Realty>() {
                    @Override
                    public int compare(Realty o1, Realty o2) {
                        return Double.compare(o1.getNumberOfSuites(), o2.getNumberOfSuites());
                    }
                });
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void retrieveRealties() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting()) {

            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(REALTIES_LOADER_ID, null, this);
        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            mEmptyStateView.setVisibility(View.VISIBLE);
            mEmptyStateMessage.setText(R.string.no_internet_connection_message);
        }
    }

    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        NetworkController networkController = new NetworkController(this);

        if (id == REALTIES_LOADER_ID) {
            return networkController.retrieveRealties();
        } else if (id == REALTY_DETAILS_LOADER_ID) {
            return networkController.retrieveRealtyDetails(args.getInt("id"));
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Response> loader, Response response) {
        mLoadingIndicator.setVisibility(View.GONE);
        mLoadingDialog.cancel();

        if (loader.getId() == REALTIES_LOADER_ID) {
            if (response.getStatusCode() == 200) {
                List<Realty> realties = ResponseParser.parseRealties(response.getJsonResponse());
                mEmptyStateMessage.setText(R.string.no_realties_found_message);
                mEmptyStateView.setVisibility(View.VISIBLE);
                mAdapter.clear();


                if (realties != null && !realties.isEmpty())
                    mAdapter.addAll(realties);
            } else {
                Toast.makeText(this, ErrorParser.parseError(this, response.getJsonResponse()), Toast.LENGTH_LONG).show();
            }
        } else if (loader.getId() == REALTY_DETAILS_LOADER_ID) {
            getSupportLoaderManager().destroyLoader(REALTY_DETAILS_LOADER_ID);

            if (response.getStatusCode() == 200) {
                Realty realty = ResponseParser.parseRealty(response.getJsonResponse());

                Intent intent = new Intent(RealtiesActivity.this, RealtyDetailsActivity.class);
                intent.putExtra("realty", realty);
                intent.putExtra("test", 1);

                startActivity(intent);

            } else {
                Toast.makeText(this, ErrorParser.parseError(this, response.getJsonResponse()), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {
        mAdapter.clear();
    }
}
