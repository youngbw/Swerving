package model;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by BrentYoung on 8/25/15.
 */
public class SwipeDetector implements View.OnTouchListener {

    private TextView counter;
    private SwervePost post;
    private boolean hit;

    public SwipeDetector(TextView counter, SwervePost post) {
        this.counter = counter;
        this.post = post;
        hit = false;
    }

    public static enum Action {
        LR, // Left to Right
        RL, // Right to Left
        TB, // Top to bottom
        BT, // Bottom to Top
        None // when no action was detected
    }

    private static final String logTag = "SwipeDetector";
    private static final int MIN_DISTANCE = 300;
    private float downX, downY, upX, upY;
    private Action mSwipeDetected = Action.None;

    public boolean swipeDetected() {
        return mSwipeDetected != Action.None;
    }

    public Action getAction() {
        return mSwipeDetected;
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                mSwipeDetected = Action.None;
                return true; // allow other events like Click to be processed
            }
            case MotionEvent.ACTION_MOVE: {
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                // horizontal swipe detection
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // left or right
                    if (deltaX < 0) {
//                            Logger.show(Log.INFO,logTag, "Swipe Left to Right");
                        mSwipeDetected = Action.LR;
                        if (hit) {
                            hit = false;
                            counter.setText("SS " + (post.getSwerveCount() - 1));
                            counter.setTextColor(Color.CYAN);
                            counter.setTextSize(18);
                            post.setSwerveCount(post.getSwerveCount() - 1);
                        }
                        return true;
                    }
                    if (deltaX > 0) {
//                            Logger.show(Log.INFO,logTag, "Swipe Right to Left");
                        mSwipeDetected = Action.RL;
                        if (!hit)  {
                            counter.setText("SS " + (post.getSwerveCount() + 1));
                            counter.setTextColor(Color.RED);
                            counter.setTextSize(20);
                            post.setSwerveCount(post.getSwerveCount() + 1);
                            hit = true;
                        }
                        return true;
                    }
                } else

                    // vertical swipe detection
                    if (Math.abs(deltaY) > MIN_DISTANCE) {
                        // top or down
                        if (deltaY < 0) {
//                                Logger.show(Log.INFO,logTag, "Swipe Top to Bottom");
                            mSwipeDetected = Action.TB;
                            return false;
                        }
                        if (deltaY > 0) {
//                                Logger.show(Log.INFO, logTag, "Swipe Bottom to Top");
                            mSwipeDetected = Action.BT;
                            return false;
                        }
                    }
                return true;
            }
        }
        return false;
    }
}