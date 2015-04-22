package model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by BrentYoung on 4/21/15.
 * Use background services with database to load swerves into this class
 */
public class SwerveLab {

    private static SwerveLab sSwerveLab;
    private ArrayList<SwervePost> mSwerves;
    private Context mAppContext;


    public static SwerveLab getInstance(Context context) {
        if (sSwerveLab == null) {
            sSwerveLab = new SwerveLab(context.getApplicationContext());
        }
        return sSwerveLab;
    }

    private SwerveLab(Context appContext) {

        mAppContext = appContext;
        mSwerves = new ArrayList<SwervePost>();

        //Take this out when services are up and running

        for (int i = 0; i < 100; i++) {
            SwervePost post = new SwervePost(new User());
            mSwerves.add(post);
        }

    }

    public ArrayList<SwervePost> getSwerves() {
        return mSwerves;
    }

    public SwervePost getSwerve(UUID id) {
        for (SwervePost sp: mSwerves) {
            if (sp.getId().equals(id)) {
                return sp;
            }
        }
        return null;
    }
}
