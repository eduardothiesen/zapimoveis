package br.com.thiesen.zapimoveis;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import br.com.thiesen.zapimoveis.network.ErrorParser;
import br.com.thiesen.zapimoveis.network.NetworkController;
import br.com.thiesen.zapimoveis.network.Response;

public class SendMessageActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Response> {

    private static final int SEND_MESSAGE_LOADER_ID = 3;

    private ProgressDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setMessage(getString(R.string.please_wait_message));
        mLoadingDialog.setCancelable(false);

        ImageButton sendMessageButton = (ImageButton) findViewById(R.id.send_message_button);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm =
                        (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting()) {

                    String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
                    String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
                    String phone = ((EditText) findViewById(R.id.phoneEditText)).getText().toString();

                    if (name != "" || email != "" || phone != "") {

                        mLoadingDialog.show();

                        LoaderManager loaderManager = getSupportLoaderManager();

                        Bundle bundle = new Bundle();
                        bundle.putInt("id", getIntent().getExtras().getInt("id"));
                        bundle.putString("name", name);
                        bundle.putString("email", email);
                        bundle.putString("phone", phone);

                        loaderManager.initLoader(SEND_MESSAGE_LOADER_ID, bundle, SendMessageActivity.this);
                    } else {
                        Toast.makeText(SendMessageActivity.this, R.string.required_fields_message, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(SendMessageActivity.this, R.string.no_internet_connection_2_message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        NetworkController networkController = new NetworkController(this);

        if (id == SEND_MESSAGE_LOADER_ID) {
            return networkController.sendMessage(args.getString("name"), args.getString("email"), args.getString("phone"), args.getInt("id"));
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Response> loader, Response response) {
        mLoadingDialog.cancel();
        getSupportLoaderManager().destroyLoader(SEND_MESSAGE_LOADER_ID);

        if (response.getStatusCode() == 200) {
            Toast.makeText(this, R.string.message_success, Toast.LENGTH_LONG).show();
            SendMessageActivity.this.finish();
        } else {
           Toast.makeText(this, ErrorParser.parseError(this, response.getJsonResponse()), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {
        //Do nothing
    }
}
