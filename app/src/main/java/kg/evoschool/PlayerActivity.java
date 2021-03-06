package kg.evoschool;

import static kg.evoschool.MainActivity.repeatBoolean;
import static kg.evoschool.MainActivity.shuffleBoolean;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import kg.evoschool.Api.CategoryDetail;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    private TextView songName, playedDuration, totalDuration;
    private ImageView imageOfSongs, btnNext, btnPrev, randomBtn, repeatBtn, playPauseBtn, favoriteBtn, shareBtn;
    private SeekBar seekBar;
    private int position = 0;
    public static ArrayList<CategoryDetail.Audio> listSong = new ArrayList<>();
    public static String uri;
    public static MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private Thread playThread, nextThread, prevThread;
    public static List<CategoryDetail.Audio> urlList = new ArrayList<>();
    private String url;
    ShapeableImageView shapeableImageView;
    String mNull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mediaPlayer = new MediaPlayer();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        url = extras.getString("sound");
        position = getIntent().getIntExtra("position", 1);
        urlList = SoundsActivity.mList;

        Log.e("PlayerActivity", url);
        Log.e("List song size", listSong.size() + "");

        mNull = "null";

        initViews();
        getIntentMethod();

        mediaPlayer.setOnCompletionListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer != null && fromUser) {
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        PlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                    playedDuration.setText(formattedTime(mCurrentPosition));
                    int durationTotal = mediaPlayer.getDuration() / 1000;
                    totalDuration.setText(formattedTime(durationTotal));
                }
                handler.postDelayed(this, 1000);
            }
        });

        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shuffleBoolean) {
                    shuffleBoolean = false;
                    randomBtn.setImageResource(R.drawable.ic_player_random_off);
                } else {
                    shuffleBoolean = true;
                    randomBtn.setImageResource(R.drawable.ic_player_random_on);
                }
            }
        });

        repeatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeatBoolean) {
                    repeatBoolean = false;
                    repeatBtn.setImageResource(R.drawable.ic_player_repeat_off);
                } else {
                    repeatBoolean = true;
                    repeatBtn.setImageResource(R.drawable.ic_player_repeat_on);
                }
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_VIEW);
                shareIntent.setData(Uri.parse("https://evoschool.ru/"));
                startActivity(shareIntent);
//                intent.setType("Text/plain");
//                String Body = "???????????????? ???? ????????????????/?? ????????????????????";
//                intent.putExtra(Intent.EXTRA_TEXT, Body);
//                startActivity(Intent.createChooser(intent, "Share"));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        playThreadBtn();
        nextThreadBtn();
        prevThreadBtn();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void prevThreadBtn() {
        prevThread = new Thread() {
            @Override
            public void run() {
                super.run();
                btnPrev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        prevBtnClicked();
                    }
                });
            }
        };
        prevThread.start();
    }

    private void prevBtnClicked() {
        if (position == 0) {
            Toast.makeText(getApplicationContext(), "?????? ???????????? ??????????????????", Toast.LENGTH_LONG).show();
        } else
            position -= 1;
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.setDataSource(urlList.get(position).audio_file);
            songName.setText(urlList.get(position).name);
            if(urlList.get(position).picture == null){
                Picasso.get().load(R.drawable.circle_forest);
            }else{
                Picasso.get().load(urlList.get(position).picture).resize(350, 350).into(imageOfSongs);
            }
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        }

        seekBar.setMax(mediaPlayer.getDuration() / 1000);

//        if (mediaPlayer.isPlaying()) {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            if (shuffleBoolean && !repeatBoolean) {
//                position = getRandom(1);
//            } else if (!shuffleBoolean && !repeatBoolean) {
//                position = 0;
//            }
////            uri = Uri.parse(listSong.get(position).getPath());
//            mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(url));
////            metaData(url);
//            songName.setText(listSong.get(position).name);
//            seekBar.setMax(mediaPlayer.getDuration() / 1000);
//            PlayerActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if (mediaPlayer != null) {
//                        int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
//                        seekBar.setProgress(mCurrentPosition);
//                    }
//                    handler.postDelayed(this, 1000);
//                }
//            });
//            mediaPlayer.setOnCompletionListener(this);
//            playPauseBtn.setBackgroundResource(R.drawable.ic_player_pause);
//            mediaPlayer.start();
//        } else {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            if (shuffleBoolean && !repeatBoolean) {
//                position = getRandom( 1);
//            } else if (!shuffleBoolean && !repeatBoolean) {
//                position = (position - 1);
//            }
////            uri = Uri.parse(listSong.get(position).getPath());
//            mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(url));
////            metaData(url);
//            songName.setText(listSong.get(position).name);
//            seekBar.setMax(mediaPlayer.getDuration() / 1000);
//            PlayerActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if (mediaPlayer != null) {
//                        int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
//                        seekBar.setProgress(mCurrentPosition);
//                    }
//                    handler.postDelayed(this, 1000);
//                }
//            });
//            mediaPlayer.setOnCompletionListener(this);
//            playPauseBtn.setBackgroundResource(R.drawable.ic_player_play);
//        }
    }

    private void nextThreadBtn() {
        nextThread = new Thread() {
            @Override
            public void run() {
                super.run();
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextBtnClicked();
                    }
                });
            }
        };
        nextThread.start();
    }

    private void nextBtnClicked() {
        if (position == urlList.size() - 1) {
            Toast.makeText(this, "?????? ?????????????????? ?????????????????? ?? ???????????? ??????????????????", Toast.LENGTH_LONG).show();

        } else {

            position += 1;
            url = getIntent().getStringExtra("sound");
            Log.e("POSITSIYA", position + "");
            Log.e("SSYLKA", url);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.setDataSource(urlList.get(position).audio_file);
                songName.setText(urlList.get(position).name);
                if(urlList.get(position).picture == null){
                    Picasso.get().load(R.drawable.circle_forest);
                }else{
                    Picasso.get().load(urlList.get(position).picture).resize(350, 350).into(imageOfSongs);
                }
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
            }

            seekBar.setMax(mediaPlayer.getDuration() / 1000);
        }

//        if (mediaPlayer.isPlaying()) {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            if (shuffleBoolean && !repeatBoolean) {
//                position = getRandom( - 1);
//            } else if (!shuffleBoolean && !repeatBoolean) {
//                position = (position + 1);
//            }
////            uri = Uri.parse(listSong.get(position).getPath());
//            mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(url));
////            metaData(url);
//            songName.setText(position);
//            seekBar.setMax(mediaPlayer.getDuration() / 1000);
//            PlayerActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if (mediaPlayer != null) {
//                        int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
//                        seekBar.setProgress(mCurrentPosition);
//                    }
//                    handler.postDelayed(this, 1000);
//                }
//            });
//            mediaPlayer.setOnCompletionListener(this);
//            playPauseBtn.setBackgroundResource(R.drawable.ic_player_pause);
//            mediaPlayer.start();
//        } else {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            if (shuffleBoolean && !repeatBoolean) {
//                position = getRandom( - 1);
//            } else if (!shuffleBoolean && !repeatBoolean) {
//                position = (position + 1);
//            }
////            url = (testList.get(position).audio_file);
//            mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(url));
////            metaData(url);
//            songName.setText(listSong.get(position).name);
//            seekBar.setMax(mediaPlayer.getDuration() / 1000);
//            PlayerActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if (mediaPlayer != null) {
//                        int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
//                        seekBar.setProgress(mCurrentPosition);
//                    }
//                    handler.postDelayed(this, 1000);
//                }
//            });
//            mediaPlayer.setOnCompletionListener(this);
//            playPauseBtn.setBackgroundResource(R.drawable.ic_player_play);
//        }
    }

    private int getRandom(int i) {
        Random random = new Random();
        return random.nextInt(i + 1);
    }

    private void playThreadBtn() {
        playThread = new Thread() {
            @Override
            public void run() {
                super.run();
                playPauseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playPauseBtnClicked();
                    }
                });
            }
        };
        playThread.start();
    }

    private void playPauseBtnClicked() {
        if (mediaPlayer.isPlaying()) {
            playPauseBtn.setImageResource(R.drawable.ic_player_play);
            mediaPlayer.pause();
            seekBar.setMax(mediaPlayer.getDuration() / 1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
        } else {
            playPauseBtn.setImageResource(R.drawable.ic_player_pause);
            mediaPlayer.start();
            seekBar.setMax(mediaPlayer.getDuration() / 1000);
            PlayerActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrentPosition);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
        }
    }

    private String formattedTime(int mCurrentPosition) {
        String totalOut = "";
        String totalNew = "";
        String seconds = String.valueOf(mCurrentPosition % 60);
        String minutes = String.valueOf(mCurrentPosition / 60);
        totalOut = minutes + ":" + seconds;
        totalNew = minutes + ":" + "0" + seconds;
        if (seconds.length() == 1) {
            return totalNew;
        } else {
            return totalOut;
        }
    }

    private void getIntentMethod() {
        position = getIntent().getIntExtra("position", 1);
        playPauseBtn.setImageResource(R.drawable.ic_player_pause);
        Log.e("POSITSIYA", position + "");
        Log.e("URL LIST SIZE", urlList.size() + "");
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(urlList.get(position).audio_file);
            mediaPlayer.prepare();
            songName.setText(urlList.get(position).name);
            if(urlList.get(position).picture == null){
                int[] images = {R.drawable.circle_forest,
                        R.drawable.circle_mountains,
                        R.drawable.circle_desert,
                        R.drawable.circle_hills};
                Random random = new Random();
                imageOfSongs.setImageResource(images[random.nextInt(images.length)]);
                Picasso.get().load(String.valueOf(imageOfSongs));
            }else{
                Picasso.get().load(urlList.get(position).picture).resize(350, 350).into(imageOfSongs);
            }
            mediaPlayer.getDuration();
            mediaPlayer.start();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        }

        seekBar.setMax(mediaPlayer.getDuration() / 1000);
//        metaData(uri);
    }

    private void initViews() {
        songName = findViewById(R.id.songName);
        playedDuration = findViewById(R.id.text_song_start);
        totalDuration = findViewById(R.id.text_song_stop);
        imageOfSongs = findViewById(R.id.forest_circle);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        randomBtn = findViewById(R.id.btnRandom);
        repeatBtn = findViewById(R.id.btnRepeat);
        playPauseBtn = findViewById(R.id.play_pause);
        favoriteBtn = findViewById(R.id.btnFavorite);
        shareBtn = findViewById(R.id.btnShare);
        seekBar = findViewById(R.id.seek_bar);
    }

    public void ImageAnimation(Context context, ImageView imageView, Bitmap bitmap) {
        Animation animOut = AnimationUtils.loadAnimation(context, android.R.anim.fade_out);
        Animation animIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        animOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Glide.with(context).load(bitmap).into(imageView);
                animIn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                imageView.startAnimation(animIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        imageView.startAnimation(animOut);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        nextBtnClicked();
        if (mediaPlayer != null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(url));
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(this);
        }
    }

    public void backOnSounds(View view) {
        onBackPressed();
    }


}