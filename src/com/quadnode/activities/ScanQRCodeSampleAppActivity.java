package com.quadnode.activities;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ScanQRCodeSampleAppActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button scanQRCode = (Button) findViewById(R.id.scan_qr_code);
        scanQRCode.setOnClickListener(this);
    }
    

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.scan_qr_code:
			IntentIntegrator integrator = new IntentIntegrator(this);
			integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {

		Log.i("MBP", "Request code: " + requestCode);
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode,
				resultCode, intent);
		String contents = result.getContents();
		if (contents != null) {
			Log.e("result found", "result found");
			showDialog(R.string.result_succeeded, result.toString());
		} else {
			Log.e("result not found", "result found");
			showDialog(R.string.result_failed,
					getString(R.string.result_failed));
			
		}
		
		}
	
	private void showDialog(int title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("OK", null);
		
		builder.show();
	}
}