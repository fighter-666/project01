package com.example.myapplication.activity.components

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAudioTrackBinding


class AudioTrackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAudioTrackBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioTrackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bufferSize = AudioTrack.getMinBufferSize(
            4000,
            AudioFormat.CHANNEL_OUT_STEREO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        val audioTrack = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            .setAudioFormat(
                AudioFormat.Builder().setSampleRate(4000)
                    .setChannelMask(AudioFormat.CHANNEL_IN_STEREO).setEncoding(
                        AudioFormat.ENCODING_PCM_16BIT
                    ).build()
            )
            .setTransferMode(AudioTrack.MODE_STREAM)
            .setBufferSizeInBytes(bufferSize)
            .build()



        Thread {
            try {
                val dis = assets.open("8k16bit.pcm")
                audioTrack.play()
//                val bufferSize = 1024
                val buffer = ByteArray(bufferSize)
                var length: Int
                while (dis.read(buffer).also { length = it } != -1) {
                    // read读取的数据放入buffer 缓冲区，将buffer 缓冲区数据写入到audiotrack 中
                    audioTrack.write(buffer, 0, length)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                audioTrack.stop()
                audioTrack.release()
            }
        }.start()


        audioTrack.positionNotificationPeriod = 1000

        audioTrack.setPlaybackPositionUpdateListener(object : AudioTrack.OnPlaybackPositionUpdateListener{
            override fun onMarkerReached(track: AudioTrack?) {
            }

            override fun onPeriodicNotification(track: AudioTrack?) {
                val play = audioTrack.playbackHeadPosition
                val rate = audioTrack.playbackRate
                val currentPlayTime = play *1.0f / rate * 1.0f
                binding.progressBar.progress=
                    ((currentPlayTime / binding.progressBar.max)*100).toInt()
            }

        })

    }
}