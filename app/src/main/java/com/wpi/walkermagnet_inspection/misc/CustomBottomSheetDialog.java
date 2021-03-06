package com.wpi.walkermagnet_inspection.misc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wpi.walkermagnet_inspection.R;
import com.wpi.walkermagnet_inspection.activity.ConfigurationActivity;
import com.wpi.walkermagnet_inspection.activity.MainActivity;
import com.wpi.walkermagnet_inspection.data.model.Magnet;
import com.wpi.walkermagnet_inspection.data.repo.MagnetRepo;

/**
 * Created by abhishek on 12/29/2016.
 */

public class CustomBottomSheetDialog extends BottomSheetDialogFragment {

    /**
     * Property to store magnet controllers
     */
    private long mMagnetControllerId;
    private Context mAppContext;
    private int mControllerPosition;

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        final View contentView = View.inflate(getContext(), R.layout.controller_bottomsheet, null);
        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }

        MagnetRepo magnetRepo = new MagnetRepo();

        Magnet magnet = magnetRepo.magnet(mMagnetControllerId);

        //Referencing Magnet name TextView
        final TextView magnetName = (TextView) contentView.findViewById(R.id.magnet_controller_name);
        magnetName.setText(magnet.getMagnetName());

        //Referencing Delete Row
        LinearLayout deleteMagnetRow = (LinearLayout) contentView.findViewById(R.id.delete_magnet_row);
        deleteMagnetRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.app_name)
                        .setMessage(R.string.delete_magnet_alert)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int whichButton) {
                                //Close the dialog
                                dialog.dismiss();

                                //Remove Magnet Controller
                                ((MainActivity) mAppContext).deleteMagnetController(mMagnetControllerId, mControllerPosition);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        //Referencing Configuration Layout
        LinearLayout configuration = (LinearLayout) contentView.findViewById(R.id.configuration_layout);
        configuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Close the dialog
                dialog.dismiss();

                Intent i = new Intent(getActivity(), ConfigurationActivity.class);

                //Passing Magnet Controller Id
                i.putExtra("magnetControllerId", mMagnetControllerId);

                // Staring Login Activity
                startActivity(i);
            }
        });
    }

    /**
     * This function is used to set the magnet id for which the menu options are shown to the user
     *
     * @param id magnet controller id
     */
    public void setControllerAttr(long id, Context context, int position) {
        mMagnetControllerId = id;
        mAppContext = context;
        mControllerPosition = position;
    }

}
